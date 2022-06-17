
package Dev_J120.lists;

import Dev_J120.Utils;
import Dev_J120.models.CompanyInfo;
import Dev_J120.models.PersonInfo;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileService { 
    
    private static final String URL_PERSON = "persons.dat";
    private static final String URL_COMPANY = "companies.dat";
    private static final String[] URL = {URL_PERSON, URL_COMPANY};
//геттеры для URL
    public static String getURL_PERSON() {
        return URL_PERSON;
    }
    public static String getURL_COMPANY() {
        return URL_COMPANY;
    }
//Метод записывает данные обоих типов клиентов в два файла.    
    public void saveClientsToFile(List<PersonInfo> persons, List<CompanyInfo> companies) throws IOException {
        
        for(String url : URL) 
        {
           Path path = Paths.get(url); 
           if(!path.isAbsolute())
              path = path.toAbsolutePath();
           Path dir = path.getParent();
           if(!Files.isDirectory(dir))
              Files.createDirectories(dir);
           if(!Files.exists(path))
              Files.createFile(path); 
           List<String> listString = new ArrayList<>();
           (url.equals(URL_PERSON)? persons : companies).forEach((s) -> { 
           listString.add(s.toString());
           });
           Files.write(path, listString, StandardCharsets.UTF_8);                
        }
 }
/*Метод извлекает данные клиентов двух типов из файлов и возвращает Map<>,
в данном случае используемая как контейнер, в который помещены оба списка.     
В качестве ключей к спискам используются URL файлов, в которых они хранились.    
*/  public Map<String, List<String>> extractClientsFromFile() throws IOException{
        
        Map<String, List<String>> sourceMap = new HashMap<>();
        List<String> sourceList;
        
        for (String url : URL) {
            Path path = Paths.get(url);
            if(!path.isAbsolute())
                path = path.toAbsolutePath();
            if(!Files.exists(path))
                throw new IOException
                    ("The source data file was not found. The client database is empty.");
            sourceList = Files.readAllLines(path); 
            sourceList = Utils.killerBOM(sourceList);                         
            if (url.equals(URL_PERSON)) {
                sourceMap.put(URL_PERSON, sourceList);
            } else {
                sourceMap.put(URL_COMPANY, sourceList);
            }  
        } 
        return sourceMap; 
    }
}

