public class ModNCounter {

	private int myCount, myMod;

	public ModNCounter(int mod) {
		myCount = 0;
		myMod = mod;
	}

	public void increment() {
		myCount = (myCount + 1) % myMod;
	}

	public void reset() { myCount = 0; }

	public int value() {
		return myCount;
	}

}
