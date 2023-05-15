package GUI;

import GUI.Interfaces.Frame;
import SolutionsData.EquationData;
import SolutionsData.SystemData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolutionFrame extends JFrame implements Frame {

    private static final String HEADER_TEXT = "Решение";

    private EquationData equationData;

    private String[] solution;

    private final JFrame parentFrame;

    private SystemData systemData;

    public SolutionFrame(EquationData data, JFrame parent) {
        this.equationData = data;
        this.parentFrame = parent;
    }

    public SolutionFrame(SystemData data, JFrame parent) {
        this.systemData = data;
        this.parentFrame = parent;
    }

    @Override
    public void Frame() {
        setTitle(TITLE);
        setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        createFilling();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createControlButtons(ActionListener buttonsListener) {
        JPanel buttons = new JPanel(new GridLayout(1, 2, 5, 0));

        JButton calculateButton = new JButton("График");
        calculateButton.setActionCommand("graph");

        JButton backButton = new JButton("Назад");
        backButton.setActionCommand("back");

        calculateButton.addActionListener(buttonsListener);
        backButton.addActionListener(buttonsListener);

        buttons.add(calculateButton);
        buttons.add(backButton);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow.add(buttons);

        return flow;
    }

    @Override
    public void createFilling() {
        getContentPane().add(createHeader(HEADER_TEXT), BorderLayout.NORTH);
        if (solution == null) {
            getContentPane().add(createError(), BorderLayout.CENTER);
        } else {
            getContentPane().add(createContent(), BorderLayout.CENTER);
        }
        getContentPane().add(createControlButtons(new ButtonPressListener()), BorderLayout.SOUTH);
    }

    private JPanel createError() {
        JPanel content = new JPanel();
        JLabel error = new JLabel("Достаточное условие не выполнено");

        content.add(error);
        return content;
    }

    private JPanel createContent() {
        JPanel content = new JPanel();

        String[] columnNames = {"Номер итерации", "Корень", "Значение функции"};

        JPanel iterationNumber = new JPanel();
        iterationNumber.setLayout(new BoxLayout(iterationNumber, BoxLayout.Y_AXIS));
        iterationNumber.add(new JLabel(columnNames[0] + ": "));
        iterationNumber.add(new JLabel(solution[0]));

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.add(new JLabel(columnNames[1] + ": "));
        root.add(new JLabel(solution[1]));

        JPanel value = new JPanel();
        value.setLayout(new BoxLayout(value, BoxLayout.Y_AXIS));
        value.add(new JLabel(columnNames[2] + ": "));
        value.add(new JLabel(solution[2]));

        content.add(iterationNumber);
        content.add(root);
        content.add(value);

        return content;
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

    public String[] getSolution() {
        return solution;
    }

    public void setSolution(String[] solution) {
        this.solution = solution;
    }

    private class ButtonPressListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "graph" -> {
                    if (equationData != null) {
                        new GraphFrame(equationData).Frame();
                    } else {
                        new GraphFrame(systemData).Frame();
                    }
                }
                case "back" -> {
                    dispose();
                    parentFrame.setVisible(true);
                }
                default -> {
                }
            }
        }
    }
}
