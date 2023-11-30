public class LoginController {                                                    
                                                                                  
    private LoginView view;                                                       
    private LoginModel model;                                                     
             
    public LoginController(LoginView view){                                       
        this.view = view;                                                         
        model = new LoginModel();                                                 
    }                                                                             
    
    public boolean checkIDnowrite(String username)
    {
    	if(username.compareToIgnoreCase("") == 0)
    	{
    		view.setErrorMessage("Please write ID");
    		return true;
    	}
    	else
    		return false;
    }
    
    
    public boolean checkpasswordnowrite(String password)
    {
		if (password.compareToIgnoreCase("") == 0) {
			view.setErrorMessage("Please write Password");
			return true;
		}
    	else
    		return false;
    }
    
    
    public void checkCredentials(String username, String password){               
        model.setUsername(username);                                              
        model.getCredentials();                                                   
		if (password.equals(model.getPassword())) {
			view.setErrorMessage("Login Success!");
		}                                                             
        else{                                                                     
            view.setErrorMessage("Login Failed!");                                
        }                                                                         
    }
    
}       