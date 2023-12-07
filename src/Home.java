import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;



public class Home extends JFrame {
	
 
	 
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
			
			JButton Chatting = new JButton("Chatting");
			gbinsert(Buttonset, Chatting, 0, 3, 1, 1);
			
			JButton Logout = new JButton("Log Out");
			gbinsert(Buttonset, Logout, 0, 4, 1, 1);
			
							
			JPanel PostPart = new JPanel();	
			JButton write_Button = new JButton("write");
			TextField post_Text = new TextField(30);
			PostPart.add(post_Text);
			PostPart.add(write_Button);
			gbinsert(this.getContentPane(), PostPart, 1, 0, 3, 1);
			
			JPanel postSeeParts = new JPanel();
			HomeModel Home_Model = new HomeModel(name);
			JList<String> postList = new JList<String>(Home_Model);
			postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
			
			Home_Model.InsertMine(name);
			Home_Model.InsertFollowing(name);
			
			JButton viewPost = new JButton("view post");
			postSeeParts.setLayout(new BorderLayout());
			postSeeParts.add(new JLabel("See your following posts:"), BorderLayout.NORTH);
			postSeeParts.add(new JScrollPane(postList), BorderLayout.CENTER);
			postSeeParts.add(viewPost, BorderLayout.SOUTH);
			gbinsert(this.getContentPane(), postSeeParts, 1, 1, 3, 2);
			
			this.pack();
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
			
			
			
			explore.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	new ExploreView(name);
	            }
	        });
			
			
			profile.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	new UserProfileView(name, name);
	            }
	        });
			
			
		
			Setting.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	Setting set = new Setting(name);
	            	
	            }
	        });
			
			
			write_Button.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent arg0) {
					//String postName = post_Text.getText();
					String postContent = post_Text.getText();
					
					int new_postid = postModel.getNewIndex();
					postModel.insertContent("p" + new_postid, postContent, name);		
			        new postView(name, "p" + new_postid);
			        dispose();
			        new Home(name);
			    }
			});
			
			
			Chatting.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent arg0) {
			        int portNumber = 12345;
			        Thread serverThread = new Thread(() -> {
			            ChattingServer chatServer = new ChattingServer(portNumber);
			            try {
			                chatServer.run(); // You might want to consider using start() if run() is not overridden
			            } catch (IOException e) {
			                e.printStackTrace();
			            }
			        });
			        serverThread.start();

			        new ChattingView();
			    }
			});

			
			
			
			
			Logout.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent arg0) {
	            	dispose();   
	                LoginView view = new LoginView();
	                view.setVisible(true);
	            }
	        });
			
			
			viewPost.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					String for_id = postList.getSelectedValue();
					String post_id = null;
					int first = -1;
					int second = -1;
					
					for(int i = 0; i < for_id.length(); i++)
					{
						if( for_id.charAt(i) == '(')
						{
							first = i;
						}
						
						if(for_id.charAt(i) == ')')
						{
							second = i;
						}
					}
					
					post_id = for_id.substring(first + 1, second);
					new postView(name, post_id);
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
