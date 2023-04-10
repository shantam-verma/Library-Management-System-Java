import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ViewStatic extends JFrame {

	private JPanel contentPane;
	private JTable table,table_return ,table_issue,table_bookId;
	private JTable table_1;
	
	public static Vector<Vector> return_values;
	public static Vector return_cols;
	
	public static Vector<Vector> values;
	public static Vector cols;
	
	public static Vector<Vector> issue_values;
	public static Vector issue_cols;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewStatic frame = new ViewStatic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ViewStatic() throws SQLException {
		setTitle("View Issued and Returned Books");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 813, 551);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		Connection con=DBInfo.conn();
		String str[]= {"Select","Book Id","username"};
		JFrame frame=new JFrame();

		setContentPane(contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Returned Books");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana", Font.BOLD, 16));
		
		try {
			String return_sql="SELECT * FROM issueBooks WHERE returnStatus='Submitted'";
			return_values=new Vector<>();
			return_cols=new Vector<>();
			PreparedStatement ps_return=con.prepareStatement(return_sql);
			ResultSet res_return=ps_return.executeQuery();
			ResultSetMetaData rsmd_return=res_return.getMetaData();
			int countCols=rsmd_return.getColumnCount();
			for(int i=1;i<=countCols;i++){
				return_cols.add(rsmd_return.getColumnName(i));
			}
			System.out.println(return_cols);
			res_return.afterLast();;
			while(res_return.previous())
			{
				Vector<String> v=new Vector<>();
				for(int i=1;i<=countCols;i++)
				{
					v.add(res_return.getString(i));
				}
				return_values.add(v);
				System.out.println(return_values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		table = new JTable(return_values,return_cols);
		table.setFont(new Font("Verdana", Font.PLAIN, 13));
		//ps_return.close();
		JLabel lblNewLabel = new JLabel("Search By");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JComboBox comboBox = new JComboBox(str);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String value=comboBox.getSelectedItem().toString();
				if(value.equalsIgnoreCase("Select"))
				{
					JOptionPane.showMessageDialog(getParent(), "Please select the given field.", "Invalid Item", JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					String str=JOptionPane.showInputDialog("Enter "+value);
					if (str.length()==0) 
					{
						JOptionPane.showMessageDialog(getParent(), value+" cannot be empty","Invalid Input",JOptionPane.ERROR_MESSAGE);
					}
					System.out.println(str);
					if(value.equalsIgnoreCase("Book Id") || value.equalsIgnoreCase("username"))
					{
						if(value.equalsIgnoreCase("Book Id"))
						{
							value="bookId";
						}
						String query="SELECT * FROM issueBooks WHERE "+value+"='"+str+"' AND returnStatus='Submitted'";
						try {
							PreparedStatement ps=con.prepareStatement(query);
							values=new Vector<>();
							cols=new Vector<>();
							ResultSet res=ps.executeQuery();
							ResultSetMetaData rsmd=res.getMetaData();
							int countCols=rsmd.getColumnCount();
							for(int i=1;i<=countCols;i++){
								cols.add(rsmd.getColumnName(i));
							}
							System.out.println(cols);
							while(res.next())
							{
								Vector<String> v=new Vector<>();
								for(int i=1;i<=countCols;i++)
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
						
						table_bookId=new JTable(values,cols);
						JScrollPane pane=new JScrollPane(table_bookId);
						frame.getContentPane().add(pane);					
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnSearch = new JButton("All Returned Books");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frame=new JFrame();
				frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				table_return=new JTable(return_values,return_cols);
				JScrollPane pane=new JScrollPane(table_return);
				frame.getContentPane().add(pane);
			}
		});
		btnSearch.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				try {
					new ViewStatic().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1 = new JLabel("Issued Books");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.BOLD, 16));

		try {
			String issue_sql="SELECT * FROM issueBooks WHERE returnStatus='Pending'";
			PreparedStatement ps_issue=con.prepareStatement(issue_sql);
			issue_values=new Vector<>();
			issue_cols=new Vector<>();
			ResultSet res_issue=ps_issue.executeQuery();
			ResultSetMetaData rsmd_issue=res_issue.getMetaData();
			int countCols2=rsmd_issue.getColumnCount();
			for(int i=1;i<=countCols2;i++)
			{
				issue_cols.add(rsmd_issue.getColumnName(i));
			}
			System.out.println(issue_cols);
			while(res_issue.next())
			{
				Vector<String> v=new Vector<>();
				for(int i=1;i<=countCols2;i++)
				{
					v.add(res_issue.getString(i));
				}
				issue_values.add(v);
				System.out.println(issue_values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//ps_issue.close();
		table_1 = new JTable(issue_values,issue_cols);
		table_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Search By");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JComboBox comboBox_1 = new JComboBox(str);
		comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String value=comboBox_1.getSelectedItem().toString();
				if(value.equalsIgnoreCase("Select"))
				{
					JOptionPane.showMessageDialog(getParent(), "Please select the given field.", "Invalid Item", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String str=JOptionPane.showInputDialog("Enter "+value);
					if (str.length()==0) 
					{
						JOptionPane.showMessageDialog(getParent(), value+" cannot be empty","Invalid Input",JOptionPane.ERROR_MESSAGE);
					}
					System.out.println(str);
					if(value.equalsIgnoreCase("Book Id") || value.equalsIgnoreCase("username"))
					{
						if(value.equalsIgnoreCase("Book Id"))
						{
							value="bookId";
						}
						String query="SELECT * FROM issueBooks WHERE "+value+"='"+str+"' AND returnStatus='pending'";
						try {
							PreparedStatement ps=con.prepareStatement(query);
							values=new Vector<>();
							cols=new Vector<>();
							ResultSet res=ps.executeQuery();
							ResultSetMetaData rsmd=res.getMetaData();
							int countCols=rsmd.getColumnCount();
							for(int i=1;i<=countCols;i++){
								cols.add(rsmd.getColumnName(i));
							}
							System.out.println(cols);
							while(res.next())
							{
								Vector<String> v=new Vector<>();
								for(int i=1;i<=countCols;i++)
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
						
						table_bookId=new JTable(values,cols);
						JScrollPane pane=new JScrollPane(table_bookId);
						frame.getContentPane().add(pane);
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnAllIssuedBooks = new JButton("All Issued Books");
		btnAllIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFrame frame=new JFrame();
				frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
				table_issue=new JTable(issue_values,issue_cols);
				JScrollPane pane=new JScrollPane(table_issue);
				frame.getContentPane().add(pane);
			}
		});
		btnAllIssuedBooks.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnRefresh_1 = new JButton("Refresh");
		btnRefresh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				try {
					new ViewStatic().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefresh_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
								.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
							.addComponent(btnSearch)
							.addGap(63)
							.addComponent(btnRefresh)
							.addGap(70))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
							.addComponent(btnAllIssuedBooks)
							.addGap(73)
							.addComponent(btnRefresh_1)
							.addGap(72))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(btnRefresh)
						.addComponent(btnSearch))
					.addGap(50)
					.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefresh_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAllIssuedBooks, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
