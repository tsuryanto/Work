package com.tsuryanto.worktime.utils;

import com.tsuryanto.worktime.data.Hours;

import static com.tsuryanto.worktime.WorkTime.NORMAL_WORK_TIME;

public class Overtime {

    private static final int ONE_HOUR_CALCULATION = 1;
    private static final int TWO_HOUR_CALCULATION = 2;
    private static final int NEXT_HOUR_CALCULATION = 2;

    private Hours hours;
    private int totalOvertimeHours;
    private int overtimeHours;
    private boolean isOvertime;

    public Overtime(Hours hour){
        this.hours = hour;
        this.isOvertime = overtimeStatus();

        setTotalOverTimeHours();
    }

    private boolean overtimeStatus(){
        overtimeHours = (hours.getWorkEnd() - hours.getWorkStart()) - NORMAL_WORK_TIME;
        return (overtimeHours > 0);
    }

    public boolean getIsOvertime(){
        return isOvertime;
    }

    public int getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    private void setTotalOverTimeHours(){
        if (isOvertime){
            for (int i = 1; i <= overtimeHours; i++){
                if (i == 1) {
                    totalOvertimeHours = totalOvertimeHours + ONE_HOUR_CALCULATION;
                }
                else if (i == 2) {
                    totalOvertimeHours = totalOvertimeHours + TWO_HOUR_CALCULATION;
                }
                else {
                    totalOvertimeHours = totalOvertimeHours + NEXT_HOUR_CALCULATION;
                }
            }
        }
        else {
            totalOvertimeHours = 0;
        }
    }

}
