import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class childCommentView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnReply, btnBack, btnView;
    childCommentModel ccm = new childCommentModel();
    DefaultListModel<String> commentListModel = new DefaultListModel<>();
    JList<String> commentList;
    JScrollPane scrollPane;
    JTextField txtComment;

    public childCommentView(final String uname, final String commentID, final String login_name) {
        setTitle("Child Comment");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(200, 200, 400, 500);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        
        JLabel lbName = new JLabel(commentID + "'s comment");
        lbName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lbName.setHorizontalAlignment(SwingConstants.CENTER);
        lbName.setBounds(50, 10, 120, 25);
        contentPane.add(lbName);
        
        String content = ccm.getContent(commentID, 0);
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
                ccm.newComment(uname, commentID, insert);
                dispose();
                new childCommentView(uname,commentID, login_name);
            }
        });
        btnReply.setBounds(270, 380, 75, 50);
        contentPane.add(btnReply);
        
        btnView = new JButton("view");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String[] parts = commentList.getSelectedValue().split(":");
            	new UserProfileView(parts[0], login_name);
            }
        });
        btnView.setBounds(195, 380, 75, 50);
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