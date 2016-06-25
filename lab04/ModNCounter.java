/**
 * Created by asisodia on 6/24/2016.
 */
public class ModNCounter extends Counter {
    int modValue;

    public ModNCounter(int mod)
    {
            modValue = mod;
    }

    public void increment() {
        super.increment();
        if (super.value()>= modValue ){super.reset();}
    }
}
