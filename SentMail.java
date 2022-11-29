import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar; 

class SentMail implements Serializable {

    //This is a Serializable class that stores the sent 
    // E-Mails(with sent dates) as objects to be saved on hard disk.   
    
        private String Recipient;
        private String subject;
        private String message;
        private String date;
        private static final long serialVersionUID = 6529685098267757690L;
    
    
        public SentMail(String Recipient, String subject, String message){
            this.message = message;
            this.Recipient=Recipient;
            this.subject=subject;
            this.date = getCurrDate();
            UtilSerializeObjects.AddNewSentMail(this);
        }
    
        public static String getCurrDate(){  
            Date date = Calendar.getInstance().getTime();  
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
            String strDate = dateFormat.format(date);  
            return strDate;
             
        }
        
        public String getSentAddress(){
            return this.Recipient;
        }
        public String getSubject(){
            return this.subject;
        }
        public String  getSentDate(){
           return this.date;
        } 
        public String getMessage(){
            return this.message;
        }
    }
    