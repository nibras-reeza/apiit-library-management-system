package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import lk.apiit.oosd.nsa.lms.exceptions.ReadOnlyException;

public class Periodical extends Item implements Serializable
{
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	private static final long	serialVersionUID	= 3970083916031616748L;
	
	public static enum CompareBy implements Comparator<Periodical>
	{
		ISSN()
		{
			@Override
			public int compare(Periodical a, Periodical b)
			{
				return a.ISSN.compareTo(b.ISSN);
			}
		},
		name()
		{
			@Override
			public int compare(Periodical a, Periodical b)
			{
				return a.name.compareTo(b.name);
			}
		};
		
		public abstract int compare(Periodical a, Periodical b);
		
	}
	
	private String	ISSN;
	
	private String	name;
	
	private int		volume;
	private int		issue;
	
	private Date	published;
	private String	publisher;
	
	public Periodical()
	{
		super(ItemType.PERIODICAL);
	}
	
	public Periodical(String iSSN, String name, int volume, int issue,
			Date published, String publisher)
	{
		this();
		this.ISSN = iSSN;
		this.name = name;
		this.volume = volume;
		this.issue = issue;
		this.published = published;
		this.publisher = publisher;
	}
	
	public String getISSN()
	{
		return this.ISSN;
	}
	
	public void setISSN(String iSSN)
	{
		this.ISSN = iSSN;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getVolume()
	{
		return this.volume;
	}
	
	public void setVolume(int volume)
	{
		this.volume = volume;
	}
	
	public int getIssue()
	{
		return this.issue;
	}
	
	public void setIssue(int issue)
	{
		this.issue = issue;
	}
	
	public Date getPublished()
	{
		return this.published;
	}
	
	public void setPublished(Date published)
	{
		this.published = published;
	}
	
	public String getPublisher()
	{
		return this.publisher;
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}
	
	public void setItemType(ItemType itemType)
	{
		throw new ReadOnlyException("You may not edit this field!");
	}
	
	public String toString()
	{
		return name + "\t Volume: " + Integer.toString(volume) + "\t Issue"
				+ Integer.toString(issue);
	}
	
}
