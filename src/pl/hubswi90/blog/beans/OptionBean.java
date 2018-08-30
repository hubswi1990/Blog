package pl.hubswi90.blog.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.entity.Option;
import pl.hubswi90.blog.interfaces.IOption;

@Stateless
public class OptionBean implements IOption {

	@PersistenceContext(unitName = "BlogMeJava")
	private EntityManager em;
	
	@Override
	public Option getOptionValue(String valueName) {
		Query query = em.createNamedQuery("getOption");
		query.setParameter("option", valueName);
		List<?> result = query.getResultList();
		if (result.isEmpty())
			return null;

		return (Option) result.get(0);
	}

	@Override
	public void setOptionValue(String optionName, String optionValue) {
		Option option = getOptionValue(optionName);
		if(!StringUtils.equals(option.getOptionValue(), optionValue)) {
			option.setOptionValue(optionValue);
			em.merge(option);
		}
	}

	@Override
	public int getCountOptions() {
		return ((Number)em.createNamedQuery("getCountOptions").getSingleResult()).intValue();
	}

	@Override
	public void addOption(String optionName, String OptionValue) {
		Option option = new Option(optionName, OptionValue);
		em.persist(option);	
	}

}
