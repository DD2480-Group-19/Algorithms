package com.williamfiset.algorithms.strings;

public class Trie {
        //creates a Trie for all

        private Node root;

        public Trie(){
            root = new Node('\0'); //char value of 0
        }

        public void insertWord(String word) {
            int index;
            Node current = root; //always starting from the very top of the trie
            for ( int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a'; //I am not sure about the reason for this, but found online that it is necessary!
                if (current.children[index] == null) {
                    current.children[index] = new Node(word.charAt(i));
                    //if the child of the previous node is empty at that spot
                    //create a new child with char c
                }
                current = current.children[index];
            }
            current.isWord = true; //make sure that the last node is marked as representing a full word!
        }

        public boolean searchWord(String word) {
            int index;
            Node current = root;
            for ( int i = 0; i < word.length(); i++) {
                index = word.charAt(i) - 'a';
                if (current.children[index] == null) return false;
                current = current.children[index];
            }
            return current.isWord;//returns true iff the word does exist in the text!
        }

        static class Node{ //not sure if needed to be static or not
            //each node representing a prefix
            boolean isWord = false;
            Node[] children;
            char c;

            public Node(char c) {
                this.c = c;
                this.children = new Node[26]; //because there are 26 letters in the alphabet
            }
        }
}