import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class DrawPanel extends JPanel {
    DrawPanelHandler dph;

    ArrayList<Shape> shapes;
    Shape shapeType;
    Shape currentShape;
    int shapeCount;
    Color currentColor;
    boolean filledShape;

    JLabel statusLabel;

    enum ShapeType{
        LINE,
        OVAL,
        RECTANGLE
    }

    void setShapeType(ShapeType type) {
        switch(type){
            case LINE:
                this.shapeType = new Line();
                break;
            case OVAL:
                this.shapeType = new Oval();
                break;
            case RECTANGLE:
                this.shapeType = new Rect();
                break;
        }
    }

    void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    void setFilledShape(boolean filled) {
        this.filledShape = filled;
    }

    void clearLastShape() {
        this.shapes.remove(shapes.size() - 1);
        this.repaint();
    }

    void clearDrawing() {
        this.shapes.clear();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Shape shape : this.shapes)
            shape.draw(g);
        if(this.currentShape != null)
            this.currentShape.draw(g);
    }

    DrawPanel(JLabel label) {
        label.setText("Welcome!");
        this.statusLabel = label;
        this.shapes = new ArrayList<>();
        this.shapes.ensureCapacity(100);
        this.shapeCount = 0;
        this.shapeType = new Line();
        this.currentShape = null;
        this.currentColor = Color.BLACK;

        this.setBounds(10, 10, 300, 300);
        this.setBackground(Color.WHITE);
        dph = new DrawPanelHandler();
    }

    public class DrawPanelHandler extends MouseAdapter implements MouseMotionListener {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            currentShape = shapeType;
            currentShape.setX1(e.getX());
            currentShape.setY1(e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());
            shapes.add(shapeCount, currentShape);
            shapeCount++;
            currentShape = null;
            repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            super.mouseMoved(e);
            statusLabel.setText("X:\t" + e.getX() + "\tY:\t" + e.getY());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            statusLabel.setText("X:\t" + e.getX() + "\tY:\t" + e.getY());
            currentShape.setX2(e.getX());
            currentShape.setY2(e.getY());
            repaint();
        }
    }
}
