import java.awt.Color;
import java.awt.Graphics;

/**
 * This generic class is used to build a binary tree.
 */
public class Tree<T extends Comparable<T>> {  


    /**
     * OVAL_SIZE is the standart parameter for size of each oval.
     * HORIZONTAL_MARGIN is the standart parameter for horizontal edge.
     * VERTICAL_MARGIN is the standart parameter for vertical edge.
     * OVAL_COLOR is the standart colour of the oval.
     * TEXT_COLOR is the standart colour of the letters.
     */
    static class Dimension {
        private static final int OVAL_SIZE = 60;
        private static final int HORIZONTAL_MARGIN = 70;
        private static final int VERTICAL_MARGIN = 50;

        private static final Color OVAL_COLOR = Color.LIGHT_GRAY;
        private static final Color TEXT_COLOR = Color.BLACK;
    }
    
    /**
     * root is the field which contains root of binary tree.
     */
    private Node<T> root = null;

    /**
     * This is basic constructor for class Tree.
     * @param root Node that is used as the root of binary tree.
     */
    public Tree(Node<T> root)
    {
        this.root = root;
    }

    /**
     * This method contains in-order overview of the binary tree.
     * @return the tree presented in-order if the tree exists. Otherless Null.
     */
    @Override
    public String toString()
    {
        if(root != null)
        {
            StringBuilder stringBuilder = new StringBuilder("Przeglad in-order:\n");
            inOrder(root, stringBuilder);
            return stringBuilder.toString();
        }
        else return "Null";

    }

    /**
     * This private method is used to put the value correctly,
     * depanding on the availability of the nodes.
     * @param node is used to check up the availability of left and right nodes.
     * @param sBuilder is used to as save of the value.
     */
    private void inOrder(Node<T> node, StringBuilder sBuilder)
    {
        if(node.getLeft() != null) inOrder(node.getLeft(), sBuilder);
        sBuilder.append("\"" + node.toString() + "\" ");
        if(node.getRight() != null) inOrder(node.getRight(), sBuilder);
    }

    /**
     * This method is used to draw the binary tree.
     * @param g This is the graphics used to draw the binary tree.
     * @param x This is the x position of root.
     * @param y This is the y position of root.
     */
    public void draw(Graphics g, int x, int y) 
    {
        if(root != null)
        {
            int depth = 0;
            drawNode(root, g, x, y, depth);
        }

    }

    /**
     * This private method is used to draw all the nodes of the binary tree.
     * @param node This is used as a node with specified value.
     * @param g This is the graphics used to paint the tree.
     * @param x This is the x position, which is used for building of ovals.
     * @param y This is the y position, which is used for building of ovals.
     * @param depth This is used to calculate the width on the JPanel for putting 
     * the ovals with values in a good-looking way.
     */
    private void drawNode(Node node, Graphics g, int x, int y, int depth)
    {
        depth++;

        /**
         * horziontalMargin is the parameter for horizontal edge.
         */
        int horziontalMargin = (int)Math.pow(2, maxDepth - depth - 1) * Dimension.HORIZONTAL_MARGIN;
        
        /*
         * This loop is used to check if the left node exists.
         */
        if(node.getLeft() != null) 
        {
            g.setColor(Dimension.TEXT_COLOR);
            g.drawLine(
                x + Dimension.OVAL_SIZE/2, 
                y + Dimension.OVAL_SIZE/2, 
                x - horziontalMargin + Dimension.OVAL_SIZE/2, 
                y + Dimension.VERTICAL_MARGIN + Dimension.OVAL_SIZE/2
            );
            drawNode(node.getLeft(), g, x - horziontalMargin, y + Dimension.VERTICAL_MARGIN, depth);
        }

        /*
         * This loop is used to check if the right node exists.
         */
        if(node.getRight() != null) 
        {
            g.setColor(Dimension.TEXT_COLOR);
            g.drawLine(
                x + Dimension.OVAL_SIZE/2, 
                y + Dimension.OVAL_SIZE/2, 
                x + horziontalMargin + Dimension.OVAL_SIZE/2, 
                y + Dimension.VERTICAL_MARGIN + Dimension.OVAL_SIZE/2
            );
            drawNode(node.getRight(), g, x + horziontalMargin, y + Dimension.VERTICAL_MARGIN, depth);
        }

        /*
         * Standart draw of ovals and putting the letters.
         */
        g.setColor(Dimension.OVAL_COLOR);
        g.fillOval(x, y, Dimension.OVAL_SIZE, Dimension.OVAL_SIZE);

        g.setColor(Dimension.TEXT_COLOR);

        g.drawOval(x, y, Dimension.OVAL_SIZE, Dimension.OVAL_SIZE);
        g.drawString(node.toString(), x + Dimension.OVAL_SIZE/10, y + Dimension.OVAL_SIZE/2 + Dimension.OVAL_SIZE/10);

        depth--;
    }

