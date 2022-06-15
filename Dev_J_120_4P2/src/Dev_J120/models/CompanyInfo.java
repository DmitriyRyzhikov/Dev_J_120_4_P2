
package Dev_J120.models;

import java.time.LocalDate;

/**
 * Keeps information about a company client as a company.
 */
public class CompanyInfo {
/**
* Phone number.
*/
    private final PhoneNumber phoneNumber;
    /**
     * Registration date. The field is filled by {@linkplain CompanyInfo#CompanyInfo constructor}.
     */
    private final LocalDate regDate;
    /**
     * Company name.
     */
    private String companyName;
    /**
     * Company address.
     */
    private String companyAddress;
    /**
     * Name of the company director.
     */
    private String companyDirectorName;
    /**
     * Name of the contact person in the company.
     */
    private String contactPersonName;
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
    public CompanyInfo(PhoneNumber phoneNumber, String companyName, String companyAddress, 
                       String companyDirectorName, String contactPersonName) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.now();
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
        setCompanyDirectorName(companyDirectorName);
        setContactPersonName(contactPersonName);
    }
    // Конструктор для загрузки данных из файла (Дата регистрации не будет соответствовать текущей дате)
    public CompanyInfo(PhoneNumber phoneNumber, String companyName, String companyAddress, 
                       String companyDirectorName, String contactPersonName, String regDate) {
    	if(phoneNumber == null)
    		throw new IllegalArgumentException("phone number can't be null.");
        this.phoneNumber = phoneNumber;
        this.regDate = LocalDate.parse(regDate);
        setCompanyName(companyName);
        setCompanyAddress(companyAddress);
        setCompanyDirectorName(companyDirectorName);
        setContactPersonName(contactPersonName);
    }
    /**
     * Returns Company name.
     */
    public String getCompanyName() {
        return companyName;
    }
    /**
     * Sets Company name.
     * 
     * @exception IllegalArgumentException if {@code companyName} is {@code null}.
     */
    public final void setCompanyName(String companyName) {
    	if(companyName == null || companyName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company name\" field must be filled.");
        this.companyName = companyName;
    }
    /**
     * Returns Company address.
     */
    public String getCompanyAddress() {
        return companyAddress;
    }
    /**
     * Sets Company address.
     * 
     * @exception IllegalArgumentException if {@code address} is {@code null}.
     */
    public final void setCompanyAddress(String companyAddress) {
    	if(companyAddress == null || companyAddress.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company address\" field must be filled.");
        this.companyAddress = companyAddress;
    }
    /**
     * Returns Name of the company director.
     */
    public String getCompanyDirectorName() {
        return companyDirectorName;
    }
    /**
     * Sets name of the company director.
     * 
     * @exception IllegalArgumentException if {@code companyDirectorName} is {@code null}.
     */
    public final void setCompanyDirectorName(String companyDirectorName) {
        if(companyDirectorName == null || companyDirectorName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company director\" field must be filled.");
        this.companyDirectorName = companyDirectorName;
    }
    /**
     * Returns name of the contact person in the company.
     */
    public String getContactPersonName() {
        return contactPersonName;
    }
    /**
     * Sets name of the contact person in the company.
     * 
     * @exception IllegalArgumentException if {@code companyDirectorName} is {@code null}.
     */
    public final void setContactPersonName(String contactPersonName) {
        if(contactPersonName == null || contactPersonName.trim().isEmpty())
    		throw new IllegalArgumentException("\"Company contact person\" field must be filled.");
        this.contactPersonName = contactPersonName;
    }    
    /**
     * Returns Company phone number.
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
    @Override
    public String toString() {
        final String CS = "\u0009"; //column separator
        String s = phoneNumber.getAreaCode() + CS + phoneNumber.getLocalNum() + CS + companyName + 
                   CS + companyAddress + CS + companyDirectorName + CS + contactPersonName +
                   CS + regDate;
        return s;
    }
}

