import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class EditGroupDialog extends JDialog {
	private JTextField departmentTF;
	private JTextField groupnumberTF;
	private JLabel departmentLabel;
	private JLabel groupnumberLabel;
	
	public EditGroupDialog(final Group group){
		setTitle("Edit group");
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setBounds(100, 100, 341, 341);
	      JPanel contentPane = new JPanel();
	      contentPane.setToolTipText("");
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(new GridBagLayout());
	      GridBagConstraints c = new GridBagConstraints();
	      
	      departmentTF = new JTextField();
	      groupnumberTF = new JTextField();
	      departmentLabel = new JLabel("Department");
	      groupnumberLabel = new JLabel("Group Number");
	      departmentTF.setText(group.getDepartment());
	      groupnumberTF.setText(""+group.getNumber());
	      
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 0;
	      c.gridy = 0;
	      contentPane.add(departmentLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 1;
	      c.gridy = 0;
	      contentPane.add(groupnumberLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 0;
	      c.gridy = 1;
	      contentPane.add(departmentTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 1;
	      c.gridy = 1;
	      contentPane.add(groupnumberTF, c);
	      
	      JPanel buttonPane = new JPanel();
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 0;
	      c.gridy = 2;
	      c.gridwidth = 4;
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, c);
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Group temp = new Group();
						temp.setDepartment(departmentTF.getText());
						temp.setNumber(Integer.parseInt(groupnumberTF.getText()));
						temp.setId(group.getId());
						DAOManager dao = DAOManager.getInstance();
						
						try {
							GroupDAO gDao = (GroupDAO) dao.getDAO(Table.GROUPOFSTUDENTS);
							gDao.update(temp);
							dao.close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JFrame frame = new JFrame();
							JOptionPane.showMessageDialog(
								    frame, "��������� ������ ��� ���������� ������",
								    "Inane error",
								    JOptionPane.ERROR_MESSAGE);
						}
						dispose();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
	}
}
