package com.dpaulenk.idea.highlighter;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
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
import com.intellij.openapi.project.DumbServiceImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiSearchHelper;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.intellij.psi.search.UsageSearchContext;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.util.Query;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static com.dpaulenk.idea.highlighter.IdentifierHighlighterEditorComponent.ElementType.*;

public class IdentifierHighlighterEditorComponent extends CaretAdapter implements DocumentListener, Disposable {
    protected enum ElementType {
        CLASS, METHOD, FIELD, PARAMETER, LOCAL, OTHER
    }

    protected IdentifierHighlighterAppComponent highlighterComponent = null;
    protected IdentifierHighlighterSettings highlighterSettings = null;
    protected Editor editor = null;
    protected ArrayList<RangeHighlighter> highlights = null;
    protected ArrayList<Boolean> forWriting = null;
    protected String currentIdentifier = null;
    protected ElementType elemType = null;
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
        if (ignoreEvents) {
            return;
        }
        Project project = editor.getProject();
        if (project != null && DumbServiceImpl.getInstance(project).isDumb()) {
            return;
        }
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                updateHighlights();
            }
        });
    }

    private void updateHighlights() {
        if (ignoreEvents || identifiersLocked || editor == null || editor.getProject() == null) {
            return;
        }

        PsiFile file = PsiDocumentManager.getInstance(editor.getProject()).getPsiFile(editor.getDocument());
        if (file == null) {
            return;
        }
        
        int caretOffset = editor.getCaretModel().getOffset();
        
        PsiElement element = file.findElementAt(caretOffset);
        if (element == null) {
            clearState();
            return;
        }
        
        if (searchInAlreadyHighlighted(element)) {
            return;
        }

        currentIdentifier = element.getText();

        ArrayList<PsiElement> usages = null;
        PsiReference reference = file.findReferenceAt(caretOffset);
        if (reference == null) {
            usages = searchFromElement(file, element);
        } else {
            usages = searchFromDeclaration(file, element, reference.resolve());
            if (usages == null) {
                usages = searchSimpleText(file, element);
            }
        }

        createHighlights(element, usages);
    }

    private boolean searchInAlreadyHighlighted(@NotNull PsiElement pElem) {
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
                    moveIdentifier(foundElem, false);
                    startElem = foundElem;
                }
                return true;
            } else {
                clearState();
            }
        }
        return false;
    }
    
    private ArrayList<PsiElement> searchFromElement(@NotNull PsiFile file, @NotNull PsiElement currentElement) {
        //TODO:!!!
        
        TextRange currentRange = currentElement.getTextRange();
        PsiElement maybeDeclarationElement = currentElement;
        while (maybeDeclarationElement != null) {
            ArrayList<PsiElement> usages = searchFromDeclaration(file, currentElement, maybeDeclarationElement);

            currentElement = currentElement.getParent();
            if (!currentElement.getTextRange().equals(currentRange)) {
                break;
            }
        }
        
        return null;
    }
    
    private ArrayList<PsiElement> searchFromDeclaration(@NotNull PsiFile file, @NotNull PsiElement currentElement, @Nullable PsiElement declarationElement) {
        //See if element is a declaration and search for references to it
        if (declarationElement == null) {
            return null;
        }

        PsiFile declarationFile = declarationElement.getContainingFile();

        String elementText = currentElement.getText();
        int elementOffset = currentElement.getTextOffset();
        
        elemType = getElementType(declarationElement);
        
        Query<PsiReference> q = ReferencesSearch.search(declarationElement, GlobalSearchScope.fileScope(file));
        PsiReference refs[] = q.toArray(new PsiReference[0]);
        
        //Sort by text offset
        Arrays.sort(refs, psiRefComp);

        final ArrayList<PsiElement> usages = new ArrayList<PsiElement>();
        for (PsiReference ref : refs) {
            PsiElement refElement = findRefElementWithText(ref.getElement(), elementText);
            if (refElement != null) {
                
                //Skip elements from other files
                if (sameFiles(file, refElement.getContainingFile())) {

                    //Check if declaration should be put in list first to keep it sorted by text offset
                    if (declareElem == -1 && elementOffset <= refElement.getTextOffset() && sameFiles(file, declarationFile)) {
                        usages.add(currentElement);
                        declareElem = usages.size() - 1;
                    }
                    usages.add(refElement);
                }
            }
        }
       
        //If haven't put declaration in the list yet, put it last
        if (declareElem == -1 && sameFiles(file, declarationFile)) {
            usages.add(currentElement);
            declareElem = usages.size() - 1;
        }
        
        return usages;
    }

    protected PsiElement findRefElementWithText(@NotNull PsiElement refElement, String elementText) {
        if (refElement.getText().equals(elementText)) {
            return refElement;
        }

        //turn it off for now, so we only searching the top-level reference element
        if (1 == 2) {
            //Packages don't implement getChildren yet they don't throw an exception.  It is caught internally so I can't catch it.
            if (refElement instanceof PsiPackage) {
                return null;
            }

            PsiElement children[] = refElement.getChildren();
            if (children.length == 0) {
                return null;
            }
            for (PsiElement child : children) {
                PsiElement foundElem = findRefElementWithText(child, elementText);
                if (foundElem != null) {
                    return foundElem;
                }
            }
        }
        return null;
    }

    protected PsiIdentifier findChildIdentifier(PsiElement refElement, String elementText) {
        if ((refElement instanceof PsiIdentifier) && (refElement.getText().equals(elementText))) {
            return (PsiIdentifier) refElement;
        }
        //Packages don't implement getChildren yet they don't throw an exception.  It is caught internally so I can't catch it.
        if (refElement instanceof PsiPackage) {
            return null;
        }
        PsiElement children[] = refElement.getChildren();
        if (children.length == 0) {
            return null;
        }
        for (PsiElement child : children) {
            PsiIdentifier foundElem = findChildIdentifier(child, elementText);
            if (foundElem != null) {
                return foundElem;
            }
        }
        return null;
    }

    private ElementType getElementType(PsiElement element) {
        if (element instanceof PsiClass) {
            return CLASS;
        } else if (element instanceof PsiMethod) {
            return METHOD;
        } else if (element instanceof PsiField) {
            return FIELD;
        } else if (element instanceof PsiParameter) {
            return PARAMETER;
        } else if (element instanceof PsiLocalVariable) {
            return LOCAL;
        } else {
            return OTHER;
        }
    }

    private ArrayList<PsiElement> searchSimpleText(@NotNull PsiFile file, @NotNull PsiElement element) {
        elemType = OTHER;
        
        final ArrayList<PsiElement> usages = new ArrayList<PsiElement>();

        //No declaration found, so resort to simple string search
        PsiSearchHelper search = PsiSearchHelper.SERVICE.getInstance(element.getProject());
        final String elementText = element.getText();
        search.processElementsWithWord(new TextOccurenceProcessor() {
            @Override
            public boolean execute(PsiElement ident, int offsetInElement) {
                if (ident.getText().equals(elementText)) {
                    usages.add(ident);
                }
                return true;
            }
        }, GlobalSearchScope.fileScope(file), elementText, UsageSearchContext.ANY, true);

        return usages;
    }

    private void createHighlights(PsiElement element, ArrayList<PsiElement> usages) {
        if (usages == null) {
            return;
        }
        
        highlights = new ArrayList<RangeHighlighter>();
        forWriting = new ArrayList<Boolean>();
        for (int i = 0; i < usages.size(); i++) {
            PsiElement elem = usages.get(i);
            TextRange range = elem.getTextRange();
            //Verify range is valid against current length of document
            if ((range.getStartOffset() >= editor.getDocument().getTextLength()) || (range.getEndOffset() >= editor.getDocument().getTextLength())) {
                continue;
            }
            boolean forWriting = isForWriting(elem);
            this.forWriting.add(forWriting);
            RangeHighlighter rh;
            if (elem.getTextRange().equals(element.getTextRange())) {
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

    protected boolean isForWriting(PsiElement element) {
        PsiExpression parentExpression = PsiTreeUtil.getParentOfType(element, PsiExpression.class);
        if (parentExpression != null) {
            return PsiUtil.isAccessedForWriting(parentExpression);
        } else {
            PsiVariable parentVariable = PsiTreeUtil.getParentOfType(element, PsiVariable.class);
            if (parentVariable != null) {
                PsiExpression initializer = parentVariable.getInitializer();
                return initializer != null;
            }
        }
        return false;
    }

    protected boolean sameFiles(@NotNull PsiFile editorFile, @Nullable PsiFile candidateFile) {
        return editorFile == candidateFile ||
               (candidateFile != null && editorFile.getName().equals(candidateFile.getName()));
    }

    protected TextAttributes getActiveHighlightColor(boolean forWriting) {
        TextAttributes retVal = new TextAttributes();
        if (!isHighlightEnabled()) {
            return retVal;
        }
        Color c;
        if (elemType == CLASS) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getClassActiveHighlightColor());
        } else if (elemType == METHOD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getMethodActiveHighlightColor());
        } else if (elemType == FIELD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getFieldWriteActiveHighlightColor() : highlighterSettings.getFieldReadActiveHighlightColor());
        } else if (elemType == PARAMETER) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getParamWriteActiveHighlightColor() : highlighterSettings.getParamReadActiveHighlightColor());
        } else if (elemType == LOCAL) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getLocalWriteActiveHighlightColor() : highlighterSettings.getLocalReadActiveHighlightColor());
        } else {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getOtherActiveHighlightColor());
        }
        retVal.setBackgroundColor(c);
        return retVal;
    }

    protected TextAttributes getHighlightColor(boolean forWriting) {
        TextAttributes retVal = new TextAttributes();
        if (!isHighlightEnabled()) {
            return retVal;
        }
        Color c;
        if (elemType == CLASS) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getClassHighlightColor());
        } else if (elemType == METHOD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getMethodHighlightColor());
        } else if (elemType == FIELD) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getFieldWriteHighlightColor() : highlighterSettings.getFieldReadHighlightColor());
        } else if (elemType == PARAMETER) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getParamWriteHighlightColor() : highlighterSettings.getParamReadHighlightColor());
        } else if (elemType == LOCAL) {
            c = IdentifierHighlighterConfiguration.getColorFromString(forWriting ? highlighterSettings.getLocalWriteHighlightColor() : highlighterSettings.getLocalReadHighlightColor());
        } else {
            c = IdentifierHighlighterConfiguration.getColorFromString(highlighterSettings.getOtherHighlightColor());
        }
        retVal.setBackgroundColor(c);
        return retVal;
    }

    protected boolean isHighlightEnabled() {
        if (elemType == CLASS) {
            return highlighterSettings.isClassHighlightEnabled();
        } else if (elemType == METHOD) {
            return highlighterSettings.isMethodHighlightEnabled();
        } else if (elemType == FIELD) {
            return highlighterSettings.isFieldHighlightEnabled();
        } else if (elemType == PARAMETER) {
            return highlighterSettings.isParamHighlightEnabled();
        } else if (elemType == LOCAL) {
            return highlighterSettings.isLocalHighlightEnabled();
        } else {
            return highlighterSettings.isOtherHighlightEnabled();
        }
    }

    protected int getHighlightLayer() {
        String highlightLayer = highlighterSettings.getHighlightLayer();
        if (highlightLayer.equals("SELECTION")) {
            return HighlighterLayer.SELECTION;
        } else if (highlightLayer.equals("ERROR")) {
            return HighlighterLayer.ERROR;
        } else if (highlightLayer.equals("WARNING")) {
            return HighlighterLayer.WARNING;
        } else if (highlightLayer.equals("GUARDED_BLOCKS")) {
            return HighlighterLayer.GUARDED_BLOCKS;
        } else if (highlightLayer.equals("ADDITIONAL_SYNTAX")) {
            return HighlighterLayer.ADDITIONAL_SYNTAX;
        } else if (highlightLayer.equals("SYNTAX")) {
            return HighlighterLayer.SYNTAX;
        } else if (highlightLayer.equals("CARET_ROW")) {
            return HighlighterLayer.CARET_ROW;
        }
        return HighlighterLayer.ADDITIONAL_SYNTAX;
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
        moveIdentifier(startElem, true);
    }

    public void declareIdentifier() {
        moveIdentifier(declareElem, true);
    }

    public void nextIdentifier() {
        moveIdentifier(currElem + 1, true);
    }

    public void previousIdentifier() {
        moveIdentifier(currElem - 1, true);
    }

    public void lockIdentifiers() {
        identifiersLocked = true;
    }

    public void unlockIdentifiers() {
        if (identifiersLocked) {
            identifiersLocked = false;
            //Simulate a caret position change so everything is up-to-date
            caretPositionChanged(null);
        }
    }

    public boolean areIdentifiersLocked() {
        return identifiersLocked;
    }

    private void moveIdentifier(int index, boolean moveCaret) {
        if (highlights == null || index < 0 || index >= highlights.size()) {
            return;
        }
        
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
        
        if (moveCaret && currElem >= 0 && currElem < highlights.size()) {
            int offset = highlights.get(currElem).getStartOffset();
            editor.getCaretModel().moveToOffset(offset);
            editor.getScrollingModel().scrollToCaret(ScrollType.CENTER);
        }
    }

    protected class PsiReferenceComparator implements Comparator<PsiReference> {
        public int compare(PsiReference ref1, PsiReference ref2) {
            int offset1 = ref1.getElement().getTextOffset();
            int offset2 = ref2.getElement().getTextOffset();
            return offset1 - offset2;
        }
    }
}
