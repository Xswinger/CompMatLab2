package GUI;

import DefaultData.EquationsSystem;
import EquationData.EquationData;
import GUI.Interfaces.ChildFrame;
import Methods.NonLinearEquations.NonlinearEquationMethodInterface;
import Methods.NonLinearEquations.SimpleIterationMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class SystemFrame extends JFrame implements ChildFrame {
    private static final Tools tool = Tools.getInstance();

    private final EquationData data = new EquationData();

    private final ButtonGroup group = new ButtonGroup();

    private static final String DEFAULT_METHOD = "Метод простой итерации";

    private static final String HEADER_TEXT = "Решение систем нелинейных уравнений";

    private static final NonlinearEquationMethodInterface SOLUTION_METHOD = new SimpleIterationMethod();

    private final JFrame parent;

    public SystemFrame(JFrame parent) {
        this.parent = parent;
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

    public JPanel createSolvedObjectChoiceComponent() {
        JPanel wrapper = new JPanel();
        JPanel radioButtonsWrapper = new JPanel();
        radioButtonsWrapper.setLayout(new BoxLayout(radioButtonsWrapper, BoxLayout.Y_AXIS));
        radioButtonsWrapper.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JLabel methodsChoiceTitle = new JLabel("Выберите систему уравнений:");

        JPanel firstRadioButtonWrapper = tool.createRadioButton(EquationsSystem.getFirstSystem());
        JPanel secondRadioButtonWrapper = tool.createRadioButton(EquationsSystem.getSecondSystem());
        JPanel thirdRadioButtonWrapper = tool.createRadioButton(EquationsSystem.getThirdSystem());

        group.add((AbstractButton) firstRadioButtonWrapper.getComponent(0));
        group.add((AbstractButton) secondRadioButtonWrapper.getComponent(0));
        group.add((AbstractButton) thirdRadioButtonWrapper.getComponent(0));

        radioButtonsWrapper.add(firstRadioButtonWrapper);
        radioButtonsWrapper.add(secondRadioButtonWrapper);
        radioButtonsWrapper.add(thirdRadioButtonWrapper);

        wrapper.add(methodsChoiceTitle);
        wrapper.add(radioButtonsWrapper);

        return wrapper;
    }

    //TODO Добавить вывод сообщения о неверном формате ввода данных
    //TODO Проверить, что реализованы верные начальные условия

    @Override
    public JPanel createFields() {
        JPanel mainWrapper = new JPanel();

        GroupLayout layout = new GroupLayout(mainWrapper);
        mainWrapper.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        JLabel methodTitle = new JLabel("Метод решения:");
        JLabel bordersTitle = new JLabel("Введите границы начального приближения:");
        JLabel accuracyTitle = new JLabel("Введите точность:");

        JLabel defaultMethod = new JLabel(DEFAULT_METHOD);
        JTextField bordersField = new JTextField();
        JTextField accuracyField = new JTextField();

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(methodTitle)
                        .addComponent(bordersTitle)
                        .addComponent(accuracyTitle)
                )
                .addGroup(layout.createParallelGroup(LEADING)
                        .addComponent(defaultMethod)
                        .addComponent(bordersField)
                        .addComponent(accuracyField)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(methodTitle)
                        .addComponent(defaultMethod))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(bordersTitle)
                        .addComponent(bordersField))
                .addGroup(layout.createParallelGroup(BASELINE)
                        .addComponent(accuracyTitle)
                        .addComponent(accuracyField))
        );

        return mainWrapper;
    }

    @Override
    public JPanel createContent() {
        JPanel content = new JPanel();

        content.add(createFields());
        content.add(createSolvedObjectChoiceComponent());

        return content;
    }

    @Override
    public void createFilling() {
        getContentPane().add(createHeader(HEADER_TEXT), BorderLayout.NORTH);
        getContentPane().add(createContent(), BorderLayout.CENTER);
        getContentPane().add(tool.createControlButtons(new ButtonPressListener()), BorderLayout.SOUTH);
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

    private class ButtonPressListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "calculate" -> SOLUTION_METHOD.iterationsCycle(data);
                case "back" -> {
                    dispose();
                    parent.setVisible(true);
                }
                default -> {
                }
            }
        }
    }
}
