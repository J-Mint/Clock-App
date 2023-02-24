import javax.swing.Spring;

/**
 * Write a description of class NumberDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumberDisplay
{
    private int value;
    private int limit;

    /**
     * Constructor for objects of class NumberDisplay
     */
    public NumberDisplay(int limit)
    {
        this.limit = limit;
        value = 0;      

    }

    public int getValue() {
        return value;
    }

    public void setValue(int x){
        if( x <= limit-1 && x >= 0){
            value = x;
        } else{
            System.out.println("Please enter an acceptable value for the minutes/seconds between and including 0 and 60 and hours between and including 0 and 24");
        }
    }

    public int getLimit() {
        return limit;
    }

    public void increment() {
        value++;
        if(value == limit) {
            value = 0;
        }
    }

    public String getFormattedValue(){
        String formattedValue;
        if (value < 10){
            formattedValue= "0" + value;
            return formattedValue;
        } else {
            formattedValue = "" + value;   
            return formattedValue;
        }
    }

}
