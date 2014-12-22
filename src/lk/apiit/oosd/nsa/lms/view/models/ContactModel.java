package lk.apiit.oosd.nsa.lms.view.models;

import lk.apiit.oosd.nsa.lms.model.types.Contact;

public class ContactModel extends Model
{
	private Contact	contact;
	
	public ContactModel()
	{
		this(new Contact());
	}
	
	public ContactModel(Contact contact)
	{
		super();
		this.contact = contact;
	}
	
	public Contact getContact()
	{
		return this.contact;
	}
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
	
	public void setTelephone(String telephone)
	{
		this.contact.setTelephone(telephone);
	}
	
	public String getTelephone()
	{
		return this.contact.getTelephone();
	}
	
	public void setEmail(String email)
	{
		this.contact.setEmail(email);
	}
	
	public String getEmail()
	{
		return this.contact.getEmail();
	}
}
