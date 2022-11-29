class OfficieFriendRecipient extends OfficialAbst implements BirthdayRecpInterface  {
    
    //Concrete Class for Official Friend Recipient

    private String birthDay;

    public String getBDay() {
        return this.birthDay;
    }

    public void setBDay(String Date){
        this.birthDay=Date;
    }
}
