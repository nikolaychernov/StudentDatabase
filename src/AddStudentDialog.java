import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class AddStudentDialog extends JDialog {
	private JTextField firstnameTF;
	private JTextField secondnameTF;
	private JTextField lastnameTF;
	private JTextField groupnumberTF;
	private JLabel firstnameLabel;
	private JLabel secondnameLabel;
	private JLabel lastnameLabel;
    private JLabel groupnumberLabel;
	
	public AddStudentDialog(){
		setTitle("Add student");
	      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      setBounds(100, 100, 341, 341);
	      JPanel contentPane = new JPanel();
	      contentPane.setToolTipText("");
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      setContentPane(contentPane);
	      contentPane.setLayout(new GridBagLayout());
	      GridBagConstraints c = new GridBagConstraints();
	      
	      firstnameTF = new JTextField();
	      secondnameTF = new JTextField();
	      lastnameTF = new JTextField();
	      groupnumberTF = new JTextField();
	      firstnameLabel = new JLabel("First Name");
	      secondnameLabel = new JLabel("Second Name");
	      lastnameLabel = new JLabel("Last Name");
	      groupnumberLabel = new JLabel("Group Number");
	      
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 0;
	      c.gridy = 0;
	      contentPane.add(firstnameLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 1;
	      c.gridy = 0;
	      contentPane.add(secondnameLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 2;
	      c.gridy = 0;
	      contentPane.add(lastnameLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 3;
	      c.gridy = 0;
	      contentPane.add(groupnumberLabel, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 0;
	      c.gridy = 1;
	      contentPane.add(firstnameTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 1;
	      c.gridy = 1;
	      contentPane.add(secondnameTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 2;
	      c.gridy = 1;
	      contentPane.add(lastnameTF, c);
	      c.fill = GridBagConstraints.HORIZONTAL;
	      c.weightx = 1;
	      c.insets = new Insets(5, 5, 5, 5);
	      c.gridx = 3;
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
						Student student = new Student();
						student.setFirstName(firstnameTF.getText());
						student.setSecondName(secondnameTF.getText());
						student.setLastName(lastnameTF.getText());
						student.setGroupNumber(Integer.parseInt(groupnumberTF.getText()));
						DAOManager dao = DAOManager.getInstance();
						
						try {
							StudentDAO sDao = (StudentDAO) dao.getDAO(Table.STUDENT);
							sDao.persist(student);
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
