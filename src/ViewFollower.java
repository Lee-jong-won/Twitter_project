import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class ViewFollower extends JFrame{
	
	private JButton view;
	protected JList<String> list;
	private JLabel label;
	private JScrollPane scrolled;
	
	
	public ViewFollower(String name)
	{
		setTitle("follower");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BorderLayout());
		
	    ViewFollowingModel new_model = new ViewFollowingModel(name);
	    new_model.setFollowerInfo();
		list = new JList<String>(new_model);
		view = new JButton("view");
		label = new JLabel("your follower:");
		scrolled = new JScrollPane(list);
		//JComponent definition
		
		
		view.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new UserProfileView(list.getSelectedValue(), name);
			}
		});
		//If user click view button, profile whose name is selected in list by user appears to user.
		
		
		
		contentPane.add(label, "North");
		contentPane.add(scrolled, "Center");
		contentPane.add(view, "South");	
		//Add components to JFrame's contentpane
		
		
		
		
	
		pack();
	    this.setSize(300, 250);
	    setVisible(true); 
	}
}
