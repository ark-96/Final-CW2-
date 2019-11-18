
public class TableTennis extends ParentCompetitor {
private String competitorTier;
private String nationality;
private int age;
private int [] score;  

public TableTennis(String a,int i, Name n, String l, int age, String country, int [] s){
 super(a, i, n, l, s)	
 nationality=country;
 this.age = age;
}
	
public String getTier(){
 return competitorTier;
}
public int getAge(){
 return age;
}
public String getCountry(){
 return nationality;
}
public String getScoreArray(){
 return s;
}
public double getOverallScore(){
 int divider= 0;
 double overall = 0;
 for(int scoreIndex = 0; scoreIndex < score.length; scoreIndex++){
 	if (scoreIndex%2==0){
	 overall +=(score[scoreIndex])*3;
	 divider +=3;
 	}
 else{
  overall +=(score[scoreIndex])*2;
  divider +=2;
 }
 }
 overall = overall/(divider);
 return overall;
}
public String getFullDetails(){
 String compNo = String.format("%s", competitorNumber);
 String years = String.format("%s", age);
 return "Competitor number " + compNo + ", name " + competitorName.getFullName() + ", country " + nationality + ".\n" + competitorName.getFirstName + " is a " + competitorTier + " aged " + years + " and received these scores " + getScoreArray() + ".\nThis gives him an overall score of " + String.format("%.3s", getOverallScore()) + ".";
}
public String getShortDetails(){
 String compNo = String.format("%s", competitorNumber);
 return "CN " + compNo + " (" + getInitials() + ") has an overall score " + String.format("%.3s", getOverallScore());
}
}
