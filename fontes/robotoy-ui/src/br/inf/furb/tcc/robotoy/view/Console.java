package br.inf.furb.tcc.robotoy.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public final class Console extends JPanel {

    public static Console SINGLETON = new Console();
    private final JTextPane console;

    private Console() {
        setLayout(new BorderLayout());

        console = new JTextPane();
        console.setFont(new Font("Consolas", Font.PLAIN, 12));
        console.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(console);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void clear() {
        console.setText("");
    }

    public void log(String message) {
        StringBuilder builder = new StringBuilder(console.getText());
        builder.append(message);
        builder.append("\n");
        console.setText(builder.toString());
    }

}
