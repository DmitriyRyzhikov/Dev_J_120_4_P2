package Dev_J120.lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dev_J120.models.PersonInfo;
import Dev_J120.models.PhoneNumber;

public class ClientListOfPerson {

    private static final ClientListOfPerson personInstance = new ClientListOfPerson();  
    private final List<PersonInfo> personClients = new ArrayList<>();
    private final Set <PhoneNumber> personNumbers = new HashSet<>();

    private ClientListOfPerson() {
	}

//Основной метод для внесения в список новых клиентов, данные которых введены через форму.
    public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth) {
        if(personNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        personClients.add(new PersonInfo(number, name, address, dateOfBirth));
        personNumbers.add(number);
    }
//Перегруженный метод для добавления в список клиентов при загрузке данных из файла во время старта приложения.
    public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth, String regDate) {
        if(personNumbers.contains(number))
            throw new IllegalArgumentException("Such a number has already been registered earlier.");
        personClients.add(new PersonInfo(number, name, address, dateOfBirth, regDate));
        personNumbers.add(number);
    }
//Метод удаляет заданную строку с данными клиента из списка.
    public void removePerson(int index) {
    	PersonInfo personClientInfo = personClients.get(index);
        personNumbers.remove(personClientInfo.getPhoneNumber());
        personClients.remove(index);
    }
//Метод возвращает кол-во клиентов (кол-во строк списка клиентов).
    public int getPersonClientsCount() {
        return personClients.size();
    }
//Метод возвращает данные о клиенте в виде объекта класса PersonInfo.
    public PersonInfo getPersonClientInfo(int index) {
        return personClients.get(index);
    }
//Статический метод для доступа к полям класса.
    public static ClientListOfPerson getPersonInstance() {
		return personInstance;
	}
//Метод возвращает весь список клиентов.
    public List<PersonInfo> getPersonClientsList() {
        return personClients;
    }    
}

