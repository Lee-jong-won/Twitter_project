import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;

public class ExploreModel extends DefaultListModel<String> {
	
	private String username;
	private Connection conn;
	
	public ExploreModel(String name)
	{
		username = name;
	}
	
	public void Set_List(String to_find)
	{
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT user_id FROM user where user_id like ?");
			select.setString(1, to_find + "%");
			ResultSet rs = select.executeQuery();
			
			while(rs.next())
			{
				String candidate = rs.getString(1);
				
				if (this.getSize() == 0) {
					this.addElement(candidate);
				}
				else {					
					boolean flag = false;
					for (int i = 0; i < this.size(); i++) {
						if (this.get(i).compareToIgnoreCase(candidate) == 0) {
							flag = true;
							break;
						}						
					}
					
					if(!flag)
						this.addElement(candidate);
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
	}
	//It finds all user's name that start with string from user
	
}
