package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;

public class PersonalNumber {

       private LocalDate date;
    // private DateFormat birthday = new DateFormat();
       // private int checksum;
        private boolean valid;
        private String personalNumber;

        public PersonalNumber(){
        }
        public PersonalNumber(String personalNumber){
           setPersonalNumber(personalNumber);
            //date =LocalDate.parse(id.substring(0,8), DateTimeFormatter.BASIC_ISO_DATE);
//            birthday.setYear(Integer.parseInt(id.substring(0,4)));
//            birthday.setMonth(Integer.parseInt(id.substring(4,6)));
//            birthday.setDay(Integer.parseInt(id.substring(6,8)));
//            birthday.setPunctuation('!');
//            birthday.setFormat('b');
           // setChecksum(id.substring(9));
           // checksum = Integer.parseInt(id.substring(9));
           // this.id = id;
        }

    public void setPersonalNumber(String personalNumber) {
        if(!validID(personalNumber)){
            throw new IllegalArgumentException("Invalid personal number");
        }
        date =LocalDate.parse(personalNumber.substring(0,8), DateTimeFormatter.BASIC_ISO_DATE);
        this.personalNumber = personalNumber;
    }

   /* public String showID(){
            return date.toString() + checksum;
        }
        public int getChecksum(){
            return checksum;
        }*/
       /* public boolean isFemale(){
           if (showID().charAt(11) % 2 == 1){
                return false;
            }
            else {
                return true;
            }
        }*/
//        public int comparedTo(PersonalNumber otherID){
//            if (showID().substring(0,7).compareTo(otherID.showID().substring(0,7)) == 0)
//                return 0;
//            else if (showID().substring(0,7).compareTo(otherID.showID().substring(0,7)) > 0){
//                return 1;
//            }
//            else  {
//                return -1;
//            }
//        }
        public boolean validID(String personalNumber){
            if(personalNumber.length()!=12){
                throw new InputMismatchException("Check the length of your input");
            }
            int ch2 = Integer.parseInt(personalNumber.substring(2,3)) * 2;
            int ch3 = Integer.parseInt(personalNumber.substring(3,4));
            int ch4 = Integer.parseInt(personalNumber.substring(4,5)) * 2;
            int ch5 = Integer.parseInt(personalNumber.substring(5,6));
            int ch6 = Integer.parseInt(personalNumber.substring(6,7)) * 2;
            int ch7 = Integer.parseInt(personalNumber.substring(7,8));
            int ch9 = Integer.parseInt(personalNumber.substring(8,9)) * 2;
            int ch10 = Integer.parseInt(personalNumber.substring(9,10));
            int ch11 = Integer.parseInt(personalNumber.substring(10,11)) * 2;
            int sum = ch2 / 10 + ch2 % 10 + ch3 / 10 + ch3 % 10 + ch4 / 10 + ch4 % 10 + ch5 / 10 + ch5 % 10 + ch6 / 10 + ch6 % 10
                    + ch7 / 10 + ch7 % 10 + ch9 / 10 + ch9 % 10 + ch10 / 10 + ch10 % 10 + ch11 / 10 + ch11 % 10;
            int chech = (10 - sum % 10) % 10;
            int checksum = Integer.parseInt(personalNumber.substring(9));
            if (checksum % 10 == chech){
                return true;
            }
            else
                return false;
        }


    public String getPersonalNumber() {
        return personalNumber;
    }
}
