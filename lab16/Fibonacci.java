import java.util.HashMap;

public class Fibonacci {
	int callsToFib;
	int result;

	HashMap<Integer, Integer> hashMapFib = new HashMap();

	public Fibonacci(int n){
		this.callsToFib = 0;
		this.result = fib(n);
	}
	
	private int fib(int n) {
		callsToFib++;
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			if (hashMapFib.containsKey(n))
				return hashMapFib.get(n);
			int returnValue = fib(n - 1) + fib(n - 2);
			hashMapFib.put(n, returnValue);
			return returnValue;
		}
	}

}
