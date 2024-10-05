package net.littlecircleoo.bv2av;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{
    private JTextField text;
    private JPanel panel;
    private JLabel label;
    private JButton button;

    public MainGUI() {
        setTitle("bv2av");
        setSize(257,103);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
        pack();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String in = text.getText();
                if (in == null || in.isEmpty()) return;
                try {
                    String out = Converter.convert(in);
                    text.setText(out);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.toString(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

    private void createUIComponents() {
    }
}