    /**
     * This method is used to delete the chosen element in the binary tree.
     * @param value is the value which will be deleted.
     */
    public void delete(T value)
    {
        /*
         * This case is used to check up if there the element is a node and it could
         * be deleted.
         */
        Node<T> nodeToDelete = search(value);
        if(nodeToDelete == null)
        {
            return;
        }

        

        /**
         * y is the node which will be deleted if the value is in the node.
         * x is used to save the all values of the deleted node.
         */
        Node<T> y = null;
        Node<T> x = null;

        /*
         * This case is used to check up if there is left\right node which should be deleted.
         */
        if(nodeToDelete.getLeft() == null || nodeToDelete.getRight() == null)
        {
            y = nodeToDelete;
        } else
        {
            y = findSuccessor(nodeToDelete);
        }

        /*
         *This case is used to make x save the values from the left\right node.
         */
        if(y.getLeft() != null)
        {
            x = y.getLeft();
        } else
        {
            x = y.getRight();
        }

        /*
         * This case is used to check up if the x has values. If it has,
         * it is possible to get the parent.
         */
        if(x != null)
        {
            x.setParent(y.getParent());
        }

        /*
         * This case is used to check up the parent and find the root of the binary tree.
         * If there is not a parent, x = root. Othereless the program checks up the value of y and
         * parent of the left node.
         */
        if(y.getParent() == null)
        {
            root = x;
        } else
        {
            if(y == y.getParent().getLeft())
                y.getParent().setLeft(x);
            else
                y.getParent().setRight(x);
        }

        /*
         * This case is used to check up if y != the note which should be deleted.
         */
        if(y!= nodeToDelete)
        {
            nodeToDelete.setValue(y.getValue());
        }

        calculateDepth();

    }

    /**
     * This method is used to find successor.
     * @param node is the node of the binary tree.
     * @return successor of given node.
     */
    private Node<T> findSuccessor(Node<T> node)
    {
        if(node.getRight() != null)
        {
            return findMin(node.getRight());
        }

        Node<T> tmp = node.getParent();

        while(tmp != null && tmp.getLeft() != node)
        {
            node = tmp;
            tmp = tmp.getParent();
        }
        return tmp;
    }

    /**
     * This method is used to find the minal value in the tree or it's subtree.
     * @param node node which is treated as subtree root.
     * @return the node with minimal value in given subtree.
     */
    private Node<T> findMin(Node<T> node)
    {
        while(node.getLeft() != null)
            node = node.getLeft();
        return node;
    }

    private int maxDepth = 0;

    /**
     * This method is used to get the correct place for putting the nodes.
     */
    private void calculateDepth()
    {
        if(root == null)
            maxDepth = 0;
        else
        {
            int depth = 0;
            maxDepth = 0;
            nextNode(root, depth);
        }
            
    }

    /**
     * This private method is used to get the depth for putting the binary tree in a better way.
     * @param node is used to spread the place for putting the frames with values on the JPanel
     * (depends on the height).
     * @param depth is used to spread the place for putting the frames with values on the JPanel
     * (depends on the width).
     */
    private void nextNode(Node<T> node, int depth)
    {
        depth++;

        if(node.getLeft() != null) nextNode(node.getLeft(), depth);
        if(node.getRight() != null) nextNode(node.getRight(), depth);

        if(depth > maxDepth)
            maxDepth = depth;
        
        depth--;
    }

    /**
     * This method is used to search the value in the binary tree.
     * @param value is the value which the program has to find.
     * @return the node with given value if value exists in tree, otherless null.
     */
    public Node<T> search(T value)
    {
        Node<T> node = root;


        while(node != null && node.getValue().compareTo(value) != 0)
        {
            if(value.compareTo(node.getValue()) < 0)
            {
                node = node.getLeft();
            } else
            {
                node = node.getRight();
            }
        }

        return node;
    }

    /**
     * This method is used to insert the value to the binary tree.
     * If given value already exists in tree, method does nothing.
     * @param value is the value which the program has to insert.
     */
    public void insert(T value)
    {

        if(this.search(value) != null)
        {
            return;
        }

        Node<T> newNode = new Node<T>(value);

        Node<T> y = null;
        Node<T> x = root;

        while(x != null)
        {
            y = x;
            if(value.compareTo(x.getValue()) < 0)
            {
                x = x.getLeft();
            } else
            {
                x = x.getRight();
            }
        }


        newNode.setParent(y);

        if(y == null) 
            this.root = newNode;
        else 
            if(value.compareTo(y.getValue()) < 0)
                y.setLeft(newNode);
            else
                y.setRight(newNode);



        calculateDepth();
    }

    /**
     * This method is used to get the root of binary tree.
     * @return the value of the root.
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * This method is used to set the root.
     * @param root is the node which will be set as root of the binary tree.
     */
    public void setRoot(Node<T> root) {
        this.root = root;
    }

}