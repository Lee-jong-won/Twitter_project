import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;



public class Home extends JFrame {
	
 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Home(final String name)
		{
			setTitle(name);
			Container contentPane = getContentPane();			
			contentPane.setLayout(new GridBagLayout());
			
			
			JPanel Buttonset = new JPanel();
			gbinsert(this.getContentPane(), Buttonset, 0 ,0, 1 ,3);			
			
			
			Buttonset.setLayout(new GridBagLayout());
					
			JButton explore = new JButton("Explore");
			gbinsert(Buttonset, explore, 0, 0, 1, 1);
			
			
			JButton profile = new JButton("Profile");
			gbinsert(Buttonset, profile, 0, 1, 1, 1);	
			
				
			JButton Setting = new JButton("Setting");
			gbinsert(Buttonset, Setting, 0, 2, 1, 1);
			
			JButton Logout = new JButton("Log Out");
			gbinsert(Buttonset, Logout, 0, 3, 1, 1);
			
							
			JPanel PostPart = new JPanel();	
			JButton write_Button = new JButton("write");
			TextField post_Text = new TextField(30);
			PostPart.add(post_Text);
			PostPart.add(write_Button);
			gbinsert(this.getContentPane(), PostPart, 1, 0, 3, 1);
			
			JPanel postSeeParts = new JPanel();
			postSeeParts.setLayout(new BorderLayout());
			postSeeParts.add(new JLabel("See your following posts:"), BorderLayout.NORTH);
			postSeeParts.add(new JScrollPane(new TextArea(5,30)), BorderLayout.CENTER);
			gbinsert(this.getContentPane(), postSeeParts, 1, 1, 3, 2);
			
			this.pack();
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			
			
			
			explore.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	          
	            }
	        });
			
			
			profile.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	new UserProfileView(name);
	            }
	        });
			
			
		
			Setting.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	Setting set = new Setting(name);
	            	
	            }
	        });
			
			
			
			Logout.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	dispose();
	            
	                LoginView view = new LoginView();
	                view.setVisible(true);
	            }
	        });
			
			
			
			

			
		}
	 
	 
	 
	 public void gbinsert(Container ct, Component c, int x, int y, int w, int h){
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.fill= GridBagConstraints.BOTH;
	        gbc.gridx = x;
	        gbc.gridy = y;
	        gbc.gridwidth = w;
	        gbc.gridheight = h;
	        ct.add(c, gbc);
	 }	 
	 
	
	 
	
}
