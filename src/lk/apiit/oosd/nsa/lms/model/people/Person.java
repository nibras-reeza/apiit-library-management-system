package lk.apiit.oosd.nsa.lms.model.people;

import java.io.Serializable;
import java.util.Date;

import lk.apiit.oosd.nsa.lms.model.entities.SystemEntity;
import lk.apiit.oosd.nsa.lms.model.types.Gender;
import lk.apiit.oosd.nsa.lms.model.types.Name;

public abstract class Person extends SystemEntity implements Serializable
{
	// Represents a human being who doesn't use the system.
	
	private static final long	serialVersionUID	= -3187749011470572901L;
	
	protected String			nic;
	protected Name				name;
	protected Gender			gender;
	protected Date				dateOfBirth;
	
	public Person()
	{
		super();
	}
	
	public Person(Name name, Gender gender, Date dateOfBirth)
	{
		super();
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getNic()
	{
		return this.nic;
	}
	
	public void setNic(String nic)
	{
		this.nic = nic;
	}
	
	public Name getName()
	{
		return this.name;
	}
	
	public void setName(Name name)
	{
		this.name = name;
	}
	
	public Gender getGender()
	{
		return this.gender;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public Date getDateOfBirth()
	{
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
}
