/*
 * 
 * authentication manager verrifies user credentials, manages user sessions,
 * handles logging in and out, and works with database to store user/session data.
 */


package users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import user.User;


public class AuthenticationManager {

	private final UserDatabase userDatabase;
	private User currentUser;
	
	//Constructor
	public AuthenticationManager(UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
		this.currentUser = null;
	}
	
	//Checks if the user is currently logged in
	public boolean isLoggedIn() {
		if (currentUser != null) {
			return true;
		}
		return false;
	}
	
	//sets the provided user as the current logged in user.
	public void login(User user) {
		currentUser = user;
	}
	
	//logs the current user out
	public void logOut() {
		currentUser = null;
	}			
	
	//getter method for providing the current logged in user
	public User getCurrentUser() {
		return currentUser;
	}
	
	//accepts or denies a user's attempt to log in based on username and password
	public User authenticate(String username, String password) {
		
		//find user by username in database
		User user = userDatabase.getUserByUsername(username);
		
		if (user == null) {
			//authentication failure
			return null;
		}
		
		//hash provided password
		String hashed = hashPassword(password);
		
		//compare password hash with stored hashed password associated with User
		if (hashed.equals(user.getPasswordHash())) {
			// authentication successful!
			return user;
		}
		
		//password mismatch, authentication failure
		return null;
	}
	
	//Creates a new user account with the provided username, email, and password.
	public User createAccount(String username, String email, String password) {
		//check if user is already in the database
		if (userDatabase.getUserByUsername(username) != null){
			return null;
		}
		
		//hash provided password
		String hashed = hashPassword(password);
		
		//create and add new user to the database
		User newUser = new User(username, email, hashed);
		userDatabase.addUser(newUser);
		return newUser;
	}
	
	//Hashes a plaintext password using SHA-256.
	private String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes());
			
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		
		}catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-256 algorithm not available", e);
		}
	}
}
