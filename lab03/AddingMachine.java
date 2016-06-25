import java.util.*;

public class AddingMachine {

	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean justStarting = true;
		int total = 0;
		int subtotal = 0;
		int input;
		int last = 1;
		int MAXIMUM_NUMBER_OF_INPUTS = 100;
		int[] inputArray = new int[100];
		int counter = 0;
		while (true) {
			input = scanner.nextInt();

			if (input == 0) {
				if (last == 0) {
					System.out.println("total " + total);
					for (int i =0; i<counter;i++) {
						System.out.println(inputArray[i]);
					}
					return;
				}
				System.out.println("subtotal " + subtotal);
				total += subtotal;
				subtotal = 0;
			}
			else {
				inputArray[counter] = input;
				counter++;
			}
			subtotal += input;
			last = input;
		    // TODO Your code here
		}
	}

}