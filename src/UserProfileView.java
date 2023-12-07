import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class UserProfileView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel info_area;
	private JLabel user_Mark;
	
	private JPanel number_info;
	
	private JPanel follower_area;
	private JLabel follower_num;
	private JLabel follower_mark;
	private JButton follower_view;
	
	private JPanel following_area;
	private JLabel following_num;
	private JLabel following_mark;
	private JButton following_view;
	
	private JButton follow_Bttn;
	
	private JPanel postarea;
	private JList<String> Postlist;
	private UserProfileModel2 Model2;
	private JButton view_Post;
	private JScrollPane scrolled;
	
	public UserProfileView(String name, String login_name)
	{
		setTitle("Profile");
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		UserProfileController controller = new UserProfileController(this, name);	
		
		info_area = new JPanel();
		user_Mark = new JLabel("  Username:" + name);
		info_area.add(user_Mark);	
		contentPane.add(info_area, BorderLayout.NORTH);
		//Info area's JComponent definition
		
		number_info = new JPanel();
		number_info.setLayout(new BoxLayout(number_info, BoxLayout.Y_AXIS));
		
		follow_Bttn = new JButton("follow");
		if(name.compareToIgnoreCase(login_name) == 0)
		{
			follow_Bttn.setEnabled(false);
		}
		follow_Bttn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new ViewFollowing(name);
			}
		});

		

		following_area = new JPanel();
		following_mark = new JLabel("following");
		following_num = new JLabel("num:");
		following_view = new JButton("view:");
		following_area.setLayout(new BoxLayout(following_area, BoxLayout.Y_AXIS));
		following_area.setBorder(BorderFactory.createTitledBorder("following"));		
		following_area.add(following_num);
		following_area.add(following_view);
		//following area JComponent definition
		
		
		following_view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new ViewFollowing(name);
			}
		});
		//If you click view button in the following area, the following list of people that follow you appears to you
		
		
		
		follower_area = new JPanel();
		follower_mark = new JLabel("follower");
		follower_num = new JLabel("num:");
		follower_view = new JButton("view");
		follower_area.setLayout(new BoxLayout(follower_area, BoxLayout.Y_AXIS));
		follower_area.setBorder(BorderFactory.createTitledBorder("follower"));
		follower_area.add(follower_num);
		follower_area.add(follower_view);
		//follower area JComponent definition
		
		
		follower_view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new ViewFollower(name);
			}
		});
		//If you click view button in the follower area, the follower list of people that you follow appears to you

		number_info.add(follow_Bttn);
		number_info.add(following_area);
		number_info.add(follower_area);
		contentPane.add(number_info, BorderLayout.WEST);
		
		
		postarea = new JPanel();
		Model2 = new UserProfileModel2(name);
		Model2.InsertMine();
		Postlist = new JList<String>(Model2);
		Postlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrolled = new JScrollPane(Postlist);
		view_Post = new JButton("view");
		
		view_Post.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String for_id = Postlist.getSelectedValue();
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
		
		
		
		
		postarea.setLayout(new BorderLayout());
		postarea.add(scrolled, BorderLayout.CENTER);
		postarea.add(view_Post, BorderLayout.SOUTH);		
		contentPane.add(postarea, BorderLayout.CENTER);
			
		following_num.setText("num:" + controller.GetFollowingNum());		
		follower_num.setText("Num:" + controller.GetFollowerNum());
				
				
		pack();
	    setVisible(true);   	    
	}
	
	
	
	
	
}
