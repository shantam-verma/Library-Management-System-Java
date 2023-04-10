import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditUser frame = new EditUser();
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
	}
	public EditUser() {
		setResizable(false);
		setTitle("Delete User");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 498);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		Image logo=new ImageIcon(this.getClass().getResource("logo.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(logo));
		JLabel lblNewLabel_1 = new JLabel("Delete User");
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("No recovery is possible after deleting a user.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) 
			{
				String username=textField.getText();
				//String name=textField_1.getText();
				//String email=textField_2.getText();
				Connection con=DBInfo.conn();
				String query="SELECT * FROM registration where username=?";
				int flag=0;
				try {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, username);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
						String userName=res.getString(5);
						String name=res.getString(2);
						String email=res.getString(4);
						
						textField.setText(userName);
						textField_1.setText(name);
						textField_2.setText(email);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("username");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Name of Applicant");
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Email Address");
		lblNewLabel_3_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnUpdate = new JButton("Search");
		Image searchIcon=new ImageIcon(this.getClass().getResource("search.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnUpdate.setIcon(new ImageIcon(searchIcon));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username=textField.getText();
				//String name=textField_1.getText();
				//String email=textField_2.getText();
				Connection con=DBInfo.conn();
				String query="SELECT * FROM registration where username=?";
				int flag=0;
				try {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, username);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
						String userName=res.getString(5);
						String name=res.getString(2);
						String email=res.getString(4);
						
						textField.setText(userName);
						textField_1.setText(name);
						textField_2.setText(email);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnCancel = new JButton("Cancel");
		Image cancelIcon=new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnCancel.setIcon(new ImageIcon(cancelIcon));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnDelete = new JButton("Delete");
		Image deleteIcon=new ImageIcon(this.getClass().getResource("deleteIcon.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnDelete.setIcon(new ImageIcon(deleteIcon));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String username=textField.getText();
				String name=textField_1.getText();
				String email=textField_2.getText();
				int num=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete "+name,"Confirm delete?",JOptionPane.INFORMATION_MESSAGE);
				if(num==0)
				{
					Connection cn=DBInfo.conn();
			        
			        String query="select * from registration where username=? and email=?";
			        int i=0;
			        String usertype="";
			        try
			        {
			        	PreparedStatement ps=cn.prepareStatement(query);
			        	ps.setString(1, username);
	                    ps.setString(2, email);
	                    ResultSet res=ps.executeQuery();
	                    while(res.next())
	                    {
	                    	i=1;
	                    	usertype=res.getString(7);
	                    }
			        }
			        catch(Exception e2)
			        {
			        	System.out.println(e2);
			        }
			        
			        if(i==1 && usertype.equalsIgnoreCase("Admin"))
			        {
			        	JOptionPane.showMessageDialog(getParent(), "You cannot delete the admin user.","Error!",JOptionPane.ERROR_MESSAGE);
			        	reset();
			        }
			        if(i==1 && usertype.equalsIgnoreCase("Student") || usertype.equalsIgnoreCase("Faculty"))
			        {
						Connection con=DBInfo.conn();
						int flag=0;
						String query2="DELETE FROM registration WHERE username=? AND email=?";
						try {
							PreparedStatement ps=con.prepareStatement(query2);
							ps.setString(1, username);
							ps.setString(2, email);
							flag=ps.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (flag==0) {
							JOptionPane.showMessageDialog(getParent(), "Please verify username as it is not registered!", "Error", JOptionPane.ERROR_MESSAGE);
						}else
						{
							JOptionPane.showMessageDialog(getParent(), "User successfully deleted","Success",JOptionPane.INFORMATION_MESSAGE);
							reset();
						}
			        }
			        if(i==0)
			        {
			        	JOptionPane.showMessageDialog(getParent(), "Wrong username or password","Error",JOptionPane.ERROR_MESSAGE);
			        }
				}
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(172)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(182, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(158)
					.addComponent(lblNewLabel_1)
					.addContainerGap(163, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addComponent(btnUpdate)
					.addGap(28)
					.addComponent(btnDelete)
					.addGap(31)
					.addComponent(btnCancel)
					.addContainerGap(33, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(14, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addGap(56)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
