package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
	TrieNode<T>[] arr;
	TrieNode<T> parent;
	int son;
	T value;
    boolean isEnd;
   
    @SuppressWarnings("unchecked")
	public TrieNode() {
        this.arr = new TrieNode[127];
       // this.parent=null;
    }
    @Override
    public T getValue() {
        return value;
    }
   

}