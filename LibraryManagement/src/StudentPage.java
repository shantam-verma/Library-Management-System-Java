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
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StudentPage extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentPage frame = new StudentPage();
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
	public StudentPage() {
		setTitle("Student Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setBounds(100, 100, 800, 500);
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
		
		JLabel lblNewLabel = new JLabel("Student Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		contentPane.add(lblNewLabel);
		
		EditNotice editNotice=new EditNotice();
		JLabel lblEditNotice = new JLabel("Edit Notice");
		lblEditNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditNotice.setFont(new Font("Verdana", Font.BOLD, 16));
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
		Image img1=new ImageIcon(this.getClass().getResource("/New book.png")).getImage();
		Image img2=new ImageIcon(this.getClass().getResource("download.png")).getImage();
		Image img3=new ImageIcon(this.getClass().getResource("issue.png")).getImage();
		
		JButton btnNewButton = new JButton("View Books");
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new ViewBooks().setVisible(true);
			}
		});
		Image viewBooksImg=new ImageIcon(this.getClass().getResource("viewBooks.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton.setIcon(new ImageIcon(viewBooksImg));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("View Notice");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new ViewNotice().setVisible(true);
			}
		});
		Image viewNoticeImg=new ImageIcon(this.getClass().getResource("notice.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_4.setIcon(new ImageIcon(viewNoticeImg));
		btnNewButton_4.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_8 = new JButton("Issue Book");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new IssueBooks().setVisible(true);
			}
		});
		btnNewButton_8.setFont(new Font("Verdana", Font.PLAIN, 15));
		Image issueImg=new ImageIcon(this.getClass().getResource("issue.png")).getImage();
		btnNewButton_8.setIcon(new ImageIcon(issueImg));
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_3 = new JButton("Return Book");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				new ReturnBook().setVisible(true);
			}
		});
		Image img10=new ImageIcon(this.getClass().getResource("return-book-1-560407.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_3.setIcon(new ImageIcon(img10));
		btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_2 = new JButton("View Static");
		btnNewButton_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new StudentStatic().setVisible(true);
			}
		});
		Image viewStatic=new ImageIcon(this.getClass().getResource("Statics.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
		btnNewButton_3_2.setIcon(new ImageIcon(viewStatic));
		btnNewButton_3_2.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_3_2);
		
		JButton btnNewButton_3_1 = new JButton("Edit Credincial");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new PasswordCheck().setVisible(true);
			}
		});
		Image img5=new ImageIcon(this.getClass().getResource("setting.png")).getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT);
		btnNewButton_3_1.setIcon(new ImageIcon(img5));
		btnNewButton_3_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		contentPane.add(btnNewButton_3_1);
	}
}
