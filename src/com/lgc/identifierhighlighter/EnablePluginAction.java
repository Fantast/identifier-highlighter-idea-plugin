package com.lgc.identifierhighlighter;

import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

public class EnablePluginAction extends ToggleAction {
  public boolean isSelected(AnActionEvent e)
  {
    Application application = ApplicationManager.getApplication();
    IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
    return(identHighlightComp.is_pluginEnabled());
  }

  public void setSelected(AnActionEvent e,boolean state)
  {
    Application application = ApplicationManager.getApplication();
    IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
    identHighlightComp.enablePlugin(state);
  }
}
