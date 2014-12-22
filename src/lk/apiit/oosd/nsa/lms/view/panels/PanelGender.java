package lk.apiit.oosd.nsa.lms.view.panels;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import lk.apiit.oosd.nsa.lms.view.models.GenderModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelGender extends ExtendedPanel
{
	
	private static final long	serialVersionUID	= 8569168626033307962L;
	GenderModel					model;
	private final ButtonGroup	buttonGroup			= new ButtonGroup();
	
	public GenderModel getModel()
	{
		return this.model;
	}
	
	public void setModel(GenderModel model)
	{
		this.model = model;
	}
	
	private JRadioButton	rdbtnMale;
	private JRadioButton	rdbtnFemale;
	
	public PanelGender(GenderModel model)
	{
		this.model = model;
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC, },
				new RowSpec[] { FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC, }));
		
		rdbtnMale = new JRadioButton("Male");
		buttonGroup.add(rdbtnMale);
		add(rdbtnMale, "2, 1, left, center");
		
		rdbtnFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnFemale);
		add(rdbtnFemale, "2, 3, left, center");
		
	}
	
	@Override
	public void displayModel()
	{
		rdbtnMale.setSelected(model.getGenderString().toLowerCase()
				.equals("male"));
		
	}
	
	@Override
	public void updateModel()
	{
		
		if (rdbtnMale.isSelected())
			model.setGender("Male");
		else if (rdbtnFemale.isSelected())
			model.setGender("Female");
	}
	
}
