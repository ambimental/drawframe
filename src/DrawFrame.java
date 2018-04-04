import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
    DrawFrame.java
    Authored John Georgvich; help from Alexander Day and Evan Black
    Due 03042018
    Implementation of Drawpanel with controls, uses actionlistener
 */
public class DrawFrame extends JFrame implements ActionListener {
    //  specify drawpanel and control panel (controls added below)
    DrawPanel drawPanel;
    JPanel controlPanel;

    //  controls as per requirements doc, implementation below
    JButton undoButton;
    JButton clearAll;
    JComboBox colorPicker;
    JComboBox shapePicker;
    JCheckBox fillPicker;

    //  array of shapetypes for combobox
    DrawPanel.ShapeType[] shapeTypes = new DrawPanel.ShapeType[] {DrawPanel.ShapeType.LINE, DrawPanel.ShapeType.OVAL, DrawPanel.ShapeType.RECTANGLE};

    //  array of colors for combobox (implemented below)
    Color[] colorArray;

    //  builds jframe (wanted to extract from constructor)
    void frameBuilder() {
        //  build color array
        this.colorArray = new Color[] {Color.BLACK, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};

        //  instantiate panels
        this.drawPanel = new DrawPanel(new JLabel());
        this.controlPanel = new JPanel(new FlowLayout());

        //  create buttons, add to control panel
        this.undoButton = new JButton("Undo");
            this.controlPanel.add(this.undoButton);
        this.clearAll = new JButton("Clear All");
            this.controlPanel.add(this.clearAll);
        this.colorPicker = new JComboBox(colorArray);
            this.controlPanel.add(this.colorPicker);
        this.shapePicker = new JComboBox(shapeTypes);
            this.controlPanel.add(this.shapePicker);
        this.fillPicker = new JCheckBox("Fill?", false);
            this.controlPanel.add(this.fillPicker);

        //  set properties of JFrame, add drawpanel/controlpanel
        this.setLayout(new BorderLayout());
        this.setBounds(100, 100, 550, 550);
        this.add(controlPanel, BorderLayout.NORTH);
        this.add(drawPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    //  action listeners per buttons
    //  nonworking
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.undoButton)
            this.drawPanel.clearLastShape();
        else if(e.getSource() == this.clearAll)
            this.drawPanel.clearDrawing();
        if(e.getSource() == colorPicker){
            if(colorPicker.getSelectedItem() == Color.BLACK)
                drawPanel.setCurrentColor(Color.BLACK);
            else if(colorPicker.getSelectedItem() == Color.RED)
                drawPanel.setCurrentColor(Color.RED);
            else if(colorPicker.getSelectedItem() == Color.ORANGE)
                drawPanel.setCurrentColor(Color.ORANGE);
            else if(colorPicker.getSelectedItem() == Color.YELLOW)
                drawPanel.setCurrentColor(Color.YELLOW);
            else if(colorPicker.getSelectedItem() == Color.GREEN)
                drawPanel.setCurrentColor(Color.GREEN);
            else drawPanel.setCurrentColor(Color.BLUE);
        }
        else if(e.getSource() == shapePicker){
            if(shapePicker.getSelectedItem() == DrawPanel.ShapeType.LINE)
                drawPanel.shapeType = DrawPanel.ShapeType.LINE;
            else if(shapePicker.getSelectedItem() == DrawPanel.ShapeType.OVAL)
                drawPanel.shapeType = DrawPanel.ShapeType.OVAL;
            else drawPanel.shapeType = DrawPanel.ShapeType.RECTANGLE;
        }
        else drawPanel.filledShape = fillPicker.isSelected();

    }

    //  constructor
    DrawFrame() {
        frameBuilder();
    }

    //  main
    public static void main(String[] args){
        DrawFrame frame = new DrawFrame();
    }
}
