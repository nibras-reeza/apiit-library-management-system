package lk.apiit.oosd.nsa.lms.view.models;

import lk.apiit.oosd.nsa.lms.model.types.Name;

public class NameModel extends Model
{
	
	private Name	name;
	
	public NameModel()
	{
		
		this(new Name());
		
	}
	
	public NameModel(Name name)
	{
		this.name = name;
	}
	
	public void setFirstName(String firstName)
	{
		if (!getReadOnly())
			name.setFirstName(firstName);
	}
	
	public void setLastName(String lastName)
	{
		if (!getReadOnly())
			name.setLastName(lastName);
	}
	
	public String getFirstName()
	{
		return name.getFirstName();
	}
	
	public String getLastName()
	{
		return name.getLastName();
	}
	
	public Name getName()
	{
		return this.name;
	}
	
	public void setName(Name name)
	{
		this.name = name;
	}
	
}
