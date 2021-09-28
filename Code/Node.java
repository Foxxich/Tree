/**
 * @param <T> This is used for generic node class.
 */
public class Node<T extends Comparable<T>> {
    /**
     * values is used to save the value of all the elements of the binary tree.
     * right is the right node.
     * left is the left node.s
     * parent is the parent in the binary tree.
     */
    private T value;
    private Node<T> right = null;
    private Node<T> left = null;
    private Node<T> parent = null;

    /**
     * This is basic constructor for Node.
     * @param value is used as a node value.
     */
    public Node(T value)
    {
        this.value = value;
    }

    /**
     * This method is used to set the left child node of the node.
     * @param left node to set.
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * This method is used to set the right child node of the node.
     * @param right node to set.
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }
    
    /**
     * This method is used to set the parent node of the node.
     * @param parent node to set.
     */
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    /**
     * This method is used to set the value.
     * @param value This is the value which will be set.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * This method is used to get the left child of the node.
     * @return the value of the left child.
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * This method is used to get the right child of the node.
     * @return the value of the right child.
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * This method is used to get the parent of the node.
     * @return the value of the parent.
     */
    public Node<T> getParent() {
        return parent;
    }

    /**
     * This method is used to get the value of the node.
     * @return the node value.
     */
    public T getValue() {
        return value;
    }

    /**
     * This method is used to convert node to string.
     * @return node value as String.
     */
    @Override
    public String toString()
    {
        return value.toString();
    }
}