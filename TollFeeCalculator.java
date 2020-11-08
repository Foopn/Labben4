package com.company;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TollFeeCalculator {

    //public TollFeeCalculator(String inputFile) { //T4 2st buggar. Metod är ej statisk samt ej void.
    public static void TollFeeCalculator(String inputFile) {
        try {
            Scanner sc = new Scanner(new File(inputFile));
            String[] dateStrings = sc.nextLine().split(", ");
            //LocalDateTime[] dates = new LocalDateTime[dateStrings.length-1]; //Bug med -1
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
            for(int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            System.out.println("The total fee for the inputfile is" + getTotalFeeCost(dates));
        } catch(IOException e) {
            System.err.println("Could not read file " + inputFile);
        }
    }

    public static int getTotalFeeCost(LocalDateTime[] dates) {
        int totalFee = 0;
        LocalDateTime intervalStart = dates[0];
        for(LocalDateTime date: dates) {
            System.out.println(date.toString());
            long diffInMinutes = intervalStart.until(date, ChronoUnit.MINUTES);
            if(diffInMinutes > 60) {
                totalFee += getTollFeePerPassing(date);
                intervalStart = date;
            } else {
                totalFee += Math.max(getTollFeePerPassing(date), getTollFeePerPassing(intervalStart));
            }
        }
        //return Math.max(totalFee, 60);  //T5 Bugg. Denna returnerar största värdet
        return Math.min(totalFee, 60);
    }

    public static int getTollFeePerPassing(LocalDateTime date) {
        if (isTollFreeDate(date)) return 0;
        int hour = date.getHour();
        int minute = date.getMinute();
        if (hour == 6 && minute >= 0 && minute <= 29) return 8;
        else if (hour == 6 && minute >= 30 && minute <= 59) return 13;
        else if (hour == 7 && minute >= 0 && minute <= 59) return 18;
        else if (hour == 8 && minute >= 0 && minute <= 29) return 13;
        //else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <= 59) return 8; //3st buggar. Börjar vid 8, ska börja vid 8:30. räknar mellan 8-14 och inom de timmarna bara mellan min 30-59. >= när det ska vara ==.
        else if (hour == 8 && minute >= 30 && minute <= 59) return 8; //T1 Bugg. 8:30-14:59  samt hour >= FEL == Rätt
        else if (hour >= 9 && hour <= 14 && minute >= 0 && minute <= 59) return 8; //T1 & T2 Bugg. ska vara hour 8 && min 30 till hour 14 till min 59
        else if (hour == 15 && minute >= 0 && minute <= 29) return 13;
        //else if (hour == 15 && minute >= 0 || hour == 16 && minute <= 59) return 18; //T3 Bugg. Fel tidsintervall ska vara 15:30 och därav drar 13 ist för 18kr
        else if (hour == 15 && minute >= 30 || hour == 16 && minute <= 59) return 18;
        else if (hour == 17 && minute >= 0 && minute <= 59) return 13;
        else if (hour == 18 && minute >= 0 && minute <= 29) return 8;
        else return 0;
    }

    public static boolean isTollFreeDate(LocalDateTime date) {
        return date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7 || date.getMonth().getValue() == 7;
    }

    public static void main(String[] args) {
        //new tollFeeCalculator("C:/Temp/Lab4.txt"); //T6 Bugg new ska inte finnas för inget ska instantieras
        TollFeeCalculator("C:/Temp/Lab4.txt");
    }
}