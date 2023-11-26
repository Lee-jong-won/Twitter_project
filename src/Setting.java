import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;



public class Setting extends JFrame {
	public Setting(final String name) {
		setTitle("Setting");
        
        final JFrame jPanel = new JFrame();
        jPanel.setBounds(100, 100, 400, 250);
        
		JLabel lblUsername = new JLabel("User : " + name);
        JButton btn1 = new JButton("Dark Mode");
        JButton btn2 = new JButton("Change Password");
        JButton btn3 = new JButton("close");

     
        
        jPanel.setSize(300, 450); //李� �겕湲� �꽕�젙
        
        lblUsername.setBounds(75, 30, 150, 30);
        btn1.setBounds(75, 150, 150, 30);
        btn2.setBounds(75, 200, 150, 30);
        btn3.setBounds(75, 250, 150, 30);

        
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        btn1.setHorizontalAlignment(JLabel.CENTER);
        btn2.setHorizontalAlignment(JLabel.CENTER);
        btn3.setHorizontalAlignment(JLabel.CENTER);

        
        jPanel.getContentPane().setLayout(null);
        
        jPanel.getContentPane().add(lblUsername);
        jPanel.getContentPane().add(btn1);
        jPanel.getContentPane().add(btn2);
        jPanel.getContentPane().add(btn3);

            
        jPanel.setVisible(true);
         
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
        });
        
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            Changepwd pwd = new Changepwd(name);
            	
            }
        });
        
        
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jPanel.dispose(); // 李� �븞蹂댁씠寃� �븯湲� 
            }
        });
    }

}