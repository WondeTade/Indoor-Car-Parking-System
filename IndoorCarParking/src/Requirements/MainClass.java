
package Requirements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainClass {
    public static EntryClass entryClass = new EntryClass();
    public static PaymentClass payClass = new PaymentClass();
    public static ExitClass exitClass = new ExitClass();
    public static Scanner input = new Scanner(System.in);
    public static boolean cont = true;
    public static long diff;
    
    MainClass(){
        long diff = 0;
    }
    public static void printWelcome(){
        System.out.println("Welcome To Our Garage!\n 20kr per hour"
                            + "\n 100kr per day \n 500kr per week\n");
    }
    
    public static boolean verifyPayReg(){
        for (int i = 0; i < entryClass.EntryList.size(); i++ ){
        if (entryClass.EntryList.get(i).getEntryRegNumber().
                equals(payClass.getPayRegNumber())){
        return true;
        }
        }
        return false;
    }
    public static boolean verifyExitReg(){
        for (int i = 0; i < payClass.PaymentList.size(); i++ ){
        if (payClass.PaymentList.get(i).getPayRegNumber().
                equals(exitClass.getExitRegNumber())){
        return true;
        }
        }
         return false;
    }
    public static boolean expiredTicketHandling() throws ParseException{
        String exitRegNum = " ";
        String exitTime = " ";
        String exitDate = "";
        String entryRegNum = " ";
        String entryTime = " ";
        String entryDate = "";
        String payRegNum = " ";
        String payDate = "";
        String payTime = " ";
        long diffExit = 0;
        long diffEntry = 0;
        int entryMinute = 0;
        int entryMin = 0;
        int exitMinute = 0;
        int exitMin = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm a");
        for (int i = 0; i < payClass.PaymentList.size(); i++){
            if (payClass.PaymentList.get(i).getPayRegNumber().
                    equals(exitClass.getExitRegNumber())){
                
                payRegNum = payClass.PaymentList.get(i).getPayRegNumber();
                payTime = payClass.PaymentList.get(i).getPayTime();
                payDate = payClass.PaymentList.get(i).getPayDate();
                
                exitRegNum = exitClass.getExitRegNumber();
                exitTime = exitClass.getExitTime();
                exitDate = exitClass.getExitDate();
                
		Date timeExit = format.parse(exitDate + " " + exitTime);
		Date timePay = format.parse(payDate +  " " + payTime);
                
		diffExit = timeExit.getTime() - timePay.getTime();
                int exitDateDiff = (int) (diffExit/(24 * 60 * 60000));
                int exitHourDiff = (int) (diffExit/(60 * 60000));
                int exitMinDiff = (int) (diffExit/(60000));
                exitMinute = Math.abs(exitMinDiff);
            }
            if (entryClass.EntryList.get(i).getEntryRegNumber().
                    equals(exitClass.getExitRegNumber())){
                
                entryRegNum = entryClass.EntryList.get(i).getEntryRegNumber();
                entryDate = entryClass.EntryList.get(i).getEntryDate();
                entryTime = entryClass.EntryList.get(i).getEntryTime();
                
                Date timeEntry = format.parse(entryTime);
                Date timeExit = format.parse(exitTime);
                diffEntry = timeExit.getTime() - timeEntry.getTime();
                entryMin = (int) (diffExit/(60000));
                entryMinute = Math.abs(entryMin);
            }
        }
        if (exitMinute <= 1 || entryMinute <= 1){
            System.out.println("Valid ticket, you can exit");
            return true;
        }else
            System.out.println("Ticket expired, please pay again the difference");
        return false;
    }
    public static void printReciept() throws ParseException{
        String entryRegNum = " ";
        String entryTime = " ";
        String entryDate = "";
        String payRegNum = " ";
        String payDate = "";
        String payTime = " ";
        long diffPay = 0;
        int payMinute = 0;
        int payMin = 0;
        int hourCounter = 0;
        int dayCounter = 0;
        int weekCounter = 0;
        for (int i = 0; i < entryClass.EntryList.size(); i++ ){
            if (entryClass.EntryList.get(i).getEntryRegNumber().
                    equals(payClass.getPayRegNumber())) {
             
                entryRegNum = entryClass.EntryList.get(i).getEntryRegNumber();
                entryTime = entryClass.EntryList.get(i).getEntryTime();
                entryDate = entryClass.EntryList.get(i).getEntryDate();
                
                payRegNum = payClass.getPayRegNumber();
                payDate = payClass.getPayDate();
                payTime = payClass.getPayTime();
              
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date timeEntry = format.parse(entryTime);
		Date timePay = format.parse(payTime);
                
                diffPay = timePay.getTime() - timeEntry.getTime();
                payMin = (int) (diffPay/(60000)); 
                payMinute = Math.abs(payMin);
            }
        }
            System.out.println("\n---------------Receipt----------------------"
                        + "\nHave a nice day! the gate is opned, "
                        + "\nplease leave the garage in 10 min\n");
        if (payMinute <= 1){
            
        }
        if (payMinute >1){
            for (int minute = 2; minute <= payMinute; minute = minute+2)
                hourCounter = hourCounter + 1;
            for (int hour = 5; hour <= hourCounter; hour = hour + 5)
                dayCounter = dayCounter + 1;
                hourCounter = hourCounter - dayCounter * 5;
            for (int day = 7; day <= dayCounter; day = day + 7)
                weekCounter = weekCounter + 1;
               dayCounter = dayCounter - weekCounter * 7;
        }
        if (hourCounter <= 2 && dayCounter < 5){
            int price = hourCounter * 20 + dayCounter * 100 + weekCounter * 500;
            System.out.println("Total price  = " + price);
        }else if (hourCounter >= 2 && dayCounter < 5){
            int price = 100 + dayCounter * 100 + weekCounter * 500;
            System.out.println("Total price = " + price);
        }else{
            int price = 500 + weekCounter * 500;
            System.out.println("Total price = " + price);
        }
            System.out.println("\nRegistration number: " + payRegNum
                                + "\nEntry Date: " + entryDate
                                + "\nEntry Time: " +entryTime
                                + "\nPayment Date: " + payDate
                                + "\nPayment Time: " + payTime
                                + "\nTotal stayed durations: \n\t"
                                + weekCounter + " Weeks and \n\t"
                                + dayCounter + " Days and \n\t"
                                + hourCounter + " hours and \n\t"
                                + payMinute + " Minutes");
            System.out.println("Note: "
                                    + "\n 20kr per hour"
                                    + "\n 100kr per day "
                                    + "\n 500kr per week");
            System.out.println("\nThanks for paying " + payRegNum);
            System.out.println("-------------------------------------------\n");
    }
    
    //Main method
    public static void main(String []arg) throws ParseException{
        printWelcome();
        while (entryClass.getEntryListSize() == 0){
            entryClass.enterEntryData();
            if(entryClass.verifyEntryReg()){
            entryClass.addEntryDataToList();
            }else{
            }
        while (cont){
            System.out.println("\nChoose option from the option list: "
                                + "\n1. Entry: to enter another car"
                                + "\n2. Pay: to pay the parking fee"
                                + "\n3. Exit: to leave the garage"
                                + "\n4. print: to print information"
                                + "\n5. Quit: to quit the system\n");
        String option = input.nextLine().toLowerCase();
        if (option.contains("en") || (option.contains("1"))){
            entryClass.enterEntryData();
            if(entryClass.verifyEntryReg()){
                if (entryClass.duplicateEntryRegNumber()){
                    entryClass.addEntryDataToList();
                }
            }else{
            }
        }else if (option.contains("pa") || (option.contains("2"))){
            payClass.payRegDateTime();
                        
            if (verifyPayReg()){
                
                if (payClass.duplicatePayRegNumber()){
                    payClass.addPaymentDataToList();
                    printReciept();
                }
            }
            else{
                System.out.println("Not found in entry list, Please try again!");
            }
        }else if(option.contains("ex") || option.contains("3")){
            exitClass.enterExitData();
            if (verifyExitReg()){
                if (exitClass.duplicateExitRegNumber()){
                    if (expiredTicketHandling()){
                    exitClass.addExitDataToList();
                    }
                }
            }else{
                System.out.println("Not found in payed list, please pay");
            }
        }else if(option.contains("pr") || option.contains("4")){
            System.out.println("---------------------------------------------");
            entryClass.displayEntity();
            payClass.printPayedCarList();
            exitClass.printExitedCarList();
            System.out.println("---------------------------------------------");
            cont = true;
        }else if (option.contains("qu") || (option.contains("5"))){
            cont = false;
            System.exit(0);
        }else{
            System.out.println("Not in the list, please try again!");
            cont = true;
        }
        }
        input.close();
        }
    }
}
