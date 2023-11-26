import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class UserProfileView extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel info_area;
	private JLabel user_Mark;
	private JLabel user_Name;
		
	private JPanel follower_area;
	private JLabel follower_num;
	private JLabel follower_mark;
	private JButton follower_view;
	
	private JPanel following_area;
	private JLabel following_num;
	private JLabel following_mark;
	private JButton following_view;
	
	public UserProfileView(String name)
	{
		setTitle("Profile");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridBagLayout());
		
		
		info_area = new JPanel();
		info_area.setLayout(new BoxLayout(info_area, BoxLayout.Y_AXIS));
		gbinsert(contentPane, info_area, 0, 0, 1, 1, 2, 1 );
		user_Mark = new JLabel("  Username:" + name);
		info_area.add(user_Mark);
		
				
		following_area = new JPanel();
		following_mark = new JLabel("following");
		following_num = new JLabel("num:");
		following_view = new JButton("view:");
		following_area.setLayout(new BoxLayout(following_area, BoxLayout.Y_AXIS));
		following_area.setBorder(BorderFactory.createTitledBorder("following"));
		gbinsert(contentPane, following_area, 0, 1, 1, 1, 3, 1);		
		following_area.add(following_num);
		following_area.add(following_view);
		
		
		following_view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new ViewFollowing(name);
			}
		});
		
		
		
		
		follower_area = new JPanel();
		follower_mark = new JLabel("follower");
		follower_num = new JLabel("num:");
		follower_view = new JButton("view");
		follower_area.setLayout(new BoxLayout(follower_area, BoxLayout.Y_AXIS));
		follower_area.setBorder(BorderFactory.createTitledBorder("follower"));
		gbinsert(contentPane, follower_area, 1, 1, 1, 1, 3, 1);
		follower_area.add(follower_num);
		follower_area.add(follower_view);
		
		
		follower_view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new ViewFollower(name);
			}
		});

		
		
		UserProfileController controller = new UserProfileController(this, name);		
		following_num.setText("num:" + controller.GetFollowingNum());		
		follower_num.setText("Num:" + controller.GetFollowerNum());
				
				
		pack();
	    this.setSize(300, 250);
	    setVisible(true);   	    
	}
	
	
	public void gbinsert(Container ct, Component c, int x, int y, int w, int h, int pad_x, int pad_y){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbc.ipadx = pad_x;
        gbc.ipady = pad_y;
        ct.add(c, gbc);
 }	 
	
	
}
