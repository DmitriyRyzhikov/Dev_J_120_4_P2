
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
    
    private static final String URLPerson = "persons.dat";
    private static final String URLCompany = "companies.dat";
    private static final String[] URL = {URLPerson, URLCompany};

    public static String getURLPerson() {
        return URLPerson;
    }

    public static String getURLCompany() {
        return URLCompany;
    }
    
    public void saveClientsToFile(List<PersonInfo> persons, List<CompanyInfo> companies) throws IOException {
        
        for(String url : URL) {
        Path path = Paths.get(url); 
        if(!path.isAbsolute())
            path = path.toAbsolutePath();
        Path dir = path.getParent();
        if(!Files.isDirectory(dir))
           Files.createDirectories(dir);
        if(!Files.exists(path))
           Files.createFile(path); 
        List<String> listString = new ArrayList<>();
        (url.equals(URLPerson)? persons : companies).forEach((s) -> { 
            listString.add(s.toString());
        });
        Files.write(path, listString, StandardCharsets.UTF_8);                
    }
 }
    
    public Map<String, List<String>> extractClientsFromFile() throws IOException{
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
            if (url.equals(URLPerson)) {
                sourceMap.put(URLPerson, sourceList);
            } else {
                sourceMap.put(URLCompany, sourceList);
            }
        }
    return sourceMap;
    }
}

