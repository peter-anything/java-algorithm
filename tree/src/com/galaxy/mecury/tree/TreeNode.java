package com.galaxy.mecury.tree;

public class TreeNode<T> {
    TreeNode left;
    TreeNode right;
    T data;

    public TreeNode(T data) {
        this.data = data;
    }
}
