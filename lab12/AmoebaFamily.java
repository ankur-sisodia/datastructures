import java.util.*;
import java.util.function.Consumer;

/**
 * An AmoebaFamily is a tree, where nodes are Amoebas, each of which can have
 * any number of children.
 */
public class AmoebaFamily implements Iterable<AmoebaFamily.Amoeba> {
    /**
     * ROOT is the root amoeba of this AmoebaFamily
     */
    public Amoeba root = null;

    /**
     * A constructor that starts an Amoeba family with an amoeba
     * @param  name the name of the first Amoeba of this AmoebaFamily
     */
    public AmoebaFamily(String name) {
        root = new Amoeba(name, null);
    }

    /**
     * Adds a new Amoeba with childName to this AmoebaFamily
     * as the youngest child of the ameoba named parentName
     * Precondition: This AmoebaFamily must contain an Amoeba named parentName.
     * @param parentName name of the parent Amoeba
     * @param childName  name of the Amoeba to add as parentName's child
     */
    public void addChild(String parentName, String childName) {
        if (root != null) {
            root.addChild(parentName, childName);
        }
    }

    /**
     * Changes the names for all Amoebas in this AmoebaFamily to use only
     * lowercase letters.
     */
    public void makeNamesLowercase() {
        // Your goal is to make this as similar as possible to addChild
        if (root != null) {
            root.makeNamesLowercase();
        }
    }

    /**
     * Replaces the name of an amoeba named currentName with the name newName.
     * Precondition: This AmoebaFamily contains exactly one Amoeba named
     * currentName.
     */
    public void replaceName(String currentName, String newName) {
        // Your goal is to make this as similar as possible to addChild
        if (root != null) {
            root.replaceName(currentName, newName);
        }
    }

    /**
     * Print the names of all amoebas in the family, one on each line.
     * Later you will write print() that has more interesting formatting
     */

    public void printFlat() {
        // Your goal is to make this as similar as possible to addChild
        if (root != null) {
            root.printFlat();
        }
    }

    /**
     * Prints the name of all Amoebas in this AmoebaFamily in preorder, with
     * the oldest Amoeba printed first.
     * Members of the AmoebaFamily constructed in the main method should
     * be printed in the following sequence:
     * Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
     * Bill, Hilary, Fred, Wilma, auntie
     * This should be formated as stated in the Pretty Print section of lab.
     */
    public void print() {
        // YOUR CODE HERE
        if (root != null) {
            System.out.println(root.name);
            root.print();
        }
    }

    /**
     * Returns the length of the longest name in this AmoebaFamily
     */
    public int longestNameLength() {
        if (root != null) {
            return root.longestNameLength();
        }
        return 0;
    }

    /**
     * Returns the longest name in this AmoebaFamily
     */
    public String longestName() {
        // your goal is to make this look as similar as possible to
        // longestNameLength
        if (root != null) {
            return root.longestName();
        }
        return null;
    }

    public int size() {
        if (root != null) {
            return root.size();
        }
        return -1;
    }

    public int height() {
        if (root != null) {
            return root.height();
        }
        return -1;
    }

    /**
     * Returns an Iterator for this AmoebaFamily
     */
    public Iterator<Amoeba> iterator() {
        return new AmoebaIterator();
    }

    /**
     * Creates a new AmoebaFamily and prints it out
     * @param args command line arguments
     */
    public static void main(String[] args) {
        AmoebaFamily family = new AmoebaFamily("Amos McCoy");
        family.addChild("Amos McCoy", "mom/dad");
        family.addChild("Amos McCoy", "auntie");
        family.addChild("mom/dad", "me");
        family.addChild("mom/dad", "Fred");
        family.addChild("mom/dad", "Wilma");
        family.addChild("me", "MikeMikeMikeMikeMike");
        family.addChild("me", "Homer");
        family.addChild("me", "Marge");
        family.addChild("Mike", "Bart");
        family.addChild("Mike", "Lisa");
        family.addChild("Marge", "Bill");
        family.addChild("Marge", "Hilary");
        //System.out.println("Here's the family:");
        //family.print();
        System.out.println("Longest name: " + family.longestName());
    }

