package pl.hubswi90.blog.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

import pl.hubswi90.blog.entity.Posts;
import pl.hubswi90.blog.interfaces.IPosts;

@Stateless
public class PostBean implements IPosts {

	@PersistenceContext(unitName = "BlogMeJava")
	private EntityManager em;

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IPosts#getArticlesFromDatabase(int, int)
	 */
	@Override
	public List<?> getArticlesFromDatabase(int firstIndex, int lastIndex) {
		Query query = em.createNamedQuery("getAllArticleInDatabase");
		query.setFirstResult(firstIndex);
		query.setMaxResults(lastIndex);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see pl.mray3th.blog.interfaces.IPosts#getCountPosts()
	 */
	@Override
	public int getCountPosts() {
		return ((Number)em.createNamedQuery("getCountPosts").getSingleResult()).intValue();
	}

	@Override
	public boolean addNewArticleToDatabase(String postContent, String postTitle) {
		Posts article = new Posts(postContent, postTitle);
		try {
			em.persist(article);
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

	@Override
	public boolean removeArticle(long articleId) {
		Posts article = em.find(Posts.class, articleId);
		try {
			em.remove(article);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editArticle(Posts article) {
		em.merge(article);
		return false;
	}

	@Override
	public Posts getArticle(long articleId) {
		return em.find(Posts.class, articleId);
	}

	@Override
	public List<?> getRecentPosts(int maxResult) {
		Query query = em.createNamedQuery("getAllArticleInDatabase");
		return query.setMaxResults(maxResult).getResultList();
	}
}
