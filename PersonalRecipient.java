class PersonalRecipient extends RecipientAbst implements BirthdayRecpInterface {

    // Concrete class for personal Recipients

   private String birthDay;
   private String nickname;

   public String getBDay() {
       return this.birthDay;
   }

   public void setBDay(String Date){
       this.birthDay=Date;
   }

   public String getnickname() {
       return this.nickname;
   }

   public void setnickname(String nickname){
       this.nickname=nickname;
   }
   
}
