package com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.animations.listeners;

import android.view.animation.Animation;

import com.dontbelievethebyte.skipshuffle.ui.elements.player.PlayerUI;

public class FlipRight extends AbstractListener{

    public FlipRight(PlayerUI playerUI)
    {
        super(playerUI);
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        playerUI.doPause();
        playerUI.buttons.skip.setImageDrawable(playerUI.buttons.drawables.getSkipPressed());
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        playerUI.buttons.skip.setImageDrawable(playerUI.buttons.drawables.getSkip());
        playerUI.doPlay();
    }
}