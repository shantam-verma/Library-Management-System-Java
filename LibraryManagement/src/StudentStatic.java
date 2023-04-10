import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class StudentStatic extends JFrame {

	private JPanel contentPane;
	public static Vector<Vector> values;
	public static Vector cols;
	private JTable table, table_all;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentStatic frame = new StudentStatic();
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
	public StudentStatic() {
		setTitle("View Issued Books");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 421, 300);
		setLocationRelativeTo(this);
		String str[]= {"Select","Book Id","username"};
		contentPane = new JPanel();
		JFrame frame=new JFrame();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblNewLabel_1_1 = new JLabel("Issued Books");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_2 = new JLabel("Search By Book Id");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Go");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String value=textField.getText();
				String query="SELECT * FROM issueBooks WHERE bookId='"+value+"' AND returnStatus='pending'";
				try {
					Connection con=DBInfo.conn();
					PreparedStatement ps=con.prepareStatement(query);
					values=new Vector<>();
					cols=new Vector<>();
					ResultSet res=ps.executeQuery();
					ResultSetMetaData rsmd=res.getMetaData();
					int countCols=rsmd.getColumnCount();
					for(int i=2;i<=countCols;i++)
					{
						cols.add(rsmd.getColumnName(i));
					}
					System.out.println(cols);
					while(res.next())
					{
						Vector<String> v=new Vector<>();
						for(int i=2;i<=countCols;i++)
						{
							v.add(res.getString(i));
						}
						values.add(v);
						System.out.println(values);
					}
					}
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
						frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
						frame.setVisible(true);
						frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						
						table=new JTable(values,cols);
						JScrollPane pane=new JScrollPane(table);
						frame.getContentPane().add(pane);
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnViewAllIssued = new JButton("View All Issued Books");
		btnViewAllIssued.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String query="SELECT * FROM issueBooks WHERE returnStatus='pending'";
				try {
					Connection con=DBInfo.conn();
					PreparedStatement ps=con.prepareStatement(query);
					values=new Vector<>();
					cols=new Vector<>();
					ResultSet res=ps.executeQuery();
					ResultSetMetaData rsmd=res.getMetaData();
					int countCols=rsmd.getColumnCount();
					for(int i=2;i<=countCols;i++){
						cols.add(rsmd.getColumnName(i));
					}
					System.out.println(cols);
					while(res.next())
					{
						Vector<String> v=new Vector<>();
						for(int i=2;i<=countCols;i++)
						{
							v.add(res.getString(i));
						}
						values.add(v);
						System.out.println(values);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				table_all=new JTable(values,cols);
				JScrollPane pane=new JScrollPane(table_all);
				frame.getContentPane().add(pane);
			}
		});
		btnViewAllIssued.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 13));
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addComponent(lblNewLabel_2)
							.addGap(34)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addComponent(btnViewAllIssued)
							.addGap(18)
							.addComponent(btnNewButton))
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 377, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(8, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnViewAllIssued, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
