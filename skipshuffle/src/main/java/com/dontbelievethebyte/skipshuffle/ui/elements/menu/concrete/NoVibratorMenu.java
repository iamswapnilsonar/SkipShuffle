/*
 * Copyright (c) 2014. Jean-Francois Berube, all rights reserved.
 */

package com.dontbelievethebyte.skipshuffle.ui.elements.menu.concrete;

import android.app.Activity;
import android.view.Menu;

import com.dontbelievethebyte.skipshuffle.R;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.callbacks.MenuItemSelectedCallback;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.AbstractMenu;
import com.dontbelievethebyte.skipshuffle.ui.elements.menu.CustomOptionsMenuInterface;

public class NoVibratorMenu extends AbstractMenu implements CustomOptionsMenuInterface {

    public NoVibratorMenu(Activity activity, Menu menu, MenuItemSelectedCallback menuItemSelectedCallback)
    {
        super(activity, menu, menuItemSelectedCallback);
    }

    @Override
    protected int getMenuResourceId()
    {
        return R.menu.main_no_vibrator;
    }

}
