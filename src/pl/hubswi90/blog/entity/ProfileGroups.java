package pl.hubswi90.blog.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProfileGroups
 *
 */
@Entity
@Table(name="blog_groups")
@NamedQueries({
	@NamedQuery(name = "getGroups", query = "SELECT g from ProfileGroups g order by g.groupsId desc"),
	@NamedQuery(name = "getGroupByName", query = "SELECT g from ProfileGroups g where g.nameGroup = :name"),
	@NamedQuery(name = "getCountProfileGroups", query = "select count(p) from ProfileGroups p")
})
public class ProfileGroups implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "groups_id")
	private long groupsId;
	
	@Column(name = "groups_name", unique = true, nullable = false)
	private String nameGroup;

	public ProfileGroups() {
		super();
	}
	
	public ProfileGroups(String nameGroup) {
		super();
		this.nameGroup = nameGroup;
	}
  
	public long getGroupsId() {
		return groupsId;
	}

	public void setGroupsId(long groupsId) {
		this.groupsId = groupsId;
	}

	public String getNameGroup() {
		return this.nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}
   
}
