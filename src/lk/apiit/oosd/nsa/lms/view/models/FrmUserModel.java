package lk.apiit.oosd.nsa.lms.view.models;

import java.util.Date;

import lk.apiit.oosd.nsa.lms.model.people.Patron;
import lk.apiit.oosd.nsa.lms.model.people.Staff;
import lk.apiit.oosd.nsa.lms.model.people.Staff.StaffType;
import lk.apiit.oosd.nsa.lms.model.people.User;
import lk.apiit.oosd.nsa.lms.model.people.User.UserType;
import lk.apiit.oosd.nsa.lms.model.types.Address;
import lk.apiit.oosd.nsa.lms.model.types.Contact;
import lk.apiit.oosd.nsa.lms.model.types.Gender;
import lk.apiit.oosd.nsa.lms.model.types.MembershipType;
import lk.apiit.oosd.nsa.lms.model.types.Name;

public class FrmUserModel extends Model
{
	private String			username;
	private String			password;
	private UserType		userType;
	
	private String			nic;
	private Name			name;
	private Gender			gender;
	private Date			dateOfBirth;
	
	private Address			address;
	private Contact			contact;
	
	// Patron
	private String			patronId	= null;
	
	private MembershipType	membershipType;
	
	// Staff
	private StaffType		staffType;
	
	public User getUser()
	{
		return this.user;
	}
	
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public Staff getStaff()
	{
		return this.staff;
	}
	
	public void setStaff(Staff staff)
	{
		this.staff = staff;
	}
	
	public Patron getPatron()
	{
		return this.patron;
	}
	
	public void setPatron(Patron patron)
	{
		this.patron = patron;
	}
	
	// References
	private User		user;
	private Staff		staff;
	private Patron		patron;
	private GenderModel	genderModel;
	private NICModel	nicModel;
	
	public FrmUserModel()
	{
		name = new Name();
		dateOfBirth = new Date();
		address = new Address();
		contact = new Contact();
		
		genderModel = new GenderModel();
		
		nicModel = new NICModel();
		
		// gender = Gender.MALE;
		
		// userType = staff.getUserType();
		// nic = staff.getNic();
		//
		// gender = staff.getGender();
		//
		//
		// membershipType = staff.getMembershipType();
		// staffType = staff.getStaffType();
		
	}
	
	public FrmUserModel(Staff staff)
	{
		username = staff.getUsername();
		userType = staff.getUserType();
		nic = staff.getNic();
		nicModel = new NICModel(staff.getNic());
		name = staff.getName();
		gender = staff.getGender();
		genderModel = new GenderModel(staff.getGender());
		dateOfBirth = staff.getDateOfBirth();
		address = staff.getAddress();
		contact = staff.getContact();
		membershipType = staff.getMembershipType();
		staffType = staff.getStaffType();
		
		this.user = this.patron = this.staff = staff;
	}
	
	public FrmUserModel(Patron patron)
	{
		username = patron.getUsername();
		userType = patron.getUserType();
		nic = patron.getNic();
		nicModel = new NICModel(patron.getNic());
		name = patron.getName();
		gender = patron.getGender();
		genderModel = new GenderModel(patron.getGender());
		dateOfBirth = patron.getDateOfBirth();
		address = patron.getAddress();
		contact = patron.getContact();
		membershipType = patron.getMembershipType();
		
		this.user = this.patron = patron;
	}
	
