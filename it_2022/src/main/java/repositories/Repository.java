package repositories;

import java.util.HashSet;
import java.util.Set;

import model.User;

public class Repository {
	
	private static Repository instance = null;
	private static Set<User> collection;
	
	private Repository() {}
	
	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
			collection = new HashSet<User>();
		}
		return instance;		
	}
	
	public boolean addUser(User user) {
		return collection.add(user);
	}
	
	public boolean ifExist(User user) {
		return collection.contains(user);
	}
	
	public User getUserByUserName(String username) {
		for(User u:collection) {
			if(u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

}
