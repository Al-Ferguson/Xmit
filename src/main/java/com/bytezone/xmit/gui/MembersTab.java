package com.bytezone.xmit.gui;

import java.util.prefs.Preferences;

import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

// -----------------------------------------------------------------------------------//
class MembersTab extends XmitTableTab
// -----------------------------------------------------------------------------------//
{
    XmitTable xmitTable = new XmitTable();

    // ---------------------------------------------------------------------------------//
    public MembersTab(String title, KeyCode keyCode)
    // ---------------------------------------------------------------------------------//
    {
        super(title, keyCode);

        setContent(xmitTable);
    }

    // ---------------------------------------------------------------------------------//
    @Override
    public void restore(Preferences prefs)
    // ---------------------------------------------------------------------------------//
    {
        xmitTable.restore(prefs);
    }

    // ---------------------------------------------------------------------------------//
    @Override
    public void save(Preferences prefs)
    // ---------------------------------------------------------------------------------//
    {
        xmitTable.save(prefs);
    }

    // ---------------------------------------------------------------------------------//
    @Override
    void setFont(Font font)
    // ---------------------------------------------------------------------------------//
    {
        super.setFont(font);

        xmitTable.setFont(font);
    }
}
