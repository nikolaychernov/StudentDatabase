import java.util.List;

import javax.swing.table.AbstractTableModel;


public class StudentTableModel<T>  extends AbstractTableModel{

	protected List<Student> modelData;
	protected List<String> columnNames;
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		
		return modelData.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object result = null;
  	  switch (col) {
  	  case 0: result = modelData.get(row).getId();
  	  		break;
  	  case 1: result = modelData.get(row).getFirstName();
  			break;
  	  case 2: result = modelData.get(row).getSecondName();
  			break;
  	  case 3: result = modelData.get(row).getLastName();
			break;
  	  case 4: result = modelData.get(row).getGroupNumber();
			break;
  	  }
  	  return result; 
    }
	
	@Override
	public String getColumnName(int col){
		return columnNames.get(col);
		
	}
	
	public StudentTableModel(List<Student> data, List<String> col){
		modelData = data;
		columnNames = col;
	}
	
	public void updateModel(List<Student> data) {
		modelData = data;
	}

}


