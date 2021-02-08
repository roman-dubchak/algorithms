package lesson6_tree;

import sun.font.CompositeGlyphMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Lesson6_tree {
    //TODO
    // 1. Создать и запустить программу для построения двоичного дерева. В цикле построить двадцать деревьев.
    // Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int.
    // Число, которое попадает в узел, должно генерироваться случайным образом в диапазоне от -100 до 100
    // 2. Проанализировать, какой процент созданных деревьев являются несбалансированными.
    // 3. * Переписать метод добавления элемента в дерево с использованием компаратора и рекурсивного подхода

    private static class Cat {
        int age;
        String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cat cat = (Cat) o;
            return age == cat.age &&
                    Objects.equals(name, cat.name);
        }

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("C(%s, %d)", name, age);
        }
    }
    private static class Tree {


        private static class TreeNode implements Comparable {
            private Cat c;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(Cat c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", c);
            }

            @Override
            public int compareTo(Object o) {
                if (!(o instanceof Cat))
                    throw new ClassCastException("Not a cat!");
                return c.age - ((Cat) o).age;
            }
        }
        TreeNode root;

        public void insert(Cat c) {
            TreeNode node = new TreeNode(c);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (c.age < current.c.age) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            return;
                        }
                    } else if (c.age > current.c.age) {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        public Cat find(int age) {
            TreeNode current = root;
            while (current.c.age != age) {
                current = (age < current.c.age) ? current.left : current.right;
                if (current == null) return null;
            }
            return current.c;
        }

        public void preOrderTraverse(TreeNode currentNode) {
            if (currentNode != null) {
                System.out.println(currentNode);
                preOrderTraverse(currentNode.left);
                preOrderTraverse(currentNode.right);
            }
        }
        // LeftRootRight
        // RightLeftRoot
        public void displayTree() {
            preOrderTraverse(root);
        }

        public Cat delete(int age)  {
            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.c.age != age) {
                parent = current;
                if (age < current.c.age) {
                    current = current.left;
                    isLeftChild = true;
                } else  {
                    current = current.right;
                    isLeftChild = false;
                }
                if (current == null) {
                    return null;
                }
            }
            //leaf
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            }
            else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return current.c;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

        public Tree(List<Integer> treeList) {
            for (int i = 0; i < treeList.size(); i++) {
                insert(new Cat(treeList.get(i),"Cat" + treeList.get(i)));
            }
        }
        public boolean isBalance(boolean prec){
            return Math.abs(countDepth(root.left) - countDepth(root.right))
            <= ((prec) ? 0 : 1);
        }

        private int countDepth(TreeNode node){
            if (node == null) return 0;

            int left = 0;
            int right = 0;

            if (node.left != null){
                left = countDepth(node.left);
            }
            if (node.right != null){
                right = countDepth(node.right);
            }
            return 1 + Math.max(left,right);
        }
    }

    private static void uniqueRandom (List<Integer> treeList, int mount){
        Random random = new Random();
        while(treeList.size() < mount){
            int num = random.nextInt();
            if (!treeList.contains(num))
                treeList.add(num);
        }
    }

    public static void main(String[] args) {
        final int TREES = 20;
        int balance = 0;
        for (int i = 0; i < TREES; i++) {
            List<Integer> treesList = new ArrayList<>();
            uniqueRandom(treesList, 200);
            Tree tree = new Tree(treesList);
            balance += (tree.isBalance(false)) ? 1 : 0;
        }
        System.out.println( balance * 100f / TREES + "%");
    }
}
