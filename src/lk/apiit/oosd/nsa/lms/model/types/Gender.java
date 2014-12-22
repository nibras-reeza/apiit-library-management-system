package lk.apiit.oosd.nsa.lms.model.types;

import java.io.Serializable;

public enum Gender implements Serializable
{
	MALE
	{
		public String toString()
		{
			return "Male";
		}
	},
	FEMALE
	{
		public String toString()
		{
			return "Female";
		}
	};
	
}