package Dev_J120.models;

import Dev_J120.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

/**
 * Keeps information about a company client as a person.
 */
public class PersonInfo {
	/**
	 * Phone number.
	 */
    private final PhoneNumber phoneNumber;
    /**
     * Registration date. The field is filled by {@link PersonInfo#ClientInfo constructor}.
     */
    private final LocalDate regDate;
    /**
     * Client name.
     */
    private String name;
    /**
     * Client address.
     */
    private String address;
    /**
     * Client date of birth.
     */
    private LocalDate dateOfBirth;
    /**
     * client's age.
     */
    private Integer clientAge;

    /**
     * Initializes instance attributes with values from corresponding constructor parameters.
     * Sets client registration date to current (today) date. 
     * 
     * @exception IllegalArgumentException If either {@code phoneNumber}, or {@code name}, 
     * 		or {@code address} is {@code null}.
     */
    /*Конструктор для новых клиентов, данные которых вводим через форму. 
    Дата регистрации соотвествуцет текущей дате.
    */
    public PersonInfo(PhoneNumber phoneNumber, String name, String address, String dateOfBirth) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.now();
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
        this.clientAge = ageCalculator(this.dateOfBirth); 
    }
    // Конструктор для загрузки данных из файла (Дата регистрации не будет соответствовать текущей дате)
    public PersonInfo(PhoneNumber phoneNumber, String name, String address, String dateOfBirth, String regDate) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        setName(name);
        setAddress(address);
        setDateOfBirth(dateOfBirth);
        this.clientAge = ageCalculator(this.dateOfBirth);
        this.regDate = LocalDate.parse(regDate);
    }
    
    /**
     * Returns client name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets client name.
     * 
     * @exception IllegalArgumentException if {@code address} is {@code null}.
     */
    public final void setName(String name) {
    	if(name == null || name.trim().isEmpty())
    		throw new IllegalArgumentException("\"Person name\" field must be filled.");
        this.name = name;
    }
    /**
     * Returns client address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets client address.
     * 
     * @exception IllegalArgumentException if {@code address} is {@code null}.
     */
    public final void setAddress(String address) {
    	if(address == null)
    		throw new IllegalArgumentException("address can't be null.");
        this.address = address;
    }    
    /**
     * Returns client phone number.
     * This attribute has no setter.
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Returns registration date.
     * This attribute has no setter.
     */
    public LocalDate getRegDate() {
        return regDate;
    }
    /**
     * Returns client date of birth.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Sets client date of birth.
     * 
     * @exception IllegalArgumentException if {@code address} is {@code null}.
     */
    public final void setDateOfBirth(String dateOfBirth) {
        if(dateOfBirth == null || dateOfBirth.trim().isEmpty())
    		throw new IllegalArgumentException("\"Date of birth\" field must be filled.");
        LocalDate parsedDate = null;
        try {
         parsedDate = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
        catch(DateTimeParseException dtpe) {JOptionPane.showMessageDialog(Utils.findLatestWindow(), 
                    "\"Date of birth\" field must be filled. Enter date of birth in this format dd.MM.yyyy",
                    "Error.", JOptionPane.ERROR_MESSAGE);}
        this.dateOfBirth = parsedDate;
    }
    /**
     * Returns client's age.
     */
    public Integer getClientAge() {
        return clientAge;
    }

    public void setClientAge(Integer clientAge) {
        this.clientAge = clientAge;
    }
    
    /**
     * calculates and returns the age.
     */
    public final Integer ageCalculator (LocalDate dateOfBirth){
        Integer age = Math.abs((int) ChronoUnit.YEARS.between(LocalDate.now(), dateOfBirth));
        return age;
    }

    @Override
    public String toString() {
        final String CS = "\u0009"; //column separator
        String s = phoneNumber.getAreaCode() + CS + phoneNumber.getLocalNum() + CS + name + 
                   CS + address + CS + dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                   CS + regDate;
        return s;
    }
    
    
}
