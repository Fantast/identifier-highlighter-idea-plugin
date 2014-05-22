package com.dpaulenk.idea.highlighter.actions;

import com.dpaulenk.idea.highlighter.IdentifierHighlighterAppComponent;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

public class EnablePluginAction extends ToggleAction {
    public boolean isSelected(AnActionEvent e) {
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        return (identHighlightComp.getSettings().isPluginEnabled());
    }

    public void setSelected(AnActionEvent e, boolean state) {
        Application application = ApplicationManager.getApplication();
        IdentifierHighlighterAppComponent identHighlightComp = application.getComponent(IdentifierHighlighterAppComponent.class);
        identHighlightComp.enablePlugin(state);
    }
}
