package user;

public class User {
	
	private String userName;
	private String passwordHash ;
	private String email;
	
	//Constructor
	public User(String userName, String email, String passwordHash) {
		this.userName = userName;
		this.email = email;
		this.passwordHash = passwordHash;
		
	}
	
	//getter method for retrieving user name
	public String getUserName() {
		return userName;
	}
	
	//getter method for retrieving email
		public String getEmail() {
			return email;
		}
		
}


