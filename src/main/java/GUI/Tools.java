package GUI;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Tools {

    private static Tools instance = null;

    public static Tools getInstance() {
        if (instance == null) {
            instance = new Tools();
        }
        return instance;
    }

    public JPanel createRadioButton(String equation) {
        TeXFormula formula = new TeXFormula(equation);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 10);
        JRadioButton button = new JRadioButton();

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setAlignmentX(JComponent.RIGHT_ALIGNMENT);
        buttonWrapper.add(button);
        buttonWrapper.add(new JLabel(icon));

        return buttonWrapper;
    }

    public JPanel createControlButtons(ActionListener buttonsListener) {
        JPanel buttons = new JPanel(new GridLayout(1, 2, 5, 0));

        JButton calculateButton = new JButton("Вычислить");
        calculateButton.setActionCommand("calculate");

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

}
