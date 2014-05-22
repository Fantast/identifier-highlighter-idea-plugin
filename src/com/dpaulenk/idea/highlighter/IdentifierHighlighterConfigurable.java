package com.dpaulenk.idea.highlighter;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class IdentifierHighlighterConfigurable implements Configurable {

    private final IdentifierHighlighterAppComponent highlighterComponent;
    private IdentifierHighlighterConfiguration configurationForm = null;

    public IdentifierHighlighterConfigurable(IdentifierHighlighterAppComponent highlighterComponent) {
        this.highlighterComponent = highlighterComponent;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return ("Identifier Highlighter Again");
    }

    @Nullable
    @Override
    public String getHelpTopic() {
//        return "doc-ih";
        return null;
    }

    @Override
    public boolean isModified() {
        return configurationForm != null && configurationForm.isModified(highlighterComponent.getSettings());
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (configurationForm == null) {
            configurationForm = new IdentifierHighlighterConfiguration();
        }
        return configurationForm.get_mainPanel();
    }

    @Override
    public void disposeUIResources() {
        configurationForm = null;
    }

    @Override
    public void apply() throws ConfigurationException {
        if (configurationForm != null) {
            configurationForm.storeData(highlighterComponent.getSettings());
            highlighterComponent.update();
        }
    }

    @Override
    public void reset() {
        if (configurationForm != null) {
            configurationForm.loadData(highlighterComponent.getSettings());
        }
    }
}
