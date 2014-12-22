package lk.apiit.oosd.nsa.lms.model.people;

import java.io.Serializable;
import java.util.Comparator;

public class Staff extends Patron implements Serializable
{
	
	public enum StaffType
	{
		LIBRARIAN, ASST_LIBRARIAN, STAFF, ADMIN
	}
	
	public static enum CompareBy implements Comparator<Staff>
	{
		StaffId()
		{
			@Override
			public int compare(Staff a, Staff b)
			{
				return a.staffId.compareTo(b.staffId);
			}
		};
		
		public abstract int compare(Staff a, Staff b);
		
	}
	
	private static final long	serialVersionUID	= -8190207231192609874L;
	
	// Represents someone who is a member of staff. All staff are assumed to be
	// patrons.
	
	private String				staffId;
	
	private static String		lastStaffId			= "stf0000";
	
	private StaffType			staffType;
	
	public Staff()
	{
		super();
	}
	
	public Staff(String staffId)
	{
		super();
		this.staffId = staffId;
	}
	
	public Staff(StaffType staffType)
	{
		super();
		this.userType = UserType.STAFF;
		
		this.staffType = staffType;
		this.staffId = lastStaffId;
		
		incrementStaffId();
		
		this.patronId = lastPatronId;
		
		incrementPatronId();
		
	}
	
	public String getStaffId()
	{
		return this.staffId;
	}
	
	public StaffType getStaffType()
	{
		return this.staffType;
	}
	
	public void setStaffType(StaffType staffType)
	{
		this.staffType = staffType;
	}
	
	private static void incrementStaffId()
	{
		lastStaffId = String.format("%s%04d", "stf",
				Integer.parseInt(lastStaffId.substring(3)) + 1);
	}
	
}
