package com.dpaulenk.idea.highlighter.actions;

import com.dpaulenk.idea.highlighter.IdentifierHighlighterAppComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;

public class UnlockIdentifiersAction extends AnAction {
    public void actionPerformed(AnActionEvent ae) {
        Editor editor = (Editor) ae.getDataContext().getData(PlatformDataKeys.EDITOR.getName());
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        identHighlightComp.unlockIdentifiers(editor);
    }

    public void update(AnActionEvent ae) {
        Editor editor = (Editor) ae.getDataContext().getData(PlatformDataKeys.EDITOR.getName());
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        Presentation p = ae.getPresentation();
        p.setEnabled(identHighlightComp.isPluginEnabled() && identHighlightComp.areIdentifiersLocked(editor));
    }
}