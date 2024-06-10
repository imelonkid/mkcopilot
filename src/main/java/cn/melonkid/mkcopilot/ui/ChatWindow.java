package cn.melonkid.mkcopilot.ui;

import javax.swing.*;

public class ChatWindow {
    private JPanel container;
    private JPanel main;
    private JPanel msgView;
    private JTextField chatBoxIn;
    private JLabel msgLb;

    public JPanel getContainer() {
        return container;
    }

    public ChatWindow() {
        chatBoxIn.addActionListener(e -> {
            String msg = chatBoxIn.getText();
            chatBoxIn.setText("");
            System.out.println(msg);
        });
    }
}
