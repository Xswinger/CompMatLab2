package GUI;

import DefaultData.Equations;
import EquationData.EquationData;
import GUI.Interfaces.ChildFrame;
import Methods.NonLinearEquations.ChordMethod;
import Methods.NonLinearEquations.NewtonsMethod;
import Methods.NonLinearEquations.NonlinearEquationMethodInterface;
import Methods.NonLinearEquations.SimpleIterationMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.GroupLayout.Alignment.*;

public class EquationFrame extends JFrame implements ChildFrame {

    //TODO Разобраться подробнее в методе Ньютона (по поводу начального приближения)

    private static final Tools tool = Tools.getInstance();

    private final EquationData data = new EquationData();

    private final String[] methodsNames = new String[] {ChordMethod.METHOD_NAME, NewtonsMethod.METHOD_NAME, SimpleIterationMethod.METHOD_NAME};

    private static final String HEADER_TEXT = "Решение нелинейных уравнений";

    private static final ButtonGroup group = new ButtonGroup();

    private final JComboBox methodsBox = new JComboBox(methodsNames);

    private final JTextField bordersField = new JTextField("a;b");

    private final JTextField accuracyField = new JTextField("x>0");

    private NonlinearEquationMethodInterface solutionMethod;

    private final JFrame parent;

    public EquationFrame(JFrame parent) {
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

    //TODO Добавить вывод сообщения о неверном формате ввода данных
    //TODO Проверить, что реализованы верные начальные условия
    @Override
    public JPanel createFields() {
        JPanel mainWrapper = new JPanel();

        GroupLayout fieldsLayout = new GroupLayout(mainWrapper);
        mainWrapper.setLayout(fieldsLayout);
        fieldsLayout.setAutoCreateGaps(true);
        fieldsLayout.setAutoCreateContainerGaps(true);

        JLabel methodTitle = new JLabel("Выберите метод решения:");
        JLabel bordersTitle = new JLabel("Введите границы начального приближения:");
        JLabel accuracyTitle = new JLabel("Введите точность:");

        methodsBox.setName("methods");
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
                            .addComponent(methodsBox)
                            .addComponent(bordersField)
                            .addComponent(accuracyField)
                    ))
                .addComponent(errorBorderLabel)
                .addComponent(errorAccuracyLabel)
        );

        fieldsLayout.setVerticalGroup(fieldsLayout.createSequentialGroup()
                .addGroup(fieldsLayout.createParallelGroup(BASELINE)
                        .addComponent(methodTitle)
                        .addComponent(methodsBox))
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
    public JPanel createSolvedObjectChoiceComponent() {
        JPanel wrapper = new JPanel();
        JPanel radioButtonsWrapper = new JPanel();
        radioButtonsWrapper.setLayout(new BoxLayout(radioButtonsWrapper, BoxLayout.Y_AXIS));
        radioButtonsWrapper.setAlignmentX(JComponent.LEFT_ALIGNMENT);

        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

        JLabel methodsChoiceTitle = new JLabel("Выберите уравнение:");

        JPanel firstRadioButtonWrapper = tool.createRadioButton(Equations.getFirstEquation());
        JPanel secondRadioButtonWrapper = tool.createRadioButton(Equations.getSecondEquation());
        JPanel thirdRadioButtonWrapper = tool.createRadioButton(Equations.getThirdEquation());
        JPanel fourthRadioButtonWrapper = tool.createRadioButton(Equations.getFourthEquation());

        group.add((AbstractButton) firstRadioButtonWrapper.getComponent(0));
        group.add((AbstractButton) secondRadioButtonWrapper.getComponent(0));
        group.add((AbstractButton) thirdRadioButtonWrapper.getComponent(0));
        group.add((AbstractButton) fourthRadioButtonWrapper.getComponent(0));

        ((AbstractButton) firstRadioButtonWrapper.getComponent(0)).setSelected(true);

        radioButtonsWrapper.add(firstRadioButtonWrapper);
        radioButtonsWrapper.add(secondRadioButtonWrapper);
        radioButtonsWrapper.add(thirdRadioButtonWrapper);
        radioButtonsWrapper.add(fourthRadioButtonWrapper);

        wrapper.add(methodsChoiceTitle);
        wrapper.add(radioButtonsWrapper);

        return wrapper;
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
            if (!checkSelectedEquation()) {

            } else {
                switch (e.getActionCommand()) {
                    case "calculate" -> {
                        data.setLeftBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[0]));
                        data.setRightBorder(Double.parseDouble(bordersField.getText().replace(",", ".").split(";")[1]));
                        data.setAccuracy(Double.parseDouble(accuracyField.getText()));
                        solutionMethod = getSelectionMethod((String) methodsBox.getSelectedItem());
                        solutionMethod.iterationsCycle(data);
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

        private boolean checkSelectedEquation() {
            return true;
        }

        private <T extends NonlinearEquationMethodInterface> NonlinearEquationMethodInterface getSelectionMethod(String methodName) {
            return switch (methodName) {
                case ChordMethod.METHOD_NAME -> new ChordMethod();
                case NewtonsMethod.METHOD_NAME -> new NewtonsMethod();
                case SimpleIterationMethod.METHOD_NAME -> new SimpleIterationMethod();
                default -> null;
            };
        }
    }

    private static class ValidateBorder extends InputVerifier {

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
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (rightBorder != leftBorder) {
                    errorLabel.setVisible(false);
                    return true;
                }
            }
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }

    private static class ValidateAccuracy extends InputVerifier {

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
                    errorLabel.setText(ERROR_MESSAGE);
                    errorLabel.setVisible(true);
                    return false;
                }
                if (value >= 0) {
                    errorLabel.setVisible(false);
                    return true;
                }
            }
            errorLabel.setText(ERROR_MESSAGE);
            errorLabel.setVisible(true);
            return false;
        }
    }
}
