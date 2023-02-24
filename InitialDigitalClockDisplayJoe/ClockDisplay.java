
/**
 * Write a description of class ClockDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClockDisplay
{
    // instance variables - replace the example below with your own
    private NumberDisplay hoursDisplay;
    private NumberDisplay minutesDisplay;
    private NumberDisplay secondsDisplay;
    int alarmHour;
    int alarmMinute;
    int alarmSecond;
    boolean isAlarmOn = false;
    int display;
    /**
     * Constructor for objects of class ClockDisplay
     */
    public ClockDisplay()
    {
        // initialise instance variables
        hoursDisplay = new NumberDisplay(24);
        minutesDisplay = new NumberDisplay(60);
        secondsDisplay = new NumberDisplay(60);
        updateDisplay();

    }

    public void timeTick() {
        secondsDisplay.increment();
        if(secondsDisplay.getValue() == 0) {
            minutesDisplay.increment();
            if(minutesDisplay.getValue() == 0) {
                // it just rolled over!
                hoursDisplay.increment();
            } 
        }
        displayMode();
        
    }
    
    //Switches to 24 hour format
    public void updateDisplay() {
        System.out.println(hoursDisplay.getFormattedValue() + ":" + minutesDisplay.getFormattedValue() + ":"+ secondsDisplay.getFormattedValue());
        display = 24;
    }
    
    //Switches to 12 hour format
    public void updateDisplay12Hour(){
        System.out.println(getHours() + ":" + minutesDisplay.getFormattedValue() + ":" + secondsDisplay.getFormattedValue() + getSuffix());
        display = 12;
    }

    public NumberDisplay getHoursDisplay() {
        return hoursDisplay;
    }

    public NumberDisplay getMinutesDisplay() {
        return minutesDisplay;
    }

    public NumberDisplay getSecondsDisplay(){
        return secondsDisplay;
    }

    public void displayMode(){
        if (display == 24){
            updateDisplay();
        } else {
            updateDisplay12Hour();
        }
    }
    
    public void setTime(int setHour,int setMinute){
            hoursDisplay.setValue(setHour);
            minutesDisplay.setValue(setMinute);
            secondsDisplay.setValue(0);
            displayMode();
    }
    
    public void showTheClockRunning(){
        int tickThisManyTimes = 999;
        // 1000ms = 1 second (1000 * 60)ms = 1 minute
        // a smaller value = a clock that runs faster
        int waitThisLongBetweenTicks = 1000;  
        while(0 < tickThisManyTimes--){
            timeTick();
            try{
                Thread.sleep(waitThisLongBetweenTicks);
            }catch(InterruptedException ie){ /* Do nothing */ }
            if (minutesDisplay.getValue() == alarmMinute && hoursDisplay.getValue() == alarmHour && secondsDisplay.getValue() == alarmSecond && isAlarmOn == true){
                do {
                    System.out.println("DING DING DING");
                    timeTick();
                    try{
                        Thread.sleep(waitThisLongBetweenTicks);
                    }catch(InterruptedException ie){ /* Do nothing */ }
                } while (isAlarmOn == true);
            }
        }
    }

    public void setAlarm(int alarmHour, int alarmMinute){
            if (alarmMinute >0 && alarmMinute <= 60 && alarmHour > 0 && alarmHour <=24){
                this.alarmMinute = alarmMinute;
                this.alarmHour = alarmHour;
                this.alarmSecond = 0;
                isAlarmOn = true;
            } 
    }

    public boolean isAlarmSet(){
        if (isAlarmOn == true){
            return true;
        } else {
            return false;
        }       
    }

    public String getAlarmTime(){
        if (isAlarmOn == true){
            return alarmHour + ":" + alarmMinute + ":" + alarmSecond;
        } else {
            return "No alarm set";
        }
    }

    public void cancelAlarm(){
        isAlarmOn = false;
    }
    
        public String getSuffix(){
        String suffix;
        if (hoursDisplay.getValue() < 12) {
            suffix = "am";
            return suffix;
        } else {
            suffix = "pm";
            return suffix;
        }
    }
    
    public int getHours(){
        int hours = hoursDisplay.getValue();
        if (hours <= 12){
            return hours;
        } else{
            hours -= 12;
            return hours;
        }
    }
    
}
    