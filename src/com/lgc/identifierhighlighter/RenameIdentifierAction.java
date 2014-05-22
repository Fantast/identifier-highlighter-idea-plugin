package com.lgc.identifierhighlighter;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

public class RenameIdentifierAction extends EditorAction {
  public RenameIdentifierAction()
  {
    super(new RenameIdentifierActionHandler());
  }

  public void update(AnActionEvent ae)
  {
    Application application = ApplicationManager.getApplication();
    IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
    Presentation p = ae.getPresentation();
    p.setEnabled(identHighlightComp.is_pluginEnabled());
  }

  protected static class RenameIdentifierActionHandler extends EditorWriteActionHandler {
    public void executeWriteAction(Editor editor,DataContext dataContext)
    {
      Application application = ApplicationManager.getApplication();
      IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
      identHighlightComp.renameIdentifier(editor);
    }
  }
}
