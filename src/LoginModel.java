import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginModel {
    private String username, password;
    private Connection conn;
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int newAcc(String name, String password) {	
    	try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			
			
			PreparedStatement select = conn.prepareStatement("SELECT * FROM user where user_id = ?");
			select.setString(1, name);
			ResultSet rs = select.executeQuery();

			if (rs.next()) {
				//user exist
				return 0;
			} else {
				//else make new account
				 
				/*
				String s1 = "insert into user values ( \'" + name + "\',  \'" + password + "\' 1\')";
				*/
				String s1 = "insert into user values (?, ? ,?)";
						
				select = conn.prepareStatement(s1);				
				select.setString(1, name);
				select.setString(2, password);
				select.setString(3, "1");
	    		select.executeUpdate();
				
				return 1;
				
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
		return 0;
    }  
    
    public void getCredentials(){
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);
			PreparedStatement select = conn.prepareStatement("SELECT * FROM User where user_id = ?");
			select.setString(1, username);
			ResultSet rs = select.executeQuery();

			if (rs.next()) {
				password = rs.getString("pwd");
			} else {
				password = "";
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
    //Get credential by comparing one user's password with DB.
    //If password from user coincides with the password that is derived from DB by using the name from user
}