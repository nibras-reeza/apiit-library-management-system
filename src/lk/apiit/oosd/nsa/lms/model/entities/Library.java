package lk.apiit.oosd.nsa.lms.model.entities;

import java.io.Serializable;

import lk.apiit.oosd.nsa.lms.model.types.Address;
import lk.apiit.oosd.nsa.lms.model.types.Contact;

public class Library extends SystemEntity implements Serializable
{
	
	private static final long	serialVersionUID	= -2446182868066279464L;
	
	private String				libraryCode			= null;
	private String				name;
	
	private static String		lastLibraryCode		= "lib0000";
	
	public Library()
	{
		super();
	}
	
	public Library(String name, Address address, Contact contact)
	{
		
		super(address, contact);
		this.libraryCode = lastLibraryCode;
		incrementLibraryCode();
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getLibraryCode()
	{
		return this.libraryCode;
	}
	
	private static void incrementLibraryCode()
	{
		
		lastLibraryCode = String.format("%s%04d", "itm",
				Integer.parseInt(lastLibraryCode.substring(3)) + 1);
		
	}
	
}