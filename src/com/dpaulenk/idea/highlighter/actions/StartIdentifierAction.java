package com.dpaulenk.idea.highlighter.actions;

import com.dpaulenk.idea.highlighter.IdentifierHighlighterAppComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;

public class StartIdentifierAction extends AnAction {
    public void actionPerformed(AnActionEvent ae) {
        Editor editor = (Editor) ae.getDataContext().getData(PlatformDataKeys.EDITOR.getName());
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        identHighlightComp.startIdentifier(editor);
    }

    public void update(AnActionEvent ae) {
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        Presentation p = ae.getPresentation();
        p.setEnabled(identHighlightComp.getSettings().isPluginEnabled());
    }
}
