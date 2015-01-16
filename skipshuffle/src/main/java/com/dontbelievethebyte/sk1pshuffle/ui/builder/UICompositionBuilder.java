/*
 * Copyright (c) 2015. Jean-François Bérubé, all rights reserved.
 */

package com.dontbelievethebyte.sk1pshuffle.ui.builder;

import android.app.Fragment;

import com.dontbelievethebyte.sk1pshuffle.activities.BaseActivity;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.layout.AbstractLayout;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.navdrawer.ContentBrowserDrawer;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.UIComposition;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.player.AbstractPlayerUI;
import com.dontbelievethebyte.sk1pshuffle.ui.elements.player.seekbar.CustomSeekBar;
import com.dontbelievethebyte.sk1pshuffle.ui.theme.structure.Colors;
import com.dontbelievethebyte.sk1pshuffle.ui.theme.structure.Drawables;

public class UICompositionBuilder {

    public AbstractPlayerUI player;
    public ContentBrowserDrawer musicPlayerDrawer;
    public BaseActivity baseActivity;
    public Fragment fragment;
    public AbstractLayout contentArea;
    public Colors colors;
    public Drawables drawables;
    public CustomSeekBar customSeekBar;

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

    public UICompositionBuilder setDrawables(Drawables drawables)
    {
        this.drawables = drawables;
        return this;
    }

    public UICompositionBuilder setColors(Colors colors)
    {
        this.colors = colors;
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