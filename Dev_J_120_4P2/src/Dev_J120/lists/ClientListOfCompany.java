
package Dev_J120.lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Dev_J120.models.CompanyInfo;
import Dev_J120.models.PhoneNumber;

public class ClientListOfCompany {

    private static final ClientListOfCompany companyInstance = new ClientListOfCompany();  
    private final List<CompanyInfo> companyClients = new ArrayList<>();
    private final Set <PhoneNumber> companyNumbers = new HashSet<>();

    private ClientListOfCompany() {
	}

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
//Метод удаляет заданную строку с данными компании из списка.
    public void removeCompany(int index) {
    	CompanyInfo companyClientInfo = companyClients.get(index);
        companyNumbers.remove(companyClientInfo.getPhoneNumber());
        companyClients.remove(index);
    }
//Метод возвращает кол-во клиентов (кол-во строк списка клиентов).
    public int getCompanyClientsCount() {
        return companyClients.size();
    }
//Метод возвращает данные о клиенте в виде объекта класса CompanyInfo.
    public CompanyInfo getCompanyClientInfo(int index) {
        return companyClients.get(index);
    }
//Статический метод для доступа к полям класса.
    public static ClientListOfCompany getCompanyInstance() {
		return companyInstance;
	}
//Метод возвращает весь список клиентов.
    public List<CompanyInfo> getCompanyClientsList() {
        return companyClients;
    }    
}

