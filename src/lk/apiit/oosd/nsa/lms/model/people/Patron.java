package lk.apiit.oosd.nsa.lms.model.people;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import lk.apiit.oosd.nsa.lms.model.types.Gender;
import lk.apiit.oosd.nsa.lms.model.types.Item;
import lk.apiit.oosd.nsa.lms.model.types.MembershipType;
import lk.apiit.oosd.nsa.lms.model.types.Name;
import lk.apiit.oosd.nsa.lms.model.types.Transaction;

//http://stackoverflow.com/questions/1029745/a-class-that-behaves-like-entity-and-embeddable

public class Patron extends User implements Serializable
{
	// http://tobega.blogspot.com/2008/05/beautiful-enums.html
	
	public static enum CompareBy implements Comparator<Patron>
	{
		PatronId()
		{
			@Override
			public int compare(Patron a, Patron b)
			{
				return a.patronId.compareTo(b.patronId);
			}
		};
		
		public abstract int compare(Patron a, Patron b);
		
	}
	
	private static final long	serialVersionUID	= 8909151053366138560L;
	
	protected String			patronId			= null;
	
	protected MembershipType	membershipType;
	
	// http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany
	protected List<Transaction>	borrowed;
	
	protected List<Transaction>	history;
	
	protected static String		lastPatronId		= "ptr0000";
	
	public Patron()
	{
		super();
		borrowed = new ArrayList<Transaction>();
		history = new ArrayList<Transaction>();
		
	}
	
	public Patron(String userId)
	{
		this.patronId = userId;
	}
	
	public Patron(String username, String password, Name name, Gender gender,
			Date dateOfBirth, MembershipType membershipType)
	{
		super(username, password, name, gender, dateOfBirth);
		this.userType = UserType.PATRON;
		this.membershipType = membershipType;
		patronId = lastPatronId;
		incrementPatronId();
		borrowed = new ArrayList<Transaction>();
		history = new ArrayList<Transaction>();
	}
	
	public Patron(MembershipType membershipType)
	{
		this.membershipType = membershipType;
		patronId = lastPatronId;
		incrementPatronId();
		borrowed = new ArrayList<Transaction>();
		history = new ArrayList<Transaction>();
		
	}
	
	public String getPatronId()
	{
		return this.patronId;
	}
	
	public MembershipType getMembershipType()
	{
		return this.membershipType;
	}
	
	public void setMembershipType(MembershipType membershipType)
	{
		this.membershipType = membershipType;
	}
	
	public Transaction borrow(Item item)
	{
		int borrowedBooks = 0;
		int borrowedPeriodicals = 0;
		int borrowedCDs = 0;
		
		for (Transaction t : borrowed)
			switch (t.getItem().getItemType())
			{
				case BOOK:
					borrowedBooks++;
					break;
				case PERIODICAL:
					borrowedPeriodicals++;
					break;
				case CD:
					borrowedCDs++;
					break;
			}
		
		Date borrowDate = new Date();
		int dueIn = 0;
		
		switch (item.getItemType())
		{
			case BOOK:
				if (!(borrowedBooks < membershipType.getMaxBooks()))
					throw new RuntimeException(
							"Already borrowed maximum number of books!");
				dueIn = membershipType.getBookPeriod();
				break;
			case PERIODICAL:
				if (!(borrowedPeriodicals < membershipType.getMaxPeriodicals()))
					throw new RuntimeException(
							"Already borrowed maximum number of periodicals!");
				dueIn = membershipType.getPeriodicalPeriod();
				break;
			case CD:
				borrowedCDs++;
				if (!(borrowedCDs < membershipType.getMaxCDs()))
					throw new RuntimeException(
							"Already borrowed maximum number of books!");
				dueIn = membershipType.getCdPeriod();
				break;
		}
		
		Date dueDate = new Date(borrowDate.getTime()
				+ (dueIn * 24 * 3600 * 1000));
		
		return new Transaction(this, item, borrowDate, dueDate);
		
	}
	
	public Transaction checkin(Item item)
	{
		for (int i = 0; i < borrowed.size(); i++)
			if (borrowed.get(i).getItem().equals(item))
			{
				Transaction t = borrowed.get(i);
				t.setReturnDate(new Date());
				history.add(borrowed.remove(i));
				return t;
			}
		
		throw new RuntimeException("Not a borrowed item!");
	}
	
	protected static void incrementPatronId()
	{
		lastPatronId = String.format("%s%04d", "itm",
				Integer.parseInt(lastPatronId.substring(3)) + 1);
	}
	
	public Iterable<Transaction> getBorrowedItems()
	{
		return this.borrowed;
	}
	
	public Iterable<Transaction> getTransactionHistory()
	{
		return this.history;
	}
	
}
