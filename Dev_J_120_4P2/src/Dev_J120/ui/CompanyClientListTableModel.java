
package Dev_J120.ui;

import java.util.HashSet;
import java.util.Set;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Dev_J120.lists.ClientListOfCompany;
import Dev_J120.models.CompanyInfo;
import Dev_J120.models.PhoneNumber;

public class CompanyClientListTableModel implements TableModel{
    
    private static final String[] COLUMN_HEADERS = new String[]{
            "Phone number",
            "Company name",
            "Company address",
            "Company director",
            "Company contact person",
            "Registration date"
        };

    private final Set<TableModelListener> companyModelListeners = new HashSet<>();
  
    @Override
	public int getColumnCount() {
		return COLUMN_HEADERS.length;
	}
    @Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 0: 
				return PhoneNumber.class;
			case 1: 
			case 2:
                        case 3:
                        case 4:    
                        case 5:
				return String.class;
		}
		throw new IllegalArgumentException("unknown columnIndex");
	} 
    @Override
	public String getColumnName(int columnIndex) {
		return COLUMN_HEADERS[columnIndex];
	}
    @Override
	public int getRowCount() {
		return ClientListOfCompany.getCompanyInstance().getCompanyClientsCount();
	}
    @Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	    CompanyInfo comI = ClientListOfCompany.getCompanyInstance().getCompanyClientInfo(rowIndex); 
		switch(columnIndex) {
			case 0: return comI.getPhoneNumber(); 
			case 1: return comI.getCompanyName();
			case 2: return comI.getCompanyAddress();
			case 3: return comI.getCompanyDirectorName();
                        case 4: return comI.getContactPersonName();
                        case 5: return comI.getRegDate();
		}
		throw new IllegalArgumentException("unknown columnIndex");
	}
    /**
	 * Returns {@code false}, since in-cell editing is prohibited.
	 */
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
    /**
	 * Does nothing, since in-cell editing is prohibited.
	 */
    @Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        /* Nothing to do, since isCellEditable is always false. */
	}
    @Override
	public void addTableModelListener(TableModelListener l) {
        companyModelListeners.add(l);
	}
    @Override
	public void removeTableModelListener(TableModelListener l) {
        companyModelListeners.remove(l);
	}   
        public CompanyInfo getCompanyClient(int rowNdx) {
	    return ClientListOfCompany.getCompanyInstance().getCompanyClientInfo(rowNdx); 
	}
/*Основной метод для добавления списка клиентов в таблицу, данные которых введены через форму. 
Дата регистрации - текущая.
*/    
    public void addCompanyClient(PhoneNumber number, String name, String address, String director, String contactPerson) {
	ClientListOfCompany.getCompanyInstance().addCompanyClient(number, name, address, director, contactPerson);
        int rowNdx = ClientListOfCompany.getCompanyInstance().getCompanyClientsCount() - 1;
        fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
    }
//перегруженный метод для добавления списка клиентов в таблицу из файла. Дата регистрации не соотвествует текущей.
    public void addCompanyClient(PhoneNumber number, String name, String address, String director, String contactPerson, String regDate) {
	ClientListOfCompany.getCompanyInstance().addCompanyClient(number, name, address, director, contactPerson, regDate);
        int rowNdx = ClientListOfCompany.getCompanyInstance().getCompanyClientsCount() - 1;
        fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
    }

    public void companyClientChanged(int index) {
        fireTableModelEvent(index, TableModelEvent.UPDATE);
    }

    public void dropCompanyClient(int index) {
        ClientListOfCompany.getCompanyInstance().removeCompany(index); 
        fireTableModelEvent(index, TableModelEvent.DELETE);
    }

    private void fireTableModelEvent(int rowNdx, int evtType) {
        TableModelEvent tme = new TableModelEvent(this, rowNdx, rowNdx,
                                                  TableModelEvent.ALL_COLUMNS, evtType);
        for (TableModelListener l : companyModelListeners) {
            l.tableChanged(tme);
        }
    }    
}
