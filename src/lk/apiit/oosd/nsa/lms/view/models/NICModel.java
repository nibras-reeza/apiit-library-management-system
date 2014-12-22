package lk.apiit.oosd.nsa.lms.view.models;

public class NICModel extends Model
{
	String	NIC;
	
	public String getNIC()
	{
		return this.NIC;
	}
	
	public void setNIC(String nIC)
	{
		this.NIC = nIC;
	}
	
	public NICModel(String nIC)
	{
		super();
		this.NIC = nIC;
	}
	
	public NICModel()
	{
		this(new String());
	}
	
}
