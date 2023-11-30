import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LoginView extends JFrame {

    private static final long serialVersionUID = 3566038652320101414L;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btsignup;
    private JLabel lblErrorMessage;
    protected boolean loginSuccess = false;
    public static String Gname, Gpwd;
    public static String name;
    
    public LoginView() {
        setTitle("MedIT: Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
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

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPassword.setBounds(140, 52, 200, 25);
        contentPane.add(txtPassword);
        //gui component definition

        final LoginController controller = new LoginController(this);
        btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				boolean flag1 = controller.checkIDnowrite(txtUsername.getText());
				boolean flag2 = controller.checkpasswordnowrite(new String(txtPassword.getPassword()));
				
				if(!flag1 && !flag2)
				{
					controller.checkCredentials(txtUsername.getText(), new String(txtPassword.getPassword()));		
					if(lblErrorMessage.getText().compareToIgnoreCase("Login Success!") == 0)
	                {
	                	new Home(txtUsername.getText());
	                	dispose();
	                }
					
				}
	
			}
		});
		//If you click login button, login function is activated by controller which performs comparing password and name with DB
		//If you don't write ID, "please write ID" message appears to you
		//If you don't write password, "please write password" message appears to you
		//If you write ID and password, but it is not correct with DB, "Login failed" message appears to you
		
		/*enter 눌렀을 때, 로그인 기능 활성화 시키기 코드 미완성
		btnLogin.addKeyListener( new KeyListener() {
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					boolean flag1 = controller.checkIDnowrite(txtUsername.getText());
					boolean flag2 = controller.checkpasswordnowrite(new String(txtPassword.getPassword()));
					
					if(!flag1 && !flag2)
					{
						controller.checkCredentials(txtUsername.getText(), new String(txtPassword.getPassword()));		
						if(lblErrorMessage.getText().compareToIgnoreCase("Login Success!") == 0)
		                {
		                	new Home(txtUsername.getText());
		                	dispose();
		                }
						
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		
		
		} );
		*/
		
        btnLogin.setFont(new Font("Tahoma", Font.PLAIN , 14));
        btnLogin.setBounds(251, 93, 89, 25);
        contentPane.add(btnLogin);

        
        btsignup = new JButton("New Accoont");
        btsignup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	// �깉 怨꾩젙
            	
            	newAccount na = new newAccount();
                
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
    }

    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }
    
    public String returnname() {
    	return name;
    }

    
    public static void main(String args[]){
        LoginView view = new LoginView();
        view.setVisible(true);
        
        
                
    }
}