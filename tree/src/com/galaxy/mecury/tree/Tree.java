package com.galaxy.mecury.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Tree {
    private TreeNode root = null;
    private TreeNode current = null;
    private boolean isLeft = true;
    /**
     *          1
     *        2  5
     *      3  4  9
     *     0
     */
    public TreeNode fromArray(Integer [] dataArray) {
        List<TreeNode> q = new LinkedList<>();
        for (Integer data : dataArray) {
            if (root == null) {
                root = new TreeNode(data);
                current = root;
                q.add(root);
            } else {
                current = q.get(0);
                if (data != null) {
                    if (isLeft) {
                        current.left = new TreeNode(data);
                        q.add(current.left);
                    } else {
                        current.right = new TreeNode(data);
                        q.add(current.right);
                    }
                }

                if (!isLeft) {
                    q.remove(0);
                    current = current.left != null ? current.left : current.right;
                }
                isLeft = !isLeft;
            }
        }

        return root;
    }

    public int height(TreeNode curr) {
        if (curr == null) return 0;
        int left = height(curr.left);
        int right = height(curr.right);

        return left > right ? left + 1 : right + 1;
    }

    public void levelTraversal() {
        List<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.remove(0);
            System.out.println(current.data);
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    private void innerPreorderTraversal(TreeNode root) {
        if (root != null) {
            System.out.println(root.data);
        }
        if (root.left != null) {
            innerPreorderTraversal(root.left);
        }

        if (root.right != null) {
            innerPreorderTraversal(root.right);
        }
    }

    public void innerMiddleOrderTraversal(TreeNode root) {
        if (root.left != null) {
            innerMiddleOrderTraversal(root.left);
        }
        System.out.println(root.data);
        if (root.right != null) {
            innerMiddleOrderTraversal(root.right);
        }
    }

    public void innerPostOrderTraversal(TreeNode root) {
        if (root.left != null) {
            innerPostOrderTraversal(root.left);
        }

        if (root.right != null) {
            innerPostOrderTraversal(root.right);
        }

        System.out.println(root.data);
    }

    public void innserPreOrderNonRecursiveTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack1.isEmpty()) {
            while (current != null) {
                System.out.println(current.data);
                stack1.push(current);
                current = current.left;
            }

            if (!stack1.isEmpty()) {
                current = stack1.pop();
                current = current.right;
            }
        }
    }

    public void innserMiddleOrderNonRecursiveTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack1.isEmpty()) {
            while (current != null) {
                stack1.push(current);
                current = current.left;
            }

            if (!stack1.isEmpty()) {
                current = stack1.pop();
                System.out.println(current.data);
                current = current.right;
            }
        }
    }

    public void innerPostOrderNonRecursiveTraversal(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
            if (stack2.peek().left != null) {
                stack1.push(stack2.peek().left);
            }

            if (stack2.peek().right != null) {
                stack1.push(stack2.peek().right);
            }
        }

        while (!stack2.empty()) {
            System.out.println(stack2.pop().data);
        }
    }

    public int height() {
        //

        return 0;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            //may be not possible
            return null;
        }
    }

    public static void main(String[] args) {
        Tree t = new Tree();
        t.fromArray(new Integer[] {3,5,1,6,2,0,8,null,null,7,4});
//        t.levelTraversal();
//        t.innserMiddleOrderNonRecursiveTraversal(t.root);
        TreeNode result = t.lowestCommonAncestor(t.root, t.root.right, t.root.left.right);
        System.out.println(result);
        System.out.println(t.height(t.root));
    }
}
