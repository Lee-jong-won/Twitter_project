import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class commentModel extends DefaultListModel<String>{
					
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public String getContent(String postid,int i){
			Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
				// 0 means get Content
		        if (i == 0) {
		            PreparedStatement select = conn.prepareStatement("SELECT comment_id FROM comment WHERE post_post_id = ?;");
		            select.setString(1, postid);
		            ResultSet rs = select.executeQuery();

		            String Content = "";
		            
		            while (rs.next()) {
		                // Use the current row's comment_id to fetch content and user_user_id
		                String currentCommentId = rs.getString(1);

		                PreparedStatement selectContent = conn.prepareStatement("SELECT content FROM comment WHERE comment_id = ?;");
		                selectContent.setString(1, currentCommentId);
		                ResultSet rsContent = selectContent.executeQuery();

		                PreparedStatement selectUser = conn.prepareStatement("SELECT user_user_id FROM comment WHERE comment_id = ?;");
		                selectUser.setString(1, currentCommentId);
		                ResultSet rsUser = selectUser.executeQuery();

		                // Check if there are results before fetching data
		                if (rsContent.next() && rsUser.next()) {
		                    Content = Content + rsUser.getString(1) + "#"+currentCommentId + ": " + rsContent.getString(1)  +"\n";
		                }
		            }		     
		            this.addElement(Content);
		            return Content;
		        }
				
				//1 mean get User_name
				else if(i == 1)
				{
					PreparedStatement select = conn.prepareStatement("select user_user_id from Posts where post_id = ?;");
					select.setString(1,postid);
					
					ResultSet rs = select.executeQuery();

					String Content = null;
					
					
					if (rs.next()) {
						Content = rs.getString(1);
						return Content;
					} else {
						return Content;
		            }
				}
				
				//2 mean get num of like
				else if(i == 2)
				{
					PreparedStatement select = conn.prepareStatement("select num_of_like from Posts where post_id = ?;");
					select.setString(1,postid);
					
					ResultSet rs = select.executeQuery();

					String Content = null;
					
					
					if (rs.next()) {
						Content = rs.getString(1);
						return Content;
					} else {
						return Content;
		            }
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
			return postid;
	    }
		
	    	
	    public int Like(String u_name, String postid){
	    	int ret = 0;
			Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
					PreparedStatement select = conn.prepareStatement("select liker_id from Post_like where liker_id = ? and posts_post_id = ?;");
					select.setString(1,u_name);
					select.setString(2,postid);
					ResultSet rs = select.executeQuery();
					
					int plid = 0;
					
					// already press like
					if (rs.next()) {
						PreparedStatement select2 = conn.prepareStatement("delete from Post_like where liker_id = ? and posts_post_id = ?;");
						select2.setString(1,u_name);
						select2.setString(2,postid);
						select2.executeUpdate();
						
						
						
						PreparedStatement select3 = conn.prepareStatement("update Posts set num_of_like = ? where post_id = ?;");
						select3.setLong(1,Integer.parseInt(getContent(postid, 2))-1);
						select3.setString(2,postid);
						select3.executeUpdate();
						
						EventPrint e = new EventPrint("You Cancle the Like!");
					}
					// new like
					else {
						while(true) {
							select = conn.prepareStatement("select l_id from Post_like where l_id = ?;");
							select.setLong(1,plid);
							rs = select.executeQuery();
							
							if(rs.next()){
								plid++;
							}
							else {
								PreparedStatement select2 = conn.prepareStatement("insert into Post_like values (?,?,?);");
								select2.setLong(1,plid);
								select2.setString(2,u_name);
								select2.setString(3,postid);
								select2.executeUpdate();
								
								PreparedStatement select3 = conn.prepareStatement("update Posts set num_of_like = ? where post_id = ?;");
								select3.setLong(1,Integer.parseInt(getContent(postid, 2))+1);
								select3.setString(2,postid);
								select3.executeUpdate();
								
								EventPrint e = new EventPrint("You Like the Post!");
								
								break;
						
							}
						}
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
			return ret;
	    }
	    
	    public void newComment(final String uname, final String postname, String text){
	    	Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
				PreparedStatement select = conn.prepareStatement("SELECT comment_id FROM comment");				
				ResultSet rs = select.executeQuery();
				int num=1;
				while (rs.next()) {
					num++;
				}
				String s1 = "insert into comment values ( \'" + num + "\',  \'" + text + "\',"+0+", \'"+postname+"\', \'"+uname+"\')";	
				select = conn.prepareStatement(s1);
	    		select.executeUpdate();
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