
public class Name {
		//instance variables
	private String firstName;
	private String middleName;
	private String lastName;

	//constructor creating object with first and last name
	public Name(String fName, String lName) {
		firstName = fName;
		middleName = "";
		lastName = lName;
	}
		//constructor creating object with full name, in which middle name could be empty if it doesn't exist
	public Name (String fName, String mName, String lName) {
		firstName = fName;
		middleName = mName;
		lastName = lName;
	}

		 //constructor to create name from full name
		 //in the format first name then space then last name
		 //or first name then space then middle name then space then last name
	public Name(String fullName) {
		int spacePos1 = fullName.indexOf(' ');
		firstName = fullName.substring(0, spacePos1);
		int spacePos2 = fullName.lastIndexOf(' ');
		if (spacePos1 == spacePos2)
			middleName = "";
		else
			middleName = fullName.substring(spacePos1+1, spacePos2);
		lastName = fullName.substring(spacePos2+1);
			
	}

		//Returns
	public String getFirstName () {return firstName;}
	public String getLastName () {return lastName;}
		
	public void setLastName (String ln) {
		lastName = ln;
	}
		
	public String getFirstAndLastName() {
		return firstName + " "+ lastName;
	}
		
	public String getLastCommaMiddleCommaFirst() {
		return lastName + ", " + firstName;
	}
		
	public String getFullName() {
		String result = firstName + " ";
		if (!middleName.equals("")) {
			result += middleName + " ";
		}
		result += lastName;
		return result;
	}
		
	public String getInitials() {
		return firstName.charAt(0) + "." + lastName.charAt(0);
	}
}
