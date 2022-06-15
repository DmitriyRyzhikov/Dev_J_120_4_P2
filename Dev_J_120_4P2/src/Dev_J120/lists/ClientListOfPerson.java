package Dev_J120.lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dev_J120.models.PersonInfo;
import Dev_J120.models.PhoneNumber;

/**
 * Manages list of personClients.
 */
public class ClientListOfPerson {
	/**
	 * The only personInstance of this class.
	 * 
	 * @see #getPersonInstance
	 */
	private static final ClientListOfPerson personInstance = new ClientListOfPerson();  
	
	/**
	 * Keeps the list of of personClients. Clients are stored in the same order,
	 * which they have been registered in.
	 */
    private final List<PersonInfo> personClients = new ArrayList<>();
    
    /**
     * List of client phone personNumbers. The list is used by {@link #addPersonClient} method to check,
     * that specified phone number is not used.
     * This set is updated simultaneously with {@link #personClients} list, when
 personClients are {@link #addPersonClient added} or {@link #removePerson removed}.
     */
    private final Set <PhoneNumber> personNumbers = new HashSet<>();
    
    /**
     * Prevents personInstance creation out of the class.
     */
    private ClientListOfPerson() {
	}

	/**
     * Adds client with specified attributes. Creates personInstance of {@code PersonInfo}
     * (see {@link PersonInfo#ClientInfo(PhoneNumber, String, String)}) while adding new client.
     * 
     * @param number client phone number
     * @param name client name
     * @param address client address
     * 
     * @exception IllegalArgumentException If some client with specified phone number 
     * 		has already been registered.
     */
    //основной метод для внесения в список новых клиентов, данные которых введены через форму
    public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth) {
        if(personNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        personClients.add(new PersonInfo(number, name, address, dateOfBirth));
        personNumbers.add(number);
    }
    //перегруженный метод для добавления в список клиентов при загрузке данных из файла во время старта приложения
    public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth, String regDate) {
        if(personNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        personClients.add(new PersonInfo(number, name, address, dateOfBirth, regDate));
        personNumbers.add(number);
    }
    
    /**
     * Removes client with the specified index.
     * 
     * @param index index of a client to be removed
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= {@link #getPersonClientsCount}).
     */
    public void removePerson(int index) {
    	PersonInfo personClientInfo = personClients.get(index);
        personNumbers.remove(personClientInfo.getPhoneNumber());
        personClients.remove(index);
    }
    
    /**
     * Returns amount of personClients, kept by the list.
     * 
     * @return Number of personClients, kept by the list.
     */
    public int getPersonClientsCount() {
        return personClients.size();
    }
    
    /**
     * Returns information about a client with specified index.
     * 
     * @param index client index, which data is retrieved
     * 
     * @return {@code PersonInfo}
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= {@link #getPersonClientsCount}).
     */
    public PersonInfo getPersonClientInfo(int index) {
        return personClients.get(index);
    }
    
    /**
     * Returns the only personInstance of this class.
     * @return 
     */
    public static ClientListOfPerson getPersonInstance() {
		return personInstance;
	}

    public List<PersonInfo> getPersonClientsList() {
        return personClients;
    }
    
}