	public FrmUserModel(User item)
	{
		switch (item.getUserType())
		{
			case STAFF:
				Staff staff = (Staff) item;
				staffType = staff.getStaffType();
			case PATRON:
				Patron patron = (Patron) item;
				this.patron = patron;
				username = patron.getUsername();
				userType = patron.getUserType();
				nic = patron.getNic();
				nicModel = new NICModel(patron.getNic());
				name = patron.getName();
				gender = patron.getGender();
				genderModel = new GenderModel(patron.getGender());
				dateOfBirth = patron.getDateOfBirth();
				address = patron.getAddress();
				contact = patron.getContact();
				membershipType = patron.getMembershipType();
				break;
		
		}
		this.user = item;
		
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public UserType getUserType()
	{
		return this.userType;
	}
	
	public void setUserType(UserType userType)
	{
		this.userType = userType;
	}
	
	public String getNic()
	{
		nic = nicModel.getNIC();
		return this.nic;
	}
	
	public void setNic(String nic)
	{
		this.nic = nic;
		nicModel.setNIC(nic);
	}
	
	public Name getName()
	{
		return this.name;
	}
	
	public void setName(Name name)
	{
		this.name = name;
	}
	
	public Gender getGender()
	{
		return this.gender;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
		genderModel.setGender(gender);
	}
	
	public Date getDateOfBirth()
	{
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	
	public Address getAddress()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address = address;
	}
	
	public Contact getContact()
	{
		return this.contact;
	}
	
	public void setContact(Contact contact)
	{
		this.contact = contact;
	}
	
	public String getPatronId()
	{
		return this.patronId;
	}
	
	public void setPatronId(String patronId)
	{
		this.patronId = patronId;
	}
	
	public MembershipType getMembershipType()
	{
		return this.membershipType;
	}
	
	public void setMembershipType(MembershipType membershipType)
	{
		this.membershipType = membershipType;
	}
	
	public StaffType getStaffType()
	{
		return this.staffType;
	}
	
	public void setStaffType(StaffType staffType)
	{
		this.staffType = staffType;
	}
	
	public NICModel getNICModel()
	{
		return nicModel;
	}
	
	public NameModel getNameModel()
	{
		NameModel model = new NameModel(name);
		
		return model;
	}
	
	public GenderModel getGenderModel()
	{
		genderModel = new GenderModel(gender);
		
		return genderModel;
	}
	
	public DateModel getDateOfBirthModel()
	{
		return new DateModel(dateOfBirth);
	}
	
	public AddressModel getAddressModel()
	{
		return new AddressModel(address);
	}
	
	public ContactModel getContactModel()
	{
		return new ContactModel(contact);
	}
	
	public User newUser()
	{
		
		User user;
		if (userType.equals(UserType.STAFF))
			user = new Staff(staffType);
		else
			user = new Patron(membershipType);
		
		Patron patron = (Patron) user;
		patron.setUsername(username);
		patron.setAddress(address);
		patron.setContact(contact);
		patron.setDateOfBirth(dateOfBirth);
		
		patron.setGender(genderModel.getGender());
		patron.setName(name);
		patron.setNic(nicModel.getNIC());
		patron.setPassword(password);
		patron.setUserType(userType);
		
		return user;
	}
	
	public Patron newPatron()
	{
		
		Patron patron = new Patron(membershipType);
		patron.setUsername(username);
		patron.setAddress(address);
		patron.setContact(contact);
		patron.setDateOfBirth(dateOfBirth);
		patron.setGender(genderModel.getGender());
		patron.setName(name);
		patron.setNic(nicModel.getNIC());
		patron.setPassword(password);
		patron.setUserType(userType);
		
		return patron;
	}
	
	public Staff newStaff()
	{
		Staff patron = new Staff(staffType);
		patron.setUsername(username);
		patron.setAddress(address);
		patron.setContact(contact);
		patron.setDateOfBirth(dateOfBirth);
		patron.setGender(genderModel.getGender());
		patron.setName(name);
		patron.setNic(nicModel.getNIC());
		patron.setPassword(password);
		patron.setUserType(userType);
		
		return patron;
	}
	
	public void updateUser()
	{
		
		switch (userType)
		{
		
			case STAFF:
				updateStaff();
			case PATRON:
				updatePatron();
				
		}
	}
	
	public void updatePatron()
	{
		
		patron.setAddress(address);
		patron.setContact(contact);
		patron.setDateOfBirth(dateOfBirth);
		patron.setGender(genderModel.getGender());
		patron.setName(name);
		patron.setNic(nicModel.getNIC());
		
		patron.setUserType(userType);
	}
	
	public void updateStaff()
	{
		
		patron.setAddress(address);
		patron.setContact(contact);
		patron.setDateOfBirth(dateOfBirth);
		patron.setGender(genderModel.getGender());
		patron.setName(name);
		patron.setNic(nicModel.getNIC());
		
		patron.setUserType(userType);
	}
}
