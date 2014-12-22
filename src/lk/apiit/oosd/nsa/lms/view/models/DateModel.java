package lk.apiit.oosd.nsa.lms.view.models;

import java.util.Calendar;
import java.util.Date;

public class DateModel extends Model
{
	private Date	date;
	
	public DateModel()
	{
		this(new Date());
	}
	
	public DateModel(Date date)
	{
		this.date = date;
	}
	
	public int getDay()
	{
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getMonth()
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}
	
	public int getYear()
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}
	
	public void setDay(int day)
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		
		c.set(Calendar.DAY_OF_MONTH, day);
		
		this.date = c.getTime();
	}
	
	public void setMonth(int month)
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		
		c.set(Calendar.MONTH, month);
		
		this.date = c.getTime();
	}
	
	public void setYear(int year)
	{
		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		
		c.set(Calendar.YEAR, year);
		
		this.date = c.getTime();
	}
	
	public Date getDate()
	{
		return this.date;
	}
	
	public void setDate(Date date)
	{
		this.date = date;
	}
	
}
