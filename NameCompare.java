import java.util.Comparator;

public abstract class NameCompare implements Comparator<ParentCompetitor>
{


	public int compareN(ParentCompetitor o1, ParentCompetitor o2) 
	{
		return o1.getName().getFullName().compareTo( (o2.getName().getFullName())) ;
	}
}
	
	
//	public NameCompare()
//	{
//		String name = "Name";
//	}
//	
//	
//	public int compareName(ParentCompetitor o1, ParentCompetitor o2) 
//	{
//		return  getFullName(o1).compareTo( getFullName(o2) );
//	}
//	
//	public String getFullName(ParentCompetitor o)
//	{
//		return getFullName(o);
//	}
//}
