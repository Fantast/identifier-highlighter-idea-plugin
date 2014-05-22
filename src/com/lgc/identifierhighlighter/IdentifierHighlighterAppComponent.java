package com.lgc.identifierhighlighter;

import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.JComponent;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.DefaultJDOMExternalizer;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.JDOMExternalizable;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.openapi.util.IconLoader;

public class IdentifierHighlighterAppComponent implements ApplicationComponent, EditorFactoryListener, Configurable, JDOMExternalizable {
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

  protected HashMap<Editor,IdentifierHighlighterEditorComponent> _editorHighlighters = null;
  protected IdentifierHighlighterConfiguration _form = null;
  protected Icon _highlightIcon = IconLoader.getIcon("/com/lgc/identifierhighlighter/images/highlighter_24.png");
  public boolean _classHighlightEnabled = DEFAULT_CLASS_HIGHLIGHT_ENABLED;
  public boolean _methodHighlightEnabled = DEFAULT_METHOD_HIGHLIGHT_ENABLED;
  public boolean _fieldHighlightEnabled = DEFAULT_FIELD_HIGHLIGHT_ENABLED;
  public boolean _paramHighlightEnabled = DEFAULT_PARAM_HIGHLIGHT_ENABLED;
  public boolean _localHighlightEnabled = DEFAULT_LOCAL_HIGHLIGHT_ENABLED;
  public boolean _otherHighlightEnabled = DEFAULT_OTHER_HIGHLIGHT_ENABLED;
  public boolean _showInMarkerBar = DEFAULT_SHOW_IN_MARKER_BAR;
  public boolean _pluginEnabled = DEFAULT_PLUGIN_ENABLED;
  public String _classActiveHighlightColor = DEFAULT_CLASS_ACTIVE_HIGHLIGHT_COLOR;
  public String _classHighlightColor = DEFAULT_CLASS_HIGHLIGHT_COLOR;
  public String _methodActiveHighlightColor = DEFAULT_METHOD_ACTIVE_HIGHLIGHT_COLOR;
  public String _methodHighlightColor = DEFAULT_METHOD_HIGHLIGHT_COLOR;
  public String _fieldReadActiveHighlightColor = DEFAULT_FIELD_READ_ACTIVE_HIGHLIGHT_COLOR;
  public String _fieldReadHighlightColor = DEFAULT_FIELD_READ_HIGHLIGHT_COLOR;
  public String _paramReadActiveHighlightColor = DEFAULT_PARAM_READ_ACTIVE_HIGHLIGHT_COLOR;
  public String _paramReadHighlightColor = DEFAULT_PARAM_READ_HIGHLIGHT_COLOR;
  public String _localReadActiveHighlightColor = DEFAULT_LOCAL_READ_ACTIVE_HIGHLIGHT_COLOR;
  public String _localReadHighlightColor = DEFAULT_LOCAL_READ_HIGHLIGHT_COLOR;
  public String _fieldWriteActiveHighlightColor = DEFAULT_FIELD_WRITE_ACTIVE_HIGHLIGHT_COLOR;
  public String _fieldWriteHighlightColor = DEFAULT_FIELD_WRITE_HIGHLIGHT_COLOR;
  public String _paramWriteActiveHighlightColor = DEFAULT_PARAM_WRITE_ACTIVE_HIGHLIGHT_COLOR;
  public String _paramWriteHighlightColor = DEFAULT_PARAM_WRITE_HIGHLIGHT_COLOR;
  public String _localWriteActiveHighlightColor = DEFAULT_LOCAL_WRITE_ACTIVE_HIGHLIGHT_COLOR;
  public String _localWriteHighlightColor = DEFAULT_LOCAL_WRITE_HIGHLIGHT_COLOR;
  public String _otherActiveHighlightColor = DEFAULT_OTHER_ACTIVE_HIGHLIGHT_COLOR;
  public String _otherHighlightColor = DEFAULT_OTHER_HIGHLIGHT_COLOR;
  public String _highlightLayer = DEFAULT_HIGHLIGHT_LAYER;

  public void initComponent()
  {
    _editorHighlighters = new HashMap<Editor, IdentifierHighlighterEditorComponent>();
    //Add listener for editors
    EditorFactory.getInstance().addEditorFactoryListener(this);
  }

  public boolean is_classHighlightEnabled()
  {
    return(_classHighlightEnabled);
  }

  public void set_classHighlightEnabled(boolean classHighlightEnabled)
  {
    _classHighlightEnabled = classHighlightEnabled;
  }

  public boolean is_methodHighlightEnabled()
  {
    return(_methodHighlightEnabled);
  }

  public void set_methodHighlightEnabled(boolean methodHighlightEnabled)
  {
    _methodHighlightEnabled = methodHighlightEnabled;
  }

  public boolean is_fieldHighlightEnabled()
  {
    return(_fieldHighlightEnabled);
  }

