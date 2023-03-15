package GUI.Interfaces;

import javax.swing.*;
import java.awt.*;

public interface Frame {

    ImageIcon icon = new ImageIcon("C:\\Users\\itru2\\IdeaProjects\\CompMatLab2\\src\\main\\resources\\icon\\title_icon.png");

    Font HEADER_FONT = new Font("Dialog", Font.PLAIN, 23);

    Font LOWER_LINE_FONT = new Font("Dialog", Font.PLAIN, 15);

    String TITLE = "Лабораторная работа №2";

    void Frame();

    void createFilling();

    JPanel createHeader(String headerText);
}
