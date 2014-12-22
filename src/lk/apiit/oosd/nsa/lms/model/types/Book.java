package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;
import java.util.Comparator;

import lk.apiit.oosd.nsa.lms.exceptions.ReadOnlyException;

public class Book extends Item implements Serializable
{
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	public static enum CompareBy implements Comparator<Book>
	{
		ISBN()
		{
			@Override
			public int compare(Book a, Book b)
			{
				return a.ISBN.compareTo(b.ISBN);
			}
		};
		
		public abstract int compare(Book a, Book b);
		
	}
	
	private static final long	serialVersionUID	= -3858957835944165065L;
	
	private String				ISBN;
	
	private String				title;
	
	private String				author;
	
	private Integer				edition;
	
	public Book()
	{
		super(ItemType.BOOK);
	}
	
	public Book(String iSBN, String title, String author, Integer edition)
	{
		this();
		this.ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.edition = edition;
	}
	
	public void setItemType(ItemType itemType)
	{
		throw new ReadOnlyException("You may not edit this field!");
	}
	
	public String getISBN()
	{
		return this.ISBN;
	}
	
	public void setISBN(String iSBN)
	{
		this.ISBN = iSBN;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public Integer getEdition()
	{
		return this.edition;
	}
	
	public void setEdition(Integer edition)
	{
		this.edition = edition;
	}
	
	public String toString()
	{
		return title + " by " + author + "\t Edition: " + edition.toString()
				+ "\t ISBN:" + ISBN;
	}
	
}
