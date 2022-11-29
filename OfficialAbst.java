abstract class OfficialAbst extends RecipientAbst{

    // Abstract class that will be inherited by official Recipients.
     
    protected String position;
 
    public void setPosition(String position) {
        this.position = position;
    }
 
    public String getPosition(){
     return this.position;
    }
 }    
