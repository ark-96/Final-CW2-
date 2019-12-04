import java.util.ArrayList;
import java.util.Collections;

public class Sorter
/** Class of Sorter
 * provides functionality to sort lits of members / competitors
 * utilising standard Java.Collections functionality
 * @param 
 * 	 
 */
{
		ArrayList<ParentCompetitor> competitor = new ArrayList<>();
	
		public Sorter(ArrayList<ParentCompetitor> competitor)
		/** Sorter
		 * * @param competitor - from an ArrayList of type ParentCompetitor 
		 */
		{	this.competitor = competitor;	}
		
		public ArrayList<ParentCompetitor> sortByName()
		/** Sort by Name
		 * * @param sorts two objects based on Name
		 */
		{	
			Collections.sort(competitor, ParentCompetitor.compareName);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByDan()
		/** Sort by Dan
		 * * @param sorts two objects based on Dan grade
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareDan);
			return competitor;
		}

		public ArrayList<ParentCompetitor> sortByAge()
		/** Sort by Age
		 * * @param sorts two objects based on age
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareAge);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByScore()
		/** Sort by Score
		 * * @param sorts two objects based on Score
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareScore);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByID()
		/** Sort by ID
		 * * @param sorts two objects based on Name
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareID);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByPosition()
		/** Sort by Position
		 * * @param sorts two objects based on Position
		 */
		{
			Collections.sort(competitor, ParentCompetitor.comparePosition);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByNationality()
		/** Sort by Nationality
		 * * @param sorts two objects based on Nationality
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareNationality);
			return competitor;
		}
		
		public ArrayList<ParentCompetitor> sortByLevel()
		/** Sort by ID
		 * * @param sorts two objects based on Level
		 */
		{
			Collections.sort(competitor, ParentCompetitor.compareLevel);
			return competitor;
		}
}