    /**
     * Ignore for lab12
     *
     * An Iterator class for the AmoebaFamily. Amoebas are enumerated in
     * preorder, with oldest children enumerated first.
     * Members of the family constructed in main method above should be
     * enumerated in the following order:
     * Amos McCoy, mom/dad, me, Mike, Bart, Lisa, Homer, Marge,
     * Bill, Hilary, Fred, Wilma
     * Complete enumeration of a family of N amoebas should take
     * O(N) operations.
     */
    public class AmoebaIterator implements Iterator<Amoeba> {
        /**
         * AmoebaIterator constructor. Sets up all of the initial information
         * for the AmoebaIterator
         */
        public AmoebaIterator() {}

        /**
         * Returns true if there is a next element that has not
         * been seen yet
         */
        public boolean hasNext() {
            return false;
        }
        /**
         * Returns the next element in preorder
         */
        public Amoeba next() {
            return null;
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
        }

        @Override
        public void forEachRemaining(Consumer<? super Amoeba> action) {
            // TODO Auto-generated method stub
        }


    }

    /**
     * An Amoeba is a node of an AmoebaFamily
     */
    public static class Amoeba {

        /**
         * name is the name of this Amoeba
         * parent is the parent of this Amoeba
         * children contains all of the child Amoebas of this Amoeba
         */
        public String name;
        public Amoeba parent;
        public ArrayList<Amoeba> children;
        static int size;

        /**
         * Amoeba constructor
         * @param  name     the name for this Amoeba
         * @param  parent the parent for this Amoeba
         */
        public Amoeba(String name, Amoeba parent) {
            this.name = name;
            this.parent = parent;
            this.children = new ArrayList<Amoeba>();
            size = 1;
        }


        /**
         * Returns a String representation of this Amoeba
         */
        public String toString() {
            return name;
        }

        /**
         * Getter method for the parent of this Amoeba
         */
        public Amoeba parent() {
            return parent;
        }

        /**
         * Adds a child to an Amoeba that matches parentName
         * @param parentName name of Amoeba to give a child to
         * @param childName  name of child Amoeba to add
         */
        public void addChild(String parentName, String childName) {
            if (name.equals(parentName)) {
                Amoeba child = new Amoeba(childName, this);
                children.add(child);
            } else {
                for (Amoeba a : children) {
                    a.addChild(parentName, childName);
                }
            }
        }

        //Add more void recursive functions below
        public void makeNamesLowercase() {
            name = name.toLowerCase();
            // Your goal is to make this as similar as possible to addChild
            for (Amoeba amoeba : children) {
                amoeba.makeNamesLowercase();
            }
        }

        public void replaceName(String currentName, String newName) {
            // Your goal is to make this as similar as possible to addChild
            if (name.equals(currentName)) {
                name = newName;
            }
            for (Amoeba amoeba : children) {
                amoeba.replaceName(currentName, newName);
            }
        }

        public void printFlat() {
            // TODO Auto-generated method stub
            System.out.println(name);

            for (Amoeba amoeba : children) {
                amoeba.printFlat();
            }
        }

        static int lev = 0;

        public void printHelper(ArrayList<Amoeba> children, int lev) {
            String space = "";

            for (int i = 0; i < lev; i++) {
                space = space.concat(" ");
            }

            for (Amoeba amoeba : children) {
                System.out.println(space + amoeba.name);
            }

        }

        public void print() {
            // TODO Auto-generated method stub
            lev = lev + 4;

            for (Amoeba amoeba : children) {
                amoeba.printHelper(amoeba.children, lev);
                amoeba.print();
            }
        }

        /**
         * Returns the length of the longest name between this Amoeba and its
         * children
         */
        public int longestNameLength() {
            int maxLengthSeen = name.length();
            for (Amoeba a : children) {
                maxLengthSeen = Math.max(maxLengthSeen, a.longestNameLength());
            }
            return maxLengthSeen;
        }

        public String longestName() {
            String longestName = name;
            for (Amoeba a : children) {
                String word = a.longestName();
                if (longestName.length() < word.length()) {
                    longestName = word;
                }

            }
            return longestName;
        }

        public int size() {
            // TODO Auto-generated method stub

            for (Amoeba amoeba : children) {
                size = size + 1;
                amoeba.size();
            }

            return size;
        }

        //In Amoeba
        private int height() {
            if (children.isEmpty()) {
                return 0;
            } else {
                int best = 0;
                for (Amoeba a : children) {
                    best = Math.max(a.height() + 1, best);
                }
                return best;
            }
        }
    }

    @Override
    public void forEach(Consumer<? super Amoeba> action) {
        // TODO Auto-generated method stub
    }

    @Override
    public Spliterator<Amoeba> spliterator() {
        // TODO Auto-generated method stub
        return null;
    }
}