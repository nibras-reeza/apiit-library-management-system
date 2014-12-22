package lk.apiit.oosd.nsa.lms.view.models;

import java.util.Date;

import lk.apiit.oosd.nsa.lms.model.types.Book;
import lk.apiit.oosd.nsa.lms.model.types.CD;
import lk.apiit.oosd.nsa.lms.model.types.Item;
import lk.apiit.oosd.nsa.lms.model.types.ItemType;
import lk.apiit.oosd.nsa.lms.model.types.Periodical;

public class FrmItemModel extends Model
{
	private Book		book;
	private CD			cd;
	private Periodical	periodical;
	
	private Item		item;
	
	// Item
	protected String	itemCode;
	protected ItemType	itemType;
	
	// Book
	private String		ISBN;
	private String		title;
	private String		author;
	private Integer		edition;
	
	// Periodical
	
	private String		ISSN;
	private String		name;
	private int			volume;
	private int			issue;
	private Date		published;
	
	private DateModel	publishedDateModel;
	
	private String		publisher;
	
	// CD
	// private String title;
	private String		version;
	
	public FrmItemModel()
	{
		this.item = new Item();
		this.publishedDateModel = new DateModel();
	}
	
	public FrmItemModel(Book book)
	{
		this.book = book;
		this.item = book;
		
		this.itemType = ItemType.BOOK;
		this.itemCode = book.getItemCode();
		
		this.ISBN = book.getISBN();
		this.author = book.getAuthor();
		this.edition = book.getEdition();
		this.title = book.getTitle();
	}
	
	public FrmItemModel(Periodical periodical)
	{
		this.periodical = periodical;
		this.publishedDateModel = (new DateModel(periodical.getPublished()));
		this.item = periodical;
		
		this.itemType = ItemType.PERIODICAL;
		this.itemCode = periodical.getItemCode();
		
		this.ISSN = periodical.getISSN();
		this.issue = periodical.getIssue();
		this.name = periodical.getName();
		this.published = periodical.getPublished();
		this.publisher = periodical.getPublisher();
		this.volume = periodical.getVolume();
		
	}
	
	public FrmItemModel(CD cd)
	{
		this.cd = cd;
		this.item = cd;
		
		this.itemType = ItemType.CD;
		this.itemCode = cd.getItemCode();
		
		this.title = cd.getTitle();
		this.version = cd.getVersion();
	}
	
	public FrmItemModel(Item item)
	{
		switch (item.getItemType())
		{
			case BOOK:
				Book book = (Book) item;
				this.book = book;
				this.ISBN = book.getISBN();
				this.author = book.getAuthor();
				this.edition = book.getEdition();
				this.title = book.getTitle();
				break;
			case CD:
				CD cd = (CD) item;
				this.cd = cd;
				
				this.title = cd.getTitle();
				this.version = cd.getVersion();
				break;
			case PERIODICAL:
				Periodical periodical = (Periodical) item;
				this.periodical = periodical;
				this.publishedDateModel = (new DateModel(
						periodical.getPublished()));
				this.item = periodical;
				this.ISSN = periodical.getISSN();
				this.issue = periodical.getIssue();
				this.name = periodical.getName();
				this.published = periodical.getPublished();
				this.publisher = periodical.getPublisher();
				this.volume = periodical.getVolume();
				break;
			default:
				break;
		}
		this.item = item;
		this.itemType = item.getItemType();
		this.itemCode = item.getItemCode();
		
	}
	
	public void updateItem()
	{
		
		switch (itemType)
		{
			case BOOK:
				updateBook();
				break;
			case CD:
				updateCD();
				break;
			case PERIODICAL:
				updatePeriodical();
				break;
			default:
				break;
		}
		
	}
	
	public void updateBook()
	{
		book.setAuthor(author);
		book.setEdition(edition);
		book.setISBN(ISBN);
		book.setTitle(title);
	}
	
	public void updatePeriodical()
	{
		periodical.setISSN(ISSN);
		periodical.setIssue(issue);
		periodical.setName(name);
		published = publishedDateModel.getDate();
		periodical.setPublished(published);
		periodical.setPublisher(publisher);
		periodical.setVolume(volume);
	}
	
	public void updateCD()
	{
		cd.setTitle(title);
		cd.setVersion(version);
		
	}
	
	public Item newItem()
	{
		switch (itemType)
		{
			case BOOK:
				item = newBook();
				break;
			case CD:
				item = newCD();
				break;
			case PERIODICAL:
				item = newPeriodical();
				break;
			default:
				break;
		}
		
		return item;
	}
	
	public Book newBook()
	{
		if (itemType != ItemType.BOOK)
			throw new RuntimeException("Not a Book");
		
		Book book = new Book();
		
		book.setAuthor(author);
		book.setEdition(edition);
		book.setISBN(ISBN);
		book.setTitle(title);
		
		this.item = book;
		
		return book;
	}
	
	public Periodical newPeriodical()
	{
		if (itemType != ItemType.CD)
			throw new RuntimeException("Not a Periodical");
		
		Periodical periodical = new Periodical();
		
		periodical.setISSN(ISSN);
		;
		periodical.setIssue(issue);
		periodical.setName(name);
		this.published = publishedDateModel.getDate();
		periodical.setPublished(published);
		periodical.setPublisher(publisher);
		periodical.setVolume(volume);
		
		this.item = periodical;
		
		return periodical;
	}
	
	public CD newCD()
	{
		if (itemType != ItemType.CD)
			throw new RuntimeException("Not a CD");
		
		CD cd = new CD();
		
		cd.setTitle(title);
		cd.setVersion(version);
		
		this.item = cd;
		
		return cd;
	}
	
	public Book getBook()
	{
		return this.book;
	}
	
	public void setBook(Book book)
	{
		this.book = book;
	}
	
	public CD getCd()
	{
		return this.cd;
	}
	
	public void setCd(CD cd)
	{
		this.cd = cd;
	}
	
	public Periodical getPeriodical()
	{
		return this.periodical;
	}
	
	public void setPeriodical(Periodical periodical)
	{
		this.periodical = periodical;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public String getItemCode()
	{
		return this.itemCode;
	}
	
	public void setItemCode(String itemCode)
	{
		this.itemCode = itemCode;
	}
	
	public ItemType getItemType()
	{
		return this.itemType;
	}
	
	public void setItemType(ItemType itemType)
	{
		this.itemType = itemType;
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
	
	public String getVersion()
	{
		return this.version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	
	public DateModel getPublishedDateModel()
	{
		return publishedDateModel;
	}
	
}
