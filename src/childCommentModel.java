import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class childCommentModel extends DefaultListModel<String>{
					
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public String getContent(String commentID,int i){
			Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
				// 0 means get Content
		        if (i == 0) {
		            PreparedStatement select = conn.prepareStatement("SELECT cmt_id FROM child_comment WHERE comment_comment_id = ?;");
		            select.setString(1, commentID);
		            ResultSet rs = select.executeQuery();

		            String Content = "";
		            
		            while (rs.next()) {
		                // Use the current row's comment_id to fetch content and user_user_id
		                String currentCommentId = rs.getString(1);

		                PreparedStatement selectContent = conn.prepareStatement("SELECT content FROM child_comment WHERE cmt_id = ?;");
		                selectContent.setString(1, currentCommentId);
		                ResultSet rsContent = selectContent.executeQuery();

		                PreparedStatement selectUser = conn.prepareStatement("SELECT user_user_id FROM child_comment WHERE cmt_id = ?;");
		                selectUser.setString(1, currentCommentId);
		                ResultSet rsUser = selectUser.executeQuery();

		                // Check if there are results before fetching data
		                if (rsContent.next() && rsUser.next()) {
		                    Content = Content + rsUser.getString(1) + ": " + rsContent.getString(1) + "\n";
		                }
		            }		     
		            this.addElement(Content);
		            return Content;
		        }			
				
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally{
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
			return commentID;
	    }
	    
	    public void newComment(final String uname, final String commentID, String text){
	    	Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
				PreparedStatement select = conn.prepareStatement("SELECT cmt_id FROM child_comment");				
				ResultSet rs = select.executeQuery();
				int num=1;
				while (rs.next()) {
					num++;
				}
				String s1 = "INSERT INTO child_comment (cmt_id, content, user_user_id, comment_comment_id) VALUES (?, ?, ?, ?)";
				PreparedStatement select1 = conn.prepareStatement(s1);
				select1.setInt(1, num);
				select1.setString(2, text);
				select1.setString(3, uname);
				select1.setString(4, commentID);
				select1.executeUpdate();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
	        finally{
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    } 
	    
	}
