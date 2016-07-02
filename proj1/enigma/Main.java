package enigma;

import java.io.*;

/** Enigma simulator.
 *  @author asisodia
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!
    static Rotor r1, r2, r3, r4, r5,r6,r7,r8, rBETA, rGAMMA, rB, rC;

    /** Process a sequence of encryptions and decryptions, as
     *  specified in the input from the standard input.  Print the
     *  results on the standard output. Exits normally if there are
     *  no errors in the input; otherwise with code 1. */
    public static void main(String[] args) {
        Machine M;
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                    new InputStreamReader(new FileInputStream(args[0])));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file found.");
        }

        String outputFilename;
        if (args.length >= 2) {
            outputFilename = args[1];
        } else {
            outputFilename = "output.txt";
        }

        buildRotors();
        M = null;
        try {
            while (true) {
                String line = input.readLine();
                if (line == null) {
                    break;
                }
                if (isConfigurationLine(line)) {
                    M = new Machine();
                    configure(M, line);
                } else {
                    if (M == null) {
                        throw new EnigmaException();
                    }
                    writeMessageLine(M.convert(standardize(line)),
                                     outputFilename);
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        //ANKUR UPDATE ALL THE CASES
        /*
        The input might not start with a configuration.
        The configuration line can contain the wrong number of arguments.
        The rotors might be misnamed.
        A rotor might be repeated in the configuration.
        The first rotor might not be a reflector.
        The initial positions string might be the wrong length or contain non-alphabetic characters.
        The message might contain non-alphabetic characters.
         */
        if (line.equals("")) return false;
        if (line.split(" ").length != 7) return false;
        if (!line.startsWith("*")) return false;
        String [] split = line.split(" ");
        for (int i=0; i<7; i++)
        {
            for (int j=i+1; j<7; j++){
                if (split[i].equals(split[j]))
                    return false;
            }
        }
        return  true;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        // ANKUR UPDATING
        String[] configSplit = config.split(" ");
        Rotor[] rotorsToConfig = new Rotor[5];
        String[][] SPECS = PermutationData.ROTOR_SPECS;
       for (int i =0; i< rotorsToConfig.length; i++) {
           if (configSplit[i + 1].equals(SPECS[0][0])) {rotorsToConfig[i] = r1;}
           if (configSplit[i + 1].equals(SPECS[1][0])) {rotorsToConfig[i] = r2;}
           if (configSplit[i + 1].equals(SPECS[2][0])) {rotorsToConfig[i] = r3;}
           if (configSplit[i + 1].equals(SPECS[3][0])) {rotorsToConfig[i] = r4;}
           if (configSplit[i + 1].equals(SPECS[4][0])) {rotorsToConfig[i] = r5;}
           if (configSplit[i + 1].equals(SPECS[5][0])) {rotorsToConfig[i] = r6;}
           if (configSplit[i + 1].equals(SPECS[6][0])) {rotorsToConfig[i] = r7;}
           if (configSplit[i + 1].equals(SPECS[7][0])) {rotorsToConfig[i] = r8;}
           if (configSplit[i + 1].equals(SPECS[8][0])) {rotorsToConfig[i] = rBETA;}
           if (configSplit[i + 1].equals(SPECS[9][0])) {rotorsToConfig[i] = rGAMMA;}
           if (configSplit[i + 1].equals(SPECS[10][0])) {rotorsToConfig[i] = rB;}
           if (configSplit[i + 1].equals(SPECS[11][0])) {rotorsToConfig[i] = rC;}
           /* switch case SPECS[11][0]: rotorsToConfig[i]= rC; break;*/
            }
        M.replaceRotors(rotorsToConfig);
        M.setRotors(configSplit[6]);

        }


    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        line = line.toUpperCase().trim();
        line = line.replace(" ", "");
        return line; // FIXME}
    }

    /** Write MSG in groups of five to out file (except that the last
     *  group may have fewer letters). */
    private static void writeMessageLine(String msg, String filename) {
        try {
            FileWriter writer = new FileWriter(filename, true);
            String outputString = ""; 
            for (int i = 0; i < msg.length(); i += 5) {
                outputString += msg.substring(i, Math.min(i + 5, msg.length()));
                if (i + 5 < msg.length()) {
                    outputString += " ";
                }
            }

            writer.write(outputString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException when writing: " + e);
        }
    }

    /** Create all the necessary rotors. */
    private static void buildRotors() {
        String[][] SPECS = PermutationData.ROTOR_SPECS;
        r1 = new Rotor(SPECS[0][0],SPECS[0][1],SPECS[0][2],SPECS[0][3]);
        r2 = new Rotor(SPECS[1][0],SPECS[1][1],SPECS[1][2],SPECS[1][3]);
        r3 = new Rotor(SPECS[2][0],SPECS[2][1],SPECS[2][2],SPECS[2][3]);
        r4 = new Rotor(SPECS[3][0],SPECS[3][1],SPECS[3][2],SPECS[3][3]);
        r5 = new Rotor(SPECS[4][0],SPECS[4][1],SPECS[4][2],SPECS[4][3]);
        r6 = new Rotor(SPECS[5][0],SPECS[5][1],SPECS[5][2],SPECS[5][3]);
        r7 = new Rotor(SPECS[6][0],SPECS[6][1],SPECS[6][2],SPECS[6][3]);
        r8 = new Rotor(SPECS[7][0],SPECS[7][1],SPECS[7][2],SPECS[7][3]);
        rBETA =  new FixedRotor(SPECS[8][0],SPECS[8][1],SPECS[8][2]);
        rGAMMA = new FixedRotor(SPECS[9][0],SPECS[9][1],SPECS[9][2]);
        rB = new Reflector(SPECS[10][0],SPECS[10][1]);
        rC = new Reflector(SPECS[11][0],SPECS[11][1]);
    }

}

