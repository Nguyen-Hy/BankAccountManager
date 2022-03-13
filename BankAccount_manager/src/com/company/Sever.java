package com.company;

import java.util.Date;
import java.util.Objects;

public abstract class Sever {

    class AccountName {
        private String fistName;
        private String midName;
        private String lastName;

        public AccountName() {
        }

        public AccountName(String fistName, String midName, String lastName) {
            this.fistName = fistName;
            this.midName = midName;
            this.lastName = lastName;
        }

        public final String getFistName() {
            return fistName;
        }

        public final void setFistName(String fistName) {
            this.fistName = fistName;
        }

        public final String getMidName() {
            return midName;
        }

        public final void setMidName(String midName) {
            this.midName = midName;
        }

        public final String getLastName() {
            return lastName;
        }

        public final void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AccountName fullName = (AccountName) o;
            return Objects.equals(fistName, fullName.fistName) && Objects.equals(midName, fullName.midName)
                    && Objects.equals(lastName, fullName.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(fistName, midName, lastName);
        }
    }


    private static int nextId = 0;
    private String id;
    private AccountName acountName;
    private String acountNumber;
    private String acountType;
    private Date dob;
    private String phoneNumber;
    private String addr;
    private double surplus;
    private String bankName;
    private String vlMember;
    private Date startDate; //phat hanh

    public Sever() {
          setId(null);
          acountName = new AccountName();
          acountNumber = "";
          acountType = "";
          dob = null;
          addr = "";
          surplus = 0.0f;
          bankName = "ABC_BANK";
          vlMember = "";
          startDate = new Date(); //phat hanh
    }

    public Sever(String id, String acountName, String acountNumber, String acountType, Date dob,
                 String addr, String phoneNumber, double surplus, String bankName, String vlMember, Date startDate) {
        this();
        this.setId(id);
        this.setAcountName(acountName);
        this.acountNumber = acountNumber;
        this.setAcountType(acountType);
        this.dob = dob;
        this.addr = addr;
        this.setPhoneNumber(phoneNumber);
        this.surplus = surplus;
        this.bankName = bankName;
        this.vlMember = vlMember;
        this.startDate = startDate;
    }


    public Sever(String id, String acountName, String acountNumber, double surplus, String acountType, String bankName, String phoneNumber, String addr, String vlMember, Date startDate) {
        this();
        this.setId(id);
        this.setAcountName(acountName);
        this.acountNumber = acountNumber;
        this.setAcountType(acountType);
        this.addr = addr;
        this.setPhoneNumber(phoneNumber);
        this.surplus = surplus;
        this.bankName = bankName;
        this.vlMember = vlMember;
        this.startDate = startDate;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if( phoneNumber != null && phoneNumber.length() == 10){
            var startWith = phoneNumber.startsWith("0");
            if( startWith == true ){
                this.phoneNumber = phoneNumber;
            }else{
                this.phoneNumber = null;
            }
        }
    }

    protected String getId() {
        return id;
    }

    protected void setId(String id){
        if(id != null ){
            this.id = id;
        }else{
            this.id = "ABC" +( nextId - 2);
            nextId += 1;
        }
    }



    public String getAcountName() {
        return this.acountName.fistName + " " + acountName.midName + acountName.lastName  ;
    }

    public final void setAcountName(String fullName) {
        this.acountName = new AccountName();
        if (fullName != null && fullName.length() > 0) {
            var words = fullName.split("\\s+"); // tách tại vị trí có dấu cách
            this.acountName.lastName = words[words.length - 1];
            this.acountName.fistName = words[0];
            this.acountName.midName = "";
            for (int i = 1; i < words.length - 1; i++) {
                this.acountName.midName += words[i] + " ";
            }
        }
    }

    public String getAcountNumber() {
        return acountNumber;
    }

    public void setAcountNumber(String acountNumber) {
        this.acountNumber = acountNumber;
    }

    public String getAcountType() {
        return acountType;
    }

