#pragma once
#include <iostream>
#include "Node.h"

template<typename T>
class Tree{
    private:
        Node<T>* root = nullptr;

        Node<T>* findSuccessor(Node<T>* node);
        Node<T>* findMin(Node<T>* node);
        void deletePostOrder(Node<T>* node);
        void print(Node<T>* node);

    public:
        Tree(Node<T>* root);
        ~Tree();
        void print();
        void deleteNode(T value);
        Node<T>* search(T value);
        void insert(T value);
        Node<T>* getRoot();
        void setRoot(Node<T>* root);
};

template <typename T>
Tree<T>::Tree(Node<T>* root) : root(root) {}

template <typename T>
void Tree<T>::print()
{
    
    if(root == nullptr)
        std::cout<<"Drzewo jest puste.";
    else 
    {
        std::cout<<"\nPrzeglad in-order:";
        print(root);
    }
}

template <typename T>
void Tree<T>::print(Node<T>* node)
{
    if(node->getLeft() != nullptr) print(node->getLeft());
    std::cout<<" "<<node->getValue();
    if(node->getRight() != nullptr) print(node->getRight());
}

template <typename T>
void Tree<T>::deleteNode(T value)
{
    Node<T>* nodeToDelete = search(value);
    if(nodeToDelete == nullptr)
    {
        return;
    }

    Node<T>* y = nullptr;
    Node<T>* x = nullptr;

    if(nodeToDelete->getLeft() == nullptr || nodeToDelete->getRight() == nullptr)
    {
        y = nodeToDelete;
    } else
    {
        y = findSuccessor(nodeToDelete);
    }

    if(y->getLeft() != nullptr)
    {
        x = y->getLeft();
    } else
    {
        x = y->getRight();
    }

    if(x != nullptr)
    {
        x->setParent(y->getParent());
    }

    if(y->getParent() == nullptr)
    {
        root = x;
    } else
    {
        if(y == y->getParent()->getLeft())
            y->getParent()->setLeft(x);
        else
            y->getParent()->setRight(x);
    }
    if(y != nodeToDelete)
    {
        nodeToDelete->setValue(y->getValue());
    }

    delete y;

}

template <typename T>
Node<T>* Tree<T>::search(T value)
{
    Node<T>* node = root;
    while(node != nullptr && node->getValue() != value)
    {
        if(std::less<>()(value, node->getValue()))
        {
            node = node->getLeft();
        } else
        {
            node = node->getRight();
        }
    }

    return node;
}
template <typename T>
void Tree<T>::insert(T value)
{
    if(search(value) != nullptr)
    {
        return;
    }

    Node<T>* newNode = new Node<T>(value);

    Node<T>* y = nullptr;
    Node<T>* x = root;

    while(x != nullptr)
    {
        y = x;
        if(std::less<>()(value, x->getValue()))
        {
            x = x->getLeft();
        } else
        {
            x = x->getRight();
        }
    }

    newNode->setParent(y);

    if(y == nullptr) 
        this->root = newNode;
    else 
        if(std::less<>()(value, y->getValue()))
            y->setLeft(newNode);
        else
            y->setRight(newNode);
}

template <typename T>
Node<T>* Tree<T>::getRoot()
{
    return root;
}

template <typename T>
Node<T>* Tree<T>::findSuccessor(Node<T>* node)
{
    if(node->getRight() != nullptr)
    {
        return findMin(node);
    }

    Node<T>* tmp = node->getParent();

    while(tmp != nullptr && tmp->getLeft() != node)
    {
        node = tmp;
        tmp = tmp->getParent();
    }
    return tmp;
}

template <typename T>
Node<T>* Tree<T>::findMin(Node<T>* node)
{
    while(node->getLeft() != nullptr)
        node = node->getLeft();
    return node;
}

template <typename T>
void Tree<T>::setRoot(Node<T>* root)
{
    if(this->root != nullptr)
        deletePostOrder(this->root);

    this->root = root;
}

template <typename T>
Tree<T>::~Tree()
{
    if(root != nullptr)
        deletePostOrder(root);
}

template <typename T>
void Tree<T>::deletePostOrder(Node<T>* node)
{
    if(node->getLeft() != nullptr) deletePostOrder(node->getLeft());
    if(node->getRight() != nullptr) deletePostOrder(node->getRight());
    delete node;
}