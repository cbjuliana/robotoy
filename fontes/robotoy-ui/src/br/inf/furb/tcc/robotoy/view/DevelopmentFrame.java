package br.inf.furb.tcc.robotoy.view;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public final class DevelopmentFrame extends JFrame {

    public static final DevelopmentFrame SINGLETON = new DevelopmentFrame();

    private DevelopmentFrame() {
        setTitle("Robotoy");
        setLookAndFeel();
        setLayout(new BorderLayout());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (FileManagerStatusBar.SINGLETON.isModified()) {
                    boolean discardChanges = Dialogs.showDiscardSourceCode();
                    if (!discardChanges) {
                        Editor.SINGLETON.setFocus();
                        return;
                    }
                }
                System.exit(0);
            }
        });

        List<Image> images = new ArrayList<Image>();
        images.add(new ImageIcon(ClassLoader.getSystemResource("img/robot_16px.png")).getImage());
        images.add(new ImageIcon(ClassLoader.getSystemResource("img/robot_32px.png")).getImage());
        images.add(new ImageIcon(ClassLoader.getSystemResource("img/robot_48px.png")).getImage());
        setIconImages(images);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, Editor.SINGLETON, Console.SINGLETON);
        splitPane.setResizeWeight(0.75);

        add(Toolbar.SINGLETON, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(FileManagerStatusBar.SINGLETON, BorderLayout.SOUTH);
    }

	private void setLookAndFeel() {
		try {
			final WindowsLookAndFeel lookAndFeel = new WindowsLookAndFeel();
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (Exception e) {
			Console.SINGLETON.log(Constants.MESSAGE_CANNOT_CHANGE_THEME);
		}
	}

}
