package lk.apiit.oosd.nsa.lms.model.entities;

import java.io.Serializable;

import lk.apiit.oosd.nsa.lms.model.types.Address;
import lk.apiit.oosd.nsa.lms.model.types.Contact;

public abstract class SystemEntity implements Serializable
{
	
	private static final long	serialVersionUID	= 45877493667764350L;
	
	protected Address			address;
	protected Contact			contact;
	
	public SystemEntity()
	{
		super();
	}
	
	public SystemEntity(Address address, Contact contact)
	{
		super();
		
		this.address = address;
		this.contact = contact;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Contact getContact()
	{
		return this.contact;
	}
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
	
}
