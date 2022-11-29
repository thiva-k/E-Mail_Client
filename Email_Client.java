// 200655N [K.Thivaharan]
// Please import javax.mail and add java.activation library before running Email_Client .

import java.util.InputMismatchException;
import java.util.Scanner;

public class Email_Client {

    public static void main(String[] args) {
            
            Email gmail =new Email("thivaharank.200655n@gmail.com","ogohaixmboujeuoj");
            Scanner scanner = null;
           
            try  {
                scanner = new Scanner(System.in);

                while(true){

                System.out.println("\nEnter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "-1- Exit application\n"); 
               
                int option = scanner.nextInt();
                
                switch(option){
                    
                    case 1:           
                                RecipientBook.addNew();  
                                // Input format - Official: nimal,nimal@gmail.com,ceo
   
                               break;
                    case 2:
                                
                                gmail.composeNewMail();
                                //Input email, subject and content in command line one by one when prompted
                               
                                break;
                    case 3:
                                
                                RecipientBook.getTodayBDDayNames();
                                // Print recipients who have Birthday today

                                RecipientBook.getBDayNames();
                                // Print recipients who have birthdays on the given input date
                                // Input format - yyyy/MM/dd (ex: 2018/09/17)
                                
                                break;

                    case 4:      
                                UtilSerializeObjects.readFile();
                                // Input format - yyyy/MM/dd (ex: 2018/09/17)
                                // Print the details of all the emails sent on the input date

                                break;
                    case 5:

                                RecipientBook.getTotalRecipients();
                                //Print the number of recipient objects in the application

                                break;
                    case -1:

                                System.exit(0);

                    default :
                                System.out.println("Invalid entry");

                }
            }  
            
            }catch (InputMismatchException e){

                System.out.println("Invalid Input");

            }finally{

              scanner.close();
            }    
    }
}
