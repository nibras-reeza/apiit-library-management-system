package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.UIFactory;
import lk.apiit.oosd.nsa.lms.view.models.ContactModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelContact extends ExtendedPanel
{
	private static final long	serialVersionUID	= -7090340446971716395L;
	ContactModel				model;
	private JTextField			txtTelephone;
	private JTextField			txtEmail;
	
	public PanelContact(ContactModel model)
	{
		super();
		this.model = model;
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC },
				new RowSpec[] { FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC }));
		
		JLabel lblTelephone = new JLabel("Telephone:");
		add(lblTelephone, "2, 1, left, center");
		
		txtTelephone = new JTextField();
		this.txtTelephone.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtTelephone.getText() == null
						|| txtTelephone.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"Telephone cannot be empty!");
				else if (txtTelephone.getText().matches("[a-zA-Z]+\\.?"))
					UIFactory.getInstance().warn("Letters in number?",
							"Telephone number may not contain any letters!");
			}
		});
		add(txtTelephone, "4, 1, left, center");
		txtTelephone.setColumns(24);
		
		JLabel lblEmail = new JLabel("EMail:");
		add(lblEmail, "2, 3, left, center");
		
		txtEmail = new JTextField();
		this.txtEmail.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtEmail.getText() == null || txtEmail.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"EMail cannot be empty!");
			}
		});
		add(txtEmail, "4, 3, left, center");
		txtEmail.setColumns(24);
	}
	
	@Override
	public void displayModel()
	{
		
		txtTelephone.setText(model.getTelephone());
		txtEmail.setText(model.getEmail());
		
	}
	
	@Override
	public void updateModel()
	{
		
		model.setTelephone(txtTelephone.getText());
		model.setEmail(txtEmail.getText());
	}
	
	public ContactModel getModel()
	{
		return this.model;
	}
	
	public void setModel(ContactModel model)
	{
		this.model = model;
	}
	
}
