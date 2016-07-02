import static org.junit.Assert.*;

/**
 * Created by asisodia on 7/2/2016.
 */
public class MeasurementTest {
    @org.junit.Test
    public void testConstructor() {
        Measurement m = new Measurement();
        assertTrue(m.getFeet() == 0);
    }
    @org.junit.Test
    public void testConstructorFeet() {
        Measurement m = new Measurement(5);
        assertTrue(m.getFeet() == 5);
        assertTrue(m.getInches() == 0);
        ;
    }
    @org.junit.Test
    public void testConstructorFeetAndInches() {
        Measurement m = new Measurement(5,10);
        assertTrue(m.getFeet() == 5);
        assertTrue(m.getInches() == 10);
    }

    @org.junit.Test
    public void testPlus() throws Exception {
        Measurement a = new Measurement(6,0);
        Measurement b = new Measurement(5,2);
        a = a.plus(b);
        assertTrue(a.getFeet() == 11 && a.getInches() == 2);
    }




}