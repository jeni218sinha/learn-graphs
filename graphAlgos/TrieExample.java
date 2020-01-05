package graphAlgos;

import java.util.Arrays;
class TrieNode
{
    TrieNode[] children;
    boolean isEndWord; //will be true if the node represents the end of word

    TrieNode(){
        this.isEndWord = false;
        this.children = new TrieNode[26]; //Total # of English Alphabets = 26
    }

    //Function to mark the currentNode as Leaf
    public void markAsLeaf(){
        this.isEndWord = true;
    }

    //Function to unMark the currentNode as Leaf
    public void unMarkAsLeaf(){
        this.isEndWord = false;
    }
}

class Trie{
    private TrieNode root; //Root Node

    //Constructor
    Trie(){
        root = new TrieNode();
    }
    //Function to get the index of a character 'x'
    public int getIndex(char x)
    {
        return x - 'a';  // the index is based on the position of character in alphabets
    }

    //Function to insert a key in the Trie
    public void insert(String key)
    {
        if(key == null) //Null keys are not allowed
        {
            System.out.println("Null Key can not be Inserted!");
            return;
        }
        key = key.toLowerCase();
        TrieNode currentNode = root;
        for(int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            int index = getIndex(c);
            if (currentNode.children[index] == null) {
                currentNode.children[index] = new TrieNode();
            }
            currentNode = currentNode.children[index];
        }
        currentNode.markAsLeaf();
    }

    public boolean search(String key) {
        if(key == null || key.length() == 0) {
            return false;
        }
        char c;
        int index;
        TrieNode curr = root;
         key = key.toLowerCase();
        for(int i = 0; i < key.length(); i++) {
            c = key.charAt(i);
            index = getIndex(c);
            if(curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEndWord;
    }

    private boolean hasNoChildren(TrieNode node) {
        if(node == null) {
            return true;
        }
        for(int i = 0; i < node.children.length; i++) {
            if(node.children[i] != null) {
                return  false;
            }
        }
        return true;
    }

    private boolean deleteHelper(String key, TrieNode currentNode, int length, int level)  {
        boolean deleteSelf = false;
        if(currentNode == null) {
            System.out.println("Not present in trie");
            return deleteSelf;
        }
        if(level == length) {
            if(hasNoChildren(currentNode)) {
                currentNode = null;
                deleteSelf = true;
            } else {
                currentNode.unMarkAsLeaf();
                deleteSelf = false;
            }
        } else {
            TrieNode childNode = currentNode.children[getIndex(key.charAt(level))];
            boolean deleteChild = deleteHelper(key, childNode, length, level + 1);
            if(deleteChild) {
                currentNode.children[getIndex(key.charAt(level))] = null;
                if(currentNode.isEndWord) {
                    deleteSelf = false;
                } else if(!hasNoChildren(currentNode)) {
                    deleteSelf = false;
                } else {
                    currentNode = null;
                    deleteSelf = true;
                }
            } else {
                deleteSelf = false;
            }
        }
        return deleteSelf;
    }

    public void delete(String key) {
        if(key == null || this.root == null) {
            return;
        }
        deleteHelper(key, root, key.length(), 0);
    }
}

public class TrieExample{  
  public static void main(String args[]){
    // Input keys (use only 'a' through 'z' and lower case)
    String keys[] = {"the", "a", "there", "answer", "any",
                     "by", "bye", "their","abc"};
    String output[] = {"Not present in trie", "Present in trie"};
    Trie t = new Trie();
    
    System.out.println("Keys to insert: "+ Arrays.toString(keys) + "\n");
        
    // Construct trie       
    int i;
    for (i = 0; i < keys.length ; i++)
    {
      t.insert(keys[i]);
      System.out.println("\""+ keys[i]+ "\"" + "Inserted.");
    }
   if(t.search("answer") == true)
      System.out.println("the --- " + output[1]);
    else System.out.println("the --- " + output[0]);
         
    if(t.search("these") == true)
      System.out.println("these --- " + output[1]);
    else System.out.println("these --- " + output[0]);
         
    if(t.search("abc") == true)
      System.out.println("abc --- " + output[1]);
    else System.out.println("abc --- " + output[0]);   

      if(t.search("abc") == true)
    {
      System.out.println("abc --- " + output[1]);
       t.delete("abc");
      System.out.println("Deleted key \"abc\""); 
    }
    else System.out.println("abc --- " + output[0]);
         
    // check if the string has deleted correctly or still present
    if(t.search("abc") == true)
      System.out.println("abc --- " + output[1]);
    else System.out.println("abc --- " + output[0]);
  }
}