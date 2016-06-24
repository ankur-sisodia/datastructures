public class SieveOfEratosthenes {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You need to enter an argument!");
		}
        int upperBound = 0;
        try {
            upperBound = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int counter;
		boolean[] isNotPrime = new boolean[upperBound];
		for (int i = 0; i < upperBound; i++) {
			if (isNotPrime[i] == true) {
				continue;
			} else {
				if (i<2){isNotPrime[i]=true;}
                else {
                    counter = 2 * i;
                    while (counter < upperBound) {
                        isNotPrime[counter] = true;
                        counter = counter + i;
                    }
                }
			}
		}
		for (int i = 0; i < upperBound; i++) {
			if (!isNotPrime[i]) {
				System.out.println(i + " is a prime number.");
			}
		}
	}
}