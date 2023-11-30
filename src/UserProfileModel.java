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
	//It is UserProfileModel's constructor.  
	
	
	public int GetFollowingNum()
	{
		return following_Num;
	}
	//It must be used after setFollowng_Num method is invoked
	
	public int GetFollowerNum()
	{
		return follower_Num;
	}
	//It must be used after setFollower_Num method is invoked
	
	public void setFollowing_Num()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT count(*) FROM following where user_user_id = ?");
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
	//Read following number of people that specific user follows
	
	
	
	public void setFollower_Num()
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT count(*) FROM follower where user_user_id = ?");
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
	//Read follower number of people that follows specific user
	
		
}

