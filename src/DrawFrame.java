import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {
    DrawPanel drawPanel;
    JPanel controlPanel;

    JButton undoButton;
    JButton clearAll;
    JComboBox colorPicker;
    JComboBox shapePicker;
    JCheckBox fillPicker;

    DrawPanel.ShapeType[] shapeTypes = new DrawPanel.ShapeType[] {DrawPanel.ShapeType.LINE, DrawPanel.ShapeType.OVAL, DrawPanel.ShapeType.RECTANGLE};

    Color[] colorArray;

    void frameBuilder() {
        this.colorArray = new Color[] {Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

        this.drawPanel = new DrawPanel(new JLabel());
        this.controlPanel = new JPanel(new FlowLayout());

        this.undoButton = new JButton("Undo");
            this.controlPanel.add(this.undoButton);
        this.clearAll = new JButton("Clear All");
            this.controlPanel.add(this.clearAll);
        this.colorPicker = new JComboBox(colorArray);
            this.controlPanel.add(this.colorPicker);
        this.shapePicker = new JComboBox(shapeTypes);
            this.controlPanel.add(this.shapePicker);
        this.fillPicker = new JCheckBox("Fill?");
            this.controlPanel.add(this.fillPicker);

        this.setLayout(new BorderLayout());
        this.setBounds(100, 100, 550, 550);
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(drawPanel, BorderLayout.CENTER);
        this.setVisible(true);

    }

    DrawFrame() {
        frameBuilder();

    }

    public static void main(String[] args){
        DrawFrame frame = new DrawFrame();
    }

}
