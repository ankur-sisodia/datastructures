public class Counter {

    private int myCount;

    public Counter() {
        myCount = 0;
    }

    public void increment() {
        myCount++;
    }

    public void reset() {
        myCount = 0;
    }
    
    public int value() {
        return myCount;
    }

    public static void main(String[] args) {
        ModNCounter modCounter = new ModNCounter(3);
        modCounter.increment();
        modCounter.increment();
        modCounter.increment();
        modCounter.increment();
        System.out.println(modCounter.value()); // prints 1
    }
}
