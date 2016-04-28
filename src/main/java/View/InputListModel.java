package View;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class InputListModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Double> inputList;
	private String[] columnNames = { "Wejscia:" };

	public InputListModel(List<Double> list) {

		this.inputList = list;
	}

	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		return inputList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object output = null;
		Double input = null;
		input = inputList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			output = input;
			break;
		}
		return output;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public List<Double> getWeightList() {
		return inputList;
	}
}
