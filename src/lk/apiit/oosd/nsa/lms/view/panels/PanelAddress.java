package lk.apiit.oosd.nsa.lms.view.panels;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import lk.apiit.oosd.nsa.lms.controller.UIFactory;
import lk.apiit.oosd.nsa.lms.view.models.AddressModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelAddress extends ExtendedPanel
{
	
	private static final long	serialVersionUID	= 3619141040901445540L;
	
	private AddressModel		model;
	private JTextField			txtAddress_1;
	private JTextField			txtAddress_2;
	private JTextField			txtCity;
	private JComboBox<String>	cmbCountry;
	
	public PanelAddress(AddressModel model)
	{
		this.model = model;
		setLayout(new FormLayout(new ColumnSpec[] { FormFactory.GLUE_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.PREF_COLSPEC, FormFactory.GLUE_COLSPEC },
				new RowSpec[] { FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.PREF_ROWSPEC }));
		
		JLabel lblAddress = new JLabel("Address:");
		add(lblAddress, "2, 1, right, center");
		
		txtAddress_1 = new JTextField();
		this.txtAddress_1.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtAddress_1.getText() == null
						|| txtAddress_1.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"Address cannot be empty!");
			}
		});
		add(txtAddress_1, "4, 1, fill, center");
		txtAddress_1.setColumns(24);
		
		txtAddress_2 = new JTextField();
		add(txtAddress_2, "4, 3, fill, center");
		txtAddress_2.setColumns(24);
		
		JLabel lblCity = new JLabel("City: ");
		add(lblCity, "2, 5, right, center");
		
		txtCity = new JTextField();
		this.txtCity.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (txtCity.getText() == null || txtCity.getText().equals(""))
					UIFactory.getInstance().warn("Cannot be empty!",
							"City cannot be empty!");
			}
		});
		add(txtCity, "4, 5, fill, center");
		txtCity.setColumns(24);
		
		JLabel lblCountry = new JLabel("Country: ");
		add(lblCountry, "2, 7, right, center");
		
		String[] countries = getCountryList();
		
		cmbCountry = new JComboBox<String>();
		this.cmbCountry.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusLost(FocusEvent e)
			{
				if (cmbCountry.getSelectedIndex() < 0)
					
					UIFactory.getInstance().warn("Select a country!",
							"Please select a valid country!");
			}
		});
		for (String s : countries)
			cmbCountry.addItem(s);
		cmbCountry.setMaximumRowCount(1);
		add(cmbCountry, "4, 7, fill, center");
		
	}
	
	private String[] getCountryList()
	{
		// http://harilalkm.blogspot.com/2007/03/country-list.html
		String[] CountryList = new String[] { "Abkhazia", "Afghanistan",
				"Akrotiri and Dhekelia", "Åland Islands", "Albania", "Algeria",
				"American Samoa", "Andorra", "Angola", "Anguilla",
				"Antigua and Barbuda", "Argentina ", "Armenia ", "Aruba",
				"Ascension Island", "Australia", "Austria", "Azerbaijan",
				"Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus",
				"Belgium", "Belize", "Benin ", "Bermuda", "Bhutan", "Bolivia",
				" Bosnia", "Botswana", "Brazil", "Brunei", " Bulgaria",
				"Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada",
				"Cape Verde", "Cayman Islands", "Central African Republic",
				"Chad", "Chile", "China", "ChristmasIsland", "Cocos",
				"Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica",
				"Côte d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech",
				"Denmark", "Djibouti", "Dominica", "Ecuador", "Egypt",
				"El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
				"Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji",
				"Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
				"Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam",
				"Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana",
				"Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland",
				"India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man",
				"Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan",
				"Kazakhstan", "Kenya", "Kiribati", "Korea", "Kosovo", "Kuwait",
				"Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
				"Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg",
				"Macao", "Macedonia", "Madagascar", "Malawi", "Malaysia",
				"Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania",
				"Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova",
				"Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco",
				"Mozambique", "Myanmar", "Nagorno-Karabakh", "Namibia",
				"Nauru", "Nepal", "Netherlands", "New Caledonia",
				"New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue",
				"Norfolk Island", "Norway", "Oman", "Pakistan", "Palau",
				"Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru",
				"Philippines", "Pitcairn", "Poland", "Portugal",
				"Pridnestrovie", "Puerto Rico", "Qatar", "Romania", "Russia",
				"Rwanda", "Saint-Barthélemy", "Saint Helena",
				"Saint Kitts and Nevis", "Saint Lucia", "Saint Martin",
				"Saint-Pierre and	Miquelon",
				"Saint Vincent and the Grenadines", "Samoa", "San Marino",
				"São Tomé and Príncipe", "Saudi Arabia", "Senegal", "Serbia",
				"Seychelles", "Sierra Leone", "Singapore", "Slovakia",
				"Slovenia", "Solomon	Islands", "Somalia", "Somaliland",
				"South Africa", "South Ossetia", "Spain", "SriLanka", "Sudan",
				"Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland",
				"Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand",
				"Timor", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago",
				"Tristan da Cunha", "Tunisia", "Turkey", "Turkmenistan",
				"Turks and Caicos", "Tuvalu", "Uganda", "Ukraine",
				"United Arab Emirates", "United Kingdom", "United States",
				"Uruguay", "Uzbekistan", "Vanuatu", "Vatican City",
				"Venezuela", "Vietnam", "Virgin Islands", "Wallis and Futuna",
				"Western Sahara", "Yemen", "Zambia", "Zimbabwe" };
		return CountryList;
	}
	
	public AddressModel getModel()
	{
		return this.model;
	}
	
	public void setModel(AddressModel model)
	{
		this.model = model;
	}
	
	public void displayModel()
	{
		
		String streetAddress = model.getStreetAddress();
		
		int i;
		
		if ((i = streetAddress.indexOf("\n")) != -1)
		{
			txtAddress_1.setText(streetAddress.substring(0, i));
			txtAddress_2.setText(streetAddress.substring(i));
		}
		else
			txtAddress_1.setText(streetAddress);
		
		txtCity.setText(model.getCity());
		
		cmbCountry.setSelectedItem(model.getCountry());
		
	}
	
	public void updateModel()
	{
		
		String address = txtAddress_1.getText();
		
		address += (txtAddress_2.getText() != null && txtAddress_2.getText() != "") ? ("\n" + txtAddress_2
				.getText()) : "";
		
		model.setStreetAddress(address);
		
		model.setCity(txtCity.getText());
		
		model.setCountry((String) cmbCountry.getSelectedItem());
		
	}
	
}
