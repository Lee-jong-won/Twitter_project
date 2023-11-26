import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfileModel {
	
	
	
	private String username;
	private int following_Num;
	private int follower_Num;
	private Connection conn;
	
	public UserProfileModel(String new_name)
	{
		username = new_name;
	}
	
	
	public int GetFollowingNum()
	{
		return following_Num;
	}
	
	public int GetFollowerNum()
	{
		return follower_Num;
	}
	
	public void setFollowing_Num()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "h6644h7749";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT count(*) FROM follower where follower_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();

			if (rs.next()) {
				following_Num = rs.getInt("COUNT(*)");
			} else {
				following_Num = -1;
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
	
	
	
	public void setFollower_Num()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "h6644h7749";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT count(*) FROM follower where user_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();

			if (rs.next()) {
				follower_Num = rs.getInt("COUNT(*)");
			} else {
				follower_Num = -1;
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
