package com.dpaulenk.idea.highlighter;

import com.intellij.openapi.components.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorFactoryEvent;
import com.intellij.openapi.editor.event.EditorFactoryListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

@State(
    name = "IdentifierHighlighterAgainAppComponent",
    storages = {
        @Storage(id = "IdentifierHighlighterAgainAppComponent", file = StoragePathMacros.APP_CONFIG + "/IdentifierHighlighterAgain.xml")
    }
)
public class IdentifierHighlighterAppComponent implements ApplicationComponent, EditorFactoryListener, PersistentStateComponent<IdentifierHighlighterSettings> {

    private IdentifierHighlighterSettings settings = new IdentifierHighlighterSettings();
    
    protected HashMap<Editor, IdentifierHighlighterEditorComponent> editorHighlighters = null;

    @NotNull
    public String getComponentName() {
        return ("IdentifierHighlighterAgainAppComponent");
    }

    public void initComponent() {
        editorHighlighters = new HashMap<Editor, IdentifierHighlighterEditorComponent>();
        EditorFactory.getInstance().addEditorFactoryListener(this);
    }

    public void disposeComponent() {
        EditorFactory.getInstance().removeEditorFactoryListener(this);
        for (IdentifierHighlighterEditorComponent value : editorHighlighters.values()) {
            value.dispose();
        }
        editorHighlighters.clear();
    }

    public void editorCreated(@NotNull EditorFactoryEvent efe) {
        Editor editor = efe.getEditor();
        if (editor.getProject() == null) {
            return;
        }

        editorHighlighters.put(editor, new IdentifierHighlighterEditorComponent(this, editor));
    }

    public void editorReleased(@NotNull EditorFactoryEvent efe) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.remove(efe.getEditor());
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.dispose();
    }

    @Override
    public void loadState(IdentifierHighlighterSettings state) {
        settings = state;
    }

    @Nullable
    @Override
    public IdentifierHighlighterSettings getState() {
        return settings;
    }


    public IdentifierHighlighterSettings getSettings() {
        return settings;
    }

    //Actions
    public void update() {
        boolean enabled = settings.isPluginEnabled();
        
        //Update all highlighters
        for (IdentifierHighlighterEditorComponent value : editorHighlighters.values()) {
            value.enablePlugin(enabled);
            value.repaint();
        }
    }

    public void enablePlugin(boolean enable) {
        settings.setPluginEnabled(enable);
        update();
    }

    public void startIdentifier(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.startIdentifier();
    }

    public void declareIdentifier(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.declareIdentifier();
    }

    public void nextIdentifier(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.nextIdentifier();
    }

    public void previousIdentifier(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.previousIdentifier();
    }

    public void lockIdentifiers(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.lockIdentifiers();
    }

    public void unlockIdentifiers(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        if (editorHighlighter == null) {
            return;
        }
        editorHighlighter.unlockIdentifiers();
    }

    public boolean areIdentifiersLocked(Editor editor) {
        IdentifierHighlighterEditorComponent editorHighlighter = editorHighlighters.get(editor);
        return editorHighlighter != null && editorHighlighter.areIdentifiersLocked();
    }
}
