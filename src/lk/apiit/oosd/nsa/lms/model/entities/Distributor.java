package lk.apiit.oosd.nsa.lms.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lk.apiit.oosd.nsa.lms.model.types.Address;
import lk.apiit.oosd.nsa.lms.model.types.Contact;
import lk.apiit.oosd.nsa.lms.model.types.Item;

public class Distributor extends SystemEntity implements Serializable
{
	
	private static final long	serialVersionUID	= 7828023462053576134L;
	
	private String				distributorCode		= null;
	
	private String				name;
	
	private List<Item>			items;
	
	private static String		lastDistributorCode	= "dst0000";
	
	public Distributor()
	{
		super();
		items = new ArrayList<Item>();
	}
	
	public Distributor(String id)
	{
		this.distributorCode = id;
	}
	
	public Distributor(String name, Address address, Contact contact)
	{
		super(address, contact);
		distributorCode = lastDistributorCode;
		incrementDistributorCode();
		this.name = name;
		items = new ArrayList<Item>();
	}
	
	public String getDistributorCode()
	{
		return this.distributorCode;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Item> getItems()
	{
		return this.items;
	}
	
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	
	private static void incrementDistributorCode()
	{
		lastDistributorCode = String.format("%s%04d", "dst",
				Integer.parseInt(lastDistributorCode.substring(3)) + 1);
	}
	
}