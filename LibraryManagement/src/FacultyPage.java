import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class FacultyPage extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyPage frame = new FacultyPage();
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
	public FacultyPage() {
		setTitle("Faculty Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 850, 500);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 2, 0, 0));
		Image img=new ImageIcon(this.getClass().getResource("/member-add-on-300x300.png")).getImage();
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Image img01=new ImageIcon(this.getClass().getResource("logoMain.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lblNewLabel_1.setIcon(new ImageIcon(img01));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Librarian Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		Image img1=new ImageIcon(this.getClass().getResource("/New book.png")).getImage();
		Image img6=new ImageIcon(this.getClass().getResource("editUser.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		
		JButton btnNewButton_8 = new JButton("View Users");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new ViewUser().setVisible(true);
			}
		});
		Image img7=new ImageIcon(this.getClass().getResource("viewUser.png")).getImage().getScaledInstance(65, 60, Image.SCALE_DEFAULT);
		
		JButton btnNewButton_1 = new JButton("Add New Book");
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				AddNewBook anb=new AddNewBook();
				anb.setVisible(true);
			}
		});
		String notice=DBInfo.getNotice();
		String value=DBInfo.value;
		if(value.equalsIgnoreCase("No Notice"))
		{
			value="";
		}
		
		JTextArea textArea = new JTextArea(value);
		textArea.setForeground(Color.RED);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground(new Color(240, 240, 240));
		textArea.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		contentPane.add(textArea);
		
		btnNewButton_1.setIcon(new ImageIcon(img1));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit Book");
		btnNewButton_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new EditBook().setVisible(true);
			}
		});
		Image img2=new ImageIcon(this.getClass().getResource("editBook.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		btnNewButton_2.setIcon(new ImageIcon(img2));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("View Static");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					new ViewStatic().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_5 = new JButton("View Books");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewBooks vb=new ViewBooks();
				vb.setVisible(true);
			}
		});
		btnNewButton_5.setFont(new Font("Verdana", Font.PLAIN, 15));
		
		contentPane.add(btnNewButton_5);
		btnNewButton_8.setIcon(new ImageIcon(img7));
		btnNewButton_8.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_3 = new JButton("Issue Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new IssueBooks().setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		Image img3=new ImageIcon(this.getClass().getResource("issue.png")).getImage();
		btnNewButton_3.setIcon(new ImageIcon(img3));
		contentPane.add(btnNewButton_3);
		Image img4=new ImageIcon(this.getClass().getResource("viewBooks.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_5.setIcon(new ImageIcon(img4));
		JButton btnNewButton_4 = new JButton("Edit Credincial");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new PasswordCheck().setVisible(true);
			}
		});
		Image img5=new ImageIcon(this.getClass().getResource("setting.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		
		JButton btnNewButton_6 = new JButton("Add Notice");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new EditNotice().setVisible(true);
			}
		});
		Image img8=new ImageIcon(this.getClass().getResource("notice.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_6.setIcon(new ImageIcon(img8));
		btnNewButton_6.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_6);
		Image img9=new ImageIcon(this.getClass().getResource("Statics.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		btnNewButton.setIcon(new ImageIcon(img9));
		
		JButton btnNewButton_7 = new JButton("Return Book");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ReturnBook().setVisible(true);
			}
		});
		Image img10=new ImageIcon(this.getClass().getResource("return-book-1-560407.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_7.setIcon(new ImageIcon(img10));
		btnNewButton_7.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_7);
		btnNewButton_4.setIcon(new ImageIcon(img5));
		btnNewButton_4.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_4);
	}
}
