import java.util.ArrayList;

public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode t) {
        root = t;
    }

    public TreeNode getRoot() {
        return root;
    }

    // Print the values in the tree in preorder: root value first,
    // then values in the left subtree (in preorder), then values
    // in the right subtree (in preorder).
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    // Print the values in the tree in inorder: values in the left
    // subtree first (in inorder), then the root value, then values
    // in the right subtree (in inorder).
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    public void fillSampleTree1() {
        TreeNode temp = new TreeNode("a");
        root = new TreeNode("a", temp, temp);
    }

    public void fillSampleTree2() {
        root = new TreeNode("a", new TreeNode("b", new TreeNode("d", new TreeNode("e"),
                new TreeNode("f")), null), new TreeNode("c"));
    }

    public void print() {
        if (root != null) {
            root.print(0);
        }
    }

    // Contains nodes already seen in the traversal.
    private ArrayList<TreeNode> alreadySeen;

    public boolean check() {
        alreadySeen = new ArrayList<TreeNode>();
        try {
            isOK(root);
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
//        finally {
//            for (int i = 0; i < alreadySeen.size(); i++) {
//                System.out.println(alreadySeen.get(i).item);
//            }
//        }
    }

    private void isOK(TreeNode t) throws IllegalStateException {
        // TODO Auto-generated method stub
        if (alreadySeen.contains(t)) {
            throw new IllegalStateException("Item: " + t.item + " has already been seen.");
        }

        if (t.right != null) {
            isOK(t.right);
        }
        alreadySeen.add(t);
        if (t.left != null) {
            isOK(t.left);
        }
    }

    public static BinaryTree fibTree(int n) {
        BinaryTree resultFib = new BinaryTree();
        resultFib.root = resultFib.fibTreeHelper(n);
        return resultFib;
    }

    public TreeNode fibTreeHelper(int n) {

        if (n == 0) {
            return new TreeNode(0);
        }
        if (n == 1) {
            return new TreeNode(1);
        }

        int total = (Integer)fibTreeHelper(n - 1).item + (Integer)fibTreeHelper(n - 2).item;

        return new TreeNode(total, fibTreeHelper(n - 1), fibTreeHelper(n - 2));

    }

    public static BinaryTree exprTree(String s) {
        BinaryTree result = new BinaryTree();
        result.root = result.exprTreeHelper(s);
        return result;
    }
    // Return the tree corresponding to the given arithmetic expression.
    // The expression is legal, fully parenthesized, contains no blanks,
    // and involves only the operations + and *.
    private TreeNode exprTreeHelper(String expr) {
        if (expr.charAt(0) != '(') {
            // you fill this in
            if (Character.isLetter(expr.charAt(0))) {
                return new TreeNode(expr.charAt(0));
            }
            else {
                return new TreeNode(new Integer(Character.getNumericValue(expr.charAt(0))));
            }
        } else {
            // expr is a parenthesized expression.
            // Strip off the beginning and ending parentheses,
            // find the main operator (an occurrence of + or * not nested
            // in parentheses, and construct the two subtrees.
            int nesting = 0;
            int opPos = 0;
            for (int k = 1; k < expr.length() - 1; k++) {
                // you supply the missing code
                if (expr.charAt(k) == '(') {
                    nesting = nesting + 1;
                }
                else if (expr.charAt(k) == ')') {
                    nesting = nesting - 1;
                }
                else if ((expr.charAt(k) == '+' || expr.charAt(k) == '*') && nesting == 0) {
                    opPos = k;
                }
            }
            String operand1 = expr.substring(1, opPos);
            String operand2 = expr.substring(opPos + 1, expr.length() - 1);
            String op = expr.substring(opPos, opPos + 1);
            System.out.println("expression = " + expr);
            System.out.println("operand 1  = " + operand1);
            System.out.println("operator   = " + op);
            System.out.println("operand 2  = " + operand2);
            System.out.println();
            return new TreeNode(op, exprTreeHelper(operand1), exprTreeHelper(operand2)); // you fill this in
        }
    }

    public void optimize() {
        if (root != null) {
            optimizeHelper(root);
        }
    }

    private void optimizeHelper(TreeNode root) {
        if (root.left != null) {
            optimizeHelper(root.left);
        }
        if (root.right != null) {
            optimizeHelper(root.right);
        }
        if (root.item instanceof String) {
            if (root.left.item instanceof Integer && root.right.item instanceof Integer) {
                System.out.println("Left: " + root.left.item + " " + ((String) root.item).charAt(0) + " Right: " + root.right.item);
                String op = (String) root.item;
                Integer left = (Integer) root.left.item;
                Integer right = (Integer) root.right.item;

                if (op.charAt(0) == '+') {
                    root.item = (new Integer(left + right)).toString();
                } else {
                    root.item = (new Integer(left * right)).toString();
                }
                root.left = null;
                root.right = null;
                return;
            }
        }

    }

    public static void main(String[] args) {
        BinaryTree t;
        t = new BinaryTree();
        t.fillSampleTree1();
        t.fillSampleTree2();
        t.check();

        BinaryTree result = new BinaryTree();
        result = exprTree("(6*5)");
        result.optimize();
    }

    private static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }

    public static class TreeNode {

        public Object item;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Object obj) {
            item = obj;
            left = right = null;
        }

        public TreeNode(Object obj, TreeNode left, TreeNode right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }
        public TreeNode getLeft() {
            return left;
        }
        public TreeNode getRight() {
            return right;
        }
        public Object getItem() {
            return item;
        }
        private static final String indentFIX = "    ";

        private void print(int indent) {
            // TODO your code here
            if (right != null) {
                right.print(indent + 1);
            }
            println(item, indent);
            if (left != null) {
                left.print(indent + 1);
            }
        }

        private static void println(Object obj, int indent) {
            for (int k=0; k<indent; k++) {
                System.out.print(indentFIX);
            }
            System.out.println(obj);
        }
    }
}
