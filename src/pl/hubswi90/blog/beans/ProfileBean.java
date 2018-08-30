package pl.hubswi90.blog.beans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import pl.hubswi90.blog.entity.Profile;
import pl.hubswi90.blog.entity.ProfileGroups;
import pl.hubswi90.blog.interfaces.IProfile;

@Stateless
public class ProfileBean implements IProfile {

	@PersistenceContext(unitName = "BlogMeJava")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#authenticateAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public Profile authenticateAdmin(String login, String haslo) {
		Profile user = findUserInDatabase(login);
		if (user != null && user.getProfilePassword().equals(hashPasswd(haslo)))
			return user;
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#findUserInDatabase(java.lang.String)
	 */
	@Override
	public Profile findUserInDatabase(String username) {
		Query query = em.createNamedQuery("findAdminByLogin");
		query.setParameter("login", username);
		List<?> result = query.getResultList();
		if (result.isEmpty())
			return null;

		return (Profile) result.get(0);
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#getProfileList(int, int)
	 */
	@Override
	public List<?> getProfileList(int firstPosition, int lastPosition) {
		Query query = em.createNamedQuery("findProfile");
		query.setFirstResult(firstPosition);
		query.setMaxResults(lastPosition);
		return query.getResultList(); 
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#getCountProfile()
	 */
	@Override
	public int getCountProfile() {
		return ((Number)em.createNamedQuery("findCountProfile").getSingleResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#addNewProfileToDatabase(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean addNewProfileToDatabase(String login, String password, String email, long role) {
		Profile profile = new Profile(login, hashPasswd(password), email);
		profile.setProfileGroup(em.find(ProfileGroups.class, role));
		try {
			em.persist(profile);
			return true;
		} catch (EntityExistsException e) {
			e.printStackTrace();
			return false;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		} catch (TransactionRequiredException e) {
			e.printStackTrace();
			return false;
		}		
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#hashPasswd(java.lang.String)
	 */
	@Override
	public String hashPasswd(String input) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("MD5");
			byte[] result = mDigest.digest(input.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < result.length; i++)
				sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#removeExistingProfileInDatabase(long)
	 */
	@Override
	public boolean removeExistingProfileInDatabase(long profileId) {
		Profile profile = em.find(Profile.class, profileId);
		em.remove(profile);
		return false;
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#getProfileFromDatabase(long)
	 */
	@Override
	public Profile getProfileFromDatabase(long profileId) {
		return em.find(Profile.class, profileId);
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IProfile#updateProfile(pl.mray3th.blog.entity.Profile)
	 */
	@Override
	public boolean updateProfile(Profile profile) {
		em.merge(profile);
		return false;
	}

	@Override
	public List<?> getAllGrooupsFromDatabase() {
		Query query = em.createNamedQuery("getGroups");
		return query.getResultList();
	}

	@Override
	public ProfileGroups getGroup(String name) {
		Query query = em.createNamedQuery("getGroupByName");
		query.setParameter("name", name);
		List<?> result = query.getResultList();
		if (result.isEmpty())
			return null;

		return (ProfileGroups) result.get(0);
	}

	@Override
	public int getCountProfileGroups() {
		return ((Number)em.createNamedQuery("getCountProfileGroups").getSingleResult()).intValue();
	}

	@Override
	public void createGroups() {
		ProfileGroups administrator = new ProfileGroups("administrator");
		ProfileGroups editor = new ProfileGroups("editor");
		ProfileGroups autor = new ProfileGroups("autor");
		ProfileGroups subscriber = new ProfileGroups("subscriber");
		
		em.persist(administrator);
		em.persist(editor);
		em.persist(autor);
		em.persist(subscriber);
	}

	@Override
	public void createAdmin(String login, String password, String email) {
		Profile admin = new Profile(login, hashPasswd(password), email);
		admin.setProfileGroup(getGroup("administrator"));
		admin.setProfileStatus(true);
		em.persist(admin);
		
	}
}
