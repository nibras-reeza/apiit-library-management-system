package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import lk.apiit.oosd.nsa.lms.model.people.User;

public class Item implements Comparable<Item>, Serializable
{
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7568525539854902045L;
	
	public static enum CompareBy implements Comparator<Item>
	{
		itemCode()
		{
			@Override
			public int compare(Item a, Item b)
			{
				return a.itemCode.compareTo(b.itemCode);
			}
		};
		
		public abstract int compare(Item a, Item b);
		
	}
	
	protected String		itemCode;
	protected ItemType		itemType;
	
	protected int			duplicates		= 1;
	protected Set<User>		reservations	= new HashSet<User>();
	
	protected int			availableCopies	= 1;
	
	protected static String	lastItemCode	= "itm0000";
	
	public Item()
	{
	}
	
	public Item(String itemId)
	{
		this.itemCode = itemId;
	}
	
	public Item(ItemType itemType)
	{
		super();
		this.itemType = itemType;
		itemCode = lastItemCode;
		
		incrementItemCode();
	}
	
	public String getItemCode()
	{
		return this.itemCode;
	}
	
	public int getDuplicates()
	{
		return this.duplicates;
	}
	
	public ItemType getItemType()
	{
		return this.itemType;
	}
	
	public void setItemType(ItemType itemType)
	{
		this.itemType = itemType;
	}
	
	public void addCopy()
	{
		duplicates++;
		availableCopies++;
	}
	
	public void addReservation(User user)
	{
		reservations.add(user);
	}
	
	public Iterable<User> getReservations()
	{
		return reservations;
	}
	
	public void checkin()
	{
		availableCopies++;
	}
	
	public void checkout()
	{
		availableCopies--;
	}
	
	public int getAvailableCopies()
	{
		return this.availableCopies;
	}
	
	@Override
	public int compareTo(Item o)
	{
		return this.itemCode.compareTo(o.getItemCode());
	}
	
	private static void incrementItemCode()
	{
		lastItemCode = String.format("%s%04d", "itm",
				Integer.parseInt(lastItemCode.substring(3)) + 1);
	}
}
