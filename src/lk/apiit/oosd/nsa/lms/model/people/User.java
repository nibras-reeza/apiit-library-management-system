package lk.apiit.oosd.nsa.lms.model.people;

import java.util.Comparator;
import java.util.Date;

import lk.apiit.oosd.nsa.lms.model.types.Gender;
import lk.apiit.oosd.nsa.lms.model.types.Name;

public class User extends Person implements Comparable<User>
{
	// Represents a human who uses the system. May or may not be a staff.
	
	public enum UserType
	{
		STAFF, PATRON;
	}
	
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	public static enum CompareBy implements Comparator<User>
	{
		UserName()
		{
			@Override
			public int compare(User a, User b)
			{
				return a.username.compareTo(b.username);
			}
		};
		
		public abstract int compare(User a, User b);
		
	}
	
	private static final long	serialVersionUID	= 3691171648838844326L;
	
	protected String			username;
	protected String			password;
	protected UserType			userType;
	
	public UserType getUserType()
	{
		return this.userType;
	}
	
	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}
	
	public User()
	{
		super();
		
	}
	
	public User(String username, String password, Name name, Gender gender,
			Date dateOfBirth)
	{
		super(name, gender, dateOfBirth);
		this.username = username;
		this.password = password;
	}
	
	public User(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	@Override
	public int compareTo(User arg0)
	{
		return this.getUsername().compareTo(arg0.getUsername());
	}
	
}
