package d20220727;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 按二叉树描述字符串，输出其中序遍历的结果。<br />
 * 如a{b{d,e{g,h{,i}}},c{f}}，输出dbgehiafc <br />
 * 如a{b}，输出ba <br />
 * 如a{b,c}，输出bac <br />
 */
public class T03OutputTheTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        sc.close();
        Node tree = assembleTree(line);
        List<String> names = new ArrayList<String>();
        inorder(tree, names);
        System.out.println(names.toString().replaceAll("[\\[\\], ]", ""));
    }

    private static void inorder(Node tree, List<String> names) {
        if (null != tree.left) {
            inorder(tree.left, names);
        }
        names.add(tree.name);
        if (null != tree.right) {
            inorder(tree.right, names);
        }
    }

    private static Node assembleTree(String line) {
        List<Node> currParent = new ArrayList<Node>();
        Node root = null;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != '{' && line.charAt(i) != '}' && line.charAt(i) != ',') {
                Node node = new Node(i, line.substring(i, i + 1));
                if (root == null) {
                    root = node;
                }
                if (currParent.isEmpty()) {
                    currParent.add(node);
                    continue;
                }
                Node parent = currParent.get(currParent.size() - 1);
                // 前一个是逗号，当前是右子树
                if (line.charAt(i - 1) == ',') {
                    parent.right = node;
                } else {
                    parent.left = node;
                }
                currParent.add(node);
            } else if (line.charAt(i) == ',') {
                if (line.charAt(i - 1) == '{') {
                    continue;
                } else {
                    currParent.remove(currParent.size() - 1);
                }
            } else if (line.charAt(i) == '}') {
                currParent.remove(currParent.size() - 1);
            }
        }
        return root;
    }

    private static class Node {
        private int id;
        private String name;
        private Node left;
        private Node right;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node [id=" + id + ", name=" + name + ", left=" + left + ", right=" + right + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + id;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (id != other.id)
                return false;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

    }
}
