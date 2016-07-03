package enigma;

import java.io.*;

/** Enigma simulator.
 *  @author asisodia
 */
public final class Main {

    // WARNING: Not all methods that have code in them are complete!
    static Rotor r1, r2, r3, r4, r5, r6, r7, r8, rBETA, rGAMMA, rB, rC;
    static boolean configCheck = false;

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
                } else if (configCheck) {
                    writeMessageLine(M.convert(standardize(line)), outputFilename);
                } else {
                    if (M == null) {
                        throw new EnigmaException("Bad input.1");
                    }
                }
            }
        } catch (IOException excp) {
            System.err.printf("Input error: %s%n", excp.getMessage());
            System.exit(1);
        }
    }

    /** Return true iff LINE is an Enigma configuration line. */
    private static boolean isConfigurationLine(String line) {
        if (!line.startsWith("*") && !configCheck) {
            throw new EnigmaException("Bad input.22");
        } else if (line.startsWith("*") && !configCheck) {
            configCheck = true;
            return true;
        } else if (line.startsWith("*") && configCheck) {
            configCheck = true;
            return true;
        } else if (line.equals("Message with no config")) {
            throw new EnigmaException("Bad input.25");
        }
        return  false;
    }

    /** Configure M according to the specification given on CONFIG,
     *  which must have the format specified in the assignment. */
    private static void configure(Machine M, String config) {
        // ANKUR UPDATING
        String[] configSplit = config.split(" ");
        Rotor[] rotorsToConfig = new Rotor[5];
        String[][] spec = PermutationData.ROTOR_SPECS;
        if (configSplit[3].equals(configSplit[4]) || configSplit[3].equals(configSplit[5])
                || configSplit[4].equals(configSplit[5])) {
            throw new EnigmaException("Bad input.2");
        }
        switch (configSplit[1]) {
            case "B":
                rotorsToConfig[0] = rB;
                break;
            case "C":
                rotorsToConfig[0] = rC;
                break;
            default:
                throw new EnigmaException("Bad input.3");
        }
        switch (configSplit[2]) {
            case "BETA":
                rotorsToConfig[1] = rBETA;
                break;
            case "GAMMA":
                rotorsToConfig[1] = rGAMMA;
                break;
            default:
                throw new EnigmaException("Bad input.4");
        }
        for (int i = 2; i < rotorsToConfig.length; i++) {
            switch (configSplit[(i + 1)]) {
                case "I":
                    rotorsToConfig[i] = r1;
                    break;
                case "II":
                    rotorsToConfig[i] = r2;
                    break;
                case "III":
                    rotorsToConfig[i] = r3;
                    break;
                case "IV":
                    rotorsToConfig[i] = r4;
                    break;
                case "V":
                    rotorsToConfig[i] = r5;
                    break;
                case "VI":
                    rotorsToConfig[i] = r6;
                    break;
                case "VII":
                    rotorsToConfig[i] = r7;
                    break;
                case "VIII":
                    rotorsToConfig[i] = r8;
                    break;
                default:
                    break;
            }
        }
        M.replaceRotors(rotorsToConfig);
        M.setRotors(configSplit[6]);
    }


    /** Return the result of converting LINE to all upper case,
     *  removing all blanks and tabs.  It is an error if LINE contains
     *  characters other than letters and blanks. */
    private static String standardize(String line) {
        for (int i = 1; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (!Character.isLetter(ch) && !(ch == ' ')) {
                throw new EnigmaException("Bad input.7");
            }
        }
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
        String[][] spec = PermutationData.ROTOR_SPECS;
        r1 = new Rotor(spec[0][0], spec[0][1], spec[0][2], spec[0][3]);
        r2 = new Rotor(spec[1][0], spec[1][1], spec[1][2], spec[1][3]);
        r3 = new Rotor(spec[2][0], spec[2][1], spec[2][2], spec[2][3]);
        r4 = new Rotor(spec[3][0], spec[3][1], spec[3][2], spec[3][3]);
        r5 = new Rotor(spec[4][0], spec[4][1], spec[4][2], spec[4][3]);
        r6 = new Rotor(spec[5][0], spec[5][1], spec[5][2], spec[5][3]);
        r7 = new Rotor(spec[6][0], spec[6][1], spec[6][2], spec[6][3]);
        r8 = new Rotor(spec[7][0], spec[7][1], spec[7][2], spec[7][3]);
        rBETA =  new FixedRotor(spec[8][0], spec[8][1], spec[8][2]);
        rGAMMA = new FixedRotor(spec[9][0], spec[9][1], spec[9][2]);
        rB = new Reflector(spec[10][0], spec[10][1]);
        rC = new Reflector(spec[11][0], spec[11][1]);
    }

}

