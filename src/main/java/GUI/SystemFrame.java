package GUI;

import DefaultData.EquationsSystem;
import EquationData.EquationData;
import GUI.Interfaces.ChildFrame;
import Methods.AbstractClasses.SimpleIterationMethodAbs;
import Methods.NonLinearEquations.NonlinearEquationMethodInterface;
import Methods.NonLinearEquations.SimpleIterationMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class SystemFrame extends JFrame implements ChildFrame {
    private static final Tools tool = Tools.getInstance();

    private final EquationData data = new EquationData();

    private final ButtonGroup group = new ButtonGroup();

    private static final String DEFAULT_METHOD = "Метод простой итерации";

    private static final String HEADER_TEXT = "Решение систем нелинейных уравнений";

    private final JTextField bordersField = new JTextField("a;b");

    private final JTextField accuracyField = new JTextField("x>0");

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

        ((AbstractButton) firstRadioButtonWrapper.getComponent(0)).setSelected(true);

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

        GroupLayout fieldsLayout = new GroupLayout(mainWrapper);
        mainWrapper.setLayout(fieldsLayout);
        fieldsLayout.setAutoCreateGaps(true);
        fieldsLayout.setAutoCreateContainerGaps(true);

        JLabel methodTitle = new JLabel("Метод решения:");
        JLabel bordersTitle = new JLabel("Введите границы начального приближения:");
        JLabel accuracyTitle = new JLabel("Введите точность:");

        JLabel defaultMethod = new JLabel(DEFAULT_METHOD);

        bordersField.setName("borders");
        accuracyField.setName("accuracy");

        JLabel errorBorderLabel = new JLabel("");
        errorBorderLabel.setForeground(Color.RED);
        errorBorderLabel.setVisible(false);
        ValidateBorder validateBorder = new ValidateBorder(errorBorderLabel);

        JLabel errorAccuracyLabel = new JLabel("");
        errorAccuracyLabel.setForeground(Color.RED);
        errorAccuracyLabel.setVisible(false);
        ValidateAccuracy validateAccuracy = new ValidateAccuracy(errorAccuracyLabel);

        bordersField.setInputVerifier(validateBorder);
        accuracyField.setInputVerifier(validateAccuracy);

        fieldsLayout.setHorizontalGroup(fieldsLayout.createParallelGroup(CENTER)
                .addGroup(fieldsLayout.createSequentialGroup()
                        .addGroup(fieldsLayout.createParallelGroup(LEADING)
                                .addComponent(methodTitle)
                                .addComponent(bordersTitle)
                                .addComponent(accuracyTitle)
                        )
                        .addGroup(fieldsLayout.createParallelGroup(LEADING)
                                .addComponent(defaultMethod)
                                .addComponent(bordersField)
                                .addComponent(accuracyField)
                        ))
                .addComponent(errorBorderLabel)
                .addComponent(errorAccuracyLabel)
        );

        fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(methodTitle)
                        .addComponent(defaultMethod))
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(bordersTitle)
                        .addComponent(bordersField))
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(accuracyTitle)
                        .addComponent(accuracyField))
                .addComponent(errorBorderLabel)
                .addComponent(errorAccuracyLabel)
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
                case "calculate" -> {
                    data.setLeftBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[0]));
                    data.setRightBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[1]));
                    data.setAccuracy(Double.parseDouble(accuracyField.getText()));
                    SOLUTION_METHOD.iterationsCycle(data);
                }
                case "back" -> {
                    dispose();
                    parent.setVisible(true);
                }
                default -> {
                }
            }
        }
    }

    private class ValidateBorder extends InputVerifier {

        private final JLabel errorLabel;

        private static final String ERROR_MESSAGE = "Введено неверное значение границы";

        public ValidateBorder(JLabel errorLabel) {
            this.errorLabel = errorLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText();
            if (text != null && !text.trim().equals("")) {
                String[] values = text.replace(",", ".").split(";");
                double rightBorder;
                double leftBorder;
                try {
                    rightBorder = Double.parseDouble(values[0]);
                    leftBorder = Double.parseDouble(values[1]);
                } catch (NumberFormatException e) {
                    tool.calculateButton.setEnabled(false);
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (rightBorder != leftBorder) {
                    errorLabel.setVisible(false);
                }
                if (accuracyField.isValidateRoot()) {
                    tool.calculateButton.setEnabled(true);
                }
                return true;
            }
            tool.calculateButton.setEnabled(false);
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }

    private class ValidateAccuracy extends InputVerifier {

        private final JLabel errorLabel;

        private static final String ERROR_MESSAGE = "Введено неверное значение точности";

        public ValidateAccuracy(JLabel errorLabel) {
            this.errorLabel = errorLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            JTextField textField = (JTextField) input;
            String text = textField.getText();
            if (text != null && !text.trim().equals("")) {
                double value;
                try {
                    value = Double.parseDouble(text.replace(",", "."));
                } catch (NumberFormatException e) {
                    tool.calculateButton.setEnabled(false);
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (value >= 0) {
                    errorLabel.setVisible(false);
                }
                if (bordersField.isValidateRoot()) {
                    tool.calculateButton.setEnabled(true);
                }
                return true;
            }
            tool.calculateButton.setEnabled(false);
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }
}
