import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;

public class HomeModel extends DefaultListModel<String> {
	
	private String username;
	private Connection conn;
	private Connection conn2;
	
	HomeModel(String new_name)
	{
		username = new_name;
	}
	
	public void InsertMine(String login_name)
	{
		
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT post_id , content FROM Posts where user_user_id = ?");
			select.setString(1, login_name);
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				String new_ID = rs.getString(1);
				String new_content = rs.getString(2);
				String to_display = login_name + "(" + new_ID + ") : " + new_content;				
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
	
	public void InsertFollowing(String login_name)
	{
		//1.login_name이 follow 하는 유저의 id 읽어고기
		//2.그 유저가 쓴 post 모두 읽어오기
		//3.2를 login_name이 follow 하는 모든 유저에 대해 적용
		
		
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn2 = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn2.prepareStatement("SELECT f_id FROM following "
					+ "where user_user_id = ?");
			select.setString(1, login_name);
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				String followingname = rs.getString(1);		
					
				try {
					
					PreparedStatement select2 = conn2.prepareStatement("SELECT post_id , content, user_user_id FROM Posts where user_user_id = ?");
					select2.setString(1, followingname);
					ResultSet rs2 = select2.executeQuery();
					
					while(rs2.next())
					{
						String new_ID = rs2.getString(1);
						String new_content = rs2.getString(2);
						String new_writer = rs2.getString(3);
						String to_display = new_writer + "(" + new_ID + ") : " + new_content;				
						this.addElement(to_display);			
					}

					
					
										
				} 
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				
			}
			
			
			

		}
		catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try {
                conn2.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		
		
		
	}		
   
}	

