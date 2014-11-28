/*
 * Copyright (c) 2014. Jean-Francois Berube, all rights reserved.
 */

package com.dontbelievethebyte.skipshuffle.ui.elements.player.buttons.animations.listeners;

import android.view.animation.Animation;

import com.dontbelievethebyte.skipshuffle.ui.elements.player.AbstractPlayerUI;

public class SpinDown extends AbstractListener{

    public SpinDown(AbstractPlayerUI playerUI)
    {
        super(playerUI);
    }

    @Override
    public void onAnimationStart(Animation animation)
    {
        playerUI.doPause();
        playerUI.buttons.shuffle.setImageDrawable(playerUI.buttons.drawables.getShufflePressed());
    }

    @Override
    public void onAnimationEnd(Animation animation)
    {
        playerUI.buttons.shuffle.setImageDrawable(playerUI.buttons.drawables.getShuffle());
        playerUI.doPlay();
    }
}
