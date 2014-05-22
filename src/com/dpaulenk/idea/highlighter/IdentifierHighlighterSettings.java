package com.dpaulenk.idea.highlighter;

public class IdentifierHighlighterSettings {

    public static final String DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_CLASS_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_METHOD_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_METHOD_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_FIELD_READ_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_FIELD_READ_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_PARAM_READ_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_PARAM_READ_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_LOCAL_READ_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_LOCAL_READ_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_FIELD_WRITE_ACTIVE_HIGHLIGHT_COLOR = "175,0,0";
    public static final String DEFAULT_FIELD_WRITE_HIGHLIGHT_COLOR = "255,128,128";
    public static final String DEFAULT_PARAM_WRITE_ACTIVE_HIGHLIGHT_COLOR = "175,0,0";
    public static final String DEFAULT_PARAM_WRITE_HIGHLIGHT_COLOR = "255,128,128";
    public static final String DEFAULT_LOCAL_WRITE_ACTIVE_HIGHLIGHT_COLOR = "175,0,0";
    public static final String DEFAULT_LOCAL_WRITE_HIGHLIGHT_COLOR = "255,128,128";
    public static final String DEFAULT_OTHER_ACTIVE_HIGHLIGHT_COLOR = "0,175,175";
    public static final String DEFAULT_OTHER_HIGHLIGHT_COLOR = "128,255,255";
    public static final String DEFAULT_HIGHLIGHT_LAYER = "ADDITIONAL_SYNTAX";
    public static final boolean DEFAULT_CLASS_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_METHOD_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_FIELD_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_PARAM_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_LOCAL_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_OTHER_HIGHLIGHT_ENABLED = true;
    public static final boolean DEFAULT_SHOW_IN_MARKER_BAR = true;
    public static final boolean DEFAULT_PLUGIN_ENABLED = true;
    
    private boolean classHighlightEnabled = DEFAULT_CLASS_HIGHLIGHT_ENABLED;
    private boolean methodHighlightEnabled = DEFAULT_METHOD_HIGHLIGHT_ENABLED;
    private boolean fieldHighlightEnabled = DEFAULT_FIELD_HIGHLIGHT_ENABLED;
    private boolean paramHighlightEnabled = DEFAULT_PARAM_HIGHLIGHT_ENABLED;
    private boolean localHighlightEnabled = DEFAULT_LOCAL_HIGHLIGHT_ENABLED;
    private boolean otherHighlightEnabled = DEFAULT_OTHER_HIGHLIGHT_ENABLED;
    private boolean showInMarkerBar = DEFAULT_SHOW_IN_MARKER_BAR;
    private boolean pluginEnabled = DEFAULT_PLUGIN_ENABLED;
    private String classActiveHighlightColor = DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR;
    private String classHighlightColor = DEFAULT_CLASS_HIGHLIGHT_COLOR;
    private String methodActiveHighlightColor = DEFAULT_METHOD_ACTIVE_HIGHLIGHT_COLOR;
    private String methodHighlightColor = DEFAULT_METHOD_HIGHLIGHT_COLOR;
    private String fieldReadActiveHighlightColor = DEFAULT_FIELD_READ_ACTIVE_HIGHLIGHT_COLOR;
    private String fieldReadHighlightColor = DEFAULT_FIELD_READ_HIGHLIGHT_COLOR;
    private String paramReadActiveHighlightColor = DEFAULT_PARAM_READ_ACTIVE_HIGHLIGHT_COLOR;
    private String paramReadHighlightColor = DEFAULT_PARAM_READ_HIGHLIGHT_COLOR;
    private String localReadActiveHighlightColor = DEFAULT_LOCAL_READ_ACTIVE_HIGHLIGHT_COLOR;
    private String localReadHighlightColor = DEFAULT_LOCAL_READ_HIGHLIGHT_COLOR;
    private String fieldWriteActiveHighlightColor = DEFAULT_FIELD_WRITE_ACTIVE_HIGHLIGHT_COLOR;
    private String fieldWriteHighlightColor = DEFAULT_FIELD_WRITE_HIGHLIGHT_COLOR;
    private String paramWriteActiveHighlightColor = DEFAULT_PARAM_WRITE_ACTIVE_HIGHLIGHT_COLOR;
    private String paramWriteHighlightColor = DEFAULT_PARAM_WRITE_HIGHLIGHT_COLOR;
    private String localWriteActiveHighlightColor = DEFAULT_LOCAL_WRITE_ACTIVE_HIGHLIGHT_COLOR;
    private String localWriteHighlightColor = DEFAULT_LOCAL_WRITE_HIGHLIGHT_COLOR;
    private String otherActiveHighlightColor = DEFAULT_OTHER_ACTIVE_HIGHLIGHT_COLOR;
    private String otherHighlightColor = DEFAULT_OTHER_HIGHLIGHT_COLOR;
    private String highlightLayer = DEFAULT_HIGHLIGHT_LAYER;

    public boolean isClassHighlightEnabled() {
        return classHighlightEnabled;
    }

    public void setClassHighlightEnabled(boolean classHighlightEnabled) {
        this.classHighlightEnabled = classHighlightEnabled;
    }

    public boolean isMethodHighlightEnabled() {
        return methodHighlightEnabled;
    }

    public void setMethodHighlightEnabled(boolean methodHighlightEnabled) {
        this.methodHighlightEnabled = methodHighlightEnabled;
    }

    public boolean isFieldHighlightEnabled() {
        return fieldHighlightEnabled;
    }

    public void setFieldHighlightEnabled(boolean fieldHighlightEnabled) {
        this.fieldHighlightEnabled = fieldHighlightEnabled;
    }

