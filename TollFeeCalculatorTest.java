package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TollFeeCalculatorTest {

    /*
    @Test
    public void Test1(){
        try {
            Scanner sc = new Scanner(new File("C:/Temp/Lab4.txt"));
            String[] dateStrings = sc.nextLine().split(", ");
            LocalDateTime[] dates = new LocalDateTime[dateStrings.length - 1];
            for (int i = 0; i < dates.length; i++) {
                dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
        }catch (Exception e){

        }
    }
 */

    @Test
    public void Test1(){
        LocalDateTime ldt = LocalDateTime.parse("2020-06-30 09:29", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int fee = TollFeeCalculator.getTollFeePerPassing(ldt);
        Assert.assertEquals(8, fee);
    }

    @Test
    public void Test2(){
        LocalDateTime ldt = LocalDateTime.parse("2020-06-30 14:15", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int fee = TollFeeCalculator.getTollFeePerPassing(ldt);
        Assert.assertEquals(8, fee);
    }

    @Test
    public void Test3(){
        LocalDateTime ldt = LocalDateTime.parse("2020-06-30 15:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int fee = TollFeeCalculator.getTollFeePerPassing(ldt);
        Assert.assertEquals(18, fee);
    }
    @Test
    public void Test4(){
        TollFeeCalculator t = new TollFeeCalculator();
        Assert.assertNotNull(t);
    }

    @Test
    public void Test5(){
        String date = "2020-06-30 00:05, 2020-06-30 06:34, 2020-06-30 08:52, 2020-06-30 10:13, 2020-06-30 10:25, 2020-06-30 11:04, 2020-06-30 16:50, 2020-06-30 18:00, 2020-06-30 21:30, 2020-07-01 00:00";
        String[] dateStrings = date.split(", ");
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length];
        for(int i = 0; i < dates.length; i++) {
            dates[i] = LocalDateTime.parse(dateStrings[i], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        Assert.assertTrue(TollFeeCalculator.getTotalFeeCost(dates)<=60);
    }

    @Test
    public void Test6(){
        String[] test = new String[2];
        TollFeeCalculator.main(test);

    }

/*
    @Test
    public void Test7(){
        String date = "2020-06-30 00:05, 2020-06-30 06:34, 2020-06-30 08:52, 2020-06-30 10:13, 2020-06-30 10:25, 2020-06-30 11:04, 2020-06-30 16:50, 2020-06-30 18:00, 2020-06-30 21:30, 2020-07-01 00:00";
        String[] dateStrings = date.split(", ");
        LocalDateTime[] dates = new LocalDateTime[dateStrings.length-1];
        Assert.assertEquals(dateStrings.length, dates.length);

    }
*/
}
