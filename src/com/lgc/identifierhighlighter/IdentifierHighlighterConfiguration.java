package com.lgc.identifierhighlighter;

import java.util.StringTokenizer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JColorChooser;
import javax.swing.JCheckBox;

public class IdentifierHighlighterConfiguration {
  public static final int PREFERRED_WIDTH = 40;
  public static final int PREFERRED_HEIGHT = 20;

  private JPanel _mainPanel;
  private JCheckBox _classHighlightCB;
  private JButton _classActiveHighlightColorB;
  private JButton _classHighlightColorB;
  private JButton _defaultClassHighlightColorsB;
  private JCheckBox _methodHighlightCB;
  private JButton _methodActiveHighlightColorB;
  private JButton _methodHighlightColorB;
  private JButton _defaultMethodHighlightColorsB;
  private JCheckBox _fieldHighlightCB;
  private JButton _fieldReadActiveHighlightColorB;
  private JButton _fieldReadHighlightColorB;
  private JButton _defaultFieldReadHighlightColorsB;
  private JCheckBox _paramHighlightCB;
  private JButton _paramReadActiveHighlightColorB;
  private JButton _paramReadHighlightColorB;
  private JButton _defaultReadParamHighlightColorsB;
  private JCheckBox _localHighlightCB;
  private JButton _localReadActiveHighlightColorB;
  private JButton _localReadHighlightColorB;
  private JButton _defaultReadLocalHighlightColorsB;
  private JCheckBox _otherHighlightCB;
  private JButton _otherActiveHighlightColorB;
  private JButton _otherHighlightColorB;
  private JButton _defaultOtherHighlightColorsB;
  private JComboBox _highlightLayerCB;
  private JButton _defaultHighlightLayerB;
  private JButton _allActiveHighlightColorB;
  private JButton _allHighlightColorB;
  private JCheckBox _showInMarkerBarCB;
  private JButton _defaultShowInMarkerBarB;
  private JPanel _colorSchemeP;
  private JPanel _optionsP;
  private JCheckBox _pluginEnabledCB;
  private JButton _defaultAllHighlightColorsB;
  private JButton _fieldWriteActiveHighlightColorB;
  private JButton _fieldWriteHighlightColorB;
  private JButton _defaultFieldWriteHighlightColorsB;
  private JButton _paramWriteActiveHighlightColorB;
  private JButton _paramWriteHighlightColorB;
  private JButton _defaultWriteParamHighlightColorsB;
  private JButton _localWriteActiveHighlightColorB;
  private JButton _localWriteHighlightColorB;
  private JButton _defaultWriteLocalHighlightColorsB;

