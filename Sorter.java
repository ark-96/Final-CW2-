import java.util.ArrayList;
import java.util.Collections;
/** Class of Sorter
 * provides functionality to sort lists of members / competitors
 * utilising standard Java.Collections functionality
 */
public class Sorter

{
		ArrayList<ParentCompetitor> competitor = new ArrayList<>();
		
		/** Sorter
		* * @param competitor - from an ArrayList of type ParentCompetitor 
		*/
		public Sorter(ArrayList<ParentCompetitor> competitor)
			{	this.competitor = competitor;	}
		
		/** Sort by Name
		* * sorts two objects based on Name
		*  @return competitor
		*/
		public ArrayList<ParentCompetitor> sortByName()
		{	
			Collections.sort(competitor, ParentCompetitor.compareName);
			return competitor;
		}
		
		/** Sort by Dan
		 * * sorts two objects based on Dan grade
		 * * @return competitor
		 */
		public ArrayList<ParentCompetitor> sortByDan()
		{
			Collections.sort(competitor, ParentCompetitor.compareDan);
			return competitor;
		}

		/** Sort by Age
		 * * sorts two objects based on age
		 * @return competitor
		 */		
		public ArrayList<ParentCompetitor> sortByAge()
		{
			Collections.sort(competitor, ParentCompetitor.compareAge);
			return competitor;
		}
		
		/** Sort by Score
		 * * @return sorts two objects based on Score
		 */
		public ArrayList<ParentCompetitor> sortByScore()
		{
			Collections.sort(competitor, ParentCompetitor.compareScore);
			return competitor;
		}
		
		/** Sort by ID
		 * * sorts two objects based on Name
		 * @return competitor
		 */
		public ArrayList<ParentCompetitor> sortByID()
		{
			Collections.sort(competitor, ParentCompetitor.compareID);
			return competitor;
		}
		
		
		/** Sort by Position
		 * * sorts two objects based on Position
		 * @return competitor
		 */
		public ArrayList<ParentCompetitor> sortByPosition()
		{
			Collections.sort(competitor, ParentCompetitor.comparePosition);
			return competitor;
		}
		
		/** Sort by Nationality
		 * * sorts two objects based on Nationality
		 * @return competitor
		 */
		public ArrayList<ParentCompetitor> sortByNationality()
		{
			Collections.sort(competitor, ParentCompetitor.compareNationality);
			return competitor;
		}
		
		/** Sort by ID
		 * *  sorts two objects based on Level
		 * @return competitor
		 */		
		public ArrayList<ParentCompetitor> sortByLevel()

		{
			Collections.sort(competitor, ParentCompetitor.compareLevel);
			return competitor;
		}
}
