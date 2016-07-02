package enigma;

/** Class that represents a rotor that has no ratchet and does not advance.
 *  @author
 */
class FixedRotor extends Rotor {

    // This needs other methods or constructors.


    FixedRotor(String rotorName, String rightToLeft, String leftToRight) {
        super(rotorName, rightToLeft, leftToRight, "");
    }
    @Override
    boolean advances() {
        return false;
    }

    @Override
    boolean atNotch() {
        return false;
    }

    /** Fixed rotors do not advance. */
    @Override
    void advance() {
    }

}
