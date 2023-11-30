import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class ChangepwdModel {
    private String username,password;
    private int IsCheck;
    Connection conn = null;
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public int getCheck() {
        return IsCheck;
    }
    
    public void changethema() {
    	try {
    		String url = "jdbc:mysql://localhost/twitter";
    		String user = "root", passwd = "010208";
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(url, user, passwd);   		
    		
    		
    		
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
    
    
    
    public void changepwd(String name, String pwd) {
    	
    	try {
    		String url = "jdbc:mysql://localhost/twitter";
    		String user = "root", passwd = "010208";
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(url, user, passwd);
    		
    		PreparedStatement select = conn.prepareStatement("update User set pwd = ? where user_id = ?");
    		select.setString(1, pwd);
    		select.setString(2, name);
    		int rs = select.executeUpdate();

    		
    		
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
    	
    
    
    
	public void pwdCheck() {
    
	try {
		String url = "jdbc:mysql://localhost/twitter";
		String user = "root", passwd = "010208";
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url, user, passwd);
		
		
		PreparedStatement select = conn.prepareStatement("SELECT * FROM User where user_id = ? and pwd = ?");
		select.setString(1, username);
		select.setString(2, password);
		ResultSet rs = select.executeQuery();

		//留욎쑝硫�
		if (rs.next()) {
			IsCheck = 0;
		}
		//��由щ㈃
		else {
			IsCheck = 1;

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
