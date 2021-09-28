#include <string>
#include <iostream>
#include "Tree.h"

int main()
{

    Tree<int>* tree = new Tree<int>(new Node<int>(10));

    tree->insert(15);
    tree->insert(20);
    tree->insert(8);
    tree->insert(10);

    tree->deleteNode(10);
    tree->deleteNode(12);
    tree->deleteNode(8);
    tree->deleteNode(15);
    tree->deleteNode(20);

    tree->print();

    delete tree;

    Tree<std::string>* tree2 = new Tree<std::string>(new Node<std::string>("Ala"));

    tree2->insert("Bolek");
    tree2->insert("Zzzzzzzz");
    tree2->insert("CCCCC");
    tree2->insert("ABCDE");

    tree2->deleteNode("Ala");
    tree2->deleteNode("Zzzzzzzz");
    tree2->insert("Zzzzzzzz");

    tree2->print();

    delete tree2;

    Tree<double>* tree3 = new Tree<double>(new Node<double>(10.2));

    tree3->insert(15.789);
    tree3->insert(20.999999);
    tree3->insert(8.0);
    tree3->insert(10);

    tree3->deleteNode(8);

    tree3->print();

    delete tree3;

    return 0;
}



