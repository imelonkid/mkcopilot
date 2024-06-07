package cn.melonkid.mkcopilot.ui;

import javax.swing.*;

public class ChatWindow {
    private JPanel container;
    private JPanel main;
    private JTextField msgInput;

    public JPanel getContainer() {
        return container;
    }

    public ChatWindow() {
        msgInput.addActionListener(e -> {
            String msg = msgInput.getText();
            msgInput.setText("");
            System.out.println(msg);
        });
    }
}
