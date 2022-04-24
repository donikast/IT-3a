package repositories;

import java.util.HashSet;
import java.util.Set;

import model.User;

public class Repository {
	
	private static Repository instance = null;
	private static Set<User> collection;
	private static int index=1;
	
	private Repository() {}
	
	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
			collection = new HashSet<User>();
		}
		return instance;		
	}
	
	public boolean addUser(User user) {
		user.setId(index++);
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
	
	public User getUserById(int id) {
		for(User u:collection) {
			if(u.getId()==id) {
				return u;
			}
		}
		return null;
	}

}
