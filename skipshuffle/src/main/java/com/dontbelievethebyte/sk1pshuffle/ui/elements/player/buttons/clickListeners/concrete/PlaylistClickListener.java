/*
 * Copyright (c) 2014. Jean-Francois Berube, all rights reserved.
 */

package com.dontbelievethebyte.sk1pshuffle.ui.elements.player.buttons.clickListeners.concrete;

import android.view.View;

import com.dontbelievethebyte.sk1pshuffle.activities.PlayerActivity;
import com.dontbelievethebyte.sk1pshuffle.exceptions.NoMediaPlayerException;
import com.dontbelievethebyte.sk1pshuffle.exceptions.PlaylistEmptyException;
import com.dontbelievethebyte.sk1pshuffle.playlist.RandomPlaylist;
import com.dontbelievethebyte.sk1pshuffle.playlist.Track;
import com.dontbelievethebyte.sk1pshuffle.service.SkipShuffleMediaPlayer;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.player.ListPlayer;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.player.buttons.clickListeners.CustomAbstractClickListener;

public class PlaylistClickListener extends CustomAbstractClickListener {

    public PlaylistClickListener(PlayerActivity baseActivity)
    {
        super(baseActivity);
    }

    @Override
    public void onClick(View view)
    {
        handleHapticFeedback(view);
        PlayerActivity playerActivity = (PlayerActivity) activity;
        playerActivity.onViewModeChanged();
    }

    @Override
    public boolean onLongClick(View view) {
        handleHapticFeedback(view);
        try {
            SkipShuffleMediaPlayer mediaPlayer = activity.getMediaPlayer();
            RandomPlaylist randomPlaylist = mediaPlayer.getPlaylist();
            Track track = randomPlaylist.getCurrent();
            ListPlayer player = (ListPlayer) activity.ui.player;
            player.listView.setSelection(track.getPosition());
        } catch (NoMediaPlayerException noMediaPlayerException) {
            activity.handleNoMediaPlayerException(noMediaPlayerException);
        } catch (PlaylistEmptyException playlistEmptyException) {
            activity.handlePlaylistEmptyException(playlistEmptyException);
        }
        return true;
    }
}