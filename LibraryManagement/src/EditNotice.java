import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EditNotice extends JFrame {

	private JPanel contentPane;
	private TextArea textArea;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditNotice frame = new EditNotice();
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
	public EditNotice() {
		setTitle("Add New Notice");
		setResizable(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 261, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 636, 406);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblEditNotice = new JLabel("Edit Notice");
		lblEditNotice.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditNotice.setFont(new Font("Verdana", Font.BOLD, 16));
		
		DBInfo.getNotice();
		String value=DBInfo.value;

		textArea = new TextArea(value);
		textArea.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		
		JButton btnNewButton = new JButton("Cancel");
		Image cancelIcon=new ImageIcon(this.getClass().getResource("red-x-mark-transparent-background-3.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnNewButton.setIcon(new ImageIcon(cancelIcon));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnSave = new JButton("Save");
		Image saveIcon=new ImageIcon(this.getClass().getResource("login.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnSave.setIcon(new ImageIcon(saveIcon));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int success=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to add a note or amend one?","Confirm",JOptionPane.INFORMATION_MESSAGE);
				if(success==0)
				{
					String notice=textArea.getText();
					System.out.println(notice);
					Connection con=DBInfo.conn();
					String query="UPDATE notice SET notice=? WHERE id=1";
					int flag=0;
					try {
						PreparedStatement ps=con.prepareStatement(query);
						ps.setString(1, notice);
						flag=ps.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if(flag==1)
					{
						JOptionPane.showMessageDialog(getParent(), "Notice successfully added","Success",JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Notice not added","Failed",JOptionPane.ERROR_MESSAGE);
				}
				lblNewLabel.setText(null);
				String textLength=textArea.getText();
				int totalLen=textLength.length()-360;
				if(textLength.length()>360)
				{
					lblNewLabel.setText("Character extended out from "+totalLen);
				}
			}
		});
		btnSave.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnDelete = new JButton("Delete");
		Image img=new ImageIcon(this.getClass().getResource("deleteIcon.png")).getImage().getScaledInstance(13, 17, Image.SCALE_DEFAULT);
		btnDelete.setIcon(new ImageIcon(img));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				String notice=textArea.getText();
				if(notice.length()==0)
				{
					JOptionPane.showMessageDialog(getParent(), "Cannot delete the notice because it is already empty.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				} else if(notice.equalsIgnoreCase("No Notice"))
				{
					JOptionPane.showMessageDialog(getParent(), "No notice is available or displayed in the text field.","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Connection con=DBInfo.conn();
					String query="UPDATE notice SET notice='No Notice' WHERE id=1";
					try {
						PreparedStatement ps=con.prepareStatement(query);
						ps.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			
					Connection conn=DBInfo.conn();
					String sql="SELECT * FROM notice WHERE notice='No Notice'";
					String noNotice = null;
					try {
						PreparedStatement ps=con.prepareStatement(sql);
						ResultSet res=ps.executeQuery();
						while(res.next())
						{
							noNotice=res.getString(2);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					textArea.setText(noNotice);
					lblNewLabel.setText(null);
					
					String textLength=textArea.getText();
					int totalLen=textLength.length()-360;
					if(textLength.length()>360)
					{
						lblNewLabel.setText("Character extended out from "+totalLen);
					}
				}
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JLabel lblNewLabel_2 = new JLabel("No notice should be longer than 360 characters.");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		JLabel lblNewLabel_2_1 = new JLabel("To see the the notice changes, restart the programme.");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Verdana", Font.ITALIC, 13));
		
		lblNewLabel = new JLabel("");
		String textLength=textArea.getText();
		int totalLen=textLength.length()-360;
		if(textLength.length()>360)
		{
			lblNewLabel.setText("Character extended out from "+totalLen);
		}
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblEditNotice, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(23)
							.addComponent(btnSave)
							.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
							.addComponent(btnDelete)
							.addGap(52)))
					.addGap(50)
					.addComponent(btnNewButton)
					.addGap(66))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 539, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblEditNotice, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(33))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
