package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;

public class Address implements Serializable
{
	
	private static final long	serialVersionUID	= 6387989911115077888L;
	private String				streetAddress;
	private String				city;
	private String				country;
	
	public Address()
	{
		super();
	}
	
	public Address(String streeAddress, String city, String country)
	{
		super();
		this.streetAddress = streeAddress;
		this.city = city;
		this.country = country;
	}
	
	public String getStreetAddress()
	{
		return this.streetAddress;
	}
	
	public void setStreetAddress(String streeAddress)
	{
		this.streetAddress = streeAddress;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public void setCity(String city)
	{
		this.city = city;
	}
	
	public String getCountry()
	{
		return this.country;
	}
	
	public void setCountry(String country)
	{
		this.country = country;
	}
	
}
