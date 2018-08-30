package pl.hubswi90.blog.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Option
 *
 */
@Entity
@Table(name = "blog_options")
@NamedQueries({
	@NamedQuery(name = "getOption", query = "SELECT o from Option o where o.optionName = :option"),
	@NamedQuery(name = "getCountOptions", query = "select count(o) from Option o")
})
public class Option implements Serializable {
	   
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "option_id")
	private long optionId;
	
	@Column(name = "option_name")
	private String optionName;
	
	@Column(name = "option_value")
	private String optionValue;
	
	private static final long serialVersionUID = 1L;

	public Option() {
		super();
	}   
	
	public Option(String optionName, String optionValue) {
		super();
		this.optionName = optionName;
		this.optionValue = optionValue;
	}

	public long getOptionId() {
		return this.optionId;
	}
	public String getOptionName() {
		return this.optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionValue() {
		return optionValue;
	}
	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}   
}
