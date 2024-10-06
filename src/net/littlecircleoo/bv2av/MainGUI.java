package net.littlecircleoo.bv2av;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class MainGUI extends JFrame{
    private JTextField text;
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private static final ResourceBundle lang = ResourceBundle.getBundle("lang");

    public MainGUI() {

        setTitle(lang.getString("ui.main_title"));

        final Dimension size = new Dimension(272, 118);
        setSize(size);
        setMinimumSize(size);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);

        setContentPane(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();

        button.addActionListener(e -> {
            String in = text.getText();
            if (in == null || in.isEmpty()) return;
            try {
                String out = Converter.convert(in);
                text.setText(out);
            } catch (Exception ex) {
                String message = switch (ex.getMessage()) {
                    case "av cannot be empty"                        -> lang.getString("error.av_empty");
                    case "bv cannot be empty"                        -> lang.getString("error.bv_empty");
                    case "id cannot be empty"                        -> lang.getString("error.id_empty");
                    case "av must be in range [1, 2251799813685248]" -> lang.getString("error.av_range");
                    case "bv must be 12 characters long"             -> lang.getString("error.bv_length");
                    case "bv must start with BV1"                    -> lang.getString("error.bv_prefix");
                    case "id must be a valid aid or bvid"            -> lang.getString("error.id_invalid");
                    default -> ex.toString();
                };
                JOptionPane.showMessageDialog(null, message, lang.getString("ui.error_title"), JOptionPane.ERROR_MESSAGE);
            }
        });

        text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });

        setVisible(true);
    }
}
