package pl.hubswi90.blog.interfaces;

import javax.ejb.Local;

import pl.hubswi90.blog.entity.Option;

@Local
public interface IOption {
	
	/**
	 * @param valueName
	 * @return
	 */
	public Option getOptionValue(String valueName);
	
	/**
	 * @param optionName
	 * @param optionValue
	 */
	public void setOptionValue(String optionName, String optionValue);
	
	/**
	 * ffff
	 * @return fff
	 */
	public int getCountOptions();
	
	public void addOption(String optionName, String OptionValue);

}