  public void set_fieldHighlightEnabled(boolean fieldHighlightEnabled)
  {
    _fieldHighlightEnabled = fieldHighlightEnabled;
  }

  public boolean is_paramHighlightEnabled()
  {
    return(_paramHighlightEnabled);
  }

  public void set_paramHighlightEnabled(boolean paramHighlightEnabled)
  {
    _paramHighlightEnabled = paramHighlightEnabled;
  }

  public boolean is_localHighlightEnabled()
  {
    return(_localHighlightEnabled);
  }

  public void set_localHighlightEnabled(boolean localHighlightEnabled)
  {
    _localHighlightEnabled = localHighlightEnabled;
  }

  public boolean is_otherHighlightEnabled()
  {
    return(_otherHighlightEnabled);
  }

  public void set_otherHighlightEnabled(boolean otherHighlightEnabled)
  {
    _otherHighlightEnabled = otherHighlightEnabled;
  }

  public boolean is_showInMarkerBar()
  {
    return(_showInMarkerBar);
  }

  public void set_showInMarkerBar(boolean showInMarkerBar)
  {
    _showInMarkerBar = showInMarkerBar;
  }

  public boolean is_pluginEnabled()
  {
    return(_pluginEnabled);
  }

  public void set_pluginEnabled(boolean pluginEnabled)
  {
    _pluginEnabled = pluginEnabled;
  }

  public String get_classActiveHighlightColor()
  {
    return(_classActiveHighlightColor);
  }

  public void set_classActiveHighlightColor(String classActiveHighlightColor)
  {
    _classActiveHighlightColor = classActiveHighlightColor;
  }

  public String get_classHighlightColor()
  {
    return(_classHighlightColor);
  }

  public void set_classHighlightColor(String classHighlightColor)
  {
    _classHighlightColor = classHighlightColor;
  }

  public String get_methodActiveHighlightColor()
  {
    return(_methodActiveHighlightColor);
  }

  public void set_methodActiveHighlightColor(String methodActiveHighlightColor)
  {
    _methodActiveHighlightColor = methodActiveHighlightColor;
  }

  public String get_methodHighlightColor()
  {
    return(_methodHighlightColor);
  }

  public void set_methodHighlightColor(String methodHighlightColor)
  {
    _methodHighlightColor = methodHighlightColor;
  }

  public String get_fieldReadActiveHighlightColor()
  {
    return(_fieldReadActiveHighlightColor);
  }

  public void set_fieldReadActiveHighlightColor(String fieldActiveHighlightColor)
  {
    _fieldReadActiveHighlightColor = fieldActiveHighlightColor;
  }

  public String get_fieldReadHighlightColor()
  {
    return(_fieldReadHighlightColor);
  }

  public void set_fieldReadHighlightColor(String fieldHighlightColor)
  {
    _fieldReadHighlightColor = fieldHighlightColor;
  }

  public String get_paramReadActiveHighlightColor()
  {
    return(_paramReadActiveHighlightColor);
  }

  public void set_paramReadActiveHighlightColor(String paramActiveHighlightColor)
  {
    _paramReadActiveHighlightColor = paramActiveHighlightColor;
  }

  public String get_paramReadHighlightColor()
  {
    return(_paramReadHighlightColor);
  }

  public void set_paramReadHighlightColor(String paramHighlightColor)
  {
    _paramReadHighlightColor = paramHighlightColor;
  }

  public String get_localReadActiveHighlightColor()
  {
    return(_localReadActiveHighlightColor);
  }

  public void set_localReadActiveHighlightColor(String localActiveHighlightColor)
  {
    _localReadActiveHighlightColor = localActiveHighlightColor;
  }

  public String get_localReadHighlightColor()
  {
    return(_localReadHighlightColor);
  }

  public void set_localReadHighlightColor(String localHighlightColor)
  {
    _localReadHighlightColor = localHighlightColor;
  }

  public String get_fieldWriteActiveHighlightColor()
  {
    return(_fieldWriteActiveHighlightColor);
  }

  public void set_fieldWriteActiveHighlightColor(String fieldActiveHighlightColor)
  {
    _fieldWriteActiveHighlightColor = fieldActiveHighlightColor;
  }

  public String get_fieldWriteHighlightColor()
  {
    return(_fieldWriteHighlightColor);
  }

  public void set_fieldWriteHighlightColor(String fieldHighlightColor)
  {
    _fieldWriteHighlightColor = fieldHighlightColor;
  }

  public String get_paramWriteActiveHighlightColor()
  {
    return(_paramWriteActiveHighlightColor);
  }

  public void set_paramWriteActiveHighlightColor(String paramActiveHighlightColor)
  {
    _paramWriteActiveHighlightColor = paramActiveHighlightColor;
  }

  public String get_paramWriteHighlightColor()
  {
    return(_paramWriteHighlightColor);
  }

