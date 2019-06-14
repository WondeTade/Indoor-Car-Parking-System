
package Requirements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ExitClass {
    
    private String exitRegNum;
    private String exitTime;
    private String exitDate;
    public ArrayList<ExitClass> ExitList = new ArrayList<>();
    public EntryClass entryClass = new EntryClass();
    public PaymentClass paymentClass = new PaymentClass();
    
    ExitClass(){
        
    }
    ExitClass(String exitRegNum, String exitDate, String exitTime){
        this.exitRegNum = exitRegNum;
        this.exitDate = exitDate;
        this.exitTime = exitTime;
    }
    public void enterExitData() throws ParseException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter leaving car registration number");
        this.exitRegNum = in.nextLine().toUpperCase();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date exitDateTime = new Date();
        this.exitDate = dateFormat.format(exitDateTime);
        this.exitTime = timeFormat.format(exitDateTime);
    }
     public boolean duplicateExitRegNumber() throws ParseException{
        for (int i = 0; i < ExitList.size(); i++){
        if((ExitList.get(i).getExitRegNumber().equals(this.exitRegNum))){
            
            System.out.println("Already left");
            
            return false;
        }
        }
        return true;
    }
    public void addExitDataToList(){
        
        ExitList.add(new ExitClass(this.exitRegNum,this.exitDate,this.exitTime));
        System.out.println("Thanks! Have a nice day! " + this.exitRegNum + "\n");
        System.out.println("Number of objects : " + (ExitList.size()-1));
    }
    public void printExitedCarList(){
        if (ExitList.size()==0){
             System.out.println("\nExited car list: ");
             System.out.println("No car has left yet");
         }else{
            System.out.println("\nExited car list: ");
            System.out.println("Reg Number \t Exited Date \t Exited Time");
        for (ExitClass exitList : ExitList){
            System.out.print(exitList.exitRegNum);
            System.out.print("\t\t" + exitList.exitDate);
            System.out.println("\t" + exitList.exitTime);
        }
        }
    }
   
     public String getExitRegNumber(){
        return this.exitRegNum;
    }
    public String getExitDate(){
        return this.exitDate;
    }
    public String getExitTime(){
        return this.exitTime;
    }
}
