package EquationData;

public class EquationData {
    private double leftBorder;
    private double rightBorder;
    private double accuracy;
    public EquationData() {
    }

    public EquationData(double leftBorder, double rightBorder, double accuracy) {
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.accuracy = accuracy;
    }

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}
