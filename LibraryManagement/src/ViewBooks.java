import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JComboBox;
import java.util.Vector;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewBooks extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox,comboBox_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBooks frame = new ViewBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */
	public ViewBooks() {
		setResizable(false);
		setTitle("View Books");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 476, 349);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblViewBooksBy = new JLabel("View Books");
		lblViewBooksBy.setFont(new Font("Verdana", Font.BOLD, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Search Book By");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		String values[]= {"Select","Author","Subject","Publisher","Category","All"};
		comboBox = new JComboBox(values);
		
		comboBox_1 = new JComboBox();
		
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton =  new JButton("Search");
		Image searchIcon=new ImageIcon(this.getClass().getResource("search.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton.setIcon(new ImageIcon(searchIcon));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=comboBox.getSelectedItem().toString();
				if(name.equalsIgnoreCase("select"))
				{
					JOptionPane.showMessageDialog(getParent(), "Please select the given field.", "Invalid Item", JOptionPane.ERROR_MESSAGE);
				}
				if(name.equalsIgnoreCase("All"))
				{
					JFrame frame=new JFrame();
					frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					try {
						DBInfo.allBooks();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table=new JTable(DBInfo.outerVector,DBInfo.colsName);
					JScrollPane pane=new JScrollPane(table);
					frame.getContentPane().add(pane);
				}
				if(name.equalsIgnoreCase("subject") || name.equalsIgnoreCase("author") || name.equalsIgnoreCase("publisher") || name.equalsIgnoreCase("category"))
				{
					String items=comboBox_1.getSelectedItem().toString();
					JFrame frame=new JFrame();
					frame.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					try {
						DBInfo.searchBooks_by(name,items);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table=new JTable(DBInfo.outerVector1,DBInfo.colsName1);
					JScrollPane pane=new JScrollPane(table);
					frame.getContentPane().add(pane);
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnCancel = new JButton("Cancel");
		Image cancelIcon=new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnCancel.setIcon(new ImageIcon(cancelIcon));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1_1 = new JLabel("Select Item");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		comboBox_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton_1 = new JButton("GO");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String name=comboBox.getSelectedItem().toString();
				if(name.equalsIgnoreCase("author"))
				{
					comboBox_1.removeAllItems();
					Vector<String> v=DBInfo.getValue("author");
					//comboBox_1.setSelectedItem(null);
					//comboBox_1.removeAll();
					for(String a:v)
					{
						comboBox_1.addItem(a);
					}
				}
				
				if(name.equalsIgnoreCase("subject"))
				{
					comboBox_1.removeAllItems();
					Vector<String> v=DBInfo.getValue("subject");
					comboBox_1.setSelectedItem(null);
					for(String s:v)
					{
						comboBox_1.addItem(s);
					}
				}
				if(name.equalsIgnoreCase("publisher"))
				{
					comboBox_1.removeAllItems();
					Vector<String> v=DBInfo.getValue("publisher");
					comboBox_1.setSelectedItem(null);
					for(String p:v)
					{
						comboBox_1.addItem(p);
					}
				}
				if(name.equalsIgnoreCase("category"))
				{
					comboBox_1.removeAllItems();
					Vector<String> v=DBInfo.getValue("category");
					comboBox_1.setSelectedItem(null);
					for(String c:v)
					{
						comboBox_1.addItem(c);
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(49)
									.addComponent(btnNewButton)))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(comboBox_1, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnNewButton_1)))
									.addGap(43))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnCancel)
									.addGap(95))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblViewBooksBy)
							.addGap(171))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addComponent(lblViewBooksBy, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1)))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(113))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
