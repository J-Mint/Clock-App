
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClockDisplayTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClockDisplayTest
{
    /**
     * Default constructor for test class ClockDisplayTest
     */

    @Test
    public void clockDisplayConstructor() {
        ClockDisplay display = new ClockDisplay();
        NumberDisplay seconds = display.getSecondsDisplay();
        int secondsValue = seconds.getValue();
        assertEquals(0, secondsValue);
        NumberDisplay minutes = display.getMinutesDisplay();
        int minutesValue = minutes.getValue();
        assertEquals(0, minutesValue);
        NumberDisplay hours = display.getHoursDisplay();
        int hoursValue = hours.getValue();
        assertEquals(0, hoursValue);
    }

    @Test
    public void timeTickTest() {
        ClockDisplay display = new ClockDisplay();
        display.timeTick();
        NumberDisplay seconds = display.getSecondsDisplay();
        int secondsValue = seconds.getValue();
        assertEquals(1, secondsValue);
        NumberDisplay minutes = display.getMinutesDisplay();
        int minutesValue = minutes.getValue();
        assertEquals(0, minutesValue);
        NumberDisplay hours = display.getHoursDisplay();
        int hoursValue = hours.getValue();
        assertEquals(0, hoursValue);
        display.timeTick();
        secondsValue = seconds.getValue();
        assertEquals(2, secondsValue);
        minutesValue = minutes.getValue();
        assertEquals(0, minutesValue);
        hoursValue = hours.getValue();
        assertEquals(0, hoursValue);
        for (int i=0; i<60; i++) {
            display.timeTick();
        }
        secondsValue = seconds.getValue();
        assertEquals("After 62 ticks, the time should be zero hours, one minute and two seconds\n",2, secondsValue);
        minutesValue = minutes.getValue();
        assertEquals("After 62 ticks, the time should be zero hours, one minute and two seconds\n",1, minutesValue);
        hoursValue = hours.getValue();
        assertEquals("After 62 ticks, the time should be zero hours, one minute and two seconds\n",0, hoursValue);
        for (int i=0; i<(60*60); i++) {
            display.timeTick();
        }
        secondsValue = seconds.getValue();
        assertEquals("After " + ((60*60)+62) + " ticks, the time should be one hour, one minute and two seconds\n",2, secondsValue);
        minutesValue = minutes.getValue();
        assertEquals("After " + ((60*60)+62) + " ticks, the time should be one hour, one minute and two seconds\n",1, minutesValue);
        hoursValue = hours.getValue();
        assertEquals("After " + ((60*60)+62) + " ticks, the time should be one hour, one minute and two seconds\n",1, hoursValue);
    }
    
    @Test
    public void correctSuffixAnd12HourFormat(){
        ClockDisplay disp = new ClockDisplay();
        NumberDisplay hours = disp.getHoursDisplay();
        int hoursValue = hours.getValue();
        //switch to 12hour mode
        disp.updateDisplay12Hour();
        assertEquals(0, disp.getHours());
        assertEquals("am", disp.getSuffix());
        //set the time to 1 am
        for (int i=0; i<(60*60); i++) {
            disp.timeTick();
        }
        hoursValue = hours.getValue();
        assertEquals(1, disp.getHours());
        assertEquals("am", disp.getSuffix());
        //set the time to 11 am
        for (int i=0; i<(60*60*10); i++) {
            disp.timeTick();
        }
        hoursValue = hours.getValue();
        assertEquals(11, disp.getHours());
        assertEquals("am", disp.getSuffix());
        //set the time to 12 pm
        for (int i=0; i<(60*60*1); i++) {
            disp.timeTick();
        }
        hoursValue = hours.getValue();
        assertEquals(12, disp.getHours());
        assertEquals("pm", disp.getSuffix());
        //set time to 11pm
        for (int i=0; i<(60*60*11); i++) {
            disp.timeTick();
        }
        hoursValue = hours.getValue();
        assertEquals(11, disp.getHours());
        assertEquals("pm", disp.getSuffix());
        //set time to 0am 
        for (int i=0; i<(60*60*1); i++) {
            disp.timeTick();
        }
        hoursValue = hours.getValue();
        assertEquals(0, disp.getHours());
        assertEquals("am", disp.getSuffix());
    }
    
    @Test
    public void settingOfHoursAndMinutes(){
        ClockDisplay disp = new ClockDisplay();
        NumberDisplay hours = disp.getHoursDisplay();
        NumberDisplay minutes = disp.getMinutesDisplay();
        NumberDisplay seconds = disp.getSecondsDisplay();
        //hours test
        //2:02 am
        disp.setTime(2, 2);
        int hoursValue = hours.getValue();
        int minutesValue = minutes.getValue();
        int secondsValue = seconds.getValue();
        assertEquals(2, hoursValue);
        assertEquals(2, minutesValue);
        assertEquals(0, secondsValue);
        //2:34pm
        disp.setTime(14, 34);
        hoursValue = hours.getValue();
        minutesValue = minutes.getValue();
        secondsValue = seconds.getValue();
        assertEquals(14, hoursValue);
        assertEquals(34, minutesValue);
        assertEquals(0, secondsValue);
               
    }

    @Test
    public void isAlarmCreated(){
        ClockDisplay disp = new ClockDisplay();
        disp.setAlarm(1, 2);
        assertEquals(1, disp.alarmHour);
        assertEquals(2, disp.alarmMinute);
        assertEquals(true, disp.isAlarmOn);

        ClockDisplay disp1 = new ClockDisplay();
        disp1.setAlarm(-4,0);
        assertEquals(false, disp1.isAlarmOn);
        assertEquals(0, disp1.alarmHour);
    }

    @Test
    public void isAlarmSetWorking(){
        ClockDisplay disp = new ClockDisplay();
        assertEquals(false, disp.isAlarmSet());
        disp.setAlarm(1, 2);
        assertEquals(true, disp.isAlarmSet());        
    }

    @Test
    public void isGetAlarmTimeWorking(){
        ClockDisplay disp = new ClockDisplay();
        assertEquals("No alarm set", disp.getAlarmTime());
        disp.setAlarm(1, 2);
        assertEquals("1:2:0", disp.getAlarmTime());   
    }

    @Test
    public void isCancelAlarmWorking(){
        ClockDisplay disp = new ClockDisplay();
        disp.setAlarm(1, 2);
        assertEquals(true, disp.isAlarmOn);
        disp.cancelAlarm();
        assertEquals(false, disp.isAlarmOn);
    }
}
