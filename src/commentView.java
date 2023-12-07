import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class commentView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnReply, btnBack, btnView;
    commentModel cm = new commentModel();
    DefaultListModel<String> commentListModel = new DefaultListModel<>();
    JList<String> commentList;
    JScrollPane scrollPane;
    JTextField txtComment;

    public commentView(final String uname, final String postname, final String login_name) {
        setTitle("Comment");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 500);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        JLabel lbName = new JLabel("comment" + " from " + cm.getContent(postname, 1) + "'s post " + "(id: "+postname+")");
        lbName.setFont(new Font("Tahoma", Font.BOLD, 12));
        lbName.setHorizontalAlignment(SwingConstants.CENTER);
        lbName.setBounds(50, 10, 250, 25);
        contentPane.add(lbName);

        String content = cm.getContent(postname, 0);
        String[] commentsArray = content.split("\n");

        for (String comment : commentsArray) {
            commentListModel.addElement(comment);
        }

        commentList = new JList<>(commentListModel);
        scrollPane = new JScrollPane(commentList);
        scrollPane.setBounds(50, 40, 290, 280);
        contentPane.add(scrollPane);

        txtComment = new JTextField();
        txtComment.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtComment.setBounds(50, 320, 290, 40);
        contentPane.add(txtComment);

        btnReply = new JButton("Reply");
        btnReply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String insert = new String(txtComment.getText());
                cm.newComment(login_name, postname, insert);
                dispose();
                new commentView(uname,postname,login_name);
            }
        });
        btnReply.setBounds(270, 380, 75, 50);
        contentPane.add(btnReply);
        
        btnView = new JButton("view");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String[] parts = commentList.getSelectedValue().split("#");
            	new UserProfileView(parts[0], login_name);
            }
        });
        btnView.setBounds(195, 380, 75, 50);
        contentPane.add(btnView);
        
        btnView = new JButton("Child");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String[] parts1 = commentList.getSelectedValue().split(":");
            	String[] parts2 = parts1[0].split("#");
            	new childCommentView(uname, parts2[1], login_name);  
            }
        });
        btnView.setBounds(120, 380, 75, 50);
        contentPane.add(btnView);

        btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        btnBack.setBounds(45, 380, 75, 50);
        contentPane.add(btnBack);
        
        
        setVisible(true);
    }
}