    public boolean isParamHighlightEnabled() {
        return paramHighlightEnabled;
    }

    public void setParamHighlightEnabled(boolean paramHighlightEnabled) {
        this.paramHighlightEnabled = paramHighlightEnabled;
    }

    public boolean isLocalHighlightEnabled() {
        return localHighlightEnabled;
    }

    public void setLocalHighlightEnabled(boolean localHighlightEnabled) {
        this.localHighlightEnabled = localHighlightEnabled;
    }

    public boolean isOtherHighlightEnabled() {
        return otherHighlightEnabled;
    }

    public void setOtherHighlightEnabled(boolean otherHighlightEnabled) {
        this.otherHighlightEnabled = otherHighlightEnabled;
    }

    public boolean isShowInMarkerBar() {
        return showInMarkerBar;
    }

    public void setShowInMarkerBar(boolean showInMarkerBar) {
        this.showInMarkerBar = showInMarkerBar;
    }

    public boolean isPluginEnabled() {
        return pluginEnabled;
    }

    public void setPluginEnabled(boolean pluginEnabled) {
        this.pluginEnabled = pluginEnabled;
    }

    public String getClassActiveHighlightColor() {
        return classActiveHighlightColor;
    }

    public void setClassActiveHighlightColor(String classActiveHighlightColor) {
        this.classActiveHighlightColor = classActiveHighlightColor;
    }

    public String getClassHighlightColor() {
        return classHighlightColor;
    }

    public void setClassHighlightColor(String classHighlightColor) {
        this.classHighlightColor = classHighlightColor;
    }

    public String getMethodActiveHighlightColor() {
        return methodActiveHighlightColor;
    }

    public void setMethodActiveHighlightColor(String methodActiveHighlightColor) {
        this.methodActiveHighlightColor = methodActiveHighlightColor;
    }

    public String getMethodHighlightColor() {
        return methodHighlightColor;
    }

    public void setMethodHighlightColor(String methodHighlightColor) {
        this.methodHighlightColor = methodHighlightColor;
    }

    public String getFieldReadActiveHighlightColor() {
        return fieldReadActiveHighlightColor;
    }

    public void setFieldReadActiveHighlightColor(String fieldActiveHighlightColor) {
        fieldReadActiveHighlightColor = fieldActiveHighlightColor;
    }

    public String getFieldReadHighlightColor() {
        return fieldReadHighlightColor;
    }

    public void setFieldReadHighlightColor(String fieldHighlightColor) {
        fieldReadHighlightColor = fieldHighlightColor;
    }

    public String getParamReadActiveHighlightColor() {
        return paramReadActiveHighlightColor;
    }

    public void setParamReadActiveHighlightColor(String paramActiveHighlightColor) {
        paramReadActiveHighlightColor = paramActiveHighlightColor;
    }

    public String getParamReadHighlightColor() {
        return paramReadHighlightColor;
    }

    public void setParamReadHighlightColor(String paramHighlightColor) {
        paramReadHighlightColor = paramHighlightColor;
    }

    public String getLocalReadActiveHighlightColor() {
        return localReadActiveHighlightColor;
    }

    public void setLocalReadActiveHighlightColor(String localActiveHighlightColor) {
        localReadActiveHighlightColor = localActiveHighlightColor;
    }

    public String getLocalReadHighlightColor() {
        return localReadHighlightColor;
    }

    public void setLocalReadHighlightColor(String localHighlightColor) {
        localReadHighlightColor = localHighlightColor;
    }

    public String getFieldWriteActiveHighlightColor() {
        return fieldWriteActiveHighlightColor;
    }

    public void setFieldWriteActiveHighlightColor(String fieldActiveHighlightColor) {
        fieldWriteActiveHighlightColor = fieldActiveHighlightColor;
    }

    public String getFieldWriteHighlightColor() {
        return fieldWriteHighlightColor;
    }

    public void setFieldWriteHighlightColor(String fieldHighlightColor) {
        fieldWriteHighlightColor = fieldHighlightColor;
    }

    public String getParamWriteActiveHighlightColor() {
        return paramWriteActiveHighlightColor;
    }

    public void setParamWriteActiveHighlightColor(String paramActiveHighlightColor) {
        paramWriteActiveHighlightColor = paramActiveHighlightColor;
    }

    public String getParamWriteHighlightColor() {
        return paramWriteHighlightColor;
    }

    public void setParamWriteHighlightColor(String paramHighlightColor) {
        paramWriteHighlightColor = paramHighlightColor;
    }

    public String getLocalWriteActiveHighlightColor() {
        return localWriteActiveHighlightColor;
    }

    public void setLocalWriteActiveHighlightColor(String localActiveHighlightColor) {
        localWriteActiveHighlightColor = localActiveHighlightColor;
    }

    public String getLocalWriteHighlightColor() {
        return localWriteHighlightColor;
    }

    public void setLocalWriteHighlightColor(String localHighlightColor) {
        localWriteHighlightColor = localHighlightColor;
    }

    public String getOtherActiveHighlightColor() {
        return otherActiveHighlightColor;
    }

    public void setOtherActiveHighlightColor(String otherActiveHighlightColor) {
        this.otherActiveHighlightColor = otherActiveHighlightColor;
    }

    public String getOtherHighlightColor() {
        return otherHighlightColor;
    }

    public void setOtherHighlightColor(String otherHighlightColor) {
        this.otherHighlightColor = otherHighlightColor;
    }

    public String getHighlightLayer() {
        return highlightLayer;
    }

    public void setHighlightLayer(String highlightLayer) {
        this.highlightLayer = highlightLayer;
    }
}
