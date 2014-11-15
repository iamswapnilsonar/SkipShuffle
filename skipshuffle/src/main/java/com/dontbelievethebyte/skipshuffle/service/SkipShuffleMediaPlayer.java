package com.dontbelievethebyte.skipshuffle.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;

import com.dontbelievethebyte.skipshuffle.activities.BaseActivity;
import com.dontbelievethebyte.skipshuffle.exceptions.AudioTrackLoadingException;
import com.dontbelievethebyte.skipshuffle.exceptions.PlaylistEmptyException;
import com.dontbelievethebyte.skipshuffle.media.MediaStoreBridge;
import com.dontbelievethebyte.skipshuffle.playlists.PlaylistInterface;
import com.dontbelievethebyte.skipshuffle.playlists.RandomPlaylist;
import com.dontbelievethebyte.skipshuffle.preferences.PreferencesHelper;
import com.dontbelievethebyte.skipshuffle.preferences.callbacks.PrefsCallbacksManager;
import com.dontbelievethebyte.skipshuffle.service.broadcastreceiver.CommandsBroadcastReceiver;
import com.dontbelievethebyte.skipshuffle.service.callbacks.HeadsetPluggedStateCallback;
import com.dontbelievethebyte.skipshuffle.service.callbacks.MediaPlayerCommandsCallback;
import com.dontbelievethebyte.skipshuffle.service.callbacks.PlayerStateChangedCallback;
import com.dontbelievethebyte.skipshuffle.service.callbacks.TrackCompleteCallback;
import com.dontbelievethebyte.skipshuffle.service.proxy.AndroidPlayer;
import com.dontbelievethebyte.skipshuffle.ui.notification.PlayerNotification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkipShuffleMediaPlayer extends Service implements PrefsCallbacksManager.PlaylistChangedCallback,
                                                               HeadsetPluggedStateCallback,
                                                               MediaPlayerCommandsCallback,
                                                               TrackCompleteCallback{

    private CommandsBroadcastReceiver clientCommandsBroadcastReceiver;
    private AndroidPlayer playerWrapper;
    private PreferencesHelper preferencesHelper;
    private PlayerNotification notification;
    private RandomPlaylist playlist;
    private MediaPlayerBinder mediaPlayerBinder = new MediaPlayerBinder();
    private Set<PlayerStateChangedCallback> playerStateChangedCallbacks;

    public class MediaPlayerBinder extends Binder
    {
        public SkipShuffleMediaPlayer getService()
        {
            return SkipShuffleMediaPlayer.this;
        }
    }

    @Override
    public void onCommand(String command, Integer newCursorPosition) {
        java.lang.reflect.Method method;
        try {
            method = this.getClass().getMethod(command);
            if (null != newCursorPosition)
                method.invoke(this);
            else
                method.invoke(this);
        } catch (Exception e) {
            handleCommandException(e);
        }
    }

    private void handleCommandException(Exception exception)
    {
        Log.d(BaseActivity.TAG, exception.getMessage());
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return mediaPlayerBinder;
    }

    @Override
    public void onCreate()
    {
        playerStateChangedCallbacks = new HashSet<PlayerStateChangedCallback>();
        clientCommandsBroadcastReceiver = new CommandsBroadcastReceiver(this);
        clientCommandsBroadcastReceiver.register();
        preferencesHelper = new PreferencesHelper(getApplicationContext());
        notification = new PlayerNotification(this);
        playerWrapper = new AndroidPlayer(this);
        initPlaylist();
    }

    private void initPlaylist()
    {
        List<String> trackIds = preferencesHelper.getLastPlaylist();

        if (null != trackIds) {
            playlist = new RandomPlaylist(trackIds, new MediaStoreBridge(getApplicationContext()));
            playlist.setPosition(preferencesHelper.getLastPlaylistPosition());
        } else {
            MediaStoreBridge mediaStoreBridge = new MediaStoreBridge(getApplicationContext());
            trackIds = new ArrayList<String>();
            Cursor cursor = mediaStoreBridge.getAllSongs();
            while (cursor.moveToNext()) {
                trackIds.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            }
            preferencesHelper.setLastPlaylist(trackIds);
            preferencesHelper.setLastPlaylistPosition(0);
        }
    }

    @Override
    public void onDestroy()
    {
        clientCommandsBroadcastReceiver.unregister();
        notification.cancel();
        doPause();
        preferencesHelper.setLastPlaylist(playlist.getTracksIds());
        preferencesHelper.setLastPlaylistPosition(playlist.getPosition());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        startForeground(PlayerNotification.getNotificationId(), notification.buildNotification());
        return START_STICKY;
    }

    @Override
    public void onHeadsetStateChanged(boolean isHeadsetPluggedIn) {
        if (!playerWrapper.isPlaying() && isHeadsetPluggedIn)
            doPause();
        notification.showNotification();
    }

    @Override
    public void onPlaylistChange()
    {
        playlist = new RandomPlaylist(
                preferencesHelper.getLastPlaylist(),
                new MediaStoreBridge(getApplicationContext())
        );
    }

    @Override
    public void onTrackComplete()
    {
        try {
            doSkip();
        } catch (PlaylistEmptyException playlistEmptyException) {
            handlePlaylistEmptyException(playlistEmptyException);
        }
    }

    public void doPlay() throws PlaylistEmptyException
    {
        try {
            playerWrapper.loadAudioFile(playlist.getCurrent());
        } catch (AudioTrackLoadingException audioLoadingTrackException) {
            handleAudioLoadingTrackException(audioLoadingTrackException);
        }
    }

    public void doPlay(int playlistPosition) throws PlaylistEmptyException
    {
        playerWrapper.resetSeekPosition();
        playlist.setPosition(playlistPosition);
        doPlay();
    }

    private void handleAudioLoadingTrackException(AudioTrackLoadingException audioTrackLoadingException) throws PlaylistEmptyException
    {
        doSkip();
    }

    public void doPause()
    {
        if (playerWrapper.isPlaying())
            playerWrapper.pausePlayingTrack();
    }

    public void doSkip() throws PlaylistEmptyException
    {
        doPlay(playlist.getPosition() + 1);
    }

    public void doPrev() throws PlaylistEmptyException
    {
        doPlay(playlist.getPosition() - 1);
    }

    public void doShuffle() throws PlaylistEmptyException
    {
        playlist.shuffle();
        playlist.setShuffle(!playlist.isShuffle());
        doPlay();
    }

    public boolean isPlaying()
    {
        return playerWrapper.isPlaying();
    }

    public void handlePlaylistEmptyException(PlaylistEmptyException playlistEmptyException)
    {
        initPlaylist();
    }

    public PlaylistInterface getPlaylist()
    {
        return playlist;
    }

    public void setPlaylist(List<String> trackIds)
    {
        playlist = new RandomPlaylist(
          trackIds,
          new MediaStoreBridge(this)
        );
    }

    public PreferencesHelper getPreferencesHelper()
    {
        return preferencesHelper;
    }

    public void registerPlayerStateChanged(PlayerStateChangedCallback playerStateChangedCallback)
    {
        playerStateChangedCallbacks.add(playerStateChangedCallback);
    }

    public void onPlayerStateChanged()
    {
        for(PlayerStateChangedCallback playerStateChangedCallback : playerStateChangedCallbacks) {
            playerStateChangedCallback.onPlayerStateChanged();
        }
    }

    public void unRegisterPlayerStateChanged(PlayerStateChangedCallback playerStateChangedCallback)
    {
        playerStateChangedCallbacks.remove(playerStateChangedCallback);
    }
}
