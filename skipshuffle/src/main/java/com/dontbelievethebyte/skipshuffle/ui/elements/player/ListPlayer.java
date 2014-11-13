package com.dontbelievethebyte.skipshuffle.ui.elements.player;

import com.dontbelievethebyte.skipshuffle.activities.BaseActivity;
import com.dontbelievethebyte.skipshuffle.exceptions.NoMediaPlayerException;
import com.dontbelievethebyte.skipshuffle.exceptions.PlaylistEmptyException;
import com.dontbelievethebyte.skipshuffle.service.SkipShuffleMediaPlayer;
import com.dontbelievethebyte.skipshuffle.ui.elements.UIElementCompositeInterface;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.ListPlayerButtons;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.listeners.PlayClick;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.listeners.PlaylistClick;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.listeners.PrevClick;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.listeners.ShuffleClick;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.listeners.SkipClick;
import com.dontbelievethebyte.skipshuffle.ui.elements.player.labels.SongLabel;

public class ListPlayer extends AbstractPlayerUI implements UIElementCompositeInterface {


    public ListPlayer(BaseActivity baseActivity, ListPlayerButtons playerButtons, SongLabel songLabel)
    {
        this.baseActivity = baseActivity;
        this.songLabel = songLabel;
        buttons = playerButtons;
        buttons.animations.setPlayerUIListeners(this);
        setButtonsOnClickListeners();
    }

    private void setButtonsOnClickListeners()
    {
        buttons.play.setOnClickListener(new PlayClick(baseActivity));
        buttons.skip.setOnClickListener(new SkipClick(baseActivity));
        buttons.prev.setOnClickListener(new PrevClick(baseActivity));
        buttons.shuffle.setOnClickListener(new ShuffleClick(baseActivity));
        buttons.playlist.setOnClickListener(new PlaylistClick(baseActivity));
    }

    @Override
    public void doPlay()
    {
        buttons.play.setImageDrawable(buttons.drawables.getPlay());
        buttons.play.startAnimation(buttons.animations.ltr);
    }

    @Override
    public void doPause()
    {
        buttons.play.setImageDrawable(buttons.drawables.getPause());
        buttons.play.startAnimation(buttons.animations.blinkAnimation);
    }

    @Override
    public void doSkip()
    {
        buttons.play.clearAnimation();
        buttons.play.setImageDrawable(buttons.drawables.getPause());
        buttons.play.startAnimation(buttons.animations.blinkAnimation);
        buttons.skip.startAnimation(buttons.animations.flipRightAnimation);
        buttons.play.setImageDrawable(buttons.drawables.getPlay());
        buttons.play.startAnimation(buttons.animations.ltr);
    }

    @Override
    public void doPrev()
    {
        buttons.play.clearAnimation();
        buttons.play.setImageDrawable(buttons.drawables.getPause());
        buttons.play.startAnimation(buttons.animations.blinkAnimation);
        buttons.prev.startAnimation(buttons.animations.flipLeftAnimation);
        buttons.play.setImageDrawable(buttons.drawables.getPlay());
        buttons.play.startAnimation(buttons.animations.ltr);
    }

    @Override
    public void doShuffle()
    {
        buttons.play.clearAnimation();
        buttons.play.setImageDrawable(buttons.drawables.getPause());
        buttons.play.startAnimation(buttons.animations.blinkAnimation);
        buttons.shuffle.startAnimation(buttons.animations.flipDownAnimation);
        buttons.play.setImageDrawable(buttons.drawables.getPlay());
        buttons.play.startAnimation(buttons.animations.ltr);
    }

    @Override
    public void reboot()
    {
        try {
            SkipShuffleMediaPlayer mediaPlayer = baseActivity.getMediaPlayer();
            if (mediaPlayer.isPlaying())
                doPlay();
            else
                doPause();

            setTitle(buildFormattedTitle());
        } catch (NoMediaPlayerException noMediaPlayerException) {
            handleNoMediaPlayerException(noMediaPlayerException);
        } catch (PlaylistEmptyException playlistEmptyException) {
            handlePlaylistEmptyException(playlistEmptyException);
        }
    }

    @Override
    public void setTitle(String title)
    {
    }

}