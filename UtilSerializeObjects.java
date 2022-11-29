import java.io.*;
import java.util.Scanner;

class UtilSerializeObjects {

    // Utility class to read and write serialized SentMail objects to an external file.

    private static File f = new File("SentMails.ser");
    private static Scanner s = new Scanner(System.in);
    // Serialized objects will be saved in a  file on hard disk as "SentMails.ser"


    // Method to traverse all SentMail objects and get EMail details on input date .

    public static void readFile()
    { 
        System.out.println("Enter the date to get the E-Mails sent...");
        String date = s.nextLine();
        
        if (f.length() != 0) {
            
            FileInputStream fis = null;
            ObjectInputStream ois =null;

            try {
                
                fis = new FileInputStream("SentMails.ser");   
                ois = new ObjectInputStream(fis);

                // Reading serialized objects through chain stream ObjectInputStream

                SentMail c = null;
                int flag=0;

                while (fis.available() != 0) {

                    c = (SentMail)ois.readObject();

                    if(c.getSentDate().equals(date))   {

                      flag =1;  
                      System.out.println("Sent to : "+c.getSentAddress()+"  ||  with subject : " +c.getSubject());
                    }                                           
                }  
                if (flag==0) {

                    System.out.println("No E-Mails sent on the date you entered.");
                }
            }
            catch ( IOException e) {
 
                System.out.println("Error Occurred in SentMail file " + e);
                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }finally{
                try {

                    ois.close();
                    
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }    
    }
 
    // Method to serialize and add SentMail objects to the file

    public static void AddNewSentMail(SentMail mail)
    {
        if (mail != null) {
            
            FileOutputStream fos = null;

            try {
  
                fos = new FileOutputStream(  "SentMails.ser", true);

                // Different ObjectOutputStreams should be created while writing and appending objects
                // to the same file, to prevent header being created each time while appending new objects
                                           
                if (f.length() == 0) {            
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(mail);
                    oos.close();
                }
               
                else {
 
                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos); 
                    oos.writeObject(mail);
                    oos.close();
                }    
            }
            catch (IOException e) {

                System.out.println("Error Occurred while adding to SentMail file.." + e);

            } finally{

                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }         
        }    
    }
}


//Overriding default ObjectOutputStream methods to prevent 
// StreamCorruptedException while reading the serialized objects


class MyObjectOutputStream extends ObjectOutputStream {    
 
    MyObjectOutputStream() throws IOException
    {
        super();
    }
    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }
    //This overriding prevents new header being created each time
    public void writeStreamHeader() throws IOException 
    {
        return;
    }
}