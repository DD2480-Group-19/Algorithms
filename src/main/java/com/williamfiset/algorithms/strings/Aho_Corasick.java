package com.williamfiset.algorithms.strings;

import java.util.Arrays;

import static java.lang.Character.toLowerCase;

public class Aho_Corasick {

    //array of all possible nodes that we have in our trie, cannot exceed the total number
    Node[]  nodes;
    //of characters in all given words
    int currentNodes;//used to give a specific node a index

    public Aho_Corasick(int numberNodes) {
        //creating the root node
        nodes = new Node[numberNodes];
        currentNodes = 1;
        //no parent node for the root
        nodes[0] = new Node();
        //no parent node for the root
        nodes[0].parentNode = -1;
        //referencing itself, as there is no suffix for empty string
        nodes[0].linkIndex = 0;
    }

    public void insertWord(String word) {
        word = word.toLowerCase();
        int idxCurrNode = 0;
        for (char ch : word.toCharArray()) {
            //due to ASCII values of small letter ranting from 97-123!
            int c = ch - 'a';
            if (nodes[idxCurrNode].childNodes[c] == -1){
                //no child with said character of said node as of now
                nodes[currentNodes] = new Node();
                nodes[currentNodes].parentNode = idxCurrNode;


                //current char is the char of the part node
                nodes[currentNodes].charParent = ch;

                //the reference to this is the index number of said node in the nodes list
                nodes[idxCurrNode].childNodes[c] = currentNodes;

                currentNodes++;
            }

            idxCurrNode = nodes[idxCurrNode].childNodes[c];
        }
        nodes[idxCurrNode].isWord = true;//the last node in the word will represent a word
    }

    public static class Node {
        //references the number of letters in the alphabet
        final int k = 26;
        int[] transArray = new int[k];
        int[] childNodes = new int[k];
        char charParent;
        int linkIndex;
        //gives index value of parent in "nodes"
        int parentNode;
        //can default that a given node's prefix does not constitute a word
        boolean isWord = false;

        public Node() {
            //fill all transitions with '-1' as they are not yet set
            Arrays.fill(transArray,-1);
            //Same here, we assume that all children are unknown
            Arrays.fill(childNodes,-1);
            linkIndex = -1;
        }
    }

    public int transition(int indexNode, char c) {
        c = toLowerCase(c);
        Node currNode = nodes[indexNode];

        int intChar = c - 'a';

        if (currNode.transArray[intChar] == -1) {
            if(currNode.childNodes[intChar] != -1) {
                currNode.transArray[intChar] = currNode.childNodes[intChar];

            }
            else {
                //have to find the failure link
                if (indexNode == 0) {
                    //as this is the root node.
                    currNode.transArray[intChar] = 0;
                }
                else {

                    currNode.transArray[intChar] = transition(SuffixLinkSearch(indexNode), c);
                }
            }
        }
        return currNode.transArray[intChar];
    }

    private int SuffixLinkSearch(int indexNode) {
        Node currentNode = nodes[indexNode];
        if (currentNode.linkIndex == -1){
            //not yet set
            if (currentNode.parentNode == 0) {
                //if parent is root, set fail link back to root
                currentNode.linkIndex = 0;
            }
            else {
                currentNode.linkIndex = transition(SuffixLinkSearch(currentNode.parentNode), currentNode.charParent);
            }
        }
        return currentNode.linkIndex;
    }
    public static int NumberOfFounds(Aho_Corasick trie, String mainString) {
        int node = 0;
        int hits = 0;
        for (char ch : mainString.toCharArray()) {
            node = trie.transition(node, ch);
            if (trie.nodes[node].isWord) {

                hits++;
            }
        }
        return hits;
    }
}
