/*
 * Copyright (c) 2014. Jean-Francois Berube, all rights reserved.
 */

package com.dontbelievethebyte.skipshuffle.ui.elements.menu.builder;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.view.Menu;

import com.dontbelievethebyte.skipshuffle.ui.elements.menu.callbacks.MenuItemSelectedCallback;
import com.dontbelievethebyte.skipshuffle.ui.elements.actionbar.CustomActionBarWrapper;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.CustomOptionsMenuInterface;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.concrete.NoVibratorMenu;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.concrete.VibratorMenu;

public class OptionsMenuBuilder {

    private Activity activity;
    private CustomActionBarWrapper customActionBarWrapper;
    private MenuItemSelectedCallback menuItemSelectedCallback;

    public OptionsMenuBuilder(Activity baseActivity)
    {
        this.activity = baseActivity;
    }

    public void setCustomActionBarWrapper(CustomActionBarWrapper customActionBarWrapper)
    {
        this.customActionBarWrapper = customActionBarWrapper;
    }

    public void setMenuItemSelectedCallback(MenuItemSelectedCallback menuItemSelectedCallback)
    {
        this.menuItemSelectedCallback = menuItemSelectedCallback;
    }

    public CustomOptionsMenuInterface build(Menu menu)
    {

        CustomOptionsMenuInterface customOptionsMenuInterface;

        customOptionsMenuInterface = (hasVibratorCapability()) ?
                new VibratorMenu(activity, menu, menuItemSelectedCallback) :
                new NoVibratorMenu(activity, menu, menuItemSelectedCallback);

        customOptionsMenuInterface.setCustomActionBarWrapper(customActionBarWrapper);
        return customOptionsMenuInterface;
    }

    private boolean hasVibratorCapability()
    {
        Vibrator vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        return vibrator.hasVibrator();
    }
}
