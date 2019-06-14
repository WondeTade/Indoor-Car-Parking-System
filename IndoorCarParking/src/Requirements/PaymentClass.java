
package Requirements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PaymentClass {
    public static ArrayList<EntryClass> EntryList = new ArrayList<>();
    public static EntryClass entryClass = new EntryClass();
    public ArrayList<PaymentClass> PaymentList = new ArrayList<>();
    private String paymentTime;
    private String paymentDate;
    private String payRegNumber;
    String entryTime;
    
    PaymentClass(){
        this.payRegNumber = "";
        this.paymentDate = "";
        this.paymentTime = "";
    }
    PaymentClass(String payRegNumber, String paymentDate, String paymentTime){
        this.payRegNumber = payRegNumber;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }
    public void  payRegDateTime() throws ParseException{
        System.out.println("\nEnter car registration number to pay");
        Scanner in = new Scanner(System.in);
        this.payRegNumber = in.nextLine().toUpperCase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date entryDateTime = new Date();
        this.paymentDate = dateFormat.format(entryDateTime);
        this.paymentTime = timeFormat.format(entryDateTime);
        
    }
    public boolean duplicatePayRegNumber() throws ParseException{
        for (int i = 0; i < PaymentList.size(); i++){
        if((PaymentList.get(i).getPayRegNumber().equals(this.payRegNumber))){
            
            System.out.println("Already payed");
            
            return false;
        }
        }
        return true;
    }
    public void addPaymentDataToList() throws ParseException{
        PaymentList.add(new PaymentClass(this.payRegNumber,this.paymentDate, 
                                    this.paymentTime));
    }
    public void printPayedCarList(){
        if (PaymentList.size()==0){
            
            System.out.println("\nPayed car list: ");
            System.out.println("No car has payed yet");
        }else{
            System.out.println("\nPayed car list: ");
            System.out.println("Reg Number \t Payed Date \t Payed Time");
        for (PaymentClass PayList : PaymentList){
            System.out.print(PayList.payRegNumber);
            System.out.print("\t\t" + PayList.paymentDate);
            System.out.println("\t" + PayList.paymentTime);
        }
        }
    }
    public String getPayRegNumber(){
        return this.payRegNumber;
    }
    public String getPayDate(){
        return this.paymentDate;
    }
    public String getPayTime(){
        return this.paymentTime;
    }
} 
