import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class postModel {
	
	public static int getNewIndex()
	{
		Connection conn = null;
		int num = -1;
		try {
			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement select = conn.prepareStatement(
					"Select count(*) from Posts");
			ResultSet rs = select.executeQuery();	
			
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;

	}

	public static void insertContent(String postName, String postContent, String login_name) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement insert = conn.prepareStatement(
					"INSERT INTO Posts (post_id, content, num_of_like, user_user_id) VALUES (?, ?, 0, ?)");
			insert.setString(1, postName);
			insert.setString(2, postContent);
			insert.setString(3, login_name);

			int rowsAffected = insert.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Data inserted successfully!");
			} else {
				System.out.println("Failed to insert data.");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String getContent(String postid, int i) {
		Connection conn = null;
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);

			// 0 mean get Content
			if (i == 0) {
				PreparedStatement select = conn.prepareStatement("select content from Posts where post_id = ?;");
				select.setString(1, postid);

				ResultSet rs = select.executeQuery();

				String Content = null;

				if (rs.next()) {
					Content = rs.getString(1);
					return Content;
				} else {
					return Content;
				}
			}

			// 1 mean get User_name
			else if (i == 1) {
				PreparedStatement select = conn.prepareStatement("select user_user_id from Posts where post_id = ?;");
				select.setString(1, postid);

				ResultSet rs = select.executeQuery();

				String Content = null;

				if (rs.next()) {
					Content = rs.getString(1);
					return Content;
				} else {
					return Content;
				}
			}

			// 2 mean get num of like
			else if (i == 2) {
				PreparedStatement select = conn.prepareStatement("select num_of_like from Posts where post_id = ?;");
				select.setString(1, postid);

				ResultSet rs = select.executeQuery();

				String Content = null;

				if (rs.next()) {
					Content = rs.getString(1);
					return Content;
				} else {
					return Content;
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return postid;
	}

	public int Like(String u_name, String postid) {
		int ret = 0;
		Connection conn = null;
		try {

			String url = "jdbc:mysql://localhost/twitter";
			String user = "root", passwd = "010208";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, passwd);

			PreparedStatement select = conn
					.prepareStatement("select liker_id from Post_like where liker_id = ? and posts_post_id = ?;");
			select.setString(1, u_name);
			select.setString(2, postid);
			ResultSet rs = select.executeQuery();

			int plid = 0;

			// already press like
			if (rs.next()) {
				PreparedStatement select2 = conn
						.prepareStatement("delete from Post_like where liker_id = ? and posts_post_id = ?;");
				select2.setString(1, u_name);
				select2.setString(2, postid);
				select2.executeUpdate();

				PreparedStatement select3 = conn
						.prepareStatement("update Posts set num_of_like = ? where post_id = ?;");
				select3.setLong(1, Integer.parseInt(getContent(postid, 2)) - 1);
				select3.setString(2, postid);
				select3.executeUpdate();

				EventPrint e = new EventPrint("You Cancle the Like!");
			}
			// new like
			else {
				while (true) {
					select = conn.prepareStatement("select l_id from Post_like where l_id = ?;");
					select.setLong(1, plid);
					rs = select.executeQuery();

					if (rs.next()) {
						plid++;
					} else {
						PreparedStatement select2 = conn.prepareStatement("insert into Post_like values (?,?,?);");
						select2.setLong(1, plid);
						select2.setString(2, u_name);
						select2.setString(3, postid);
						select2.executeUpdate();

						PreparedStatement select3 = conn
								.prepareStatement("update Posts set num_of_like = ? where post_id = ?;");
						select3.setLong(1, Integer.parseInt(getContent(postid, 2)) + 1);
						select3.setString(2, postid);
						select3.executeUpdate();

						EventPrint e = new EventPrint("You Like the Post!");

						break;

					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

}
