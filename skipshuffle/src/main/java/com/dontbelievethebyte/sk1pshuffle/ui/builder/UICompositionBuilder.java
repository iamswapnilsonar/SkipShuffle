/*
 * Copyright (c) 2015. Jean-François Bérubé, all rights reserved.
 */

package com.dontbelievethebyte.sk1pshuffle.ui.builder;

import android.app.Fragment;

import com.dontbelievethebyte.sk1pshuffle.activity.BaseActivity;
import com.dontbelievethebyte.sk1pshuffle.ui.element.UIComposition;
import com.dontbelievethebyte.sk1pshuffle.ui.element.layout.AbstractLayout;
import com.dontbelievethebyte.sk1pshuffle.ui.element.navdrawer.ContentBrowserDrawer;
import com.dontbelievethebyte.sk1pshuffle.ui.element.player.AbstractPlayerUI;
import com.dontbelievethebyte.sk1pshuffle.ui.element.player.seekbar.CustomSeekBar;
import com.dontbelievethebyte.sk1pshuffle.ui.theme.Theme;

public class UICompositionBuilder {

    public AbstractPlayerUI player;
    public ContentBrowserDrawer musicPlayerDrawer;
    public BaseActivity baseActivity;
    public Fragment fragment;
    public AbstractLayout contentArea;
    public CustomSeekBar customSeekBar;
    public Theme theme;

    public UICompositionBuilder setActivity(BaseActivity baseActivity)
    {
        this.baseActivity = baseActivity;
        return this;
    }

    public UICompositionBuilder setFragment(Fragment fragment)
    {
        this.fragment = fragment;
        return this;
    }

    public UICompositionBuilder setTheme(Theme theme)
    {
        this.theme = theme;
        return this;
    }

    public UICompositionBuilder setNavigationDrawer(ContentBrowserDrawer musicPlayerDrawer)
    {
        this.musicPlayerDrawer = musicPlayerDrawer;
        return this;
    }

    public UICompositionBuilder setPlayer(AbstractPlayerUI playerUIInterface)
    {
        this.player = playerUIInterface;
        return this;
    }

    public UICompositionBuilder setContentArea(AbstractLayout contentArea)
    {
        this.contentArea = contentArea;
        return this;
    }

    public UICompositionBuilder setSeekbar(CustomSeekBar customSeekBar)
    {
        this.customSeekBar = customSeekBar;
        return this;
    }

    public UIComposition build()
    {
        return new UIComposition(this);
    }
}