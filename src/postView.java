import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class postView extends JFrame{
	JButton btnLike,btnfollow,btnreply,btnback;
	postModel pm = new postModel();
	public postView(final String uname, final String postname) {
		setTitle("Post");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 500);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
		
		
        JLabel lbname = new JLabel(pm.getContent(postname,1) + "'s post" + "(id:" + postname + ")");
        lbname.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbname.setHorizontalAlignment(SwingConstants.CENTER);
        lbname.setBounds(50, 10, 200, 25);
        contentPane.add(lbname);
        

        String post = pm.getContent(postname,0);

        	
        
        JLabel lblpost = new JLabel("<html><body>"+ post + "</body></html>");
        lblpost.setHorizontalAlignment(SwingConstants.LEFT);
        lblpost.setBounds(50, 40, 290, 320);
        contentPane.add(lblpost);
       
        lblpost.setOpaque(true);
        lblpost.setBackground(Color.white);
        
        
        
        btnLike = new JButton("<html><body> Like <br>"+  pm.getContent(postname, 2)  +"</body></html>");
        btnLike.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	pm.Like(uname, postname);
            	btnLike.setText("<html><body> Like <br>"+  pm.getContent(postname, 2)  +"</body></html>");
            }
        });
        btnLike.setBounds(45, 380, 75, 50);
        contentPane.add(btnLike);
        
        
        btnfollow = new JButton("User");
        btnfollow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	new UserProfileView(pm.getContent(postname, 1), uname);
            }
        });
        btnfollow.setBounds(120, 380, 75, 50);
        contentPane.add(btnfollow);
        
        
        btnreply = new JButton("Reply");
        btnreply.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	new commentView(pm.getContent(postname, 1), postname, uname);
            }
        });
        btnreply.setBounds(195, 380, 75, 50);
        contentPane.add(btnreply);
        
        
        btnback = new JButton("Back");
        btnback.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
            	dispose();
            }
        });
        btnback.setBounds(270, 380, 75, 50);
        contentPane.add(btnback);
        
        
        setVisible(true);
	}
}
