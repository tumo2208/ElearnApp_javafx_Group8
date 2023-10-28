package dictionary.elearnapp_javafx_group8.Trie;

import java.util.*;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insertWord(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node = current.children.get(c);
            if (node == null) {
                node = new TrieNode();
                current.children.put(c, node);
            }
            current = node;
        }
        current.isEndOfWord = true;
    }

    public List<String> wordComplete(String word) {
        TrieNode current = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            current = current.children.get(c);
            if (current == null) {
                return res;
            }
        }
        searchWord(current, res, word);
        return res;
    }

    public void searchWord(TrieNode current, List<String> res, String word) {
        if (current == null) {
            return;
        }
        if (current.isEndOfWord) {
            res.add(word);
        }
        Map<Character, TrieNode> map = current.children;
        for (Character c : map.keySet()) {
            searchWord(map.get(c), res, word + c);
        }
    }
}