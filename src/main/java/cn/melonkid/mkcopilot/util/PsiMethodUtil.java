package cn.melonkid.mkcopilot.util;

import com.intellij.codeInsight.hints.InlayHintsUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PsiMethodUtil {

    public static List<PsiMethod> getPesiMethods(Editor editor) {
        return ApplicationManager.getApplication().runReadAction((Computable<List<PsiMethod>>) () -> {
            List<PsiMethod> psiMethods = new ArrayList<>();
            Project project = Optional.ofNullable(editor.getProject()).orElseThrow(() -> new IllegalArgumentException("Editor must have a project"));

            PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
            if (psiFile != null) {
                processPsiElements(psiMethods, psiFile);
            }

            return psiMethods;
        });
    }

    private static void processPsiElements(List<PsiMethod> psiMethods, PsiFile file) {
        SyntaxTraverser<PsiElement> traverser = SyntaxTraverser.psiTraverser(file);
        traverser.forEach(element -> {
            if (element instanceof PsiMethod && InlayHintsUtils.isFirstInLine(element)) {
                psiMethods.add((PsiMethod) element);
            }
        });
    }

}