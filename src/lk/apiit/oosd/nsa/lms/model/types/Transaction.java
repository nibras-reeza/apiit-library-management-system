package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import lk.apiit.oosd.nsa.lms.model.people.Patron;
import lk.apiit.oosd.nsa.lms.model.people.Staff;

public class Transaction implements Comparable<Transaction>, Serializable
{
	
	public static enum CompareBy implements Comparator<Transaction>
	{
		transactionId()
		{
			@Override
			public int compare(Transaction a, Transaction b)
			{
				return Integer.compare(a.transactionId, b.transactionId);
			}
		};
		
		public abstract int compare(Transaction a, Transaction b);
		
	}
	
	private static final long	serialVersionUID	= 526188817568632069L;
	
	private int					transactionId;
	
	private Patron				patron;
	
	private Item				item;
	
	private Date				borrowDate;
	private Date				dueDate;
	
	private Date				returnDate;
	
	private Staff				checkoutStaff;
	private Staff				checkinStaff;
	
	public Transaction()
	{
		super();
	}
	
	public Transaction(Patron patron, Item item, Date borrowDate, Date dueDate)
	{
		super();
		this.patron = patron;
		this.item = item;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
	}
	
	public int getTransactionId()
	{
		return this.transactionId;
	}
	
	public Patron getPatron()
	{
		return this.patron;
	}
	
	public void setPatron(Patron patron)
	{
		this.patron = patron;
	}
	
	public Item getItem()
	{
		return this.item;
	}
	
	public void setItem(Item item)
	{
		this.item = item;
	}
	
	public Date getBorrowDate()
	{
		return this.borrowDate;
	}
	
	public void setBorrowDate(Date borrowDate)
	{
		this.borrowDate = borrowDate;
	}
	
	public Date getDueDate()
	{
		return this.dueDate;
	}
	
	public void setDueDate(Date dueDate)
	{
		this.dueDate = dueDate;
	}
	
	public Date getReturnDate()
	{
		return this.returnDate;
	}
	
	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}
	
	public Staff getCheckoutStaff()
	{
		return this.checkoutStaff;
	}
	
	public void setCheckoutStaff(Staff checkoutStaff)
	{
		this.checkoutStaff = checkoutStaff;
	}
	
	public Staff getCheckinStaff()
	{
		return this.checkinStaff;
	}
	
	public void setCheckinStaff(Staff checkinStaff)
	{
		this.checkinStaff = checkinStaff;
	}
	
	@Override
	public int compareTo(Transaction o)
	{
		return Integer.compare(this.getTransactionId(), o.getTransactionId());
	}
}
