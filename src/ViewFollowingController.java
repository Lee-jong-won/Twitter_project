import javax.swing.JList;

public class ViewFollowingController {
	
	private ViewFollowing view;
	private ViewFollowingModel model;
	
	public ViewFollowingController(ViewFollowing view, String name)
	{
		this.view = view;
		model = new ViewFollowingModel(name);
	}
	
	
}
