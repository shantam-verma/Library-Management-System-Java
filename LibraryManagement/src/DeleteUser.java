import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
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
	public DeleteUser() {
		setTitle("Delete Account");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 372);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("Student Id Number");
		lblNewLabel.setForeground(new Color(240, 240, 240));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Image logoImg=new ImageIcon(this.getClass().getResource("logo.jpg")).getImage();
		lblNewLabel_1.setIcon(new ImageIcon(logoImg));
		
		lblNewLabel_2 = new JLabel("Delete my account");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JTextArea txtrAreYouSure = new JTextArea();
		txtrAreYouSure.setWrapStyleWord(true);
		txtrAreYouSure.setBackground(new Color(240, 240, 240));
		txtrAreYouSure.setFont(new Font("Verdana", Font.ITALIC, 13));
		txtrAreYouSure.setLineWrap(true);
		txtrAreYouSure.setEditable(false);
		txtrAreYouSure.setText("Are you sure you want to remove your account from the library management system? Keep in mind that you won't be able to access your account again or retrieve any of the book's data added in Library Management System.");
		
		lblNewLabel_3 = new JLabel("If you would still like your account deleted, click \"Delete My Account\".");
		lblNewLabel_3.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		JButton btnNewButton = new JButton("Delete My Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String id=lblNewLabel.getText();
				int x=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete ","Confirm delete",JOptionPane.INFORMATION_MESSAGE);
				if(x==0)
				{
					Connection con=DBInfo.conn();
					String query="DELETE FROM registration WHERE id=?";
					int flag=0;
					try {
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, id);
						flag=ps.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag==0) {
						JOptionPane.showMessageDialog(getParent(), "User not deleted please contact your librarian", "Error", JOptionPane.ERROR_MESSAGE);
					}else
					{
							JOptionPane.showMessageDialog(getParent(), "User successfully deleted","Success",JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
					}
				}
			}
		});
		btnNewButton.setBackground(new Color(70, 163, 255));
		btnNewButton.setFont(new Font("Verdana", Font.BOLD, 13));
		
		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.BOLD, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(txtrAreYouSure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
									.addGap(128)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(20)
							.addComponent(btnNewButton)
							.addGap(32)
							.addComponent(btnNewButton_1)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(txtrAreYouSure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_3)
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
