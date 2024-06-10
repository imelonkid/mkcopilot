package cn.melonkid.mkcopilot.hint;

import cn.melonkid.mkcopilot.handler.RefactoryEventHandler;
import cn.melonkid.mkcopilot.util.PsiMethodUtil;
import com.intellij.codeInsight.codeVision.*;
import com.intellij.codeInsight.codeVision.ui.model.ClickableTextCodeVisionEntry;
import com.intellij.codeInsight.hints.InlayHintsUtils;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiMethod;
import kotlin.Pair;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 重构hint工厂
 */
public class AssistHintFactory implements CodeVisionProvider {

    private static final String ASSIST_HINT_ID = "AssistHint";

    private static final String ASSIST_HINT_TEXT = "Assist Hint";

    @NotNull
    @Override
    public CodeVisionAnchorKind getDefaultAnchor() {
        return CodeVisionAnchorKind.Top;
    }

    @NotNull
    @Override
    public String getId() {
        return ASSIST_HINT_ID;
    }

    @Nls
    @NotNull
    @Override
    public String getName() {
        return ASSIST_HINT_TEXT;
    }

    @NotNull
    @Override
    public List<CodeVisionRelativeOrdering> getRelativeOrderings() {
        return List.of(CodeVisionRelativeOrdering.CodeVisionRelativeOrderingFirst.INSTANCE);
    }


    @NotNull
    @Override
    public CodeVisionState computeCodeVision(@NotNull Editor editor, Object uiData) {
        List<PsiMethod> methods = PsiMethodUtil.getPesiMethods(editor);

        List<Pair<TextRange, CodeVisionEntry>> lenses = new ArrayList<>();
        for (PsiMethod method : methods) {
            TextRange textRange = InlayHintsUtils.INSTANCE.getTextRangeWithoutLeadingCommentsAndWhitespaces(method);
            int startOffset = textRange.getStartOffset();

            // 创建第一个hint 重构
            String refactoredText = "重构";
            String refactoredName = "refactoredHint";
            TextRange refactoredRange = new TextRange(startOffset, startOffset + refactoredText.length());
            RefactoryEventHandler refactoryEventHandler = new RefactoryEventHandler(method);
            CodeVisionEntry refactoredEntry = new ClickableTextCodeVisionEntry(refactoredText, refactoredName, refactoryEventHandler,
                    null, getName(), getName(), List.of());
            lenses.add(new Pair<>(refactoredRange, refactoredEntry));

            // 创建第二个hint 找BUG
            String bugText = "找BUG";
            String bugHintName = "bugHintName";
            TextRange bugRange = refactoredRange.shiftRight(refactoredText.length() + 1);
            CodeVisionEntry bugEntry = new ClickableTextCodeVisionEntry(bugText, bugHintName, null,
                    null, getName(), getName(), List.of());
            lenses.add(new Pair<>(bugRange, bugEntry));
        }

        return new CodeVisionState.Ready(lenses);
    }

    @Override
    public Object precomputeOnUiThread(@NotNull Editor editor) {
        return null;
    }
}
