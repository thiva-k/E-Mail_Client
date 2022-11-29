import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

class Email {
    
    private final String username ;        //  "thivaharank.200655n@gmail.com"    
    private final String password ;        // "ogohaixmboujeuoj"         
    // Cannot use all E-mails by default for SMTP since gmail requires two factor authentication        
    private Properties prop = new Properties();
    private String Recipientaddress;
    private String messagetext;
    private String subjecttext;
    private RecipientBook book;
    private Scanner scn = new Scanner(System.in);


    public Email(String username, String password){
      
      System.out.println("\n__Welcome__");
      this.username=username;
      this.password = password;  
      book= new RecipientBook("clientList.txt");
      sendBDMails();
      
    }     

    // Methods to do all work related to sending a new EMail and creating a serialized object.

    public  void composeNewMailmsg(String Recipientaddress,String subjecttext ,String messagetext){

        this.Recipientaddress= Recipientaddress;
        this.messagetext=messagetext;
        this.subjecttext=subjecttext;
        process();
        new SentMail(Recipientaddress, subjecttext, messagetext);

    }
    public void composeNewMail(){   
        
        getRecipientMail();
        getSubject();
        getText();
        process();
        new SentMail(Recipientaddress, subjecttext, messagetext);   
    }
    public void process() {
        
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(Recipientaddress)
            );
            message.setSubject(subjecttext);
            message.setText(messagetext);

            Transport.send(message);

            System.out.println("Mail Delievered !");

        } catch (MessagingException e) {
            System.out.println("Error in connecting with E-Mail server.. Check Details");
            e.printStackTrace(); 
        }
    }

    public void getRecipientMail(){    // Methods to get inputs for email, subject, message
       System.out.println("Enter Recipient mail :");
       Recipientaddress = scn.nextLine();

    }
    public void getSubject(){
        System.out.println("Enter Subject :");
        subjecttext = scn.nextLine();
 
     }
     public void getText(){
        System.out.println("Enter Text Body :");
        messagetext= scn.nextLine();
 
     }
     public void sendBDMails(){
          book.SendBdayMails(this);

     }   
}