package user;

import java.util.UUID;

public class User {
	
	private final String ID;
	private String userName;
	private String passwordHash ;
	private String email;
	
	
	//Constructor
	public User(String ID, String userName, String email, String passwordHash) {
		this.ID = UUID.randomUUID().toString();
		this.userName = userName;
		this.email = email;
		this.passwordHash = passwordHash;
		
	}
	
	//getter method for retrieving user name
	public String getUserName() {
		return userName;
	}
	
	//getter method for retrieving user ID
		public String getID() {
			return ID;
		}

	
	//getter method for retrieving email
		public String getEmail() {
			return email;
		}
		
		//getter method for retrieving user name
		public String getPasswordHash() {
			return passwordHash;
		}
		
		//setter method for setting user name
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		//setter method for setting email
				public void setEmail(String email) {
					this.email = email;
				}
				
		//setter method for setting user name
				public void setPasswordHash(String passwordHash) {
				this.passwordHash = passwordHash;
				}
				
		@Override
		public String toString() {
			return "UserAccount{" + "ID:'" + ID + '\'' +
					", userName: '" + userName + '\'' +
					", email:'" + email + '\'' +
					'}';
		}
}
		


