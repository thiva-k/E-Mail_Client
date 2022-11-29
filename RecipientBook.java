import java.util.ArrayList;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.util.Scanner;

class RecipientBook {

    //This class is responsible for storing and classifying Recipient objects and implementing related functions .
            
        private static  ArrayList<RecipientAbst> RecipientList; 
         //Creating ArrayList for all Recipients
        private static ArrayList<BirthdayRecpInterface> AllBDay;
         // Separate ArrayList for Recipients who have known Birthdays
        private static RecipientFactory fac ;
          //Creating a separate factory to create Recipient objects from the clientList.txt file
        private static int totalRecipients;
        private static Scanner s = new Scanner(System.in);
    
    
        public RecipientBook(String textfile){
            
    
            RecipientList = new ArrayList<RecipientAbst>();
            AllBDay= new ArrayList<BirthdayRecpInterface>();
            fac = new RecipientFactory(this, textfile);
            fac.createRecipient();
            CreateBDayList();
            totalRecipients = RecipientList.size();
        
           }  
           
        public  void AddRecipient(RecipientAbst recp){
    
              RecipientList.add(recp);
         }
        
        public  void CreateBDayList(){         //Adding Recipients who have Birth Days given to separate ArrayList
           
            for (RecipientAbst persons : RecipientList){
                if(persons instanceof BirthdayRecpInterface){
                    BirthdayRecpInterface person=(BirthdayRecpInterface) persons;
                    AllBDay.add(person);
                }
            }
        
        }
                      
        
        // Getting today's date in required format
    
        public static String getCurrDate(){  
    
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
            String strDate = dateFormat.format(date);  
            return strDate;
             
        }  
              
    
        // Traverse the BirthDay ArrayList and send Mails if they have BDay today
    
        public void SendBdayMails(Email email){
        
        for (BirthdayRecpInterface br: AllBDay){
    
            RecipientAbst BRrec = (RecipientAbst) br;
                
            if (BRrec instanceof PersonalRecipient && ((br.getBDay().substring(5)).equals(getCurrDate().substring(5)))){
    
            System.out.println("Sending Birthday wishes to " + BRrec.getName());
    
            email.composeNewMailmsg(BRrec.getEmail(),"Many more happy returns of the day!",
                   "Hugs and love on your birthday. \n\n- Thivaharan.");
    
            }else if(BRrec instanceof OfficieFriendRecipient && ((br.getBDay().substring(5)).equals(getCurrDate().substring(5)))){
    
            System.out.println("Sending Birthday wishes to " + BRrec.getName());
    
            email.composeNewMailmsg(BRrec.getEmail(),"Happy Birthday !","Wish you a Happy Birthday.\n\n- Thivaharan.");
    
               }
          } 
        }
                                                   
        
        public static void getTodayBDDayNames(){  //Get the names of Recipients who have Birthday today
            
            int flag=0;
    
            for (BirthdayRecpInterface bd : AllBDay){
                RecipientAbst  b = (RecipientAbst)bd;
                if (((bd.getBDay().substring(5)).equals(getCurrDate().substring(5)))){
                    flag=1;
                    System.out.println(b.getName() + " has Birthday today !" );
                   }
            }
            if(flag==0){
                System.out.println("No Birthdays today");
            }
        }
                   
        
        public static void getBDayNames(){  // Get the names of Recipients who have BDay on the user input date
            
            try {
                
            System.out.println("\nEnter date to check if anyone has Birthday on that day...");
            String Datestr = s.nextLine();
            int flag=0;
    
            for(BirthdayRecpInterface bdr : AllBDay){
                RecipientAbst  b = (RecipientAbst)bdr;
                if (((bdr.getBDay().substring(5)).equals(Datestr.substring(5)))){
                    flag=1;
                    System.out.println(b.getName() + " has Birthday on " +  Datestr);
                   }
        
            }
            if (flag==0){
                System.out.println("No Birthdays on the date you entered..");
            }
           } catch(RuntimeException e){
        
            System.out.println("Enter Birthday in Correct format");
            e.printStackTrace();
            }
        }
        
    
        public static void addNew(){   // Add a new Recipient by writing to a file through RecipientFactory
            
            System.out.print("Enter new Recipient details in correct format...\n");
            String recp = s.nextLine();     
            fac.addtoFile(recp);
        }
        
        
        public static void getTotalRecipients(){   // Gives total number of Recipients
            System.out.println("There are " + totalRecipients + " Recipients.");
        }
    
        }    
    