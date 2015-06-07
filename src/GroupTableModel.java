import java.util.List;

import javax.swing.table.AbstractTableModel;


public class GroupTableModel<T> extends AbstractTableModel{

	protected List<Group> modelData;
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
  	  case 1: result = modelData.get(row).getNumber();
  			break;
  	  case 2: result = modelData.get(row).getDepartment();
  			break;
  	  }
  	  return result; 
    }
	
	@Override
	public String getColumnName(int col){
		return columnNames.get(col);
		
	}
	
	public GroupTableModel(List<Group> data, List<String> col){
		modelData = data;
		columnNames = col;
	}
	
	public void updateModel(List<Group> data) {
		modelData = data;
	}

}
