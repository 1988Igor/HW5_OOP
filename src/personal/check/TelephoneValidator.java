package personal.check;

public class TelephoneValidator {
    public void checkNumber(String telephone) throws Exception {
        if(!telephone.substring(0,1).equals("+")) {
            throw new PhoneException("Please put the symbol (+) before digits");
        }
        else if(telephone.length() != 6) {
            throw new PhoneException("The length of the telephone number should be 5");
        }
    }
}
