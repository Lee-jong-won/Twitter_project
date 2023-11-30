import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class UserProfileModel2 extends DefaultListModel<String> {
	
	private Connection conn;
	private String username;
	
	
	public UserProfileModel2(String new_name)
	{
		username = new_name;
	}
	
	
	public void InsertMine()
	{
		
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT post_id , content FROM Posts where user_user_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				String new_ID = rs.getString(1);
				String new_content = rs.getString(2);
				String to_display = username + "(" + new_ID + ") : " + new_content;				
				this.addElement(to_display);			
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
