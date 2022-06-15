
package Dev_J120.lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dev_J120.models.CompanyInfo;
import Dev_J120.models.PhoneNumber;


/**
 * Manages list of companyClients.
 */
public class ClientListOfCompany {
 /**
	 * The only CompanyInstance of this class.
	 * 
	 * @see #getPersonInstance
	 */
	private static final ClientListOfCompany companyInstance = new ClientListOfCompany();  
	
	/**
	 * Keeps the list of of companyClients. Clients are stored in the same order,
	 * which they have been registered in.
	 */
    private final List<CompanyInfo> companyClients = new ArrayList<>();
    
    /**
     * List of client phone companyNumbers. The list is used by {@link #addClient} method to check,
     * that specified phone number is not used.
     * This set is updated simultaneously with {@link #personClients} list, when
 personClients are {@linkplain #addClient added} or {@linkplain #remove removed}.
     */
    private final Set <PhoneNumber> companyNumbers = new HashSet<>();
    
    /**
     * Prevents companyInstance creation out of the class.
     */
    private ClientListOfCompany() {
	}

	/**
     * Adds client with specified attributes. Creates companyInstance of {@code CompanyInfo}
     * (see {@link CompanyInfo#CompanyInfo(PhoneNumber, String, String, String)}) while adding new client.
     * 
     * @param number company phone number
     * @param name company name
     * @param address company address
     * @param director name of the company director
     * @param contactPerson name of the contact person in the company
     * 
     * @exception IllegalArgumentException If some client with specified phone number 
     * 		has already been registered.
     */
    //основной метод для внесения в список новых клиентов, данные которых введены через форму
    public void addCompanyClient(PhoneNumber number, String name, String address, 
                                 String director, String contactPerson) {
        if(companyNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        companyClients.add(new CompanyInfo(number, name, address, director, contactPerson));
        companyNumbers.add(number); 
    }
    //перегруженный метод для добавления в список клиентов при загрузке данных из файла во время старта приложения
    public void addCompanyClient(PhoneNumber number, String name, String address, 
                                 String director, String contactPerson, String regDate) {
        if(companyNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        companyClients.add(new CompanyInfo(number, name, address, director, contactPerson, regDate));
        companyNumbers.add(number); 
    }
    
    /**
     * Removes client with the specified index.
     * 
     * @param index index of a client to be removed
     * 
     * @exception IndexOutOfBoundsException 
     * 		If the index is out of range (index < 0 || index >= {@link #getClientsCount}).
     */
    public void removeCompany(int index) {
    	CompanyInfo companyClientInfo = companyClients.get(index);
        companyNumbers.remove(companyClientInfo.getPhoneNumber());
        companyClients.remove(index);
    }
    
    /**
     * Returns amount of companyClients, kept by the list.
     * 
     * @return Number of companyClients, kept by the list.
     */
    public int getCompanyClientsCount() {
        return companyClients.size();
    }
    
    /**
     * Returns information about a client with specified index.
     * 
     * @param index client index, which data is retrieved
     * 
     * @return {@code ClientInfo}
     * 
     * @exception IndexOutOfBoundsException 
     * 		If the index is out of range (index < 0 || index >= {@link #getClientsCount}).
     */
    public CompanyInfo getCompanyClientInfo(int index) {
        return companyClients.get(index);
    }
    
    /**
     * Returns the only companyInstance of this class.
     * @return 
     */
    public static ClientListOfCompany getCompanyInstance() {
		return companyInstance;
	}

    public List<CompanyInfo> getCompanyClientsList() {
        return companyClients;
    }
    
}

