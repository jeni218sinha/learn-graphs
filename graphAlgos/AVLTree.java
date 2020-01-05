package graphAlgos;

class Node { 
    int key, height; 
    Node left, right; 
  
    Node(int d) { 
        key = d; 
        height = 1; 
    } 
} 


class AVLTree {
    Node root;
    
    public int getHeight(Node node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    public int max(int a, int b) { 
        return Math.max(a,b);
    }

    public int getBalance(Node node) { 
        if (node == null) 
            return 0; 
        return getHeight(node.left) - getHeight(node.right); 
    }

    public void preOrder(Node node) { 
        if (node != null) { 
            System.out.print(node.key + " "); 
            preOrder(node.left); 
            preOrder(node.right); 
        } 
    }

    private Node leftRotate(Node node) { 
        Node rightChild = node.right; 
        Node leftSubTreeOfRightChild = rightChild.left; 
  
        // Perform rotation 
        rightChild.left = node; 
        node.right = leftSubTreeOfRightChild; 
  
        //  Update heights 
        node.height = max(getHeight(node.left), getHeight(node.right)) + 1; 
        rightChild.height = max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1; 
  
        // Return new root 
        return rightChild; 
    }

     private Node rightRotate(Node node) { 
        Node leftChild = node.left; 
        Node rightSubTreeOfLeftChild = leftChild.right; 
  
        // Perform rotation 
        leftChild.right = node;
        node.left = rightSubTreeOfLeftChild;
  
        //  Update heights 
        node.height = max(getHeight(node.left), getHeight(node.right)) + 1; 
        leftChild.height = max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1; 
  
        // Return new root 
        return leftChild; 
    }
    
    public Node insert(Node node, int key) { 
  
        /* 1.  Perform the normal BST insertion */
        if (node == null) 
            return (new Node(key)); 
  
        if (key < node.key) 
            node.left = insert(node.left, key); 
        else if (key > node.key) 
            node.right = insert(node.right, key); 
        else // Duplicate keys not allowed 
            return node; 
  
        /* 2. Update height of this ancestor node */
        node.height = 1 + max(getHeight(node.left), 
                              getHeight(node.right)); 
  
        /* 3. Get the balance factor of this ancestor 
              node to check whether this node became 
              unbalanced */
        int balance = getBalance(node); 
  
        // If this node becomes unbalanced, then there 
        // are 4 cases Left Left Case 
        if(balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
  
        // Right Right Case 
        if(balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
  
        // Left Right Case 
        if(balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return  rightRotate(node);
        }
  
        // Right Left Case 
        if(balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
  
        /* return the (unchanged) node pointer */
        return node;
    }


    public Node deleteNode(Node root, int key)  {
        if(root == null) {
            return root;
        }
        if(root.key < key) {
            root.right =  deleteNode(root.right, key);
        } else if(root.key > key) {
            root.left = deleteNode(root.left, key);
        } else {
            if(root.left == null && root.right == null) {
                root = null;
            } else if(root.left == null || root.right == null) {
                root = root.left != null ? root.left : root.right;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)  
            return root;
        root.height = 1+  max(getHeight(root.left), getHeight(root.right));
        int balance = getBalance(root);
        if(balance > 1 && getBalance(root.left)>=0) {
            // LL case
            return rightRotate(root);
        }
        if(balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if(balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }
        if(balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    public Node minValueNode(Node node) {  
        Node current = node;  
  
        /* loop down to find the leftmost leaf */
        while (current.left != null)  
        current = current.left;  
  
        return current;  
    } 

    public static void main(String[] args) { 
AVLTree tree = new AVLTree();  
  
        /* Constructing tree given in the above figure */
        tree.root = tree.insert(tree.root, 9);  
        tree.root = tree.insert(tree.root, 5);  
        tree.root = tree.insert(tree.root, 10);  
        tree.root = tree.insert(tree.root, 0);  
        tree.root = tree.insert(tree.root, 6);  
        tree.root = tree.insert(tree.root, 11);  
        tree.root = tree.insert(tree.root, -1);  
        tree.root = tree.insert(tree.root, 1);  
        tree.root = tree.insert(tree.root, 2);  
  
        /* The constructed AVL Tree would be  
        9  
        / \  
        1 10  
        / \ \  
        0 5 11  
        / / \  
        -1 2 6  
        */
        System.out.println("Preorder traversal of "+  
                            "constructed tree is : ");  
        tree.preOrder(tree.root);  
  
        tree.root = tree.deleteNode(tree.root, 10);  
  
        /* The AVL Tree after deletion of 10  
        1  
        / \  
        0 9  
        /     / \  
        -1 5 11  
        / \  
        2 6  
        */
        System.out.println("");  
        System.out.println("Preorder traversal after "+  
                        "deletion of 10 :");  
        tree.preOrder(tree.root);  
    }
}

