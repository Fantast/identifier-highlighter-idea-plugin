package com.lgc.identifierhighlighter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;

public class DeclareIdentifierAction extends AnAction {
  public void actionPerformed(AnActionEvent ae)
  {
    Editor editor = (Editor)ae.getDataContext().getData(DataConstants.EDITOR);
    Application application = ApplicationManager.getApplication();
    IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
    identHighlightComp.declareIdentifier(editor);
  }

  public void update(AnActionEvent ae)
  {
    Application application = ApplicationManager.getApplication();
    IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
    Presentation p = ae.getPresentation();
    p.setEnabled(identHighlightComp.is_pluginEnabled());
  }
}
