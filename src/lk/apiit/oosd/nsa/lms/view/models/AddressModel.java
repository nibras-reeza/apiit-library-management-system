package lk.apiit.oosd.nsa.lms.view.models;

import lk.apiit.oosd.nsa.lms.model.types.Address;

public class AddressModel extends Model
{
	private Address	address;
	
	public AddressModel()
	{
		this(new Address());
	}
	
	public AddressModel(Address address)
	{
		this.address = address;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		
		this.address = address;
	}
	
	public String getStreetAddress()
	{
		return address.getStreetAddress();
	}
	
	public void setStreetAddress(String streetAddress)
	{
		if (!getReadOnly())
			address.setStreetAddress(streetAddress);
	}
	
	public String getCity()
	{
		return address.getCity();
	}
	
	public void setCity(String city)
	
	{
		if (!getReadOnly())
			address.setCity(city);
	}
	
	public String getCountry()
	{
		return address.getCountry();
	}
	
	public void setCountry(String country)
	{
		if (!getReadOnly())
			address.setCountry(country);
	}
	
}
