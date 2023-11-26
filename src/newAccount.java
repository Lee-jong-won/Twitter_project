import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class newAccount extends JFrame{
	
    private JTextField txtUsername, txtPassword;
    private JButton btnLogin, btsignup;
    private JLabel lblErrorMessage;
    protected boolean loginSuccess = false;
    public static String Gname, Gpwd;
    public static String name;
    
    
	public newAccount() {
	setTitle("Sign Up");
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(150, 150, 400, 250);
    Container contentPane = this.getContentPane();
    contentPane.setLayout(null);

    JLabel lblUsername = new JLabel("Username:");
    lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
    lblUsername.setBounds(10, 11, 120, 25);
    contentPane.add(lblUsername);

    txtUsername = new JTextField();
    txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txtUsername.setBounds(140, 11, 200, 25);
    contentPane.add(txtUsername);

    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
    lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
    lblPassword.setBounds(10, 52, 120, 25);
    contentPane.add(lblPassword);

    txtPassword = new JTextField();
    txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
    txtPassword.setBounds(140, 52, 200, 25);
    contentPane.add(txtPassword);
    //gui component들 정의

    btnLogin = new JButton("Sign Up");
    btnLogin.addActionListener(new ActionListener(){

        public void actionPerformed(ActionEvent arg0) {
        	// 새 계정
        	LoginModel lm = new LoginModel();
        	
        	int i = lm.newAcc(txtUsername.getText(),txtPassword.getText());
        	
        	if(i == 0)
        		lblErrorMessage.setText("User id Already exist!");
        	else {
        		EventPrint ep = new EventPrint("New Account Created!");
        		dispose();
        	}
        }
    });
    btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btnLogin.setBounds(251, 93, 89, 25);
    contentPane.add(btnLogin);

    
    btsignup = new JButton("Back");
    btsignup.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {

        	dispose();
            
        }
    });
    btsignup.setFont(new Font("Tahoma", Font.PLAIN, 14));
    btsignup.setBounds(75, 93, 115, 25);
    contentPane.add(btsignup);   
    
    
    lblErrorMessage = new JLabel("");
    lblErrorMessage.setHorizontalAlignment(SwingConstants.RIGHT);
    lblErrorMessage.setForeground(Color.RED);
    lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblErrorMessage.setBounds(10, 151, 330, 25);
    contentPane.add(lblErrorMessage);
    
    setVisible(true);
    
	}
}
