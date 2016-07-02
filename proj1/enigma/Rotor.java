// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a rotor in the enigma machine.
 *  @asisodia
 */
class Rotor {
    // This needs other methods, fields, and constructors.
    protected String myRotorName, myR2L, myL2R, myNotches;

    /** Size of alphabet used for plaintext and ciphertext. */
    static final int ALPHABET_SIZE = 26;


    Rotor(String rotorName, String rightToLeft, String leftToRight, String notches) {
        myRotorName = rotorName;
        myR2L = rightToLeft;
        myL2R = leftToRight;
        myNotches = notches;
        //System.out.println("setting:" + _setting);
        //System.out.println("mynotches: "+ myNotches);
    }


    /** Assuming that P is an integer in the range 0..25, returns the
     *  corresponding upper-case letter in the range A..Z. */
    static char toLetter(int p) {
        return (char) ('A' + p);
        //return 'A';  // FIXME
    }

    /** Assuming that C is an upper-case letter in the range A-Z, return the
     *  corresponding index in the range 0..25. Inverse of toLetter. */
    static int toIndex(char c) {
        return (int) (c - 'A');
        // FIXME
    }

    /** Returns true iff this rotor has a ratchet and can advance. */
    boolean advances() {
        return true;
    }

    /** Returns true iff this rotor has a left-to-right inverse. */
    boolean hasInverse() {
        if (myR2L.equals("")) {
            return false;
        }
        return true;
    }

    /** Return my current rotational setting as an integer between 0
     *  and 25 (corresponding to letters 'A' to 'Z').  */
    int getSetting() {
        return _setting;
    }

    /** Set getSetting() to POSN.  */
    void set(int posn) {
        assert 0 <= posn && posn < ALPHABET_SIZE;
        _setting = posn;
    }

    /** Return the conversion of P (an integer in the range 0..25)
     *  according to my permutation. */
    int convertForward(int p) {
        int lookupNum = (((p + getSetting()) % ALPHABET_SIZE) + 26) % 26;
        char convertedNum = this.myR2L.charAt(lookupNum);
        return (((toIndex(convertedNum) - getSetting()) % ALPHABET_SIZE) + 26) % 26;
    }

    /** Return the conversion of E (an integer in the range 0..25)
     *  according to the inverse of my permutation. */
    int convertBackward(int e) {
        int lookupNum = (((e + getSetting()) % ALPHABET_SIZE) + 26) % 26;
        char convertedNum = this.myL2R.charAt(lookupNum);
        return (((toIndex(convertedNum) - getSetting()) % ALPHABET_SIZE) + 26) % 26;
    }

    /** Returns true iff I am positioned to allow the rotor to my left
     *  to advance. */
    boolean atNotch() {

        char[] myNotchesSplit = myNotches.toCharArray();
        for (char ch : myNotchesSplit) {
            if (toIndex(ch) == getSetting()) {
                return true;
            }
        }
        return false; // FIXME
    }

    /** Advance me one position. */
    void advance() {
        _setting = (_setting+1)%ALPHABET_SIZE;
    }

    /** My current setting (index 0..25, with 0 indicating that 'A'
     *  is showing). */
    private int _setting;

}
