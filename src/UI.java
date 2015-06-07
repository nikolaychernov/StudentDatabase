import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;




public class UI extends JFrame{

	DAOManager dao;
	GroupDAO gDao;
	StudentDAO sDao;
	ArrayList<Group> groupList;
	ArrayList<Student> studentList;
	ArrayList<String> studentColumns;
	ArrayList<String> groupColumns;
	JTable groupTable;
	JScrollPane groupScrollpane;
	JTable studentTable;
	JScrollPane studentScrollpane;
	GroupTableModel<Group> groupModel;
	StudentTableModel<Group> studentModel;
	JButton addGroupButton;
    JButton editGroupButton;
    JButton deleteGroupButton;
    JButton addStudentButton;
    JButton editStudentButton;
    JButton deleteStudentButton;
    
    JTextField lastNameTF;
    JTextField groupNumberTF;
    JLabel lastNameLabel;
    JLabel groupNumberLabel;
    
    String lastnameFilter;
    String groupnumberFilter;
	
	public UI() throws SQLException{
		dao = DAOManager.getInstance();
		
		gDao= (GroupDAO) dao.getDAO(Table.GROUPOFSTUDENTS);
		sDao = (StudentDAO) dao.getDAO(Table.STUDENT);
		
		groupList = (ArrayList<Group>) gDao.getAll();
		studentList = (ArrayList<Student>) sDao.getAll();
		
		
		groupColumns = new ArrayList<String>();
		groupColumns.add("id");
		groupColumns.add("Number");
		groupColumns.add("Department");
		studentColumns = new ArrayList<String>();
		studentColumns.add("id");
		studentColumns.add("First Name");
		studentColumns.add("Second Name");
		studentColumns.add("Last Name");
		studentColumns.add("Group Number");
		groupModel = new GroupTableModel<Group>(groupList, groupColumns);
		studentModel = new StudentTableModel<Group>(studentList, studentColumns);
	      
	      setTitle("Student database");
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      setBounds(100, 100, 1041, 741);
	      JPanel contentPane = new JPanel();
	      contentPane.setToolTipText("");
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(new GridBagLayout());
	      GridBagConstraints c = new GridBagConstraints();
	      
	      groupTable = new JTable(groupModel);
	      
	      groupTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	      groupScrollpane = new JScrollPane(groupTable);
	      groupScrollpane.setPreferredSize(new Dimension(100,150));
	     
	      
	      studentTable = new JTable(studentModel);
	      studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	      studentScrollpane = new JScrollPane(studentTable);
	      studentScrollpane.setPreferredSize(new Dimension(100,150));
	      
	      addGroupButton = new JButton("Add");
	      editGroupButton = new JButton("Edit");
	      deleteGroupButton = new JButton("");
	      deleteGroupButton = new JButton("Delete");
	      addStudentButton = new JButton("Add");
	      editStudentButton = new JButton("Edit");
	      deleteStudentButton = new JButton("Delete");
	      
	      lastNameTF = new JTextField();
	      groupNumberTF = new JTextField();
	      lastNameLabel = new JLabel("Last Name");
	      groupNumberLabel = new JLabel("Group Number");
	      
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 6;
	      c.gridy = 0;
	      contentPane.add(lastNameLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 7;
	      c.gridy = 0;
	      contentPane.add(groupNumberLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 6;
	      c.gridy = 1;
	      
	      contentPane.add(lastNameTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 7;
	      c.gridy = 1;
	      contentPane.add(groupNumberTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 0;
	      c.gridy = 1;
	      contentPane.add(addGroupButton, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 1;
	      c.gridy = 1;
	      contentPane.add(editGroupButton, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 2;
	      c.gridy = 1;
	      contentPane.add(deleteGroupButton, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 3;
	      c.gridy = 1;
	      contentPane.add(addStudentButton, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 4;
	      c.gridy = 1;
	      contentPane.add(editStudentButton, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.gridx = 5;
	      c.gridy = 1;
	      contentPane.add(deleteStudentButton, c);
	      
	      c.gridx = 0;
	      c.gridy = 2;
	      c.gridwidth = 3;
	      contentPane.add(groupScrollpane, c);
	      
	      c.gridx = 3;
	      c.gridy = 2;
	      c.gridwidth = 5;
	      contentPane.add(studentScrollpane, c);
	      
	      addStudentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddStudentDialog dialog = new AddStudentDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
					try {
						studentList = (ArrayList<Student>) sDao.getAll();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					studentModel.updateModel(studentList);
					studentModel.fireTableDataChanged();
				}
			});
	      editStudentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int studentId = (Integer)studentTable.getValueAt(studentTable.getSelectedRow(), 0);
					Student temp = null;
					try {
						temp = sDao.getbyPK(studentId);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					EditStudentDialog dialog = new EditStudentDialog(temp);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
					try {
						studentList = (ArrayList<Student>) sDao.getAll();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					studentModel.updateModel(studentList);
					studentModel.fireTableDataChanged();
				}
			});
	      addGroupButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddGroupDialog dialog = new AddGroupDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
					try {
						groupList = (ArrayList<Group>) gDao.getAll();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					groupModel.updateModel(groupList);
					groupModel.fireTableDataChanged();
				}
			});
	      editGroupButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int groupId = (Integer)groupTable.getValueAt(groupTable.getSelectedRow(), 0);
					Group temp = null;
					try {
						temp = gDao.getbyPK(groupId);
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					EditGroupDialog dialog = new EditGroupDialog(temp);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
					dialog.setVisible(true);
					try {
						groupList = (ArrayList<Group>) gDao.getAll();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					groupModel.updateModel(groupList);
					groupModel.fireTableDataChanged();
				}
			});
	      deleteGroupButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int groupId = (Integer)groupTable.getValueAt(groupTable.getSelectedRow(), 0);
					deleteGroup(groupId);
				}
			});
	      deleteStudentButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int studentId = (Integer)studentTable.getValueAt(studentTable.getSelectedRow(), 0);
					deleteStudent(studentId);
				}
			});
	      lastNameTF.getDocument().addDocumentListener(new DocumentListener() {
	    	  public void changedUpdate(DocumentEvent e) {
	    		   	lastnameFilter = lastNameTF.getText();
	    		   	
	    		   	filterStudentList();
	    		  }

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				lastnameFilter = lastNameTF.getText();
				filterStudentList();
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				lastnameFilter = lastNameTF.getText();
				filterStudentList();
			}
	    		  

	    		  
	    		});
	      groupNumberTF.getDocument().addDocumentListener(new DocumentListener() {
	    	  public void changedUpdate(DocumentEvent e) {
	    		   	groupnumberFilter = groupNumberTF.getText();
	    		   	filterStudentList();
	    		  }

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				groupnumberFilter = groupNumberTF.getText();
				filterStudentList();
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				groupnumberFilter = groupNumberTF.getText();
				filterStudentList();
			}
	    		  

	    		  
	    		});
	      
	}
	
	private void deleteGroup(int key){
		try {
			Group temp = gDao.getbyPK(key);
			gDao.delete(temp);
			groupList = (ArrayList<Group>) gDao.getAll();
			groupModel.updateModel(groupList);
			groupModel.fireTableDataChanged();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,	
				    "���������� ������� ������",
				    "������",
				    JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
    }
	
	private void deleteStudent(int key){
		try {
			Student temp = sDao.getbyPK(key);
			sDao.delete(temp);
			studentList = (ArrayList<Student>) sDao.getAll();
			studentModel.updateModel(studentList);
			studentModel.fireTableDataChanged();
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
    }
	
	private void filterStudentList(){
		try {
			System.out.println("Entered filterStudentList()");
			System.out.println(lastnameFilter + " " + groupnumberFilter);
			if (groupnumberFilter==null) {
				studentList = (ArrayList<Student>) sDao.selectLastname(lastnameFilter);
			} else if (lastnameFilter==null){
				studentList = (ArrayList<Student>) sDao.selectGroupNum( groupnumberFilter);
			} else {
				studentList = (ArrayList<Student>) sDao.select(lastnameFilter, groupnumberFilter);
			}
			for (int i=0;i<studentList.size();i++) {
				System.out.println(studentList.get(i).getId() + " " + studentList.get(i).getFirstName() + " " + studentList.get(i).getSecondName() 
						+ " " + studentList.get(i).getLastName() + " " + studentList.get(i).getGroupNumber());
			}
			studentModel.updateModel(studentList);
			studentModel.fireTableDataChanged();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			UI ui = new UI();
			ui.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
