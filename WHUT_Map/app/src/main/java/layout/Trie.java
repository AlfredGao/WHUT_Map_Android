package layout;

/**
 * Created by AlfredGao on 5/24/16.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by AlfredGao on 5/24/16.
 */
class TrieNode {
    // Initialize your data structure here.
    boolean isWord;
    char c;
    HashMap<Character,TrieNode> child = new HashMap<Character,TrieNode>();

    public TrieNode(char c){
        this.c = c;
    }

    public TrieNode() {

    }
}

public class Trie {
    private TrieNode root;
    public ArrayList<Character> result = new ArrayList<>();
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character,TrieNode> child = root.child;
        for(int i = 0;i < word.length();i++){
            char c = word.charAt(i);
            TrieNode node = new TrieNode();
            if(child.containsKey(c)){
                node = child.get(c);
            }
            else{
                node = new TrieNode(c);
                child.put(c,node);
            }
            child = node.child;
            if(i == word.length() - 1) node.isWord = true;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode T = searchNode(word);
        return T != null&&T.isWord;
    }


    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }




    public TrieNode searchNode(String word) {
        HashMap<Character,TrieNode> child = root.child;
        TrieNode T = null;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!child.containsKey(c)){
                return null;
            }
            T = child.get(c);
            child = T.child;
        }
        return T;
    }

//返回所有相同前序的字符串
public List<String> showSamePrefix(TrieNode tnode){
    List<String> Result = new ArrayList<String>();
    if(tnode == null){
        return Result;
    }

    String temp = new String();
    prefixHelper(tnode,temp,Result);
    //System.out.print(Result);
    return Result;
}
//递归辅助函数
    public void prefixHelper(TrieNode tnode, String temp, List<String> Result){
        if (tnode == null) {
            return;
        }

        temp += String.valueOf(tnode.c);
        //System.out.print(temp);
        int length = temp.length();

        if (tnode.isWord) {
            Result.add(temp);
            return;
        }

        HashMap<Character, TrieNode> hs = new HashMap<>();
        hs = tnode.child;
        for(Character c : hs.keySet()){
            prefixHelper(hs.get(c),temp,Result);
            temp.substring(0,length - 1);

        }
    }
}

