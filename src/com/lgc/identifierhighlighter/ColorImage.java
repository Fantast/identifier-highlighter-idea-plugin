package com.lgc.identifierhighlighter;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class ColorImage extends BufferedImage {
  protected Color _color = null;

  public ColorImage(int width,int height,Color color)
  {
    super(width,height,TYPE_INT_RGB);
    _color = color;
    Graphics2D g2d = createGraphics();
    g2d.setPaint(_color);
    g2d.fillRect(0,0,getWidth(),getHeight());
  }

  public Color getColor()
  {
    return(_color);
  }
}
