public class JugContents {

    public int jugs[];	// contents of the three jugs.
    
    public JugContents (int amt0, int amt1, int amt2) {
        jugs = new int [3];
        jugs[0] = amt0;
        jugs[1] = amt1;
        jugs[2] = amt2;
    }
    
    public JugContents (JugContents b) {
        jugs = new int [3];
        jugs[0] = b.jugs[0];
        jugs[1] = b.jugs[1];
        jugs[2] = b.jugs[2];
    }
    
    public String toString() {
        return "Configuration = (" + jugs[0] + "," 
            + jugs[1] + "," + jugs[2] + ")";
    }
    
    // YOUR CODE HERE
    // equals method && hashCode method

    public boolean equals(JugContents jug) {
        if ((jugs[0] == jug.jugs[0]) && (jugs[1] == jug.jugs[1]) && (jugs[2] == jug.jugs[2])) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode()
    {
        int hashValue = 0;
        for(int i =  0; i < jugs.length; i++)
        {
            hashValue = hashValue + jugs[i]*(10^(jugs.length-i));
        }
        return hashValue;
    }
}