  public void set_paramWriteHighlightColor(String paramHighlightColor)
  {
    _paramWriteHighlightColor = paramHighlightColor;
  }

  public String get_localWriteActiveHighlightColor()
  {
    return(_localWriteActiveHighlightColor);
  }

  public void set_localWriteActiveHighlightColor(String localActiveHighlightColor)
  {
    _localWriteActiveHighlightColor = localActiveHighlightColor;
  }

  public String get_localWriteHighlightColor()
  {
    return(_localWriteHighlightColor);
  }

  public void set_localWriteHighlightColor(String localHighlightColor)
  {
    _localWriteHighlightColor = localHighlightColor;
  }

  public String get_otherActiveHighlightColor()
  {
    return(_otherActiveHighlightColor);
  }

  public void set_otherActiveHighlightColor(String otherActiveHighlightColor)
  {
    _otherActiveHighlightColor = otherActiveHighlightColor;
  }

  public String get_otherHighlightColor()
  {
    return(_otherHighlightColor);
  }

  public void set_otherHighlightColor(String otherHighlightColor)
  {
    _otherHighlightColor = otherHighlightColor;
  }

  public String get_highlightLayer()
  {
    return _highlightLayer;
  }

  public void set_highlightLayer(String highlightLayer)
  {
    _highlightLayer = highlightLayer;
  }

  public void disposeComponent()
  {
    //Remove listener for editors
    EditorFactory.getInstance().removeEditorFactoryListener(this);
    for(IdentifierHighlighterEditorComponent value : _editorHighlighters.values())
      value.dispose();
    _editorHighlighters.clear();
  }

  @NotNull
  public String getComponentName()
  {
    return("IdentifierHighlighterAppComponent");
  }

  //EditorFactoryListener interface implementation
  public void editorCreated(EditorFactoryEvent efe)
  {
    Editor editor = efe.getEditor();
    if(editor.getProject() == null)
      return;
    IdentifierHighlighterEditorComponent editorHighlighter = new IdentifierHighlighterEditorComponent(this,efe.getEditor());
    _editorHighlighters.put(efe.getEditor(),editorHighlighter);
  }

  public void editorReleased(EditorFactoryEvent efe)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.remove(efe.getEditor());
    if(editorHighlighter == null)
      return;
    editorHighlighter.dispose();
  }

  //Configurable interface implementation
  public String getDisplayName()
  {
    return("Identifier Highlighter");
  }

  public Icon getIcon()
  {
    return(_highlightIcon);
  }

  public String getHelpTopic()
  {
    return("doc-ih");
  }

  public boolean isModified()
  {
    if(_form == null)
      return(false);
    return(_form.isModified(this));
  }

  public void apply() throws ConfigurationException
  {
    if(_form == null)
      return;
    _form.getData(this);
    //Update all highlighters
    for(IdentifierHighlighterEditorComponent value : _editorHighlighters.values())
      value.repaint();
  }

  public void reset()
  {
    if(_form == null)
      return;
    _form.setData(this);
  }

  public void disposeUIResources()
  {
    _form = null;
  }

  public JComponent createComponent()
  {
    if(_form == null)
      _form = new IdentifierHighlighterConfiguration();
    return(_form.get_mainPanel());
  }

  //JDOMExternalizable interface implementation
  public void readExternal(Element element) throws InvalidDataException
  {
    DefaultJDOMExternalizer.readExternal(this, element);
  }

  public void writeExternal(Element element) throws WriteExternalException
  {
    DefaultJDOMExternalizer.writeExternal(this, element);
  }

  //Actions
  public void startIdentifier(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.startIdentifier();
  }

  public void declareIdentifier(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.declareIdentifier();
  }

  public void nextIdentifier(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.nextIdentifier();
  }

  public void previousIdentifier(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.previousIdentifier();
  }

  public void renameIdentifier(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    //Ask user what to rename to
    String newIdentifier = Messages.showInputDialog("Enter new identifier name:","Rename Identifier",Messages.getQuestionIcon(),editorHighlighter.getCurrentIdentifier(),null);
    if(newIdentifier == null)
      return;
    editorHighlighter.renameIdentifier(newIdentifier);
  }

  public void lockIdentifiers(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.lockIdentifiers();
  }

  public void unlockIdentifiers(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return;
    editorHighlighter.unlockIdentifiers();
  }

  public void enablePlugin(boolean enable)
  {
    set_pluginEnabled(enable);
    for(IdentifierHighlighterEditorComponent editorHighlighter : _editorHighlighters.values())
      editorHighlighter.enablePlugin(enable);
  }

  public boolean areIdentifiersLocked(Editor editor)
  {
    IdentifierHighlighterEditorComponent editorHighlighter = _editorHighlighters.get(editor);
    if(editorHighlighter == null)
      return(false);
    return(editorHighlighter.areIdentifiersLocked());
  }
}
