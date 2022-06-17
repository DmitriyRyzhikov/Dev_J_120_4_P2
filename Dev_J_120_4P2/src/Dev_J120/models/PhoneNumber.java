package Dev_J120.models;

import java.util.Objects;

public class PhoneNumber {

    private final String areaCode;
    private final String localNum;
    private String strVal;

    public PhoneNumber(String areaCode, String localNum) {
        checkArg(areaCode, "area code");
        checkArg(localNum, "local number");
        this.areaCode = areaCode;
        this.localNum = localNum;
    }

    private void checkArg(String value, String field) {
        if(value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(field + " is null or empty.");
        for(int i = 0; i < value.trim().length(); i++) {
            if(!Character.isDigit(value.trim().charAt(i)))
                throw new IllegalArgumentException(field + " must consist of digits only.");
        } 
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getLocalNum() {
        return localNum;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof PhoneNumber))
            return false;
        PhoneNumber other = (PhoneNumber)o;
        return this.areaCode.equals(other.areaCode)
                && this.localNum.equals(other.localNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaCode, localNum);
    }

    @Override
    public String toString() {
        if(strVal == null) {
            StringBuilder sb = new StringBuilder(localNum);
            int p = sb.length() - 2;
            while(p > 1) {
                sb.insert(p, '-');
                p -= 2;
            }
            strVal = "(" + areaCode + ")" + sb.toString();
        }
        return strVal;
    }
}