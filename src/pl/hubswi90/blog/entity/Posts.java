package pl.hubswi90.blog.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Posts
 *
 */

@Entity
@Table(name="blog_posts")
@NamedQueries({
	@NamedQuery(name = "getAllArticleInDatabase", 
			query = "select p from Posts p order by p.postId desc"),
	@NamedQuery(name = "getCountPosts", 
			query = "select count(p) from Posts p")
})
public class Posts implements Serializable {

	private static final long serialVersionUID = 1L;
	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private long postId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "posts_registered")
	private Date postCreateAt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "posts_modified")
	private Date postChangeAt;
	
	@Column(name = "posts_content", nullable = false, columnDefinition = "text")
	private String postContent;
	
	@Column(name = "posts_title", nullable = false)
	private String postTitle;

	//only hibernate request this constructor
	@Deprecated
	public Posts() {}   
	
	public Posts(String postContent, String postTitle) {
		super();
		this.postContent = postContent;
		this.postTitle = postTitle;
	}

	public long getPostId() {
		return this.postId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getPostCreateAt() {
		return postCreateAt;
	}

	public Date getPostChangeAt() {
		return postChangeAt;
	}

	@PrePersist
	private void generateDate() {
		postCreateAt = new Date(System.currentTimeMillis());
		postChangeAt = new Date(System.currentTimeMillis());
	}
	
	@PreUpdate
	private void setModifiedDate(){
		postChangeAt = new Date(System.currentTimeMillis());
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", postCreateAt=" + postCreateAt
				+ ", postChangeAt=" + postChangeAt + ", postContent="
				+ postContent + ", postTitle=" + postTitle + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((postChangeAt == null) ? 0 : postChangeAt.hashCode());
		result = prime * result
				+ ((postContent == null) ? 0 : postContent.hashCode());
		result = prime * result
				+ ((postCreateAt == null) ? 0 : postCreateAt.hashCode());
		result = prime * result + (int) (postId ^ (postId >>> 32));
		result = prime * result
				+ ((postTitle == null) ? 0 : postTitle.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posts other = (Posts) obj;
		if (postChangeAt == null) {
			if (other.postChangeAt != null)
				return false;
		} else if (!postChangeAt.equals(other.postChangeAt))
			return false;
		if (postContent == null) {
			if (other.postContent != null)
				return false;
		} else if (!postContent.equals(other.postContent))
			return false;
		if (postCreateAt == null) {
			if (other.postCreateAt != null)
				return false;
		} else if (!postCreateAt.equals(other.postCreateAt))
			return false;
		if (postId != other.postId)
			return false;
		if (postTitle == null) {
			if (other.postTitle != null)
				return false;
		} else if (!postTitle.equals(other.postTitle))
			return false;
		return true;
	}

}
