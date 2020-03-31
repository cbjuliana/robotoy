package br.inf.furb.tcc.robotoy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

class EditorPane extends JPanel {

    private final JTextArea editor;
    private final JTextArea lineNumbers;

    public EditorPane() {
        setLayout(new BorderLayout());

        Font font = new Font("Consolas", Font.PLAIN, 14);
        editor = new JTextArea();
        editor.setFont(font);
        editor.setTabSize(4);
        lineNumbers = new JTextArea("1");
        lineNumbers.setBackground(Color.LIGHT_GRAY);
        lineNumbers.setEditable(false);
        lineNumbers.setFont(font);

        editor.getDocument().addDocumentListener(new DocumentListener() {

            public String getText() {
                int caretPosition = editor.getDocument().getLength();
                Element root = editor.getDocument().getDefaultRootElement();
                int i = 1;
                StringBuilder builder = new StringBuilder();
                for (; i < root.getElementIndex(caretPosition) + 1; i++) {
                    builder.append(i).append(System.getProperty("line.separator"));
                }
                builder.append(i);
                return builder.toString();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lineNumbers.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lineNumbers.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lineNumbers.setText(getText());
            }

        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(editor);
        scrollPane.setRowHeaderView(lineNumbers);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public synchronized void addKeyListener(KeyListener keyListener) {
        editor.addKeyListener(keyListener);
    }

    public String getText() {
        return editor.getText();
    }

    public void setText(String text) {
        editor.setText(text);
    }

    public boolean setFocus() {
        return editor.requestFocusInWindow();
    }
}
