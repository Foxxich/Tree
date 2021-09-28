#pragma once

template <typename T>
class Node {
    private:
        T value;
        Node<T>* right = nullptr;
        Node<T>* left = nullptr;
        Node<T>* parent = nullptr;

    public:
        Node(T value);

        void setLeft(Node<T>* left);
        void setRight(Node<T>* right);
        void setParent(Node<T>* parent);
        void setValue(T value);

        Node<T>* getLeft();
        Node<T>* getRight();
        Node<T>* getParent();
        T getValue();
};


template <typename T>
Node<T>::Node(T value) : value(value) {}

template <typename T>
void Node<T>::setLeft(Node<T>* left)
{
    this->left = left;
}

template <typename T>
void Node<T>::setRight(Node<T>* right)
{
    this->right = right;
}

template <typename T>
void Node<T>::setParent(Node<T>* parent)
{
    this->parent = parent;
}

template <typename T>
void Node<T>::setValue(T value)
{
    this->value = value;
}

template <typename T>
Node<T>* Node<T>::getLeft()
{
    return this->left;
}

template <typename T>
Node<T>* Node<T>::getRight()
{
    return this->right;
}

template <typename T>
Node<T>* Node<T>::getParent()
{
    return this->parent;
}

template <typename T>
T Node<T>::getValue()
{
    return this->value;
}

