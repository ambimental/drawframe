import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/*
    DrawPanel.java
    Authored John Georgvich; help from Alexander Day and Evan Black
    due 03042018
    Implemented in DrawFrame.java
 */
public class DrawPanel extends JPanel {
    //  actionlistener
    DrawPanelHandler dph;

    //  data members required as per assignment document
    ArrayList<Shape> shapes;
    ShapeType shapeType;
    Shape currentShape;
    int shapeCount;
    Color currentColor;
    boolean filledShape;

    JLabel statusLabel;

    //  ShapeType enum
    enum ShapeType{
        LINE,
        OVAL,
        RECTANGLE
    }

    //  shapeType setter
    void setShapeType(ShapeType type) {
        switch(type){
            case LINE:
                this.shapeType = ShapeType.LINE;
                break;
            case OVAL:
                this.shapeType = ShapeType.OVAL;
                break;
            case RECTANGLE:
                this.shapeType = ShapeType.RECTANGLE;
                break;
        }
    }

    //  currentColor setter
    //  nonworking
    void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    //  fill setter
    //  nonworking
    void setFilledShape(boolean filled) {
        this.filledShape = filled;
    }

    //  removes most recent shape from panel
    //  nonworking
    void clearLastShape() {
        this.shapes.remove(shapes.size() - 1);
        this.repaint();
    }

    //  removes all shapes from panel
    //  nonworking
    void clearDrawing() {
        this.shapes = new ArrayList<>();
        this.repaint();
    }

    //  paintcomponent method
    //  draws current shape and all shapes in shape arraylist
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Shape shape : this.shapes){
            shape.draw(g);
        }

        if(this.currentShape != null)
            this.currentShape.draw(g);
    }

    //  constructor
    DrawPanel(JLabel label) {
        label.setText("Welcome!");

        //  initialize data members to default values as per requirements document
        this.statusLabel = label;
        this.shapes = new ArrayList<>();
        //this.shapes.ensureCapacity(100);
        this.shapeCount = 0;
        this.shapeType = ShapeType.LINE;
        this.currentShape = null;
        this.currentColor = Color.BLACK;

        //  set up mouse listener
        dph = new DrawPanelHandler();
        this.addMouseListener(dph);
        this.addMouseMotionListener(dph);

        //  set panel bounds and color
        this.setBounds(10, 10, 300, 300);
        this.setBackground(Color.WHITE);

        //  draw test line, add to arraylist
        currentShape = new Line();
        currentShape.setX1(0);
        currentShape.setY1(0);
        currentShape.setX2(100);
        currentShape.setY2(100);
        shapes.add(currentShape);
        currentShape = null;
        repaint();
    }

    //  mouse listener
    public class DrawPanelHandler extends MouseAdapter implements MouseMotionListener {
        @Override
        //  on mouse press, begin draw
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            System.out.print("press");
            switch(shapeType){
                case LINE:
                    currentShape = new Line();
                    break;
                case OVAL:
                    currentShape = new Oval();
                    break;
                case RECTANGLE:
                    currentShape = new Rect();
                    break;
            }
            currentShape.setX1(e.getX());
            currentShape.setY1(e.getY());
            currentShape.setColor(currentColor);
        }

        @Override
        //  on mouse release, finish drawing shape and add shape to arraylist, then nullify currentshape
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            System.out.print("release");
            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());
            currentShape.setColor(currentColor);
            shapes.add(currentShape);
            //shapeCount++;
            currentShape = null;
            repaint();
        }

        @Override
        //  on mousemove, update status label
        //  nonworking
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            statusLabel.setText("X:\t" + e.getX() + "\tY:\t" + e.getY());
        }

        @Override
        //  on mouse drag, continue drawing shape
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            statusLabel.setText("X:\t" + e.getX() + "\tY:\t" + e.getY());
            currentShape.setColor(currentColor);
            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());
            repaint();
        }
    }
}
