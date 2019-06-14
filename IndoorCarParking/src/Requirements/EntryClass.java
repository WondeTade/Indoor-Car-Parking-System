
package Requirements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class EntryClass {
    private String entryDate; //car entry date
    private String entryTime; //car entry time
    private String entryRegNumber;
    public ArrayList<EntryClass> EntryList = new ArrayList<>();
    private int size = EntryList.size();
    public ArrayList<PaymentClass> PaymentList = new ArrayList<>();
    EntryClass(){
        this.entryRegNumber = "";
        this.entryDate = "";
        this.entryTime = "";
    }
    EntryClass(String entryRegNum, String entryDate,String entryTime){
        this.entryRegNumber = entryRegNum;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        
    }
  
    public void addEntryDataToList(){
        EntryList.add(new EntryClass(this.entryRegNumber,this.entryDate, 
                                    this.entryTime));
        System.out.println("Welcome " + this.entryRegNumber + "\n");
        System.out.println("Number of objects : " + EntryList.size());
    }
    public void displayEntity(){
        System.out.println("List of cars in garage: ");
        System.out.println("Reg Number \t Entry Date \t Entry Time");
        for (EntryClass RegList : EntryList){
            System.out.print(RegList.entryRegNumber);
            System.out.print("\t\t" + RegList.entryDate);
            System.out.println("\t" + RegList.entryTime);
        }
    }
    
    public void enterEntryData() throws ParseException{
        Scanner in = new Scanner(System.in);
        System.out.println("Enter arriving car registration number");
        this.entryRegNumber = in.nextLine().toUpperCase();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date entryDateTime = new Date();
            
        this.entryDate = dateFormat.format(entryDateTime);
        this.entryTime = timeFormat.format(entryDateTime);
    }
     public boolean verifyEntryReg(){
        if(!(entryRegNumber.isEmpty() || 
                entryRegNumber.length()<6)){
            return true;
        }else 
            System.out.println("Please enter correct "
                                + "licence number like: ABC123");
        
        return false;
    }
    public boolean duplicateEntryRegNumber() throws ParseException{
        for (int i = 0; i < EntryList.size(); i++){
        if((EntryList.get(i).getEntryRegNumber().equals(this.entryRegNumber))){
            
            System.out.println("Already Exists");
            
            return false;
        }
        }
        return true;
    }

    public String getEntryRegNumber(){
        return this.entryRegNumber;
    }
    public String getEntryDate(){
        return this.entryDate;
    }
    public String getEntryTime(){
        return this.entryTime;
    }
    public int getEntryListSize(){
        return this.size;
    }
}
