package com.bookstore.gui.theme;

import java.awt.Font;

public class ThemeFont {
    private String fontStyle = "Segoe UI";
    private int fontFamily = Font.PLAIN;
    private int largeFontSize = 48;
    private int mediumFontSize = 24;
    private int smallFontSize = 14;
    private Font largeFont = new Font(fontStyle, fontFamily, largeFontSize);
    private Font mediumFont = new Font(fontStyle, fontFamily, mediumFontSize);
    private Font smallFont = new Font(fontStyle, fontFamily, smallFontSize);

    public String getFontStyle() {
        return fontStyle;
    }

    public int getFontFamily() {
        return fontFamily;
    }

    public int getLargeFontSize() {
        return largeFontSize;
    }

    public int getSmallFontSize() {
        return smallFontSize;
    }

    public Font getLargeFont() {
        return largeFont;
    }

    public Font getSmallFont() {
        return smallFont;
    }

    public Font getMediumFont() {
        return mediumFont;
    }

}
