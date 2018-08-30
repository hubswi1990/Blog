package pl.hubswi90.blog.interfaces;

import java.util.List;

import javax.ejb.Remote;

import pl.hubswi90.blog.entity.Profile;
import pl.hubswi90.blog.entity.ProfileGroups;

@Remote
public interface IProfile {
	
	/**
	 * using this method will verify the data which give you user with what is in the database 
	 * @param username login this user
	 * @param password this user
	 * @return object of class Profile with data this user
	 */
	public Profile authenticateAdmin(String username, String password);
	
	/**
	 * using this method will check if the user exists in the database
	 * @param username login this user
	 * @return object of class Profile with data this user
	 */
	public Profile findUserInDatabase(String username);	
	
	/**
	 * use this method to create pagination
	 * @param firstPosition first element
	 * @param lastPosition last element
	 * @return list with profile
	 */
	public List<?> getProfileList(int firstPosition, int lastPosition);
	
	/**
	 * if need number of records in entity Profile you can use this method
	 * @return number of records in database
	 */
	public int getCountProfile();
	
	
	/**
	 * if need number of records in entity
	 * @return number of records in database
	 */
	public int getCountProfileGroups();
	
	/**
	 * add new records to table in database represent Profile entity
	 * @param login 
	 * @param password
	 * @param email
	 * @return true it all is ok, false when something did not work
	 */
	public boolean addNewProfileToDatabase(String login, String password, String email, long role);
	
	/**
	 * delete in database existing record represent profile user
	 * @param profileId id user
	 * @return true it all is ok, false when something did not work
	 */
	public boolean removeExistingProfileInDatabase(long profileId);
	
	/**
	 * @param profileId
	 * @return
	 */
	public Profile getProfileFromDatabase(long profileId);
	
	/**
	 * @param profile
	 * @return
	 */
	public boolean updateProfile(Profile profile);
	
	/**
	 * hash user password
	 * @param text to hash
	 * @return hash password
	 */
	public String hashPasswd(String input);
	
	/**
	 * 
	 * @return list with available groups
	 */
	public List<?> getAllGrooupsFromDatabase();
	
	/**
	 * sss
	 * @param name
	 * @return
	 */
	public ProfileGroups getGroup(String name);
	
	public void createGroups();
	
	public void createAdmin(String login, String password, String email);
}
