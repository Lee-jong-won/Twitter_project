import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class postModel {
					
	    public String getContent(String postid){
			Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
				PreparedStatement select = conn.prepareStatement("select content from Posts where post_id = ?;");
				select.setString(1,postid);
				
				ResultSet rs = select.executeQuery();

				String Content = null;
				
				
				if (rs.next()) {
					Content = rs.getString(1);
					return Content;
				} else {
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
			return postid;
	    }
		
	    public String getName(String postid){
			Connection conn = null;
			try {

				String url = "jdbc:mysql://localhost/twitter";
				String user = "root", passwd = "010208";
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, user, passwd);
				
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
	    
	}
