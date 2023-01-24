// --== CS400 Project One File Header ==--
// Name: Cole Johnstone
// CSL Username: johnstone
// Email: cjohnstone@wisc.edu
// Lecture #: 002 @1:00pm
// Notes to Grader: N/A

/**
 * This class acts like an instance of a show
 * 
 * @author Cole Johnstone
 *
 */
public class Show implements IShow {
	private String title;
	private int year;
	private int rating;
	private String providers; // this string includes the names of every streaming source

	/**
	 * Creates a new Show with this given parameters
	 * 
	 * @param title
	 * @param year
	 * @param rating
	 * @param providers
	 */
	public Show(String title, int year, int rating, String providers) {
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.providers = providers;
	}

	/**
	 * retrieve the title of this show object
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * retrieve the year that this show was first produced
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * retrieve the Rotten Tomatoes Rating (out of 100)
	 * 
	 * @return rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * checks show availability
	 * 
	 * @param provider
	 * @return
	 */
	public boolean isAvailableOn(String provider) {
		if (provider == null) {
			return false;
		}
		if (providers.contains(provider))
			return true;

		return false;
	}

	/**
	 * supports sorting shows in descending order by rating
	 * 
	 * @return int value
	 */
	@Override
	public int compareTo(IShow o) {
		if (this.rating < o.getRating())
			return -1;
		if (this.rating == o.getRating())
			return 0;
		return 1;
	}
}
