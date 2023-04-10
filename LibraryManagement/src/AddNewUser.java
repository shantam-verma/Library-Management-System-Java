import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class AddNewUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewUser frame = new AddNewUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void reset() 
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		passwordField.setText(null);
		passwordField_1.setText(null);
		comboBox.setSelectedIndex(0);
	}
	public String studentId()
	{
		String studentNum = "";
		for(int i=0;i<6;i++) 
		{
			int num=(int)(Math.random()*9)+1;
			studentNum=studentNum+num;
		}
		System.out.println(studentNum);
		return studentNum;
	}
	public AddNewUser() {
		setResizable(false);
		setTitle("Add User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 593);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("logo.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		JLabel lblNewLabel_1 = new JLabel("Library Management Form");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Fill out the form carefully for new user");
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		JLabel lblNewLabel_3 = new JLabel("Name of Applicant");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Phone Number");
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Email Address");
		lblNewLabel_3_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("username");
		lblNewLabel_3_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Enter Password");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1_1_1_1_1 = new JLabel("Retype Password");
		lblNewLabel_3_1_1_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=textField.getText();
				String mobile=textField_1.getText();
				String email=textField_2.getText();
				String username=textField_3.getText();
				String password=String.copyValueOf(passwordField.getPassword());
				String re_password=String.copyValueOf(passwordField_1.getPassword());
				String userType=comboBox.getSelectedItem().toString();
				
				if(name.length()==0 || mobile.length()==0 || email.length()==0 || username.length()==0 ||password.length()==0 || re_password.length()==0 || userType.equalsIgnoreCase("select"))				
				{
					JOptionPane.showMessageDialog(getParent(), "Field was left empty, a value must be provided.","Field is required",JOptionPane.ERROR_MESSAGE);
				}else
				{
					if (!password.equals(re_password)) 
					{
						JOptionPane.showMessageDialog(getParent(), "Password does not match. Please re-enter your password","Invalid Password",JOptionPane.ERROR_MESSAGE);
					} else {
						Connection con=DBInfo.conn();
						String query="INSERT INTO registration(id,name,mobile,email,username,password,usertype) values(?,?,?,?,?,?,?)";
						int i=0;
						try 
						{
							PreparedStatement ps=con.prepareStatement(query);
							ps.setString(1, studentId());
							ps.setString(2, name);
							ps.setString(3, mobile);
							ps.setString(4, email);
							ps.setString(5, username);
							ps.setString(6, password);
							ps.setString(7, userType);
							i=ps.executeUpdate();
						} catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
						if (i==1) 
						{
							JOptionPane.showMessageDialog(getParent(), "Your registration has been successfully completed","Registration Successful",JOptionPane.INFORMATION_MESSAGE);
							reset();
						}
					}
				}
			}
		});
		Image saveIcon=new ImageIcon(this.getClass().getResource("login.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton.setIcon(new ImageIcon(saveIcon));
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnCancel = new JButton("Reset");
		Image resetIcon=new ImageIcon(this.getClass().getResource("resetIcon.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnCancel.setIcon(new ImageIcon(resetIcon));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				reset();
			}
		});
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnReset = new JButton("Cancel");
		Image cancelIcon=new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnReset.setIcon(new ImageIcon(cancelIcon));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnReset.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		String usertype[]= {"Select","Admin","Faculty","Student"};
		comboBox = new JComboBox(usertype);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1_1_1_1_1_1 = new JLabel("Account Type");
		lblNewLabel_3_1_1_1_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_3_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(57)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
										.addComponent(passwordField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(comboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(passwordField_1, 211, 211, Short.MAX_VALUE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(106)
							.addComponent(lblNewLabel_1)))
					.addGap(27))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addComponent(lblNewLabel)
					.addContainerGap(178, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2)
					.addGap(89))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
					.addComponent(btnCancel)
					.addGap(36)
					.addComponent(btnReset)
					.addGap(42))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
