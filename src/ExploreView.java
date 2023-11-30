import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ExploreView extends JFrame{
	private JList<String> list;
	private JTextField inputfield;
	private JButton SearchBtn;		//추가 버튼
	private JButton ViewBtn;
	private JScrollPane scrolled;
	private ExploreModel model;
	
	public ExploreView(String title)
	{
		model = new ExploreModel(title);
		list = new JList<String>(model);
		inputfield = new JTextField(35);
		SearchBtn = new JButton("검색");
		ViewBtn = new JButton("프로필 보기");		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	
		
		this.setLayout(new BorderLayout());
		
		JPanel topPanel=new JPanel(new FlowLayout(10,10,FlowLayout.LEFT));
		topPanel.add(inputfield);
		topPanel.add(SearchBtn);
		topPanel.add(ViewBtn);		
		topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));	
		
		scrolled = new JScrollPane(list);
		scrolled.setBorder(BorderFactory.createEmptyBorder(0,10,10,10)); 
		//JComponent definition
		
		
		SearchBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {	
				model.clear();
				model.Set_List(inputfield.getText());
			}
		});
		//if you write name to search and click search button, the list of names you want to find appears 
		
		
		ViewBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				new UserProfileView(list.getSelectedValue(), title);
			}
			
		});
		//If you choose someone after exploring someone that you want to find, that user's profile appears to you
		
		
		
		this.add(topPanel,"North");
		this.add(scrolled,"Center");	//가운데 list		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(620,400);
		this.setLocationRelativeTo(null);	//창 가운데 위치
		this.setVisible(true);	
				
	}
	
}
