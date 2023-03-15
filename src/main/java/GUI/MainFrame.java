package GUI;

import GUI.Interfaces.ChildFrame;
import GUI.Interfaces.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JDialog implements Frame {

    private ChildFrame childFrame;

    private final JFrame parent = new JFrame(TITLE);

    private static final String HEADER_TEXT = "Выберите меню:";

    @Override
    public void Frame() {
        parent.setIconImage(icon.getImage());
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parent.setResizable(false);

        createFilling();

        parent.pack();
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);
    }

    private JPanel createChoiceButtons() {
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 5, 0));

        ActionListener buttonPressListener = new ButtonPressListener();

        JButton equationMenuRedirect = new JButton("Нелинейные уравнения");
        equationMenuRedirect.setActionCommand("equation");

        JButton systemMenuRedirect = new JButton("Системы нелинейных уравнений");
        systemMenuRedirect.setActionCommand("system");

        equationMenuRedirect.addActionListener(buttonPressListener);
        systemMenuRedirect.addActionListener(buttonPressListener);

        buttonsPanel.add(equationMenuRedirect);
        buttonsPanel.add(systemMenuRedirect);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(buttonsPanel);

        return flow;
    }

    @Override
     public JPanel createHeader(String headerText) {
        JPanel headerPanel = new JPanel();

        JLabel header =  new JLabel(headerText);

        header.setFont(HEADER_FONT);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(header);
        return headerPanel;
    }

    @Override
    public void createFilling() {
        parent.getContentPane().add(createHeader(HEADER_TEXT), BorderLayout.NORTH);
        parent.getContentPane().add(createChoiceButtons(), BorderLayout.SOUTH);
    }

    private class ButtonPressListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "system":
                    childFrame = new SystemFrame(parent);
                    break;
                case "equation":
                    childFrame = new EquationFrame(parent);
                    break;
                default:
                    break;
            }
            childFrame.Frame();
            parent.setVisible(false);
        }
    }

}
