
public class TableTennis extends ParentCompetitor {
private String nationality;
private int age;  

public TableTennis(String a,int i, Name n, String l, int age, String country, int [] s){
 super(a, i, n, l, s);	
 nationality=country;
 this.age = age;
}

public int getAge(){
 return age;
}
public String getCountry(){
 return nationality;
}

public double getOverallScore(){
 int divider= 0;
 double overall = 0;
 for(int scoreIndex = 0; scoreIndex < getScoreArray().length; scoreIndex++){
 	if (scoreIndex%2==0){
	 overall +=(getScoreArray()[scoreIndex])*3;
	 divider +=3;
 	}
 else{
  overall +=(getScoreArray()[scoreIndex])*2;
  divider +=2;
 }
 }
 overall = overall/(divider);
 return overall;
}
public String getFullDetails(){
 String compNo = String.format("%s", getId());
 String years = String.format("%s", age);
 return "Competitor number " + compNo + ", name " + getName().getFullName() + ", country " + nationality + ".\n" + getName().getFirstName() + " is a " + getLevel() + " aged " + years + " and received these scores " + getScoreArray() + ".\nThis gives him an overall score of " + String.format("%.3s", getOverallScore()) + ".";
}
public String getShortDetails(){
 String compNo = String.format("%s", getId());
 return "CN " + compNo + " (" + getName().getInitials() + ") has an overall score " + String.format("%.3s", getOverallScore());
}
}
