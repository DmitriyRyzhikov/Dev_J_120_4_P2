package Dev_J120.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

import Dev_J120.Utils;

public class PersonInfo {

    private final PhoneNumber phoneNumber;
    private final String regDate;
    private String name;
    private String address;
    private String dateOfBirth;
    private Integer personAge;

    /*Конструктор для новых клиентов, данные которых вводим через форму. 
    Дата регистрации соотвествует текущей дате.
    */
    public PersonInfo(PhoneNumber phoneNumber, String name, String address, String dateOfBirth) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")); 
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
        this.personAge = ageCalculator(this.dateOfBirth); 
    }
    // Конструктор для загрузки данных из файла (Дата регистрации не будет соответствовать текущей дате)
    public PersonInfo(PhoneNumber phoneNumber, String name, String address, String dateOfBirth, String regDate) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
        this.personAge = ageCalculator(this.dateOfBirth);
        this.regDate = regDate;
    }
//геттеры для полей класса    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getRegDate() {
        return regDate;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    
    public Integer getAge() {
        return personAge;
    }
//сеттеры для полей класса    
    public final void setName(String name) {
    	if(name == null || name.trim().isEmpty())
    		throw new IllegalArgumentException("\"Person name\" field must be filled.");
        this.name = name;
    }

    public final void setAddress(String address) {
    	if(address == null)
    		throw new IllegalArgumentException("address can't be null.");
        this.address = address;
    }       

    public final void setDateOfBirth(String dateOfBirth) {
        if(dateOfBirth == null || dateOfBirth.trim().isEmpty())
    		throw new IllegalArgumentException("\"Date of birth\" field must be filled.");
        int age = ageCalculator(dateOfBirth);
        checkAge(age);         
        this.dateOfBirth = dateOfBirth;
    }
 
    public void setPersonAge(Integer personAge) {
        this.personAge = personAge;
    }
//метод считает возраст клиента в годах, исходя из введенной даты рождения клиента    
    public final Integer ageCalculator (String dateOfBirth){
        
        LocalDate parsedDate = null;
        try {
         parsedDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        catch(DateTimeParseException dtpe) {JOptionPane.showMessageDialog(Utils.findLatestWindow(), 
                                            "\"Date of birth\" field must be filled. Enter date of "
                                            + "birth in this format dd.MM.yyyy",
                                            "Error.", JOptionPane.ERROR_MESSAGE);}                
        Integer age = (int) ChronoUnit.YEARS.between(parsedDate, LocalDate.now());
        checkAge(age); 
            return age;
    }
/*Метод проверяет возраст клиента. Если возраст находится в допустимом диапазоне, ничего не делает,
если нет - кидает исключение.
*/    
    public void checkAge(int age){
        if(age < 2 || age > 110)
           throw new IllegalArgumentException("The person has already died or has not "
                   + "yet been born, and if it was born, it does not know how to talk. "
                   + "Correct the person's date of birth.");
    }
//Переопределенный toString(). Представляет все данные одного клиента в одной строке.
    @Override
    public String toString() {
        final String CS = "\u0009"; //column separator
        String s = phoneNumber.getAreaCode() + CS + phoneNumber.getLocalNum() + CS + name + 
                   CS + address + CS + dateOfBirth + CS + regDate;
        return s;
    }   
}
