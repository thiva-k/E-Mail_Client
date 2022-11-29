abstract class RecipientAbst {

    // Abstract class that will be inherited by all types of Recipients .

    protected String name;
    protected String email;

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}    
