
public class UserProfileController {
	private UserProfileView view;
	private UserProfileModel model;
	
	public UserProfileController(UserProfileView view, String name)
	{
		this.view = view;
		model = new UserProfileModel(name);
	}
	
	public int GetFollowingNum()
	{
		model.setFollowing_Num();	
		return model.GetFollowingNum();		
	}
	
	public int GetFollowerNum()
	{
		model.setFollower_Num();
		return model.GetFollowerNum();
	}
	
	
	
}
