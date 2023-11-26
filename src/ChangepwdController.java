import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ChangepwdController {   
	
    private Changepwd pwd;                                                       
    private ChangepwdModel model; 
    private int IsCheck;
    
    public static String name , password;
	
        public ChangepwdController(Changepwd pwd){                                       
            this.pwd = pwd;                                                         
             model = new ChangepwdModel();                                  
        }  

        
        public void checkCredentials(String username, String password){               
            model.setUsername(username);    
            model.setPassword(password); 
            model.pwdCheck();                       
                                                  
            if(model.getCheck() == 0){                                 
                pwd.setErrorMessage("Correct Password");   
            }                                                                         
            else{                                                                     
                pwd.setErrorMessage("Wrong Password");                                
            }                                                                         
        }                                                          
}
