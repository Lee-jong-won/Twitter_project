import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class ViewFollowingModel extends DefaultListModel<String>{
		
	private String username;
	private Connection conn;
	
	public ViewFollowingModel(String name)
	{
		username = name;
	}
	

	public void setFollowingInfo()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "h6644h7749";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT user_id FROM follower where follower_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				this.addElement(rs.getString(1));
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
	}
	
	
	public void setFollowerInfo()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "h6644h7749";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT follower_id FROM follower where user_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				this.addElement(rs.getString(1));
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
	}
	
	
	
	
}
