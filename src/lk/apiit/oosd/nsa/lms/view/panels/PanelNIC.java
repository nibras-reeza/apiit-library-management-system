package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.UIFactory;
import lk.apiit.oosd.nsa.lms.view.models.NICModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelNIC extends ExtendedPanel
{
	
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 2301805713468257752L;
	
	NICModel					model;
	private JLabel				lblNic;
	private JTextField			txtNIC;
	
	public PanelNIC(NICModel model)
	{
		super();
		this.model = model;
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC, },
				new RowSpec[] { FormFactory.DEFAULT_ROWSPEC, }));
		this.lblNic = new JLabel("NIC:");
		add(this.lblNic, "2, 1, left, center");
		this.txtNIC = new JTextField();
		this.txtNIC.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtNIC.getText() == null || txtNIC.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"NIC cannot be empty!");
			}
		});
		add(this.txtNIC, "4, 1, right, center");
		this.txtNIC.setColumns(24);
	}
	
	public NICModel getModel()
	{
		return this.model;
	}
	
	public void setModel(NICModel model)
	{
		this.model = model;
	}
	
	@Override
	public void displayModel()
	{
		;
		
		txtNIC.setText(model.getNIC());
		
	}
	
	@Override
	public void updateModel()
	{
		
		model.setNIC(txtNIC.getText());
		
	}
	
}