    public void setAcountType(String acountType) {
        if( acountType.equals("tt")  ||  acountType.equals("TT")  ){
            this.acountType = TyoeAccount.TT.getTypeAc();
        }else if ( acountType.equals("tk")  ||  acountType.equals("TK")){
            this.acountType = TyoeAccount.TK.getTypeAc();
        }else if (acountType.equals("vv")  ||  acountType.equals("VV")) {
            this.acountType = TyoeAccount.VV.getTypeAc();
        }else if ( acountType.equals("td")  ||  acountType.equals("TD")) {
            this.acountType = TyoeAccount.TD.getTypeAc();
        }
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public double getSurplus() {
        return surplus;
    }

    public void setSurplus(double surplus) {
        this.surplus = surplus;
    }

  abstract  String getBankName();

//    public void setBankName(String bankName) {
//        this.bankName = bankName;
//    }

    protected String getVlMember() {
        return vlMember;
    }

    public void setVlMember(String vlMember) {
        this.vlMember = vlMember;
    }

    protected String calVlMember() {
        if( surplus >= 100000){
            this.vlMember = Member_Level.DIAMOND.getLevel();
        }else if( surplus >= 50000){
            this.vlMember = Member_Level.PLATINUM.getLevel();
        }else if( surplus >= 10000 ){
            this.vlMember = Member_Level.GOLD.getLevel();
        }else{
            this.vlMember = Member_Level.NORMAL.getLevel();
        }
        return vlMember;
       }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sever sever = (Sever) o;
        return Double.compare(sever.surplus, surplus) == 0 && Objects.equals(id, sever.id) && Objects.equals(acountName, sever.acountName) && Objects.equals(acountNumber, sever.acountNumber) && Objects.equals(acountType, sever.acountType) && Objects.equals(dob, sever.dob) && Objects.equals(phoneNumber, sever.phoneNumber) && Objects.equals(addr, sever.addr) && Objects.equals(bankName, sever.bankName) && Objects.equals(vlMember, sever.vlMember) && Objects.equals(startDate, sever.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, acountName, acountNumber, acountType, dob, phoneNumber, addr, surplus, bankName, vlMember, startDate);
    }


    public boolean addMoney( double money ){
        if( money > 50 ){
            this.surplus += money;
            return true;
        }else{
            return false;
        }
    }

    public boolean minusMoney(double money ){
        if(surplus >= 20 && surplus >= money){
            this.surplus -= money;
            return true;
        }else{
            return false;
        }
    }



    public boolean checkMoney(){
        if( surplus >= 55.00 ){
            return true;
        }else{
//            System.out.println("Số tiền trong tài khoản không đủ để thực hiện giao dịch.");
            return false;
        }
    }


}


 class ABC_BANK extends Sever{
    public ABC_BANK() {
    }

     public ABC_BANK(String id, String acountName, String acountNumber, String acountType, Date dob, String addr, String phoneNumber, double surplus, String bankName, String vlMember, Date startDate) {
         super(id, acountName, acountNumber, acountType, dob, addr, phoneNumber, surplus, bankName, vlMember, startDate);
     }
//
//     public ABC_BANK(String id, String acountName, String acountNumber, double surplus, String acountType, String bankName, String phoneNumber, String addr, String vlMember, Date startDate) {
//         super(id, acountName, acountNumber, acountType, addr, phoneNumber, surplus, bankName, vlMember, startDate);
//     }
//     public ABC_BANK(String id, String acountName, String acountNumber, String acountType, Date dob, String addr, String phoneNumber, double surplus, String bankName, String vlMember, Date startDate) {
//         super(id, acountName, acountNumber, acountType, dob, addr, phoneNumber, surplus, bankName, vlMember, startDate);
//     }



     private static int nextId = 100001;

     @Override
     protected void setId(String id) {
         super.setId(id);
         nextId ++;
     }

     @Override
     protected String getId() {
         return super.getId();
     }

     @Override
     String getBankName() {
         return "ABC_BANK";
     }


 }