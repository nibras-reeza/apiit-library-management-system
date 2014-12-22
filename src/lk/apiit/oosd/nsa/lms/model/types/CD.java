package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;
import java.util.Comparator;

import lk.apiit.oosd.nsa.lms.exceptions.ReadOnlyException;

public class CD extends Item implements Serializable
{
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	private static final long	serialVersionUID	= 7611415390113995667L;
	
	public static enum CompareBy implements Comparator<CD>
	{
		title()
		{
			@Override
			public int compare(CD a, CD b)
			{
				return a.title.compareTo(b.title);
			}
		};
		
		public abstract int compare(CD a, CD b);
		
	}
	
	private String	title;
	
	private String	version;
	
	public CD()
	{
		super(ItemType.CD);
	}
	
	public void setItemType(ItemType itemType)
	{
		throw new ReadOnlyException("You may not edit this field!");
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getVersion()
	{
		return this.version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public String toString()
	{
		return title + "\t Version: " + version;
	}
	
}
