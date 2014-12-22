package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;

public enum MembershipType implements Serializable
{
	
	SILVER(2, 1, 1, 7, 3, 3), GOLD(4, 2, 2, 14, 5, 5), STAFF(8, 4, 3, 14, 7, 5);
	private int	maxBooks;
	private int	maxPeriodicals;
	private int	maxCDs;
	
	private int	bookPeriod;
	private int	periodicalPeriod;
	private int	cdPeriod;
	
	private MembershipType(int maxBooks, int maxPeriodicals, int maxCDs,
			int bookPeriod, int periodicalPeriod, int cdPeriod)
	{
		this.maxBooks = maxBooks;
		this.maxPeriodicals = maxPeriodicals;
		this.maxCDs = maxCDs;
		
		this.bookPeriod = bookPeriod;
		this.periodicalPeriod = periodicalPeriod;
		this.cdPeriod = cdPeriod;
	}
	
	public int getMaxBooks()
	{
		return this.maxBooks;
	}
	
	public void setMaxBooks(int maxBooks)
	{
		this.maxBooks = maxBooks;
	}
	
	public int getMaxPeriodicals()
	{
		return this.maxPeriodicals;
	}
	
	public void setMaxPeriodicals(int maxPeriodicals)
	{
		this.maxPeriodicals = maxPeriodicals;
	}
	
	public int getBookPeriod()
	{
		return this.bookPeriod;
	}
	
	public void setBookPeriod(int bookPeriod)
	{
		this.bookPeriod = bookPeriod;
	}
	
	public int getPeriodicalPeriod()
	{
		return this.periodicalPeriod;
	}
	
	public void setPeriodicalPeriod(int periodicalPeriod)
	{
		this.periodicalPeriod = periodicalPeriod;
	}
	
	public int getMaxCDs()
	{
		return this.maxCDs;
	}
	
	public void setMaxCDs(int maxCDs)
	{
		this.maxCDs = maxCDs;
	}
	
	public int getCdPeriod()
	{
		return this.cdPeriod;
	}
	
	public void setCdPeriod(int cdPeriod)
	{
		this.cdPeriod = cdPeriod;
	}
	
}