  public IdentifierHighlighterConfiguration()
  {
    _classActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_classActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_classActiveHighlightColorB,"Class Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_classActiveHighlightColorB,userColor);
      }
    });
    _classHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_classHighlightColorB);
        Color userColor = JColorChooser.showDialog(_classHighlightColorB,"Class Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_classHighlightColorB,userColor);
      }
    });
    _defaultClassHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultClassHighlightColors();
      }
    });
    _methodActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_methodActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_methodActiveHighlightColorB,"Method Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_methodActiveHighlightColorB,userColor);
      }
    });
    _methodHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_methodHighlightColorB);
        Color userColor = JColorChooser.showDialog(_methodHighlightColorB,"Method Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_methodHighlightColorB,userColor);
      }
    });
    _defaultMethodHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultMethodHighlightColors();
      }
    });
    _fieldReadActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_fieldReadActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_fieldReadActiveHighlightColorB,"Field Read Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_fieldReadActiveHighlightColorB,userColor);
      }
    });
    _fieldReadHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_fieldReadHighlightColorB);
        Color userColor = JColorChooser.showDialog(_fieldReadHighlightColorB,"Field Read Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_fieldReadHighlightColorB,userColor);
      }
    });
    _defaultFieldReadHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultFieldReadHighlightColors();
      }
    });
    _fieldWriteActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_fieldWriteActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_fieldWriteActiveHighlightColorB,"Field Write Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_fieldWriteActiveHighlightColorB,userColor);
      }
    });
    _fieldWriteHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_fieldWriteHighlightColorB);
        Color userColor = JColorChooser.showDialog(_fieldWriteHighlightColorB,"Field Write Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_fieldWriteHighlightColorB,userColor);
      }
    });
    _defaultFieldWriteHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultFieldWriteHighlightColors();
      }
    });
    _paramReadActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_paramReadActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_paramReadActiveHighlightColorB,"Parameter Read Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_paramReadActiveHighlightColorB,userColor);
      }
    });
    _paramReadHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_paramReadHighlightColorB);
        Color userColor = JColorChooser.showDialog(_paramReadHighlightColorB,"Parameter Read Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_paramReadHighlightColorB,userColor);
      }
    });
    _defaultReadParamHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultParamReadHighlightColors();
      }
    });
    _paramWriteActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_paramWriteActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_paramWriteActiveHighlightColorB,"Parameter Write Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_paramWriteActiveHighlightColorB,userColor);
      }
    });
    _paramWriteHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_paramWriteHighlightColorB);
        Color userColor = JColorChooser.showDialog(_paramWriteHighlightColorB,"Parameter Write Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_paramWriteHighlightColorB,userColor);
      }
    });
    _defaultWriteParamHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultParamWriteHighlightColors();
      }
    });
    _localReadActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_localReadActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_localReadActiveHighlightColorB,"Local Variable Read Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_localReadActiveHighlightColorB,userColor);
      }
    });
    _localReadHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_localReadHighlightColorB);
        Color userColor = JColorChooser.showDialog(_localReadHighlightColorB,"Local Variable Read Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_localReadHighlightColorB,userColor);
      }
    });
    _defaultReadLocalHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultLocalReadHighlightColors();
      }
    });
    _localWriteActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_localWriteActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_localWriteActiveHighlightColorB,"Local Variable Write Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_localWriteActiveHighlightColorB,userColor);
      }
    });
    _localWriteHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_localWriteHighlightColorB);
        Color userColor = JColorChooser.showDialog(_localWriteHighlightColorB,"Local Variable Write Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_localWriteHighlightColorB,userColor);
      }
    });
    _defaultWriteLocalHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultLocalWriteHighlightColors();
      }
    });
    _otherActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_otherActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_otherActiveHighlightColorB,"Other Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_otherActiveHighlightColorB,userColor);
      }
    });
    _otherHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_otherHighlightColorB);
        Color userColor = JColorChooser.showDialog(_otherHighlightColorB,"Other Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_otherHighlightColorB,userColor);
      }
    });
    _defaultOtherHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultOtherHighlightColors();
      }
    });
    defaultAllHighlightColors();
    _allActiveHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_allActiveHighlightColorB);
        Color userColor = JColorChooser.showDialog(_allActiveHighlightColorB,"All Active Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_classActiveHighlightColorB,userColor);
        setColorToColorButton(_methodActiveHighlightColorB,userColor);
        setColorToColorButton(_fieldReadActiveHighlightColorB,userColor);
        setColorToColorButton(_paramReadActiveHighlightColorB,userColor);
        setColorToColorButton(_localReadActiveHighlightColorB,userColor);
        setColorToColorButton(_fieldWriteActiveHighlightColorB,userColor);
        setColorToColorButton(_paramWriteActiveHighlightColorB,userColor);
        setColorToColorButton(_localWriteActiveHighlightColorB,userColor);
        setColorToColorButton(_otherActiveHighlightColorB,userColor);
        setColorToColorButton(_allActiveHighlightColorB,userColor);
      }
    });
    _allHighlightColorB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        Color currColor = getColorFromColorButton(_allHighlightColorB);
        Color userColor = JColorChooser.showDialog(_allHighlightColorB,"All Highlight Color",currColor);
        if(userColor == null)
          return;
        setColorToColorButton(_classHighlightColorB,userColor);
        setColorToColorButton(_methodHighlightColorB,userColor);
        setColorToColorButton(_fieldReadHighlightColorB,userColor);
        setColorToColorButton(_paramReadHighlightColorB,userColor);
        setColorToColorButton(_localReadHighlightColorB,userColor);
        setColorToColorButton(_fieldWriteHighlightColorB,userColor);
        setColorToColorButton(_paramWriteHighlightColorB,userColor);
        setColorToColorButton(_localWriteHighlightColorB,userColor);
        setColorToColorButton(_otherHighlightColorB,userColor);
        setColorToColorButton(_allHighlightColorB,userColor);
      }
    });
    _defaultAllHighlightColorsB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        defaultClassHighlightColors();
        defaultMethodHighlightColors();
        defaultFieldReadHighlightColors();
        defaultParamReadHighlightColors();
        defaultLocalReadHighlightColors();
        defaultFieldWriteHighlightColors();
        defaultParamWriteHighlightColors();
        defaultLocalWriteHighlightColors();
        defaultOtherHighlightColors();
        defaultAllHighlightColors();
      }
    });
    _defaultHighlightLayerB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        _highlightLayerCB.setSelectedItem(IdentifierHighlighterAppComponent.DEFAULT_HIGHLIGHT_LAYER);
      }
    });
    _defaultShowInMarkerBarB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        _showInMarkerBarCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_SHOW_IN_MARKER_BAR);
      }
    });
    _pluginEnabledCB.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent)
      {
        updatePluginEnabled();
      }
    });
  }

  protected void defaultAllHighlightColors()
  {
    setStringToColorButton(_allActiveHighlightColorB, IdentifierHighlighterAppComponent.DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_allHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_CLASS_HIGHLIGHT_COLOR);
  }

  protected void defaultOtherHighlightColors()
  {
    _otherHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_OTHER_HIGHLIGHT_ENABLED);
    setStringToColorButton(_otherActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_OTHER_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_otherHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_OTHER_HIGHLIGHT_COLOR);
  }

  protected void defaultLocalReadHighlightColors()
  {
    _localHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_LOCAL_HIGHLIGHT_ENABLED);
    setStringToColorButton(_localReadActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_LOCAL_READ_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_localReadHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_LOCAL_READ_HIGHLIGHT_COLOR);
  }

  protected void defaultParamReadHighlightColors()
  {
    _paramHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_PARAM_HIGHLIGHT_ENABLED);
    setStringToColorButton(_paramReadActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_PARAM_READ_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_paramReadHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_PARAM_READ_HIGHLIGHT_COLOR);
  }

  protected void defaultFieldReadHighlightColors()
  {
    _fieldHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_FIELD_HIGHLIGHT_ENABLED);
    setStringToColorButton(_fieldReadActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_FIELD_READ_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_fieldReadHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_FIELD_READ_HIGHLIGHT_COLOR);
  }

  protected void defaultLocalWriteHighlightColors()
  {
    _localHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_LOCAL_HIGHLIGHT_ENABLED);
    setStringToColorButton(_localWriteActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_LOCAL_WRITE_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_localWriteHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_LOCAL_WRITE_HIGHLIGHT_COLOR);
  }

  protected void defaultParamWriteHighlightColors()
  {
    _paramHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_PARAM_HIGHLIGHT_ENABLED);
    setStringToColorButton(_paramWriteActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_PARAM_WRITE_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_paramWriteHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_PARAM_WRITE_HIGHLIGHT_COLOR);
  }

  protected void defaultFieldWriteHighlightColors()
  {
    _fieldHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_FIELD_HIGHLIGHT_ENABLED);
    setStringToColorButton(_fieldWriteActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_FIELD_WRITE_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_fieldWriteHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_FIELD_WRITE_HIGHLIGHT_COLOR);
  }

  protected void defaultMethodHighlightColors()
  {
    _methodHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_METHOD_HIGHLIGHT_ENABLED);
    setStringToColorButton(_methodActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_METHOD_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_methodHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_METHOD_HIGHLIGHT_COLOR);
  }

  protected void defaultClassHighlightColors()
  {
    _classHighlightCB.setSelected(IdentifierHighlighterAppComponent.DEFAULT_CLASS_HIGHLIGHT_ENABLED);
    setStringToColorButton(_classActiveHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR);
    setStringToColorButton(_classHighlightColorB,IdentifierHighlighterAppComponent.DEFAULT_CLASS_HIGHLIGHT_COLOR);
  }

  protected void updatePluginEnabled()
  {
    setHierarchyEnabled(_colorSchemeP,_pluginEnabledCB.isSelected());
    setHierarchyEnabled(_optionsP,_pluginEnabledCB.isSelected());
  }

  protected void setHierarchyEnabled(Component comp,boolean enabled)
  {
    comp.setEnabled(enabled);
    Component children[] = new Component[0];
    if(comp instanceof Container)
      children = ((Container)comp).getComponents();
    for(Component child : children)
      setHierarchyEnabled(child,enabled);
  }

  public JPanel get_mainPanel()
  {
    return _mainPanel;
  }

  public void setData(IdentifierHighlighterAppComponent data)
  {
    _classHighlightCB.setSelected(data.is_classHighlightEnabled());
    setStringToColorButton(_classActiveHighlightColorB,data.get_classActiveHighlightColor());
    setStringToColorButton(_classHighlightColorB,data.get_classHighlightColor());
    _methodHighlightCB.setSelected(data.is_methodHighlightEnabled());
    setStringToColorButton(_methodActiveHighlightColorB,data.get_methodActiveHighlightColor());
    setStringToColorButton(_methodHighlightColorB,data.get_methodHighlightColor());
    _fieldHighlightCB.setSelected(data.is_fieldHighlightEnabled());
    setStringToColorButton(_fieldReadActiveHighlightColorB,data.get_fieldReadActiveHighlightColor());
    setStringToColorButton(_fieldReadHighlightColorB,data.get_fieldReadHighlightColor());
    setStringToColorButton(_fieldWriteActiveHighlightColorB,data.get_fieldWriteActiveHighlightColor());
    setStringToColorButton(_fieldWriteHighlightColorB,data.get_fieldWriteHighlightColor());
    _paramHighlightCB.setSelected(data.is_paramHighlightEnabled());
    setStringToColorButton(_paramReadActiveHighlightColorB,data.get_paramReadActiveHighlightColor());
    setStringToColorButton(_paramReadHighlightColorB,data.get_paramReadHighlightColor());
    setStringToColorButton(_paramWriteActiveHighlightColorB,data.get_paramWriteActiveHighlightColor());
    setStringToColorButton(_paramWriteHighlightColorB,data.get_paramWriteHighlightColor());
    _localHighlightCB.setSelected(data.is_localHighlightEnabled());
    setStringToColorButton(_localReadActiveHighlightColorB,data.get_localReadActiveHighlightColor());
    setStringToColorButton(_localReadHighlightColorB,data.get_localReadHighlightColor());
    setStringToColorButton(_localWriteActiveHighlightColorB,data.get_localWriteActiveHighlightColor());
    setStringToColorButton(_localWriteHighlightColorB,data.get_localWriteHighlightColor());
    _otherHighlightCB.setSelected(data.is_otherHighlightEnabled());
    setStringToColorButton(_otherActiveHighlightColorB,data.get_otherActiveHighlightColor());
    setStringToColorButton(_otherHighlightColorB,data.get_otherHighlightColor());
    _highlightLayerCB.setSelectedItem(data.get_highlightLayer());
    _showInMarkerBarCB.setSelected(data.is_showInMarkerBar());
    _pluginEnabledCB.setSelected(data.is_pluginEnabled());
    updatePluginEnabled();
  }

  public void getData(IdentifierHighlighterAppComponent data)
  {
    data.set_classHighlightEnabled(_classHighlightCB.isSelected());
    data.set_classActiveHighlightColor(getStringFromColorButton(_classActiveHighlightColorB));
    data.set_classHighlightColor(getStringFromColorButton(_classHighlightColorB));
    data.set_methodHighlightEnabled(_methodHighlightCB.isSelected());
    data.set_methodActiveHighlightColor(getStringFromColorButton(_methodActiveHighlightColorB));
    data.set_methodHighlightColor(getStringFromColorButton(_methodHighlightColorB));
    data.set_fieldHighlightEnabled(_fieldHighlightCB.isSelected());
    data.set_fieldReadActiveHighlightColor(getStringFromColorButton(_fieldReadActiveHighlightColorB));
    data.set_fieldReadHighlightColor(getStringFromColorButton(_fieldReadHighlightColorB));
    data.set_fieldWriteActiveHighlightColor(getStringFromColorButton(_fieldWriteActiveHighlightColorB));
    data.set_fieldWriteHighlightColor(getStringFromColorButton(_fieldWriteHighlightColorB));
    data.set_paramHighlightEnabled(_paramHighlightCB.isSelected());
    data.set_paramReadActiveHighlightColor(getStringFromColorButton(_paramReadActiveHighlightColorB));
    data.set_paramReadHighlightColor(getStringFromColorButton(_paramReadHighlightColorB));
    data.set_paramWriteActiveHighlightColor(getStringFromColorButton(_paramWriteActiveHighlightColorB));
    data.set_paramWriteHighlightColor(getStringFromColorButton(_paramWriteHighlightColorB));
    data.set_localHighlightEnabled(_localHighlightCB.isSelected());
    data.set_localReadActiveHighlightColor(getStringFromColorButton(_localReadActiveHighlightColorB));
    data.set_localReadHighlightColor(getStringFromColorButton(_localReadHighlightColorB));
    data.set_localWriteActiveHighlightColor(getStringFromColorButton(_localWriteActiveHighlightColorB));
    data.set_localWriteHighlightColor(getStringFromColorButton(_localWriteHighlightColorB));
    data.set_otherHighlightEnabled(_otherHighlightCB.isSelected());
    data.set_otherActiveHighlightColor(getStringFromColorButton(_otherActiveHighlightColorB));
    data.set_otherHighlightColor(getStringFromColorButton(_otherHighlightColorB));
    data.set_highlightLayer((String)_highlightLayerCB.getSelectedItem());
    data.set_showInMarkerBar(_showInMarkerBarCB.isSelected());
    data.enablePlugin(_pluginEnabledCB.isSelected());
  }

  public boolean isModified(IdentifierHighlighterAppComponent data)
  {
    if(_classHighlightCB.isSelected() != data.is_classHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_classActiveHighlightColorB) != null ? !getStringFromColorButton(_classActiveHighlightColorB).equals(data.get_classActiveHighlightColor()) : data.get_classActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_classHighlightColorB) != null ? !getStringFromColorButton(_classHighlightColorB).equals(data.get_classHighlightColor()) : data.get_classHighlightColor() != null)
      return true;
    if(_methodHighlightCB.isSelected() != data.is_methodHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_methodActiveHighlightColorB) != null ? !getStringFromColorButton(_methodActiveHighlightColorB).equals(data.get_methodActiveHighlightColor()) : data.get_methodActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_methodHighlightColorB) != null ? !getStringFromColorButton(_methodHighlightColorB).equals(data.get_methodHighlightColor()) : data.get_methodHighlightColor() != null)
      return true;
    if(_fieldHighlightCB.isSelected() != data.is_fieldHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_fieldReadActiveHighlightColorB) != null ? !getStringFromColorButton(_fieldReadActiveHighlightColorB).equals(data.get_fieldReadActiveHighlightColor()) : data.get_fieldReadActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_fieldReadHighlightColorB) != null ? !getStringFromColorButton(_fieldReadHighlightColorB).equals(data.get_fieldReadHighlightColor()) : data.get_fieldReadHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_fieldWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_fieldWriteActiveHighlightColorB).equals(data.get_fieldWriteActiveHighlightColor()) : data.get_fieldWriteActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_fieldWriteHighlightColorB) != null ? !getStringFromColorButton(_fieldWriteHighlightColorB).equals(data.get_fieldWriteHighlightColor()) : data.get_fieldWriteHighlightColor() != null)
      return true;
    if(_paramHighlightCB.isSelected() != data.is_paramHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_paramReadActiveHighlightColorB) != null ? !getStringFromColorButton(_paramReadActiveHighlightColorB).equals(data.get_paramReadActiveHighlightColor()) : data.get_paramReadActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_paramReadHighlightColorB) != null ? !getStringFromColorButton(_paramReadHighlightColorB).equals(data.get_paramReadHighlightColor()) : data.get_paramReadHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_paramWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_paramWriteActiveHighlightColorB).equals(data.get_paramWriteActiveHighlightColor()) : data.get_paramWriteActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_paramWriteHighlightColorB) != null ? !getStringFromColorButton(_paramWriteHighlightColorB).equals(data.get_paramWriteHighlightColor()) : data.get_paramWriteHighlightColor() != null)
      return true;
    if(_localHighlightCB.isSelected() != data.is_localHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_localReadActiveHighlightColorB) != null ? !getStringFromColorButton(_localReadActiveHighlightColorB).equals(data.get_localReadActiveHighlightColor()) : data.get_localReadActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_localReadHighlightColorB) != null ? !getStringFromColorButton(_localReadHighlightColorB).equals(data.get_localReadHighlightColor()) : data.get_localReadHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_localWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_localWriteActiveHighlightColorB).equals(data.get_localWriteActiveHighlightColor()) : data.get_localWriteActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_localWriteHighlightColorB) != null ? !getStringFromColorButton(_localWriteHighlightColorB).equals(data.get_localWriteHighlightColor()) : data.get_localWriteHighlightColor() != null)
      return true;
    if(_otherHighlightCB.isSelected() != data.is_otherHighlightEnabled())
      return(true);
    if(getStringFromColorButton(_otherActiveHighlightColorB) != null ? !getStringFromColorButton(_otherActiveHighlightColorB).equals(data.get_otherActiveHighlightColor()) : data.get_otherActiveHighlightColor() != null)
      return true;
    if(getStringFromColorButton(_otherHighlightColorB) != null ? !getStringFromColorButton(_otherHighlightColorB).equals(data.get_otherHighlightColor()) : data.get_otherHighlightColor() != null)
      return true;
    if((_highlightLayerCB.getSelectedItem() != null) ? (!_highlightLayerCB.getSelectedItem().equals(data.get_highlightLayer())) : (data.get_highlightLayer() != null))
      return true;
    if(_showInMarkerBarCB.isSelected() != data.is_showInMarkerBar())
      return(true);
    if(_pluginEnabledCB.isSelected() != data.is_pluginEnabled())
      return(true);
    return false;
  }

  protected Color getColorFromColorButton(JButton b)
  {
    ImageIcon ii = (ImageIcon)b.getIcon();
    ColorImage ci = (ColorImage)ii.getImage();
    return(ci.getColor());
  }

  protected String getStringFromColorButton(JButton b)
  {
    Color c = getColorFromColorButton(b);
    return(getStringFromColor(c));
  }

  protected void setColorToColorButton(JButton b,Color c)
  {
    b.setIcon(new ImageIcon(new ColorImage(PREFERRED_WIDTH,PREFERRED_HEIGHT,c)));
  }

  protected void setStringToColorButton(JButton b,String s)
  {
    Color c = getColorFromString(s);
    setColorToColorButton(b,c);
  }

  public static String getStringFromColor(Color c)
  {
    return("" + c.getRed() + "," + c.getGreen() + "," + c.getBlue());
  }

  public static Color getColorFromString(String s)
  {
    StringTokenizer tokens = new StringTokenizer(s,",");
    if(tokens.countTokens() == 3) {
      String redToken = tokens.nextToken();
      String greenToken = tokens.nextToken();
      String blueToken = tokens.nextToken();
      try {
        int red = Integer.parseInt(redToken);
        int green = Integer.parseInt(greenToken);
        int blue = Integer.parseInt(blueToken);
        if((red >= 0) && (red <= 255) && (green >= 0) && (green <= 255) && (blue >= 0) && (blue <= 255)) {
          Color retVal = new Color(red,green,blue);
          return(retVal);
        }
      } catch(NumberFormatException nfe) {
        //Ignore
      }
    }
    return(null);
  }
}
