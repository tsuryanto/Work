package com.tsuryanto.worktime;

import com.tsuryanto.worktime.data.Hours;
import com.tsuryanto.worktime.helpers.ErrorHandler;
import com.tsuryanto.worktime.utils.Overtime;

import java.util.Scanner;

import static com.tsuryanto.worktime.helpers.Message.ERR_UNVALID_TIME;
import static com.tsuryanto.worktime.helpers.Message.ERR_UNVALID_TIME_RANGE;

/* ----------------------------------
*   Created By Taufiq S.
* -----------------------------------*/

public class WorkTime {

    private Scanner reader;
    private static Hours mHour;
    private static ErrorHandler error;
    private int startHour;
    private int endHour;
    private static int numb = 1;

    public static final int NORMAL_WORK_TIME = 8;
    public static final int MIN_HOUR = 0;
    public static final int MAX_HOUR = 23;

    public static void main(String[] args) {
        WorkTime workTime = new WorkTime();
        error = new ErrorHandler();

        do {
            workTime.input();
            if (workTime.validate()){
                workTime.output(workTime.countWorkTime());;
                numb++;
            }
        }
        while (workTime.startHour != 0 && workTime.endHour != 0);
    }

    private WorkTime() {
        reader = new Scanner(System.in);  // Reading from System.in
        mHour  = new Hours();
    }

    private void input() {
//        System.out.print("Input jam kerja " + "#" + numb + " : ");

        startHour = Integer.parseInt(reader.next());
        endHour = Integer.parseInt(reader.next());

        mHour.setWorkStart(startHour);
        mHour.setWorkEnd(endHour);

    }

    private void output(int total) {
        System.out.print("h#" + numb + " : " + total + "\n\n");
    }

    private boolean validate(){
        if (endHour < startHour){
            error.showError(ERR_UNVALID_TIME);
            return false;
        }
        else if ( startHour < MIN_HOUR || startHour > MAX_HOUR || endHour < MIN_HOUR || endHour > MAX_HOUR){
            error.showError(ERR_UNVALID_TIME_RANGE);
            return false;
        }
        else {
            return true;
        }
    }

    private int countWorkTime(){
        int totalNormalWorkTime = endHour - startHour;

        Overtime overtime = new Overtime(mHour);
        if (!overtime.getIsOvertime()){
            return totalNormalWorkTime;
        } else {
            return (NORMAL_WORK_TIME + overtime.getTotalOvertimeHours());
        }
    }
}
