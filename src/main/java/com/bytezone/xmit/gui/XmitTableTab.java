package com.bytezone.xmit.gui;

import javafx.scene.input.KeyCode;

// -----------------------------------------------------------------------------------//
abstract class XmitTableTab extends XmitTab
// -----------------------------------------------------------------------------------//
{

    // ---------------------------------------------------------------------------------//
    public XmitTableTab(String title, KeyCode keyCode)
    // ---------------------------------------------------------------------------------//
    {
        super(title, keyCode);
    }

    // ---------------------------------------------------------------------------------//
    @Override
    void update()
    // ---------------------------------------------------------------------------------//
    {
        valid = true;
    }
}
