package pl.hubswi90.blog.interfaces;

import java.util.List;

import javax.ejb.Local;

import pl.hubswi90.blog.entity.Posts;

@Local
public interface IPosts {

	/**
	 * Take a list of all articles in the database
	 * @param firstIndex
	 * @param lastIndex
	 * @return list with articles 
	 */
	public List<?> getArticlesFromDatabase(int firstIndex, int lastIndex);
	
	/**
	 * if need number of records in entity Posts you can use this method
	 * @return number of records in database
	 */
	public int getCountPosts();
	
	public boolean addNewArticleToDatabase(String postContent, String postTitle);
	
	public boolean removeArticle(long articleId);
	
	public boolean editArticle(Posts article);
	
	public Posts getArticle(long articleId);
	
	public List<?> getRecentPosts(int maxResult);
}
