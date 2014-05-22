package com.dpaulenk.idea.highlighter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ColorImage extends BufferedImage {
    protected Color _color = null;

    public ColorImage(int width, int height, Color color) {
        super(width, height, TYPE_INT_RGB);
        _color = color;
        Graphics2D g2d = createGraphics();
        g2d.setPaint(_color);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public Color getColor() {
        return (_color);
    }
}
