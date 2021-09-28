import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class is used to create JPanel for putting the binary tree on it.
 */

// MyPanel extends JPanel, which will eventually be placed in a JFrame
public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * mouseX is the position of the mouse cursor relative to the window.
     * mouseY is the position of the mouse cursor relative to the window.
     * x is the standart position which is used to calculate the position for the ovals
     * with the inserted values. Also this position is relative to the window.
     * y is the standart position which is used to calculate the position for the ovals
     * with the inserted values. Also this position is relative to the window.
     */
    private int mouseX = 0;
    private int mouseY = 0;
    private int x = 200, y = 10;
    private boolean mouseButtonLeft = false;

    private Tree tree = null;

    /**
     * This method is used to add Listeners and create the binary tree with specified type of values.
     * @param type is given by user and used for get the specified type of tree.
     */
    @SuppressWarnings("unchecked")
    public MyPanel(String type) {

        switch(type)
        {
            /*
             * Case if the type is int.
             */
            case "I":
                tree = new Tree<Integer>(null);
                tree.insert(10);
                tree.insert(9);
                tree.insert(15);
                tree.insert(20);
                tree.insert(5);
                tree.insert(8);
                tree.insert(25);

                break;

            /*
             * Case if the type is double.
             */
            case "D":
                tree = new Tree<Double>(null);
                tree.insert(10.1512);
                tree.insert(9.58455);
                tree.insert(15.5);
                tree.insert(0.020);
                tree.insert(0.5);
                tree.insert(0.82);
                tree.insert(2.5);
                break;

            /*
             * Case if the type is string.
             */
            case "S":
                tree = new Tree<String>(null);
                tree.insert("Ala");
                tree.insert("Alicja");
                tree.insert("Kuba");
                tree.insert("Jakub");
                tree.insert("Amarant");
                tree.insert("Bety");
                tree.insert("Bartek");
                break;
            default:
                throw new IllegalStateException();
        }

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * This private method is used to change the cursor if it is over the binary tree.
     * Otherless it looks standardly.
     */
    private void changeCursor() {

        if (mouseButtonLeft)
            this.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        else
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * This method is used to paint each oval with the standart parameter (g) and
     * specified parameter (the position relative to the window).
     * @param g This is the graphics used to paint the ovals for the binary tree.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tree.draw(g, x, y);
    }

    /**
     * This method is used when the mouse button has been clicked 
     * (pressed and released) on a component.
     * @param e the event to be processed.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * This method is used when a mouse button has been pressed on a component.
     * @param e the event to be processed.
     */
    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            mouseButtonLeft = true;
        }
    }

    /**
     * This method is used when a mouse button has been released on a component.
     * @param e the event to be processed.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            mouseButtonLeft = false;
    }

    /**
     * This method is used when the mouse enters a component.
     * @param e the event to be processed.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * This method is used when the mouse exits a component.
     * @param e the event to be processed.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * This method is used when a mouse button is pressed on a component and then dragged.
     * @param e the event to be processed. 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseButtonLeft) {
            changeCursor();
            x += e.getX() - mouseX;
            y += e.getY() - mouseY;
        }
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();

    }

    /**
     * This method is used when the mouse cursor has been 
     * moved onto a component but no buttons have been pushed.
     * @param e the event to be processed.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        changeCursor();
    }

    /**
     * This method is used to insert the specified value given by user.
     * @param val which is of the comparable type and it is inserted
     * to the given binary tree. 
     */
    @SuppressWarnings("unchecked")
    public void insert(Comparable val) {
        tree.insert(val);
        showTree();
    }

    /**
     * This method is used to delete the specified value given by user.
     * @param val which is of the comparable type and it is deleted
     * from the given binary tree. 
     */
    @SuppressWarnings("unchecked")
    public void delete(Comparable val) {
        tree.delete(val);
        showTree();
    }

    /**
     * This method is used to search the specified value given by user.
     * @param val which is of the comparable type and it is serched
     * in the given binary tree. 
     * @return true if the value exists in tree. Otherless false.
     */
    @SuppressWarnings("unchecked")
    public Boolean search(Comparable val) {
        return tree.search(val) != null;
    }

    /**
     * This method is used to repaint the binary tree after doing every operation.
     */
    public void showTree() {
        repaint();
    }
}
