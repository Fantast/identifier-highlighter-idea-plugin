package com.dpaulenk.idea.highlighter;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.editor.event.CaretAdapter;
import com.intellij.openapi.editor.event.CaretEvent;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.markup.HighlighterLayer;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.Query;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class IdentifierHighlighterEditorComponent extends CaretAdapter implements DocumentListener, Disposable {
    protected enum ELEMENT_TYPE {CLASS, METHOD, FIELD, PARAMETER, LOCAL}

    protected IdentifierHighlighterAppComponent highlighterComponent = null;
    protected IdentifierHighlighterSettings highlighterSettings = null;
    protected Editor editor = null;
    protected ArrayList<RangeHighlighter> highlights = null;
    protected ArrayList<Boolean> forWriting = null;
    protected String currentIdentifier = null;
    protected ELEMENT_TYPE elemType = null;
    protected int startElem = -1;
    protected int currElem = -1;
    protected int declareElem = -1;
    protected boolean ignoreEvents;
    protected boolean identifiersLocked = false;
    protected PsiReferenceComparator psiRefComp = null;

    public IdentifierHighlighterEditorComponent(IdentifierHighlighterAppComponent appComponent, Editor editor) {
        highlighterComponent = appComponent;
        highlighterSettings = highlighterComponent.getSettings();

        ignoreEvents = !highlighterSettings.isPluginEnabled();

        this.editor = editor;
        this.editor.getCaretModel().addCaretListener(this);
        this.editor.getDocument().addDocumentListener(this);
        psiRefComp = new PsiReferenceComparator();
    }

    //CaretListener interface implementation
    public void caretPositionChanged(final CaretEvent ce) {
        //Execute later so we are not searching Psi model while updating it
        //Fixes Idea 7 exception
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                handleCaretPositionChanged(ce);
            }
        });
    }

    protected void handleCaretPositionChanged(CaretEvent ce) {
        if (ignoreEvents || identifiersLocked) {
            return;
        }
        if (editor == null || editor.getProject() == null || editor.getDocument() == null) {
            return;
        }
        PsiFile pFile = PsiDocumentManager.getInstance(editor.getProject()).getPsiFile(editor.getDocument());
        if (pFile == null) {
            return;
        }
        PsiElement pElem = pFile.findElementAt(editor.getCaretModel().getOffset());
        if (!(pElem instanceof PsiIdentifier)) {
            pElem = null;
        }
        if (pElem == null) {
            if (highlights != null) {
                clearState();
            }
            return;
        }
        //We have a pElem
        //Check if different identifier than before
        if (highlights != null) {
            int foundElem = -1;
            TextRange pElemRange = pElem.getTextRange();
            for (int i = 0; i < highlights.size(); i++) {
                RangeHighlighter highlight = highlights.get(i);
                if ((highlight.getStartOffset() == pElemRange.getStartOffset()) && (highlight.getEndOffset() == pElemRange.getEndOffset())) {
                    foundElem = i;
                    break;
                }
            }
            if (foundElem != -1) {
                if (foundElem != currElem) {
                    moveIdentifier(foundElem);
                    startElem = foundElem;
                }
                return;
            } else {
                clearState();
            }
        }
        currentIdentifier = pElem.getText();
        final ArrayList<PsiElement> elems = new ArrayList<PsiElement>();
        PsiReference pRef = pFile.findReferenceAt(editor.getCaretModel().getOffset());
        if (pRef == null) {
            //See if I am a declaration so search for references to me
            PsiElement pElemCtx = pElem.getContext();
            if (pElemCtx instanceof PsiClass) {
                elemType = ELEMENT_TYPE.CLASS;
            } else if (pElemCtx instanceof PsiMethod) {
                elemType = ELEMENT_TYPE.METHOD;
            } else if (pElemCtx instanceof PsiField) {
                elemType = ELEMENT_TYPE.FIELD;
            } else if (pElemCtx instanceof PsiParameter) {
                elemType = ELEMENT_TYPE.PARAMETER;
            } else if (pElemCtx instanceof PsiLocalVariable) {
                elemType = ELEMENT_TYPE.LOCAL;
            }
            Query<PsiReference> q = ReferencesSearch.search(pElemCtx, GlobalSearchScope.fileScope(pFile));
            PsiReference qRefs[] = q.toArray(new PsiReference[0]);
            //Sort by text offset
            Arrays.sort(qRefs, psiRefComp);
            for (PsiReference qRef : qRefs) {
                //Find child PsiIdentifier so highlight is just on it
                PsiElement qRefElem = qRef.getElement();
                PsiIdentifier qRefElemIdent = findChildIdentifier(qRefElem, pElem.getText());
                if (qRefElemIdent == null) {
                    continue;
                }
                //Skip elements from other files
                if (!areSameFiles(pFile, qRefElemIdent.getContainingFile())) {
                    continue;
                }
                //Check if I should be put in list first to keep it sorted by text offset
                if ((declareElem == -1) && (pElem.getTextOffset() <= qRefElemIdent.getTextOffset())) {
                    elems.add(pElem);
                    declareElem = elems.size() - 1;
                }
                elems.add(qRefElemIdent);
            }
            //If haven't put me in list yet, put me in last
            if (declareElem == -1) {
                elems.add(pElem);
                declareElem = elems.size() - 1;
            }
        } else {
            //Resolve to declaration
            PsiElement pRefElem;
            try {
                pRefElem = pRef.resolve();
            } catch (Throwable t) {
                pRefElem = null;
            }
            if (pRefElem != null) {
                if (pRefElem instanceof PsiClass) {
                    elemType = ELEMENT_TYPE.CLASS;
                } else if (pRefElem instanceof PsiMethod) {
                    elemType = ELEMENT_TYPE.METHOD;
                } else if (pRefElem instanceof PsiField) {
                    elemType = ELEMENT_TYPE.FIELD;
                } else if (pRefElem instanceof PsiParameter) {
                    elemType = ELEMENT_TYPE.PARAMETER;
                } else if (pRefElem instanceof PsiLocalVariable) {
                    elemType = ELEMENT_TYPE.LOCAL;
                }
            }
            if (pRefElem != null) {
                PsiIdentifier pRefElemIdent = findChildIdentifier(pRefElem, pElem.getText());
                if (pRefElemIdent != null) {
                    //Search for references to my declaration
                    Query<PsiReference> q = ReferencesSearch.search(pRefElemIdent.getContext(), GlobalSearchScope.fileScope(pFile));
                    PsiReference qRefs[] = q.toArray(new PsiReference[0]);
                    //Sort by text offset
                    Arrays.sort(qRefs, psiRefComp);
                    for (PsiReference qRef : qRefs) {
                        //Find child PsiIdentifier so highlight is just on it
                        PsiElement qRefElem = qRef.getElement();
                        PsiIdentifier qRefElemIdent = findChildIdentifier(qRefElem, pElem.getText());
                        if (qRefElemIdent == null) {
                            continue;
                        }
                        //Skip elements from other files
                        if (!areSameFiles(pFile, qRefElemIdent.getContainingFile())) {
                            continue;
                        }
                        //Check if I should be put in list first to keep it sorted by text offset
                        if ((areSameFiles(pFile, pRefElemIdent.getContainingFile())) && (declareElem == -1) && (pRefElemIdent.getTextOffset() <= qRefElemIdent.getTextOffset())) {
                            elems.add(pRefElemIdent);
                            declareElem = elems.size() - 1;
                        }
                        elems.add(qRefElemIdent);
                    }
                    if (elems.size() == 0) {
                        //Should at least put the original found element at cursor in list
                        //Check if I should be put in list first to keep it sorted by text offset
                        if ((areSameFiles(pFile, pRefElemIdent.getContainingFile())) && (declareElem == -1) && (pRefElemIdent.getTextOffset() <= pElem.getTextOffset())) {
                            elems.add(pRefElemIdent);
                            declareElem = elems.size() - 1;
                        }
                        elems.add(pElem);
                    }
                    //If haven't put me in list yet, put me in last
                    if ((areSameFiles(pFile, pRefElemIdent.getContainingFile())) && (declareElem == -1)) {
                        elems.add(pRefElemIdent);
                        declareElem = elems.size() - 1;
                    }
                }
            } else {
                //No declaration found, so resort to simple string search

//                PsiSearchHelper search = PsiSearchHelper.SERVICE.getInstance(pElem.getProject());
//                final String elementText = pElem.getText();
//                search.processElementsWithWord(new TextOccurenceProcessor() {
//                    @Override
//                    public boolean execute(PsiElement ident, int offsetInElement) {
//                        if (ident.getText().equals(elementText)) {
//                            elems.add(ident);
//                        }
//                        return true;
//                    }
//                }, GlobalSearchScope.fileScope(pFile), elementText, UsageSearchContext.ANY, true);

//                PsiSearchHelper search = PsiSearchHelper.SERVICE.getInstance(pElem.getProject());
//                PsiElement idents[] = search.findIdentifiers(pElem.getText(), GlobalSearchScope.fileScope(pFile), UsageSearchContext.ANY);
//                for (PsiElement ident : idents) {
//                    elems.add(ident);
//                }
            }
        }
        highlights = new ArrayList<RangeHighlighter>();
        forWriting = new ArrayList<Boolean>();
        for (int i = 0; i < elems.size(); i++) {
            PsiElement elem = elems.get(i);
            TextRange range = elem.getTextRange();
            //Verify range is valid against current length of document
            if ((range.getStartOffset() >= editor.getDocument().getTextLength()) || (range.getEndOffset() >= editor.getDocument().getTextLength())) {
                continue;
            }
            boolean forWriting = isForWriting(elem);
            this.forWriting.add(forWriting);
            RangeHighlighter rh;
            if (elem.getTextRange().equals(pElem.getTextRange())) {
                startElem = i;
                currElem = i;
                rh = editor.getMarkupModel().addRangeHighlighter(range.getStartOffset(), range.getEndOffset(), getHighlightLayer(), getActiveHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
                if (highlighterSettings.isShowInMarkerBar()) {
                    rh.setErrorStripeMarkColor(getActiveHighlightColor(forWriting).getBackgroundColor());
                }
            } else {
                rh = editor.getMarkupModel().addRangeHighlighter(range.getStartOffset(), range.getEndOffset(), getHighlightLayer(), getHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
                if (highlighterSettings.isShowInMarkerBar()) {
                    rh.setErrorStripeMarkColor(getHighlightColor(forWriting).getBackgroundColor());
                }
            }
            if (highlighterSettings.isShowInMarkerBar()) {
                rh.setErrorStripeTooltip(currentIdentifier + " [" + i + "]");
            }
            highlights.add(rh);
        }
    }

    protected boolean isForWriting(PsiElement elem) {
        PsiExpression parentExpression = PsiTreeUtil.getParentOfType(elem, PsiExpression.class);
        if (parentExpression != null) {
            return (PsiUtil.isAccessedForWriting(parentExpression));
        } else {
            PsiVariable parentVariable = PsiTreeUtil.getParentOfType(elem, PsiVariable.class);
            if (parentVariable != null) {
                PsiExpression initializer = parentVariable.getInitializer();
                return (initializer != null);
            }
        }
        return (false);
    }

    protected boolean areSameFiles(PsiFile editorFile, PsiFile candidateFile) {
        if ((editorFile == null) && (candidateFile == null)) {
            return (true);
        }
        if (editorFile == null) {
            return (true);
        }
        if (candidateFile == null) {
            return (true);
        }
        String editorFileName = editorFile.getName();
        String candidateFileName = candidateFile.getName();
        if ((editorFileName == null) && (candidateFileName == null)) {
            return (true);
        }
        if (editorFileName == null) {
            return (true);
        }
        if (candidateFileName == null) {
            return (true);
        }
        return (editorFileName.equals(candidateFileName));
    }

    protected PsiIdentifier findChildIdentifier(PsiElement parent, String childText) {
        if ((parent instanceof PsiIdentifier) && (parent.getText().equals(childText))) {
            return ((PsiIdentifier) parent);
        }
        //Packages don't implement getChildren yet they don't throw an exception.  It is caught internally so I can't catch it.
        if (parent instanceof PsiPackage) {
            return (null);
        }
        PsiElement children[] = parent.getChildren();
        if (children.length == 0) {
            return (null);
        }
        for (PsiElement child : children) {
            PsiIdentifier foundElem = findChildIdentifier(child, childText);
            if (foundElem != null) {
                return (foundElem);
            }
        }
        return (null);
    }

    protected boolean isHighlightEnabled() {
        if (elemType == ELEMENT_TYPE.CLASS) {
            return (highlighterSettings.isClassHighlightEnabled());
        } else if (elemType == ELEMENT_TYPE.METHOD) {
            return (highlighterSettings.isMethodHighlightEnabled());
        } else if (elemType == ELEMENT_TYPE.FIELD) {
            return (highlighterSettings.isFieldHighlightEnabled());
        } else if (elemType == ELEMENT_TYPE.PARAMETER) {
            return (highlighterSettings.isParamHighlightEnabled());
        } else if (elemType == ELEMENT_TYPE.LOCAL) {
            return (highlighterSettings.isLocalHighlightEnabled());
        } else {
            return (highlighterSettings.isOtherHighlightEnabled());
        }
    }

    protected TextAttributes getActiveHighlightColor(boolean forWriting) {
        TextAttributes retVal = new TextAttributes();
        if (!isHighlightEnabled()) {
            return (retVal);
        }
        Color c;
        if (elemType == ELEMENT_TYPE.CLASS) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getClassActiveHighlightColor());
        } else if (elemType == ELEMENT_TYPE.METHOD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getMethodActiveHighlightColor());
        } else if (elemType == ELEMENT_TYPE.FIELD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getFieldWriteActiveHighlightColor() : highlighterSettings.getFieldReadActiveHighlightColor());
        } else if (elemType == ELEMENT_TYPE.PARAMETER) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getParamWriteActiveHighlightColor() : highlighterSettings.getParamReadActiveHighlightColor());
        } else if (elemType == ELEMENT_TYPE.LOCAL) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getLocalWriteActiveHighlightColor() : highlighterSettings.getLocalReadActiveHighlightColor());
        } else {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getOtherActiveHighlightColor());
        }
        retVal.setBackgroundColor(c);
        return (retVal);
    }

    protected TextAttributes getHighlightColor(boolean forWriting) {
        TextAttributes retVal = new TextAttributes();
        if (!isHighlightEnabled()) {
            return (retVal);
        }
        Color c;
        if (elemType == ELEMENT_TYPE.CLASS) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getClassHighlightColor());
        } else if (elemType == ELEMENT_TYPE.METHOD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getMethodHighlightColor());
        } else if (elemType == ELEMENT_TYPE.FIELD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getFieldWriteHighlightColor() : highlighterSettings.getFieldReadHighlightColor());
        } else if (elemType == ELEMENT_TYPE.PARAMETER) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getParamWriteHighlightColor() : highlighterSettings.getParamReadHighlightColor());
        } else if (elemType == ELEMENT_TYPE.LOCAL) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getLocalWriteHighlightColor() : highlighterSettings.getLocalReadHighlightColor());
        } else {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getOtherHighlightColor());
        }
        retVal.setBackgroundColor(c);
        return (retVal);
    }

    protected int getHighlightLayer() {
        String highlightLayer = highlighterSettings.getHighlightLayer();
        if (highlightLayer.equals("SELECTION")) {
            return (HighlighterLayer.SELECTION);
        } else if (highlightLayer.equals("ERROR")) {
            return (HighlighterLayer.ERROR);
        } else if (highlightLayer.equals("WARNING")) {
            return (HighlighterLayer.WARNING);
        } else if (highlightLayer.equals("GUARDED_BLOCKS")) {
            return (HighlighterLayer.GUARDED_BLOCKS);
        } else if (highlightLayer.equals("ADDITIONAL_SYNTAX")) {
            return (HighlighterLayer.ADDITIONAL_SYNTAX);
        } else if (highlightLayer.equals("SYNTAX")) {
            return (HighlighterLayer.SYNTAX);
        } else if (highlightLayer.equals("CARET_ROW")) {
            return (HighlighterLayer.CARET_ROW);
        }
        return (HighlighterLayer.ADDITIONAL_SYNTAX);
    }

    //DocumentListener interface implementation
    public void beforeDocumentChange(DocumentEvent de) {
    }

    public void documentChanged(DocumentEvent de) {
        if (ignoreEvents) {
            return;
        }
        caretPositionChanged(null);
    }

    protected void clearState() {
        if (highlights != null) {
            for (RangeHighlighter highlight : highlights) {
                editor.getMarkupModel().removeHighlighter(highlight);
            }
        }
        highlights = null;
        forWriting = null;
        currentIdentifier = null;
        elemType = null;
        startElem = -1;
        currElem = -1;
        declareElem = -1;
        unlockIdentifiers();
    }

    @Override
    public void dispose() {
        clearState();
        editor.getCaretModel().removeCaretListener(this);
        editor.getDocument().removeDocumentListener(this);
        editor = null;
    }

    public void repaint() {
        if (highlights == null) {
            return;
        }
        for (int i = 0; i < highlights.size(); i++) {
            RangeHighlighter rh = highlights.get(i);
            boolean forWriting = this.forWriting.get(i);
            int startOffset = rh.getStartOffset();
            int endOffset = rh.getEndOffset();
            editor.getMarkupModel().removeHighlighter(rh);
            if (i == currElem) {
                rh = editor.getMarkupModel().addRangeHighlighter(startOffset, endOffset, getHighlightLayer(), getActiveHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
                if (highlighterSettings.isShowInMarkerBar()) {
                    rh.setErrorStripeMarkColor(getActiveHighlightColor(forWriting).getBackgroundColor());
                }
            } else {
                rh = editor.getMarkupModel().addRangeHighlighter(startOffset, endOffset, getHighlightLayer(), getHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
                if (highlighterSettings.isShowInMarkerBar()) {
                    rh.setErrorStripeMarkColor(getHighlightColor(forWriting).getBackgroundColor());
                }
            }
            if (highlighterSettings.isShowInMarkerBar()) {
                rh.setErrorStripeTooltip(currentIdentifier + " [" + i + "]");
            }
            highlights.set(i, rh);
        }
    }

    public void enablePlugin(boolean enable) {
        clearState();
        ignoreEvents = !enable;
    }

    public void startIdentifier() {
        if (highlights == null) {
            return;
        }
        moveIdentifier(startElem);
        int offset = highlights.get(currElem).getStartOffset();
        editor.getCaretModel().moveToOffset(offset);
        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
    }

    public void declareIdentifier() {
        if (highlights == null) {
            return;
        }
        if (declareElem == -1) {
            return;
        }
        moveIdentifier(declareElem);
        int offset = highlights.get(currElem).getStartOffset();
        editor.getCaretModel().moveToOffset(offset);
        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
    }

    public void nextIdentifier() {
        if (highlights == null) {
            return;
        }
        int newIndex = currElem + 1;
        if (newIndex == highlights.size()) {
            return;
        }
        moveIdentifier(newIndex);
        int offset = highlights.get(currElem).getStartOffset();
        editor.getCaretModel().moveToOffset(offset);
        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
    }

    public void previousIdentifier() {
        if (highlights == null) {
            return;
        }
        int newIndex = currElem - 1;
        if (newIndex == -1) {
            return;
        }
        moveIdentifier(newIndex);
        int offset = highlights.get(currElem).getStartOffset();
        editor.getCaretModel().moveToOffset(offset);
        editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
    }

    public String getCurrentIdentifier() {
        return (currentIdentifier);
    }

    public void lockIdentifiers() {
        if (identifiersLocked) {
            return;
        }
        identifiersLocked = true;
    }

    public void unlockIdentifiers() {
        if (!identifiersLocked) {
            return;
        }
        identifiersLocked = false;
        //Simulate a caret position change so everything is up-to-date
        caretPositionChanged(null);
    }

    public boolean areIdentifiersLocked() {
        return (identifiersLocked);
    }

    protected void moveIdentifier(int index) {
        try {
            if (currElem != -1) {
                RangeHighlighter rh = highlights.get(currElem);
                boolean forWriting = this.forWriting.get(currElem);
                int startOffset = rh.getStartOffset();
                int endOffset = rh.getEndOffset();
                editor.getMarkupModel().removeHighlighter(rh);
                rh = editor.getMarkupModel().addRangeHighlighter(startOffset, endOffset, getHighlightLayer(), getHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
                if (highlighterSettings.isShowInMarkerBar()) {
                    rh.setErrorStripeMarkColor(getHighlightColor(forWriting).getBackgroundColor());
                    rh.setErrorStripeTooltip(currentIdentifier + " [" + currElem + "]");
                }
                highlights.set(currElem, rh);
            }
            currElem = index;
            RangeHighlighter rh = highlights.get(currElem);
            boolean forWriting = this.forWriting.get(currElem);
            int startOffset = rh.getStartOffset();
            int endOffset = rh.getEndOffset();
            editor.getMarkupModel().removeHighlighter(rh);
            rh = editor.getMarkupModel().addRangeHighlighter(startOffset, endOffset, getHighlightLayer(), getActiveHighlightColor(forWriting), HighlighterTargetArea.EXACT_RANGE);
            if (highlighterSettings.isShowInMarkerBar()) {
                rh.setErrorStripeMarkColor(getActiveHighlightColor(forWriting).getBackgroundColor());
                rh.setErrorStripeTooltip(currentIdentifier + " [" + currElem + "]");
            }
            highlights.set(currElem, rh);
        } catch (Throwable t) {
            //Ignore
        }
    }

    protected class PsiReferenceComparator implements Comparator<PsiReference> {
        public int compare(PsiReference ref1, PsiReference ref2) {
            int offset1 = ref1.getElement().getTextOffset();
            int offset2 = ref2.getElement().getTextOffset();
            return (offset1 - offset2);
        }
    }
}
