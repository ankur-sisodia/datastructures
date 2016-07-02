// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a reflector in the enigma.
 *  @author asisodia
 */
class Reflector extends Rotor {

    // This needs other methods or constructors.
    Reflector(String rotorName, String rightToLeft) {
        super(rotorName, rightToLeft, "", "");
    }
    @Override
    boolean hasInverse() {
        return false;
    }

    @Override
    int convertForward(int p) {
        int lookupNum = (p + getSetting());
        char convertedNum = this.myR2L.charAt(lookupNum);
        return (toIndex(convertedNum) - getSetting());
    }

    /** Returns a useless value; should never be called. */
    @Override
    int convertBackward(int unused) {
        throw new UnsupportedOperationException();
    }

}
