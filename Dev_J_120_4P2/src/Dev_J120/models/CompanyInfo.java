
package Dev_J120.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompanyInfo {

    private final PhoneNumber phoneNumber;
    private final String regDate;
    private String companyName;
    private String companyAddress;
    private String companyDirectorName;
    private String contactPersonName;
 
/*Конструктор для новых клиентов, данные которых вводим через форму. 
  Дата регистрации соотвествует текущей дате.
*/    
    public CompanyInfo(PhoneNumber phoneNumber, String companyName, String companyAddress, 
                       String companyDirectorName, String contactPersonName) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); 
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
        setCompanyDirectorName(companyDirectorName);
        setContactPersonName(contactPersonName);
    }
/* Конструктор для загрузки данных из файла. 
   Дата регистрации не изменяется и не будет соответствовать текущей дате.
*/    
    public CompanyInfo(PhoneNumber phoneNumber, String companyName, String companyAddress, 
                       String companyDirectorName, String contactPersonName, String regDate) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = regDate;
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
        setCompanyDirectorName(companyDirectorName);
        setContactPersonName(contactPersonName);
    }
//геттеры для полей класса.
    public String getCompanyName() {
        return companyName;
    }    
    public String getCompanyAddress() {
        return companyAddress;
    }    
    public String getCompanyDirectorName() {
        return companyDirectorName;
    }
    public String getContactPersonName() {
        return contactPersonName;
    }
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    public String getRegDate() {
        return regDate;
    }
//сеттеры для полей класса
    public final void setCompanyName(String companyName) {
    	if(companyName == null || companyName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company name\" field must be filled.");
        this.companyName = companyName;
    }

    public final void setCompanyAddress(String companyAddress) {
    	if(companyAddress == null || companyAddress.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company address\" field must be filled.");
        this.companyAddress = companyAddress;
    }

    public final void setCompanyDirectorName(String companyDirectorName) {
        if(companyDirectorName == null || companyDirectorName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company director\" field must be filled.");
        this.companyDirectorName = companyDirectorName;
    }

    public final void setContactPersonName(String contactPersonName) {
        if(contactPersonName == null || contactPersonName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company contact person\" field must be filled.");
        this.contactPersonName = contactPersonName;
    }    
//Переопределенный toString(). Представляет все данные одного клиента в одной строке.    
    @Override
    public String toString() {
        final String CS = "\u0009"; //column separator
        String s = phoneNumber.getAreaCode() + CS + phoneNumber.getLocalNum() + CS + companyName + 
                   CS + companyAddress + CS + companyDirectorName + CS + contactPersonName +
                   CS + regDate;
        return s;
    }
}

