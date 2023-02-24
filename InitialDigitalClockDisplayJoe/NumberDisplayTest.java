

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class NumberDisplayTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class NumberDisplayTest
{
    /**
     * Default constructor for test class NumberDisplayTest
     */
    public NumberDisplayTest()
    {
    }
    
    @Test
    public void constructorTest(){
        NumberDisplay numDisp = new NumberDisplay(3);
        assertEquals(3, numDisp.getLimit());
        assertEquals(0, numDisp.getValue());
    }

    @Test
    public void incrementTest() {
        NumberDisplay numDisp = new NumberDisplay(3);
        assertEquals(0, numDisp.getValue());
        assertEquals(3, numDisp.getLimit());
        numDisp.increment();
        assertEquals(1, numDisp.getValue());
        numDisp.increment();
        assertEquals(2, numDisp.getValue());
        numDisp.increment();
        assertEquals(0, numDisp.getValue());
    }
    
    @Test
    public void formattingTest(){
        NumberDisplay numDisp = new NumberDisplay(12);
        assertEquals("00", numDisp.getFormattedValue());
        numDisp.increment();
        assertEquals("01", numDisp.getFormattedValue());
        numDisp.increment();
        assertEquals("02", numDisp.getFormattedValue());
        numDisp.increment();
        assertEquals("03", numDisp.getFormattedValue());
        numDisp.increment();
        numDisp.increment();
        numDisp.increment();
        numDisp.increment();
        numDisp.increment();
        numDisp.increment();
        numDisp.increment();
        assertEquals("10", numDisp.getFormattedValue());
        numDisp.increment();
        assertEquals("11", numDisp.getFormattedValue());
        numDisp.increment();
        assertEquals("00", numDisp.getFormattedValue());
    }
    


}
