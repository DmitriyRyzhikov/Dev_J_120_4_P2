package Dev_J120.ui;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Dev_J120.lists.ClientListOfPerson;
import Dev_J120.models.PersonInfo;
import Dev_J120.models.PhoneNumber;

/**
 * Clients list table model for the table of application main window. 
 */
public class PersonClientListTableModel implements TableModel {
    private static final String[] COLUMN_HEADERS = new String[]{
            "Phone number",
            "Person name",
            "Person address",
            "Registration date",
            "Person age"
        };

    private final Set<TableModelListener> personModelListeners = new HashSet<>();
    
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
				return String.class;
			case 3: 
				return LocalDate.class;
                        case 4:
                                return Integer.class;
		}
		throw new IllegalArgumentException("unknown columnIndex");
	}

	@Override
	public String getColumnName(int columnIndex) {
		return COLUMN_HEADERS[columnIndex];
	}

	@Override
	public int getRowCount() {
		return ClientListOfPerson.getPersonInstance().getPersonClientsCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PersonInfo ci = ClientListOfPerson.getPersonInstance().getPersonClientInfo(rowIndex);
		switch(columnIndex) {
			case 0: return ci.getPhoneNumber(); 
			case 1: return ci.getName();
			case 2: return ci.getAddress();
			case 3: return ci.getRegDate();
                        case 4: return ci.getClientAge();
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
        personModelListeners.add(l);
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
        personModelListeners.remove(l);
	}
	
	public PersonInfo getPersonClient(int rowNdx) {
		return ClientListOfPerson.getPersonInstance().getPersonClientInfo(rowNdx);
	} 
	
        /*основной метод для добавления списка клиентов в таблицу, данные которых введены через форму. 
        Дата регистрации - текущая.
        */
	public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth) {
	    ClientListOfPerson.getPersonInstance().addPersonClient(number, name, address, dateOfBirth);
            int rowNdx = ClientListOfPerson.getPersonInstance().getPersonClientsCount() - 1;
            fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
	}
        //перегруженный метод для добавления списка клиентов в таблицу из файла. Дата регистрации не соотвествует текущей.
        public void addPersonClient(PhoneNumber number, String name, String address, String dateOfBirth, String regDate) {
	    ClientListOfPerson.getPersonInstance().addPersonClient(number, name, address, dateOfBirth, regDate);
            int rowNdx = ClientListOfPerson.getPersonInstance().getPersonClientsCount() - 1;
            fireTableModelEvent(rowNdx, TableModelEvent.INSERT);
	}
	
	/**
	 * Just notifies model listeners, that data of a person client with specified index has been changed.
	 * 
	 * @param index index of a client in the client list, which data has been changed 
	 */
	public void personClientChanged(int index) {
        fireTableModelEvent(index, TableModelEvent.UPDATE);
	}

	/**
	 * Removes person client with the specified index. Notifies model listeners about removal.
	 *  
	 * @param index index of a client record to removePerson
	 */
	public void dropPersonClient(int index) {
		ClientListOfPerson.getPersonInstance().removePerson(index);
        fireTableModelEvent(index, TableModelEvent.DELETE);
	}

	/**
	 * Creates {@code TableModelEvent} of specified type (see {@link TableModelEvent} constants),
	 * and calls listeners to notify them about the change.
	 * 
	 * @param rowNdx index of table row, which is the reason of the event
	 * @param evtType event type; one of {@code TableModelEvent} constants 
	 * 		({@link TableModelEvent#UPDATE} for example)
	 */
    private void fireTableModelEvent(int rowNdx, int evtType) {
        TableModelEvent tme = new TableModelEvent(this, rowNdx, rowNdx,
                TableModelEvent.ALL_COLUMNS, evtType);
        for (TableModelListener l : personModelListeners) {
            l.tableChanged(tme);
        }
    }
}
