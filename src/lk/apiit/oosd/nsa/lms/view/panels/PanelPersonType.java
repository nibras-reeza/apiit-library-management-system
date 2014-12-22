package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import lk.apiit.oosd.nsa.lms.exceptions.ReadOnlyException;
import lk.apiit.oosd.nsa.lms.model.people.Staff.StaffType;
import lk.apiit.oosd.nsa.lms.model.people.User.UserType;
import lk.apiit.oosd.nsa.lms.model.types.MembershipType;
import lk.apiit.oosd.nsa.lms.view.models.FrmUserModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelPersonType extends ExtendedPanel
{
	
	private static final long	serialVersionUID	= -1997550422851484249L;
	
	public FrmUserModel getModel()
	{
		return this.model;
	}
	
	public void setModel(FrmUserModel model)
	{
		this.model = model;
	}
	
	private FrmUserModel				model;
	private JLabel						lblUserType;
	private JLabel						lblMembershipType;
	private JLabel						lblStaffType;
	private JComboBox<UserType>			cmbUser;
	private JComboBox<MembershipType>	cmbMember;
	private JComboBox<StaffType>		cmbStaff;
	
	public PanelPersonType(FrmUserModel model)
	{
		super();
		this.model = model;
		setLayout(new FormLayout(new ColumnSpec[] {
		
		FormFactory.GLUE_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.PREF_COLSPEC,
				FormFactory.GLUE_COLSPEC }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.PREF_ROWSPEC, }));
		this.lblUserType = new JLabel("User Type:");
		add(this.lblUserType, "2, 2, left, fill");
		this.cmbUser = new JComboBox<UserType>();
		this.cmbUser.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent arg0)
			{
				
				if (cmbUser.getSelectedItem().equals(UserType.STAFF))
				{
					cmbMember.setSelectedItem(MembershipType.STAFF);
					
					cmbMember.setEnabled(false);
					cmbStaff.setEnabled(true);
				}
				else
				{
					cmbStaff.setEnabled(false);
					
					cmbMember.setEnabled(true);
				}
				
			}
		});
		this.cmbUser.setModel(new DefaultComboBoxModel<UserType>(UserType
				.values()));
		add(this.cmbUser, "4, 2, fill, center");
		this.lblMembershipType = new JLabel("Membership Type:");
		add(this.lblMembershipType, "2, 4, left, center");
		this.cmbMember = new JComboBox<MembershipType>();
		this.cmbMember.setModel(new DefaultComboBoxModel<MembershipType>(
				MembershipType.values()));
		add(this.cmbMember, "4, 4, fill, center");
		this.lblStaffType = new JLabel("Staff Type: ");
		add(this.lblStaffType, "2, 6, left, center");
		this.cmbStaff = new JComboBox<StaffType>();
		this.cmbStaff.setModel(new DefaultComboBoxModel<StaffType>(StaffType
				.values()));
		add(this.cmbStaff, "4, 6, fill, center");
	}
	
	@Override
	public void displayModel()
	{
		cmbUser.setSelectedItem(model.getUserType());
		cmbMember.setSelectedItem(model.getMembershipType());
		cmbStaff.setSelectedItem(model.getStaffType());
		
	}
	
	@Override
	public void updateModel()
	{
		if (model.getReadOnly())
			throw new ReadOnlyException("You cannot edit this Contact!");
		
		model.setUserType((UserType) cmbUser.getSelectedItem());
		model.setMembershipType((MembershipType) cmbMember.getSelectedItem());
		
		if (cmbUser.getSelectedItem().equals(UserType.PATRON))
			model.setStaffType(null);
		else
			model.setStaffType((StaffType) cmbStaff.getSelectedItem());
		
	}
	
}
