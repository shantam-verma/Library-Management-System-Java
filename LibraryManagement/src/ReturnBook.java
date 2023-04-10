import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JTextField textField_2;
	private JDateChooser dateChooser;
	private SimpleDateFormat dFormate;
	private DateTimeFormatter dtf;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
		comboBox.setSelectedIndex(0);
		textField_3.setText(null);
		dateChooser.setCalendar(null);
		textField_1.setEditable(true);
		comboBox.setEnabled(true);
	}
	public ReturnBook() {
		setTitle("Return Book");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 574);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		Image clgLogo=new ImageIcon(this.getClass().getResource("logo.jpg")).getImage();
		lblNewLabel.setIcon(new ImageIcon(clgLogo));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Return Book");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Thank you for returning the books on time");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		JLabel lblNewLabel_2_1 = new JLabel("and helping us maintain our library collections.");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		JLabel lblNewLabel_3 = new JLabel("Book Id");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) 
			{
				String id=textField.getText();
				Connection con=DBInfo.conn();
				String query="SELECT * FROM issueBooks WHERE bookId=?";
				int flag=0;
				try {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1, id);
					ResultSet res=ps.executeQuery();
					while(res.next())
					{
						flag=1;
						String title=res.getString(3);
						String author=res.getString(4);
						String issueDate=res.getString(5);
						
						textField.setText(id);
						textField_1.setText(title);
						comboBox.setSelectedItem(author);
						textField_3.setText(issueDate);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(flag==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Book Id Number is in-correct.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					textField_1.setEditable(false);
					comboBox.setEnabled(false);
				}
			}
		});
		textField.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_1.setColumns(10);
		
		comboBox = new JComboBox(DBInfo.getValue("author"));
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1 = new JLabel("Book Title");
		lblNewLabel_3_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Author Name");
		lblNewLabel_3_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
	
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Issue Date");
		lblNewLabel_3_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3_1_1_1_1 = new JLabel("Due Date");
		lblNewLabel_3_1_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnIssue = new JButton("Submit");
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				SimpleDateFormat dFormate=new SimpleDateFormat("dd-MM-yyyy");
				String username=textField_2.getText();
				String bookId=textField.getText();
				String title=textField_1.getText();
				String author=comboBox.getSelectedItem().toString();
				String issueDate=textField_3.getText();
				String dueDate=dFormate.format(dateChooser.getDate());
				System.out.println("Due Date "+dueDate);
				System.out.println("Issue Date "+issueDate);
				
				if(username.length()==0 ||bookId.length()==0 || title.length()==0 || author.equalsIgnoreCase("Select") || issueDate.length()==0 || dueDate.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Field was left empty, a value must be provided.","Field is required",JOptionPane.ERROR_MESSAGE);
				}else
				{
					Connection con=DBInfo.conn();
					
					try {
						Statement stmt=con.createStatement();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					String sql="SELECT * FROM issueBooks WHERE username=?";
					PreparedStatement ps_username = null;
					try {
						ps_username = con.prepareStatement(sql);
						ps_username.setString(1, username);
						ResultSet res=ps_username.executeQuery();
						if(res.next())
						{
							String sqlId="SELECT * FROM issueBooks WHERE bookId=?";
							PreparedStatement ps_bookId=con.prepareStatement(sqlId);
							ps_bookId.setString(1, bookId);
							ResultSet res_id=ps_bookId.executeQuery();
							if(res_id.next())
							{
								String query="UPDATE issueBooks SET dueDate='"+dueDate+"' , returnStatus='Submitted' WHERE bookId=?";
								int flag=0;
								try {
									PreparedStatement ps=con.prepareStatement(query);
//									ps.setString(1, dueDate);
//									ps.setString(2, "submitted");
									ps.setString(1, bookId);
									
									flag=ps.executeUpdate();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
								if(flag==0)
								{
									JOptionPane.showMessageDialog(getParent(), "Book cannot be returned, contact the librarian.","Error",JOptionPane.ERROR_MESSAGE);
								}else
								{
									JOptionPane.showMessageDialog(getParent(), "Book Successfully Returned","Success",JOptionPane.INFORMATION_MESSAGE);
									reset();
								}
							}
							else
							{
								JOptionPane.showMessageDialog(getParent(), "There is no book with this "+bookId+" id.","Invalid Input",JOptionPane.ERROR_MESSAGE);
							}
						}else
						{
							JOptionPane.showMessageDialog(getParent(), "username is in correct","Invalid Input",JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}
		});
		btnIssue.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new FacultyPage().setVisible(true);
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3_2 = new JLabel("username");
		lblNewLabel_3_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField_3.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(45)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_1_1_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3_1_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(btnIssue)
									.addGap(28)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
										.addGap(18))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
										.addGap(70)))
								.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))))
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(11)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(lblNewLabel_2)
					.addGap(6)
					.addComponent(lblNewLabel_2_1)
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_3_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3_1_1)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3_1_1_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_3_1_1_1_1, Alignment.TRAILING)
						.addComponent(dateChooser, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIssue)
						.addComponent(btnCancel))
					.addGap(30))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
