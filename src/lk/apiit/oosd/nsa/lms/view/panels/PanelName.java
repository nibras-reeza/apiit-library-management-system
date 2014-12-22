package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.UIFactory;
import lk.apiit.oosd.nsa.lms.view.models.NameModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelName extends ExtendedPanel
{
	
	private static final long	serialVersionUID	= -405296662545105472L;
	private JTextField			txtFirstName;
	private JTextField			txtLastName;
	
	private NameModel			model;
	
	public PanelName(NameModel model)
	{
		this.model = model;
		
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC },
				new RowSpec[] { FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC }));
		
		JLabel lblFirstName = new JLabel("First Name:");
		add(lblFirstName, "2, 1, left, center");
		
		txtFirstName = new JTextField();
		this.txtFirstName.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent arg0)
			{
				if (txtFirstName.getText() == null
						|| txtFirstName.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"First Name cannot be empty!");
			}
		});
		add(txtFirstName, "4, 1, right, center");
		txtFirstName.setColumns(24);
		
		JLabel lblLastName = new JLabel("Last Name:");
		add(lblLastName, "2, 3, left, center");
		
		txtLastName = new JTextField();
		this.txtLastName.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtLastName.getText() == null
						|| txtLastName.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"Last Name cannot be empty!");
			}
		});
		add(txtLastName, "4, 3, right, center");
		txtLastName.setColumns(24);
		
	}
	
	public NameModel getModel()
	{
		return this.model;
	}
	
	public void setModel(NameModel model)
	{
		this.model = model;
	}
	
	public void displayModel()
	{
		
		txtFirstName.setText(model.getFirstName());
		txtLastName.setText(model.getLastName());
	}
	
	public void updateModel()
	{
		
		model.setFirstName(txtFirstName.getText());
		model.setLastName(txtLastName.getText());
	}
}
