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


public class Newpwd extends JFrame {
	
	 JLabel lblErrorMessage;
	 final JTextField first_pwd, sec_pwd;
	 JButton btn1, btn2;
	 
	public Newpwd(final String name,final String password){
		
		setTitle("Password Change");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 250, 250);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        
        JLabel lblUsername = new JLabel("Enter new password Twice");
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setBounds(10, 10, 200, 25);
        contentPane.add(lblUsername);
        
        first_pwd = new JTextField();
        first_pwd.setFont(new Font("Tahoma", Font.PLAIN, 14));
        first_pwd.setHorizontalAlignment(SwingConstants.CENTER);
        first_pwd.setBounds(10, 40, 200, 25);
        contentPane.add(first_pwd);
        
        sec_pwd = new JTextField();
        sec_pwd.setFont(new Font("Tahoma", Font.PLAIN, 14));
        sec_pwd.setHorizontalAlignment(SwingConstants.CENTER);
        sec_pwd.setBounds(10, 80, 200, 25);
        contentPane.add(sec_pwd);
        
        lblErrorMessage = new JLabel("");
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setForeground(Color.RED);
        lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblErrorMessage.setBounds(35, 150, 200, 25);
        contentPane.add(lblErrorMessage);
		
        
        btn1 = new JButton("ok");
        
        btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                {
                    if(first_pwd.getText().equals(sec_pwd.getText())) {
                    	if(first_pwd.getText().equals(password))
                    		lblErrorMessage.setText("Can not change same password");
                    	else {
                    		ChangepwdModel model = new ChangepwdModel();
                    		model.changepwd(name, first_pwd.getText());
                    		EventPrint ep = new EventPrint("Password Changed!");
                    		dispose();
                    	}
                    }
                    else
                    	lblErrorMessage.setText("Two password is different!");
                }
            }
        });
        btn1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn1.setBounds(70, 120, 89, 25);
        contentPane.add(btn1);
        
        
        btn2 = new JButton("Close");
        btn2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	dispose();
            }
        });
        btn2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn2.setBounds(70, 170, 89, 25);
        contentPane.add(btn2);
        
        
        
        setVisible(true);
	}
}
