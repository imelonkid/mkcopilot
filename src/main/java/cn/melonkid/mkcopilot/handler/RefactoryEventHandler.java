package cn.melonkid.mkcopilot.handler;

import cn.melonkid.mkcopilot.constants.SystemConstants;
import cn.melonkid.mkcopilot.util.UIComponentUtil;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiMethod;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

import javax.swing.*;
import java.awt.event.MouseEvent;

import static cn.melonkid.mkcopilot.constants.SystemConstants.MK_COPILOT_CHATWINDOW_LABEL_NAME;

public class RefactoryEventHandler implements Function2<MouseEvent, Editor, Unit> {

    private final PsiMethod method;

    public RefactoryEventHandler(PsiMethod method) {
        this.method = method;
    }

    @Override
    public Unit invoke(MouseEvent mouseEvent, Editor editor) {
        var toolWindow = ToolWindowManager.getInstance(method.getProject())
                .getToolWindow(SystemConstants.MK_COPILOT_TEXT);

        JPanel container = (JPanel) toolWindow.getComponent();
        JPanel main = (JPanel) container.getComponent(0);
        JLabel msglb = (JLabel) UIComponentUtil.findComponentByFieldName(main, MK_COPILOT_CHATWINDOW_LABEL_NAME);
        String selectedText = editor.getSelectionModel().getSelectedText();

        assert msglb != null;
        msglb.setText(selectedText);
        return Unit.INSTANCE;
    }


}
