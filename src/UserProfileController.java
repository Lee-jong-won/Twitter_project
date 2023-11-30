
public class UserProfileController {
	private UserProfileView view;
	private UserProfileModel model;
	
	public UserProfileController(UserProfileView view, String name)
	{
		this.view = view;
		model = new UserProfileModel(name);
	}
	//Name of specific account which one user's login into is send to the constructor  
	
	public int GetFollowingNum()
	{
		model.setFollowing_Num();	
		return model.GetFollowingNum();		
	}
	//From model, read following number of people that one user follows
	
	public int GetFollowerNum()
	{
		model.setFollower_Num();
		return model.GetFollowerNum();
	}
	//From model, read follower number of people that follows one user
	
	
	
}
