package cn.melonkid.mkcopilot.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

public class ChatWindowFactory implements ToolWindowFactory {

    private ChatWindow chatWindow = new ChatWindow();

    @Override
    public void createToolWindowContent(@NotNull Project project,
                                        @NotNull ToolWindow toolWindow) {
        Content content = ContentFactory.getInstance()
                .createContent(chatWindow.getContainer(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
