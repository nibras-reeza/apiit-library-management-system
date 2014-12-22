package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;

public class Contact implements Serializable
{
	
	private static final long	serialVersionUID	= -1030446266339683866L;
	
	private String				telephone;
	private String				email;
	
	public Contact()
	{
		super();
	}
	
	public Contact(String telephone, String email)
	{
		super();
		this.telephone = telephone;
		this.email = email;
	}
	
	public String getTelephone()
	{
		return this.telephone;
	}
	
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
}