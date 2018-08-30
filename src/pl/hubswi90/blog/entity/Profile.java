package pl.hubswi90.blog.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;

/**
 * Entity implementation class for Entity: Profile
 *
 */
@Entity
@Table(name = "blog_users")
@NamedQueries({ 
	@NamedQuery(name = "findAdminByLogin", query = "Select distinct p from Profile p, "
			+ "ProfileGroups g where p.profileName = :login AND p.profileStatus = true"),
	@NamedQuery(name = "findProfile", query = "Select p from Profile p"),
	@NamedQuery(name = "findCountProfile", query = "select COUNT(p) from Profile p")
})
public class Profile implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id	
	@Column(name = "users_id")	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long profileId;
	
	@Column(name = "users_login", length = 16, unique = true, nullable = false)
	private String profileName;
	
	@Column(name = "users_passwd", nullable = false)
	private String profilePassword;
	
	@Column(name = "users_email", nullable = false, unique = true)
	private String profileEmail;
	
	@Temporal(TemporalType.DATE) @Column(name = "users_registered")
	private Date profileRegistered;
	
	@Column(name = "users_activation_key")
	private String activationKey;
	
	@Column(name = "users_status")
	private boolean profileStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_group", referencedColumnName = "groups_name")
	private ProfileGroups profileGroup;

	@Deprecated
	public Profile() {}  
	
	public Profile(String profileName, String profilePassword, String profileEmail) {
		super();
		this.profileName = profileName;
		this.profilePassword = profilePassword;
		this.profileEmail = profileEmail;
	}

	public long getProfileId() {
		return this.profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public String getProfilePassword() {
		return profilePassword;
	}

	public void setProfilePassword(String profilePassword) {
		this.profilePassword = profilePassword;
	}

	public String getProfileEmail() {
		return profileEmail;
	}

	public void setProfileEmail(String profileEmail) {
		this.profileEmail = profileEmail;
	}

	public Date getProfileRegistered() {
		return profileRegistered;
	}

	public boolean getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(boolean profileStatus) {
		this.profileStatus = profileStatus;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public String getProfileGroup() {
		return profileGroup.getNameGroup();
	}

	public void setProfileGroup(ProfileGroups profileGroup) {
		this.profileGroup = profileGroup;
	}

	@PrePersist
	private void generateActivationKeyAndDate() {
		if (StringUtils.isBlank(activationKey)) {
			String chars = "abcdefghijklmnoprstuwvxyz1234567890";
			Random rand = new Random();
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < 10; i++)
				buf.append(chars.charAt(rand.nextInt(chars.length())));

			activationKey = buf.toString();
		}
		profileRegistered = new Date(System.currentTimeMillis());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((activationKey == null) ? 0 : activationKey.hashCode());
		result = prime * result
				+ ((profileEmail == null) ? 0 : profileEmail.hashCode());
		result = prime * result + (int) (profileId ^ (profileId >>> 32));
		result = prime * result
				+ ((profileName == null) ? 0 : profileName.hashCode());
		result = prime * result
				+ ((profilePassword == null) ? 0 : profilePassword.hashCode());
		result = prime
				* result
				+ ((profileRegistered == null) ? 0 : profileRegistered
						.hashCode());
		result = prime * result + (profileStatus ? 1231 : 1237);
		return result;
	}

	@Override
	public String toString() {
		return "Profile [profileId=" + profileId + ", profileName="
				+ profileName + ", profilePassword=" + profilePassword
				+ ", profileEmail=" + profileEmail + ", profileRegistered="
				+ profileRegistered + ", activationKey=" + activationKey
				+ ", profileStatus=" + profileStatus + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		if (activationKey == null) {
			if (other.activationKey != null)
				return false;
		} else if (!activationKey.equals(other.activationKey))
			return false;
		if (profileEmail == null) {
			if (other.profileEmail != null)
				return false;
		} else if (!profileEmail.equals(other.profileEmail))
			return false;
		if (profileId != other.profileId)
			return false;
		if (profileName == null) {
			if (other.profileName != null)
				return false;
		} else if (!profileName.equals(other.profileName))
			return false;
		if (profilePassword == null) {
			if (other.profilePassword != null)
				return false;
		} else if (!profilePassword.equals(other.profilePassword))
			return false;
		if (profileRegistered == null) {
			if (other.profileRegistered != null)
				return false;
		} else if (!profileRegistered.equals(other.profileRegistered))
			return false;
		if (profileStatus != other.profileStatus)
			return false;
		return true;
	}	
   
}
