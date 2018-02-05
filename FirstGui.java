package firstGui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.Color;

public class FirstGui {

	private JFrame frmLoginForm;
	private JTextField username_field;
	private JTextField email_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstGui window = new FirstGui();
					window.frmLoginForm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FirstGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginForm = new JFrame();
		frmLoginForm.setResizable(false);
		frmLoginForm.setBackground(new Color(0, 51, 255));
		frmLoginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginForm.setSize(240, 240);
		frmLoginForm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\takis\\Desktop\\ico.ico"));
		frmLoginForm.setTitle("Login Form ");
		
		JButton login = new JButton("Login");
		login.addActionListener(
			    new ActionListener() {
			        public void actionPerformed(ActionEvent event) {
			           try {
			        	String driverName = "com.mysql.jdbc.Driver";
			        	Class.forName(driverName);
						String serverName = "localhost:3307";
						String mydatabase = "users";
						String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
						String username = "root";
						String password = "";
						Connection connection = DriverManager.getConnection(url, username, password);
						System.out.println("Connected");
						String name=username_field.getText();
						String pass=email_field.getText();
						String query="select * from clients where username=? and email=?";
						java.sql.PreparedStatement statement=connection.prepareStatement(query);
						statement.setString(1,name);
						statement.setString(2,pass);
						ResultSet set=statement.executeQuery();
						if (set.next()){
							JOptionPane.showMessageDialog(null,"Login Sucessful");
						}
						else {
							JOptionPane.showMessageDialog(null,"Invalid Username or E-mail");
						}
						
						
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        }
			    }
			);
		
		username_field = new JTextField();
		username_field.setColumns(10);
		
		email_field = new JTextField();
		email_field.setColumns(10);
		
		JLabel lblNewLabel1 = new JLabel("User-Name");
		
		JLabel lblNewLabel2 = new JLabel("e-mail");
		GroupLayout groupLayout = new GroupLayout(frmLoginForm.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel1)
						.addComponent(lblNewLabel2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(login)
						.addComponent(email_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(username_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(username_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel1))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel2)
						.addComponent(email_field, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(login)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		frmLoginForm.getContentPane().setLayout(groupLayout);
	}

}
