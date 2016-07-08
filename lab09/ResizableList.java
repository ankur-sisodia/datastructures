/**
 * Created by asisodia on 7/7/2016.
 */
public class ResizableList extends FixedSizeList {
    public ResizableList (){
        values = new int[2];
    }

    @Override
    public void add (int k){
        if (count == values.length) {
            values = new int[values.length * 2];
        }
        values[count] = k;
        count++;
    }

    public void add(int i, int k) {
        if (count == values.length) {
            values = new int[values.length *2];
        }

        for (int j = count; j > 1; j--) {
            values[j] = values[j -1];
        }
        values[i] = k;
        count++;
    }

}
