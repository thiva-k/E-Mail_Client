import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;  


class RecipientFactory{   
    
    //This is a separate factory class that is responsible for 
    // creating Recipient object from the file and appending new Recipients to the file .

    private RecipientBook rebook;
    private String textfile;  

    public RecipientFactory(RecipientBook rebook, String textfile){

         this.textfile=textfile;
         this.rebook = rebook;

    }
    
    //Reading from the file and adding Recipients to  the RecipientBook ArrayList

    public void createRecipient(){ 
        
        Scanner reader =null;
        try {
            File Obj = new File(textfile);
            reader = new Scanner(Obj);
            

           while (reader.hasNextLine()) {

                String data = reader.nextLine(); 
                String[] contents= data.split(":");          
                            
                if (contents[0].equals("Official")){
                                  
                        OfficialRecipient off = new OfficialRecipient();
                        off.setEmail(contents[1].split(",")[1].strip());
                        off.setName(contents[1].split(",")[0].strip());
                        off.setPosition(contents[1].split(",")[2].strip());
                        rebook.AddRecipient(off);

                }else if (contents[0].equals("Office_friend")){

                        OfficieFriendRecipient offfrnd = new OfficieFriendRecipient();
                        offfrnd.setEmail(contents[1].split(",")[1].strip());
                        offfrnd.setBDay(contents[1].split(",")[3].strip());
                        offfrnd.setName(contents[1].split(",")[0].strip());
                        offfrnd.setPosition(contents[1].split(",")[2].strip());
                        rebook.AddRecipient(offfrnd);

                }else if(contents[0].equals("Personal")){

                        PersonalRecipient per = new PersonalRecipient();
                        per.setName(contents[1].split(",")[0].strip());
                        per.setEmail(contents[1].split(",")[2].strip());
                        per.setnickname(contents[1].split(",")[1].strip());
                        per.setBDay(contents[1].split(",")[3].strip());
                        rebook.AddRecipient(per);
                                
                                
                }else{
                        System.out.println("Invalid Input or format found in clientList.txt!");
                    }
            }         
        }
        catch (IOException e) {
            System.out.println("An error has occurred while reading the clientList.txt file...");
            e.printStackTrace();

        } finally{
            
            reader.close();
            
        }

    }



    // Method to add new Recipient to file by writing it through BufferedWriter in append mode
    public void addtoFile(String newDetails){
        
        BufferedWriter out = null;

        try {

            out = new BufferedWriter(new FileWriter(textfile, true));
         
            out.newLine();
            out.write(newDetails);
            System.out.println("Recipient added sucessfully");;
            out.close();
         }
         
        catch (IOException e) {
         
           System.out.println("Exception occurred while writing to file..." + e);

        }finally{
            
            try {
                out.close();
            } catch (IOException e) {                
                e.printStackTrace();
            }
        }
    }
}  
