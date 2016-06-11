package tvmaze.embed;

import java.lang.reflect.Field;

import tvmaze.model.CastCredit;
import tvmaze.model.CrewCredit;
import tvmaze.model.Person;
import tvmaze.model.Show;

public abstract class Embeddable {
	private static final String _EPISODES = "episodes";
	private static final String _CAST = "cast";
	private static final String _CAST_CREDITS = "castcredits";
	private static final String _CHARACTER = "character";
	private static final String _CREW_CREDITS = "crewcredits";
	private static final String _NEXT_EPISODE = "nextepisode";
	private static final String _PREVIOUS_EPISODE = "previousepisode";
	private static final String _SHOW = "show";
	
	public static final class CASTCREDIT {
		public static final String CHARACTER = Embeddable._CHARACTER;
		public static final String SHOW = Embeddable._SHOW;
	}
	
	public static final class CREWCREDIT {
		public static final String SHOW = Embeddable._SHOW;
	}
	
	public static final class PERSON {
		public static final String CAST_CREDITS = Embeddable._CAST_CREDITS;
		public static final String CREW_CREDITS = Embeddable._CREW_CREDITS;
	}
	
	public static final class SHOW {
		public static final String EPISODES = Embeddable._EPISODES;
		public static final String CAST = Embeddable._CAST;
		public static final String NEXT_EPISODE = Embeddable._NEXT_EPISODE;
		public static final String PREVIOUS_EPISODE = Embeddable._PREVIOUS_EPISODE;
	}
	
	public static boolean isEmbeddable(Class<?> cl, String embeddable) {
		Class<?> c = null;
		
		if (CastCredit.class.equals(cl))
			c = Embeddable.CASTCREDIT.class;
		else if (CrewCredit.class.equals(cl))
			c = Embeddable.CREWCREDIT.class;
		else if (Person.class.equals(cl))
			c = Embeddable.PERSON.class;
		else if (Show.class.equals(cl))
			c = Embeddable.SHOW.class;
		
		if (c != null)
			for(Field f : c.getFields())
				try {
					if (((String)(f.get(null))).equals(embeddable))
						return true;
				} catch (IllegalArgumentException e) {
				} catch (IllegalAccessException e) {}
		
		return false;
	}
}
