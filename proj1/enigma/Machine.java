// This is a SUGGESTED skeleton file.  Throw it away if you don't use it.
package enigma;

/** Class that represents a complete enigma machine.
 *  @asisodia
 */
class Machine {

    // This needs other methods or constructors.
    static Rotor[] myRotors;
    static int myNumRotors;

    /**
     * Set my rotors to (from left to right) ROTORS.  Initially, the rotor
     * settings are all 'A'.
     */
    Machine() {
        myRotors = new Rotor[5];
        myNumRotors = 5;
    }

    void replaceRotors(Rotor[] rotors) {
//        myRotors = rotors;
        myNumRotors = 5;
        for (int i = 0; i < 5; i++) {
            myRotors[i] = rotors[i];
        } //Set to A
    }
    /** Set my rotors according to SETTING, which must be a string of four
     *  upper-case letters. The first letter refers to the leftmost
     *  rotor setting.  */
    void setRotors(String setting) {
        char[] settingChar = setting.toCharArray();
        for (int i = 0; i < myNumRotors - 1; i++) {
            myRotors[i + 1].set(Rotor.toIndex(settingChar[i]));
        }
        // FIXME
    }
    /** Returns the encoding/decoding of MSG, updating the state of
     *  the rotors accordingly. */
    String convert(String msg) {
        char[] settingChar = msg.toCharArray();
        int codeTracker;
        String newMSG = "";
        for (int i = 0; i < settingChar.length; i++) {
            if (myRotors[3].atNotch()){
                myRotors[2].advance();
                myRotors[3].advance();
                myRotors[4].advance();
            }
            else if (myRotors[4].atNotch()) {
                myRotors[3].advance();
                myRotors[4].advance();
            }
            else {
                myRotors[4].advance();
            }
            codeTracker = Rotor.toIndex(settingChar[i]);
            codeTracker = myRotors[4].convertForward(codeTracker);
            codeTracker = myRotors[3].convertForward(codeTracker);
            codeTracker = myRotors[2].convertForward(codeTracker);
            codeTracker = myRotors[1].convertForward(codeTracker);
            codeTracker = myRotors[0].convertForward(codeTracker);
            codeTracker = myRotors[1].convertBackward(codeTracker);
            codeTracker = myRotors[2].convertBackward(codeTracker);
            codeTracker = myRotors[3].convertBackward(codeTracker);
            codeTracker = myRotors[4].convertBackward(codeTracker);
            newMSG = newMSG + Rotor.toLetter(codeTracker);
        }
        return newMSG; //NEED TO UPDATE!!
        // FIXME
    }
}
