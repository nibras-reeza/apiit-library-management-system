package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;

public class Name implements Serializable
{
	
	private static final long	serialVersionUID	= 1247272406054753632L;
	private String				firstName;
	private String				lastName;
	
	public Name()
	{
		super();
		
	}
	
	public Name(String firstName, String lastName)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setFirstName(String firstName)
	{
		
		this.firstName = firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
}