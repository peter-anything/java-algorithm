package com.galaxy.mecury.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie {
    public static void main(String[] args) {
        String[] words = new String[] { "杨文婷","联系","杨洋洋","杨sir大警官","杨y文w婷t","杨文婷是小学生",
                "杨钰莹","杨文婷ywt是小学生","联系a群众","阳光么","阳光明媚","ywt是小学生","联系ywt","杨文t爱吃面","杨文婷妹妹",
                "杨光明眉","小学生","杨文婷爱吃面","我是小学生","我是中国人","ywt要吃面","y杨文婷","有问题"};
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        System.out.println(trie.fuzzySearch("杨文"));

    }

    private TrieNode root;


    public void insert(String word) {
        if (root == null) {
            root = new TrieNode(' ');
        }
        char[] arr = word.toCharArray();
        TrieNode currNode = root;
        for (char c : arr) {
            TrieNode node = currNode.findNode(c);
            if (node == null) {
                TrieNode n = new TrieNode(c);
                currNode.childList.add(n);
                currNode = n;
            } else {
                currNode = node;
            }
        }

        currNode.isEnd = true;
    }

    public List<String> fuzzySearch(String word) {
        char[] arr = word.toCharArray();
        List<String> words = new LinkedList<>();
        TrieNode currNode = root;
        StringBuffer stringBuffer = new StringBuffer();
        TrieNode n = null;
        for (char c : arr) {
            n = currNode.findNode(c);
            if (n != null) {
                currNode = n;
                stringBuffer.append(c);
            }
        }

        if (n != null) {
            for (TrieNode childNode : n.childList) {
                words.add(stringBuffer.toString() + childNode.c);
            }
        }

        return words;
    }

    public boolean search(String word) {
        char[] arr = word.toCharArray();
        TrieNode currNode = root;
        for (char c : arr) {
            TrieNode n = currNode.findNode(c);
            if (n != null) {
                currNode = n;
                if (n.isEnd) {
                    if (n.c == arr[arr.length - 1]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static class TrieNode {
        char c;
        boolean isEnd;
        List<TrieNode> childList;
        TrieNode(char c) {
            this.c = c;
            this.isEnd = false;
            childList = new LinkedList<>();
        }

        public TrieNode findNode(char c) {
            for (TrieNode node : childList) {
                if (node.c == c) {
                    return node;
                }
            }
            return null;
        }
    }
}
