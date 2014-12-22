package lk.apiit.oosd.nsa.lms.view.models;

import lk.apiit.oosd.nsa.lms.model.types.Gender;

public class GenderModel extends Model
{
	private Gender	gender;
	
	public GenderModel()
	{
		
	}
	
	public GenderModel(Gender gender)
	{
		this.gender = gender;
	}
	
	public Gender getGender()
	{
		return this.gender;
	}
	
	public void setGender(Gender gender)
	{
		this.gender = gender;
	}
	
	public void setGender(String gender)
	{
		this.gender = Gender.valueOf(gender.toUpperCase());
	}
	
	public String getGenderString()
	{
		return gender.toString();
	}
}
