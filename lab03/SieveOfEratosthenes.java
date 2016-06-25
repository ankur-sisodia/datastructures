public class SieveOfEratosthenes {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("You need to enter an argument!");
			return;
		}
        int upperBound = 0;
        try {
            upperBound = Integer.parseInt(args[0]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int counter;
		boolean[] isNotPrime = new boolean[upperBound+1];
		for (int i = 0; i < isNotPrime.length; i++) {
			if (isNotPrime[i] == true) {
				continue;
			} else {
				if (i<2){isNotPrime[i]=true;}
				else {
                    if(i==2){isNotPrime[i]=false;}
					counter = 2*i;
                    while (counter < upperBound) {
                        isNotPrime[counter] = true;
                        counter = counter + i;
                    }
                }
			}
		}
		for (int i = 0; i < isNotPrime.length; i++) {
			//System.out.println(i + "is a " + isNotPrime[i]);
			if (!isNotPrime[i]) {
				System.out.println(i + " is a prime number.");
			}
		}
	}
}