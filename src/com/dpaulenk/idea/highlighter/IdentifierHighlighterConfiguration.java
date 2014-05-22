package com.dpaulenk.idea.highlighter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

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

    public IdentifierHighlighterConfiguration() {
        _classActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_classActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_classActiveHighlightColorB, "Class Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_classActiveHighlightColorB, userColor);
            }
        });
        _classHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_classHighlightColorB);
                Color userColor = JColorChooser.showDialog(_classHighlightColorB, "Class Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_classHighlightColorB, userColor);
            }
        });
        _defaultClassHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultClassHighlightColors();
            }
        });
        _methodActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_methodActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_methodActiveHighlightColorB, "Method Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_methodActiveHighlightColorB, userColor);
            }
        });
        _methodHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_methodHighlightColorB);
                Color userColor = JColorChooser.showDialog(_methodHighlightColorB, "Method Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_methodHighlightColorB, userColor);
            }
        });
        _defaultMethodHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultMethodHighlightColors();
            }
        });
        _fieldReadActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_fieldReadActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_fieldReadActiveHighlightColorB, "Field Read Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_fieldReadActiveHighlightColorB, userColor);
            }
        });
        _fieldReadHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_fieldReadHighlightColorB);
                Color userColor = JColorChooser.showDialog(_fieldReadHighlightColorB, "Field Read Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_fieldReadHighlightColorB, userColor);
            }
        });
        _defaultFieldReadHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultFieldReadHighlightColors();
            }
        });
        _fieldWriteActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_fieldWriteActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_fieldWriteActiveHighlightColorB, "Field Write Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_fieldWriteActiveHighlightColorB, userColor);
            }
        });
        _fieldWriteHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_fieldWriteHighlightColorB);
                Color userColor = JColorChooser.showDialog(_fieldWriteHighlightColorB, "Field Write Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_fieldWriteHighlightColorB, userColor);
            }
        });
        _defaultFieldWriteHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultFieldWriteHighlightColors();
            }
        });
        _paramReadActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_paramReadActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_paramReadActiveHighlightColorB, "Parameter Read Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_paramReadActiveHighlightColorB, userColor);
            }
        });
        _paramReadHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_paramReadHighlightColorB);
                Color userColor = JColorChooser.showDialog(_paramReadHighlightColorB, "Parameter Read Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_paramReadHighlightColorB, userColor);
            }
        });
        _defaultReadParamHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultParamReadHighlightColors();
            }
        });
        _paramWriteActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_paramWriteActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_paramWriteActiveHighlightColorB, "Parameter Write Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_paramWriteActiveHighlightColorB, userColor);
            }
        });
        _paramWriteHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_paramWriteHighlightColorB);
                Color userColor = JColorChooser.showDialog(_paramWriteHighlightColorB, "Parameter Write Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_paramWriteHighlightColorB, userColor);
            }
        });
        _defaultWriteParamHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultParamWriteHighlightColors();
            }
        });
        _localReadActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_localReadActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_localReadActiveHighlightColorB, "Local Variable Read Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_localReadActiveHighlightColorB, userColor);
            }
        });
        _localReadHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_localReadHighlightColorB);
                Color userColor = JColorChooser.showDialog(_localReadHighlightColorB, "Local Variable Read Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_localReadHighlightColorB, userColor);
            }
        });
        _defaultReadLocalHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultLocalReadHighlightColors();
            }
        });
        _localWriteActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_localWriteActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_localWriteActiveHighlightColorB, "Local Variable Write Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_localWriteActiveHighlightColorB, userColor);
            }
        });
        _localWriteHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_localWriteHighlightColorB);
                Color userColor = JColorChooser.showDialog(_localWriteHighlightColorB, "Local Variable Write Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_localWriteHighlightColorB, userColor);
            }
        });
        _defaultWriteLocalHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultLocalWriteHighlightColors();
            }
        });
        _otherActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_otherActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_otherActiveHighlightColorB, "Other Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_otherActiveHighlightColorB, userColor);
            }
        });
        _otherHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_otherHighlightColorB);
                Color userColor = JColorChooser.showDialog(_otherHighlightColorB, "Other Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_otherHighlightColorB, userColor);
            }
        });
        _defaultOtherHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                defaultOtherHighlightColors();
            }
        });
        defaultAllHighlightColors();
        _allActiveHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_allActiveHighlightColorB);
                Color userColor = JColorChooser.showDialog(_allActiveHighlightColorB, "All Active Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_classActiveHighlightColorB, userColor);
                setColorToColorButton(_methodActiveHighlightColorB, userColor);
                setColorToColorButton(_fieldReadActiveHighlightColorB, userColor);
                setColorToColorButton(_paramReadActiveHighlightColorB, userColor);
                setColorToColorButton(_localReadActiveHighlightColorB, userColor);
                setColorToColorButton(_fieldWriteActiveHighlightColorB, userColor);
                setColorToColorButton(_paramWriteActiveHighlightColorB, userColor);
                setColorToColorButton(_localWriteActiveHighlightColorB, userColor);
                setColorToColorButton(_otherActiveHighlightColorB, userColor);
                setColorToColorButton(_allActiveHighlightColorB, userColor);
            }
        });
        _allHighlightColorB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color currColor = getColorFromColorButton(_allHighlightColorB);
                Color userColor = JColorChooser.showDialog(_allHighlightColorB, "All Highlight Color", currColor);
                if (userColor == null) {
                    return;
                }
                setColorToColorButton(_classHighlightColorB, userColor);
                setColorToColorButton(_methodHighlightColorB, userColor);
                setColorToColorButton(_fieldReadHighlightColorB, userColor);
                setColorToColorButton(_paramReadHighlightColorB, userColor);
                setColorToColorButton(_localReadHighlightColorB, userColor);
                setColorToColorButton(_fieldWriteHighlightColorB, userColor);
                setColorToColorButton(_paramWriteHighlightColorB, userColor);
                setColorToColorButton(_localWriteHighlightColorB, userColor);
                setColorToColorButton(_otherHighlightColorB, userColor);
                setColorToColorButton(_allHighlightColorB, userColor);
            }
        });
        _defaultAllHighlightColorsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
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
            public void actionPerformed(ActionEvent actionEvent) {
                _highlightLayerCB.setSelectedItem(IdentifierHighlighterSettings.DEFAULT_HIGHLIGHT_LAYER);
            }
        });
        _defaultShowInMarkerBarB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                _showInMarkerBarCB.setSelected(IdentifierHighlighterSettings.DEFAULT_SHOW_IN_MARKER_BAR);
            }
        });
        _pluginEnabledCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                updatePluginEnabled();
            }
        });
    }

    protected void defaultAllHighlightColors() {
        setStringToColorButton(_allActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_allHighlightColorB, IdentifierHighlighterSettings.DEFAULT_CLASS_HIGHLIGHT_COLOR);
    }

    protected void defaultOtherHighlightColors() {
        _otherHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_OTHER_HIGHLIGHT_ENABLED);
        setStringToColorButton(_otherActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_OTHER_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_otherHighlightColorB, IdentifierHighlighterSettings.DEFAULT_OTHER_HIGHLIGHT_COLOR);
    }

    protected void defaultLocalReadHighlightColors() {
        _localHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_LOCAL_HIGHLIGHT_ENABLED);
        setStringToColorButton(_localReadActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_LOCAL_READ_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_localReadHighlightColorB, IdentifierHighlighterSettings.DEFAULT_LOCAL_READ_HIGHLIGHT_COLOR);
    }

    protected void defaultParamReadHighlightColors() {
        _paramHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_PARAM_HIGHLIGHT_ENABLED);
        setStringToColorButton(_paramReadActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_PARAM_READ_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_paramReadHighlightColorB, IdentifierHighlighterSettings.DEFAULT_PARAM_READ_HIGHLIGHT_COLOR);
    }

    protected void defaultFieldReadHighlightColors() {
        _fieldHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_FIELD_HIGHLIGHT_ENABLED);
        setStringToColorButton(_fieldReadActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_FIELD_READ_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_fieldReadHighlightColorB, IdentifierHighlighterSettings.DEFAULT_FIELD_READ_HIGHLIGHT_COLOR);
    }

    protected void defaultLocalWriteHighlightColors() {
        _localHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_LOCAL_HIGHLIGHT_ENABLED);
        setStringToColorButton(_localWriteActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_LOCAL_WRITE_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_localWriteHighlightColorB, IdentifierHighlighterSettings.DEFAULT_LOCAL_WRITE_HIGHLIGHT_COLOR);
    }

    protected void defaultParamWriteHighlightColors() {
        _paramHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_PARAM_HIGHLIGHT_ENABLED);
        setStringToColorButton(_paramWriteActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_PARAM_WRITE_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_paramWriteHighlightColorB, IdentifierHighlighterSettings.DEFAULT_PARAM_WRITE_HIGHLIGHT_COLOR);
    }

    protected void defaultFieldWriteHighlightColors() {
        _fieldHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_FIELD_HIGHLIGHT_ENABLED);
        setStringToColorButton(_fieldWriteActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_FIELD_WRITE_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_fieldWriteHighlightColorB, IdentifierHighlighterSettings.DEFAULT_FIELD_WRITE_HIGHLIGHT_COLOR);
    }

    protected void defaultMethodHighlightColors() {
        _methodHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_METHOD_HIGHLIGHT_ENABLED);
        setStringToColorButton(_methodActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_METHOD_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_methodHighlightColorB, IdentifierHighlighterSettings.DEFAULT_METHOD_HIGHLIGHT_COLOR);
    }

    protected void defaultClassHighlightColors() {
        _classHighlightCB.setSelected(IdentifierHighlighterSettings.DEFAULT_CLASS_HIGHLIGHT_ENABLED);
        setStringToColorButton(_classActiveHighlightColorB, IdentifierHighlighterSettings.DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR);
        setStringToColorButton(_classHighlightColorB, IdentifierHighlighterSettings.DEFAULT_CLASS_HIGHLIGHT_COLOR);
    }

    protected void updatePluginEnabled() {
        setHierarchyEnabled(_colorSchemeP, _pluginEnabledCB.isSelected());
        setHierarchyEnabled(_optionsP, _pluginEnabledCB.isSelected());
    }

    protected void setHierarchyEnabled(Component comp, boolean enabled) {
        comp.setEnabled(enabled);
        Component children[] = new Component[0];
        if (comp instanceof Container) {
            children = ((Container) comp).getComponents();
        }
        for (Component child : children) {
            setHierarchyEnabled(child, enabled);
        }
    }

    public JPanel get_mainPanel() {
        return _mainPanel;
    }

    public void loadData(IdentifierHighlighterSettings settings) {
        
        _classHighlightCB.setSelected(settings.isClassHighlightEnabled());
        setStringToColorButton(_classActiveHighlightColorB, settings.getClassActiveHighlightColor());
        setStringToColorButton(_classHighlightColorB, settings.getClassHighlightColor());
        _methodHighlightCB.setSelected(settings.isMethodHighlightEnabled());
        setStringToColorButton(_methodActiveHighlightColorB, settings.getMethodActiveHighlightColor());
        setStringToColorButton(_methodHighlightColorB, settings.getMethodHighlightColor());
        _fieldHighlightCB.setSelected(settings.isFieldHighlightEnabled());
        setStringToColorButton(_fieldReadActiveHighlightColorB, settings.getFieldReadActiveHighlightColor());
        setStringToColorButton(_fieldReadHighlightColorB, settings.getFieldReadHighlightColor());
        setStringToColorButton(_fieldWriteActiveHighlightColorB, settings.getFieldWriteActiveHighlightColor());
        setStringToColorButton(_fieldWriteHighlightColorB, settings.getFieldWriteHighlightColor());
        _paramHighlightCB.setSelected(settings.isParamHighlightEnabled());
        setStringToColorButton(_paramReadActiveHighlightColorB, settings.getParamReadActiveHighlightColor());
        setStringToColorButton(_paramReadHighlightColorB, settings.getParamReadHighlightColor());
        setStringToColorButton(_paramWriteActiveHighlightColorB, settings.getParamWriteActiveHighlightColor());
        setStringToColorButton(_paramWriteHighlightColorB, settings.getParamWriteHighlightColor());
        _localHighlightCB.setSelected(settings.isLocalHighlightEnabled());
        setStringToColorButton(_localReadActiveHighlightColorB, settings.getLocalReadActiveHighlightColor());
        setStringToColorButton(_localReadHighlightColorB, settings.getLocalReadHighlightColor());
        setStringToColorButton(_localWriteActiveHighlightColorB, settings.getLocalWriteActiveHighlightColor());
        setStringToColorButton(_localWriteHighlightColorB, settings.getLocalWriteHighlightColor());
        _otherHighlightCB.setSelected(settings.isOtherHighlightEnabled());
        setStringToColorButton(_otherActiveHighlightColorB, settings.getOtherActiveHighlightColor());
        setStringToColorButton(_otherHighlightColorB, settings.getOtherHighlightColor());
        _highlightLayerCB.setSelectedItem(settings.getHighlightLayer());
        _showInMarkerBarCB.setSelected(settings.isShowInMarkerBar());
        _pluginEnabledCB.setSelected(settings.isPluginEnabled());
        
        updatePluginEnabled();
    }

    public void storeData(IdentifierHighlighterSettings settings) {
        settings.setClassHighlightEnabled(_classHighlightCB.isSelected());
        settings.setClassActiveHighlightColor(getStringFromColorButton(_classActiveHighlightColorB));
        settings.setClassHighlightColor(getStringFromColorButton(_classHighlightColorB));
        settings.setMethodHighlightEnabled(_methodHighlightCB.isSelected());
        settings.setMethodActiveHighlightColor(getStringFromColorButton(_methodActiveHighlightColorB));
        settings.setMethodHighlightColor(getStringFromColorButton(_methodHighlightColorB));
        settings.setFieldHighlightEnabled(_fieldHighlightCB.isSelected());
        settings.setFieldReadActiveHighlightColor(getStringFromColorButton(_fieldReadActiveHighlightColorB));
        settings.setFieldReadHighlightColor(getStringFromColorButton(_fieldReadHighlightColorB));
        settings.setFieldWriteActiveHighlightColor(getStringFromColorButton(_fieldWriteActiveHighlightColorB));
        settings.setFieldWriteHighlightColor(getStringFromColorButton(_fieldWriteHighlightColorB));
        settings.setParamHighlightEnabled(_paramHighlightCB.isSelected());
        settings.setParamReadActiveHighlightColor(getStringFromColorButton(_paramReadActiveHighlightColorB));
        settings.setParamReadHighlightColor(getStringFromColorButton(_paramReadHighlightColorB));
        settings.setParamWriteActiveHighlightColor(getStringFromColorButton(_paramWriteActiveHighlightColorB));
        settings.setParamWriteHighlightColor(getStringFromColorButton(_paramWriteHighlightColorB));
        settings.setLocalHighlightEnabled(_localHighlightCB.isSelected());
        settings.setLocalReadActiveHighlightColor(getStringFromColorButton(_localReadActiveHighlightColorB));
        settings.setLocalReadHighlightColor(getStringFromColorButton(_localReadHighlightColorB));
        settings.setLocalWriteActiveHighlightColor(getStringFromColorButton(_localWriteActiveHighlightColorB));
        settings.setLocalWriteHighlightColor(getStringFromColorButton(_localWriteHighlightColorB));
        settings.setOtherHighlightEnabled(_otherHighlightCB.isSelected());
        settings.setOtherActiveHighlightColor(getStringFromColorButton(_otherActiveHighlightColorB));
        settings.setOtherHighlightColor(getStringFromColorButton(_otherHighlightColorB));
        settings.setHighlightLayer((String) _highlightLayerCB.getSelectedItem());
        settings.setShowInMarkerBar(_showInMarkerBarCB.isSelected());
        settings.setPluginEnabled(_pluginEnabledCB.isSelected());
    }

    public boolean isModified(IdentifierHighlighterSettings settings) {
        if (_classHighlightCB.isSelected() != settings.isClassHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_classActiveHighlightColorB) != null ? !getStringFromColorButton(_classActiveHighlightColorB).equals(settings.getClassActiveHighlightColor()) : settings.getClassActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_classHighlightColorB) != null ? !getStringFromColorButton(_classHighlightColorB).equals(settings.getClassHighlightColor()) : settings.getClassHighlightColor() != null) {
            return true;
        }
        if (_methodHighlightCB.isSelected() != settings.isMethodHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_methodActiveHighlightColorB) != null ? !getStringFromColorButton(_methodActiveHighlightColorB).equals(settings.getMethodActiveHighlightColor()) : settings.getMethodActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_methodHighlightColorB) != null ? !getStringFromColorButton(_methodHighlightColorB).equals(settings.getMethodHighlightColor()) : settings.getMethodHighlightColor() != null) {
            return true;
        }
        if (_fieldHighlightCB.isSelected() != settings.isFieldHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_fieldReadActiveHighlightColorB) != null ? !getStringFromColorButton(_fieldReadActiveHighlightColorB).equals(settings.getFieldReadActiveHighlightColor()) : settings.getFieldReadActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_fieldReadHighlightColorB) != null ? !getStringFromColorButton(_fieldReadHighlightColorB).equals(settings.getFieldReadHighlightColor()) : settings.getFieldReadHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_fieldWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_fieldWriteActiveHighlightColorB).equals(settings.getFieldWriteActiveHighlightColor()) : settings.getFieldWriteActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_fieldWriteHighlightColorB) != null ? !getStringFromColorButton(_fieldWriteHighlightColorB).equals(settings.getFieldWriteHighlightColor()) : settings.getFieldWriteHighlightColor() != null) {
            return true;
        }
        if (_paramHighlightCB.isSelected() != settings.isParamHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_paramReadActiveHighlightColorB) != null ? !getStringFromColorButton(_paramReadActiveHighlightColorB).equals(settings.getParamReadActiveHighlightColor()) : settings.getParamReadActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_paramReadHighlightColorB) != null ? !getStringFromColorButton(_paramReadHighlightColorB).equals(settings.getParamReadHighlightColor()) : settings.getParamReadHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_paramWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_paramWriteActiveHighlightColorB).equals(settings.getParamWriteActiveHighlightColor()) : settings.getParamWriteActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_paramWriteHighlightColorB) != null ? !getStringFromColorButton(_paramWriteHighlightColorB).equals(settings.getParamWriteHighlightColor()) : settings.getParamWriteHighlightColor() != null) {
            return true;
        }
        if (_localHighlightCB.isSelected() != settings.isLocalHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_localReadActiveHighlightColorB) != null ? !getStringFromColorButton(_localReadActiveHighlightColorB).equals(settings.getLocalReadActiveHighlightColor()) : settings.getLocalReadActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_localReadHighlightColorB) != null ? !getStringFromColorButton(_localReadHighlightColorB).equals(settings.getLocalReadHighlightColor()) : settings.getLocalReadHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_localWriteActiveHighlightColorB) != null ? !getStringFromColorButton(_localWriteActiveHighlightColorB).equals(settings.getLocalWriteActiveHighlightColor()) : settings.getLocalWriteActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_localWriteHighlightColorB) != null ? !getStringFromColorButton(_localWriteHighlightColorB).equals(settings.getLocalWriteHighlightColor()) : settings.getLocalWriteHighlightColor() != null) {
            return true;
        }
        if (_otherHighlightCB.isSelected() != settings.isOtherHighlightEnabled()) {
            return (true);
        }
        if (getStringFromColorButton(_otherActiveHighlightColorB) != null ? !getStringFromColorButton(_otherActiveHighlightColorB).equals(settings.getOtherActiveHighlightColor()) : settings.getOtherActiveHighlightColor() != null) {
            return true;
        }
        if (getStringFromColorButton(_otherHighlightColorB) != null ? !getStringFromColorButton(_otherHighlightColorB).equals(settings.getOtherHighlightColor()) : settings.getOtherHighlightColor() != null) {
            return true;
        }
        if ((_highlightLayerCB.getSelectedItem() != null) ? (!_highlightLayerCB.getSelectedItem().equals(settings.getHighlightLayer())) : (settings.getHighlightLayer() != null)) {
            return true;
        }
        if (_showInMarkerBarCB.isSelected() != settings.isShowInMarkerBar()) {
            return (true);
        }
        if (_pluginEnabledCB.isSelected() != settings.isPluginEnabled()) {
            return (true);
        }
        return false;
    }

    protected Color getColorFromColorButton(JButton b) {
        ImageIcon ii = (ImageIcon) b.getIcon();
        ColorImage ci = (ColorImage) ii.getImage();
        return (ci.getColor());
    }

    protected String getStringFromColorButton(JButton b) {
        Color c = getColorFromColorButton(b);
        return (getStringFromColor(c));
    }

    protected void setColorToColorButton(JButton b, Color c) {
        b.setIcon(new ImageIcon(new ColorImage(PREFERRED_WIDTH, PREFERRED_HEIGHT, c)));
    }

    protected void setStringToColorButton(JButton b, String s) {
        Color c = getColorFromString(s);
        setColorToColorButton(b, c);
    }

    public static String getStringFromColor(Color c) {
        return ("" + c.getRed() + "," + c.getGreen() + "," + c.getBlue());
    }

    public static Color getColorFromString(String s) {
        StringTokenizer tokens = new StringTokenizer(s, ",");
        if (tokens.countTokens() == 3) {
            String redToken = tokens.nextToken();
            String greenToken = tokens.nextToken();
            String blueToken = tokens.nextToken();
            try {
                int red = Integer.parseInt(redToken);
                int green = Integer.parseInt(greenToken);
                int blue = Integer.parseInt(blueToken);
                if ((red >= 0) && (red <= 255) && (green >= 0) && (green <= 255) && (blue >= 0) && (blue <= 255)) {
                    Color retVal = new Color(red, green, blue);
                    return (retVal);
                }
            } catch (NumberFormatException nfe) {
                //Ignore
            }
        }
        return (null);
    }

    public static class ColorImage extends BufferedImage {
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
}
