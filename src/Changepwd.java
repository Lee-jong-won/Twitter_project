import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;


public class Changepwd extends JFrame{
	
	 JLabel lblErrorMessage;
	
	public Changepwd(final String name){
		final JTextField txtUsername;
		 JButton btn1, btn2;
		 
        setTitle("Password Change");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        
        JLabel lblUsername = new JLabel("Enter Current Password");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(10, 11, 200, 25);
        contentPane.add(lblUsername);
        
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
        txtUsername.setBounds(10, 40, 200, 25);
        contentPane.add(txtUsername);

        btn1 = new JButton("ok");
        
    	final ChangepwdController np = new ChangepwdController(this);
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                {
                	np.checkCredentials(name, txtUsername.getText());
                	
                    if(lblErrorMessage.getText().compareToIgnoreCase("Correct Password") == 0)
                    {
                    	Newpwd cp = new Newpwd(name,txtUsername.getText());
                    	dispose();
                    } 
                }
            }
        });
        btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn1.setBounds(70, 75, 89, 25);
        contentPane.add(btn1);

        lblErrorMessage = new JLabel("");
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblErrorMessage.setBounds(70, 120, 200, 25);
        contentPane.add(lblErrorMessage);
        
        
        btn2 = new JButton("Close");
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	dispose();
            }
        });
        btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn2.setBounds(70, 120, 89, 25);
        contentPane.add(btn2);
        
        
        setVisible(true);
		
        
        
        
        
	}
	
    public void setErrorMessage(String errorMessage) {
        lblErrorMessage.setText(errorMessage);
    }
}
