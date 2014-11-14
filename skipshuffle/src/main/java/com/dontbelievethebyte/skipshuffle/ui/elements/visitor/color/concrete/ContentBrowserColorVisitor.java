package com.dontbelievethebyte.skipshuffle.ui.elements.visitor.color.concrete;

import android.app.Activity;
import android.widget.ListView;

import com.dontbelievethebyte.skipshuffle.ui.elements.UIElementCompositeInterface;
import com.dontbelievethebyte.skipshuffle.ui.elements.visitor.color.AbstractColorVisitor;
import com.dontbelievethebyte.skipshuffle.ui.structured.Colors;

public class ContentBrowserColorVisitor extends AbstractColorVisitor {

    public ContentBrowserColorVisitor(Activity activity)
    {
        super(activity);
    }

    @Override
    public void visit(UIElementCompositeInterface uiElement)
    {
        if (uiElement instanceof com.dontbelievethebyte.skipshuffle.ui.elements.navdrawer.ContentBrowser) {
            ListView drawerList = ((com.dontbelievethebyte.skipshuffle.ui.elements.navdrawer.ContentBrowser) uiElement).getDrawerList();
            if (null != drawerList) {
                drawerList.setBackgroundResource(colors.navDrawerBackground);
                drawerList.setDivider(Colors.toColorDrawable(colors.listDivider));
            }
        }
    }
}