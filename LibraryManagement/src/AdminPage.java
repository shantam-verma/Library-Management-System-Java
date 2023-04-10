import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 850, 500);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 3, 0, 0));
		Image img=new ImageIcon(this.getClass().getResource("/member-add-on-300x300.png")).getImage();
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		Image img01=new ImageIcon(this.getClass().getResource("logoMain.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		lblNewLabel_1.setIcon(new ImageIcon(img01));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Admin Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Librarian");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AddNewUser().setVisible(true);
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

		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton.setIcon(new ImageIcon(img));
		contentPane.add(btnNewButton);
		Image img1=new ImageIcon(this.getClass().getResource("/New book.png")).getImage();
		
		JButton btnNewButton_7 = new JButton("Delete Librarian");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new EditUser().setVisible(true);
			}
		});
		Image img6=new ImageIcon(this.getClass().getResource("editUser.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		btnNewButton_7.setIcon(new ImageIcon(img6));
		btnNewButton_7.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("View Users");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new ViewUser().setVisible(true);
			}
		});
		Image img7=new ImageIcon(this.getClass().getResource("viewUser.png")).getImage().getScaledInstance(65, 60, Image.SCALE_DEFAULT);
		btnNewButton_8.setIcon(new ImageIcon(img7));
		btnNewButton_8.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_8);
		Image img2=new ImageIcon(this.getClass().getResource("editBook.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		
		JButton btnNewButton_3 = new JButton("View Static");
		Image issueImg=new ImageIcon(this.getClass().getResource("Statics.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		btnNewButton_3.setIcon(new ImageIcon(issueImg));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				try {
					new ViewStatic().setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_5 = new JButton("View Books");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ViewBooks vb=new ViewBooks();
				vb.setVisible(true);
			}
		});
		btnNewButton_5.setFont(new Font("Verdana", Font.PLAIN, 15));
		Image img4=new ImageIcon(this.getClass().getResource("viewBooks.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_5.setIcon(new ImageIcon(img4));
		contentPane.add(btnNewButton_5);
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
	}
}
