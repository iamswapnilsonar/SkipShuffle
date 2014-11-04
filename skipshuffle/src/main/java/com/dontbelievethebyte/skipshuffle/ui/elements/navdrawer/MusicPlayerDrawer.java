package com.dontbelievethebyte.skipshuffle.ui.elements.navdrawer;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.dontbelievethebyte.skipshuffle.activities.BaseActivity;
import com.dontbelievethebyte.skipshuffle.ui.elements.UIElementCompositeInterface;
import com.dontbelievethebyte.skipshuffle.ui.elements.visitor.DimensionsVisitor;
import com.dontbelievethebyte.skipshuffle.ui.elements.visitor.DrawablesVisitor;

public class MusicPlayerDrawer implements UIElementCompositeInterface,
                                          DimensionsVisitor.Visitable,
                                          DrawablesVisitor.Visitable {

    private ListView drawerList;

    public MusicPlayerDrawer(BaseActivity baseActivity, int drawerId)
    {
        drawerList = (ListView) baseActivity.findViewById(drawerId);
    }

    public void setClickListener(ListView.OnItemClickListener navDrawerClickListener)
    {
        drawerList.setOnItemClickListener(navDrawerClickListener);
    }

    public void setTouchListener(View.OnTouchListener touchListener)
    {
        drawerList.setOnTouchListener(touchListener);
    }

    public ListView getDrawerList() {
        return drawerList;
    }

    public void setAdapter(BaseAdapter baseAdapter)
    {
        drawerList.setAdapter(baseAdapter);
    }


    @Override
    public void acceptDimensionsVisitor(DimensionsVisitor dimensionsVisitor)
    {
        dimensionsVisitor.visit(this);
    }

    @Override
    public void acceptDrawablesVisitor(DrawablesVisitor drawablesVisitor)
    {
        drawablesVisitor.visit(this);
    }
}