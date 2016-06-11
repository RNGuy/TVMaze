package tvmaze;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import org.glassfish.jersey.client.ClientConfig;

import org.joda.time.LocalDate;

import tvmaze.embed.Embeddable;
import tvmaze.model.AKA;
import tvmaze.model.CastCredit;
import tvmaze.model.CastMember;
import tvmaze.model.CrewCredit;
import tvmaze.model.Episode;
import tvmaze.model.Person;
import tvmaze.model.Result;
import tvmaze.model.ScheduledEpisode;
import tvmaze.model.Show;

public class TVMaze {
	private static final String TVMAZE_URL = "http://api.tvmaze.com";
	private static final String[] IMAGE_MIME_TYPES = {"image/bmp", "image/x-windows-bmp", "image/gif", "image/x-icon", "image/jpeg", "image/pjpeg", "image/x-portable-bitmap", "image/png"};
	
	private ClientConfig config;
	private Client client;
	private WebTarget target;
	
	public TVMaze() {
		config = new ClientConfig();
		client = ClientBuilder.newClient(config);
		target = client.target(TVMAZE_URL);
	}
	
	public List<CastMember> getCastForShow(Show show) {
		if (show == null)
			throw new NullPointerException("Show cannot be null");
		
		return getCastForShow(show.getId());			
	}
	
	public List<CastMember> getCastForShow(int showid) {
		List<CastMember> castmembers = null;
		
		Response response = target
				.path("shows")
				.path(Integer.toString(showid))
				.path("cast")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			castmembers = response.readEntity(new GenericType<List<CastMember>>(){});
		}
		
		return castmembers;
	}
	
	public Episode getEpisodeByNumber(int showid, int season, int number) {
		Episode episode = null;
		
		Response response = target
				.path("shows")
				.path(Integer.toString(showid))
				.path("episodebynumber")
				.queryParam("season", season)
				.queryParam("number", number)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			episode = response.readEntity(Episode.class);
			if (episode != null)
				episode.setShowId(showid);
		}
		
		return episode;
	}
	
	public Episode getEpisodeByNumber(Show show, int season, int number) {
		if (show == null)
			throw new NullPointerException("Show cannot be null");
		
		return getEpisodeByNumber(show.getId(), season, number);
	}
	
	public List<Episode> getEpisodes(int showId) {
		return getEpisodes(showId, false);
	}
	
	private List<Episode> getEpisodes(int showId, boolean specials) {
		List<Episode> episodes = null;
		Response response = target
				.path("shows")
				.path(Integer.toString(showId))
				.path("episodes")
				.queryParam("specials", specials ? "1" : "0")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			episodes = response.readEntity(new GenericType<List<Episode>>(){});
			if (episodes != null)
				for(Episode e : episodes)
					e.setShowId(showId);
		}
		
		return episodes;
	}
	
	public List<Episode> getEpisodes(Show show) {
		return getEpisodes(show.getId(), false);
	}
	
	public List<Episode> getEpisodesAndSpecials(int showId) {
		return getEpisodes(showId, true);
	}
	
	public List<Episode> getEpisodesAndSpecials(Show show) {
		return getEpisodes(show.getId(), true);
	}

	public List<Episode> getEpisodesByDate(int showid, LocalDate date) {
		List<Episode> episodes = null;
		
		Response response = target
				.path("shows")
				.path(Integer.toString(showid))
				.path("episodesbydate")
				.queryParam("date", date)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			episodes = response.readEntity(new GenericType<List<Episode>>(){});
			if (episodes != null)
				for(Episode e : episodes)
					e.setShowId(showid);
		}
		
		return episodes;
	}
	
	public List<Episode> getEpisodesByDate(Show show, LocalDate date) {
		if (show == null)
			throw new NullPointerException("Show cannot be null");
		
		return getEpisodesByDate(show.getId(), date);
	}
	
	public byte[] getImageBytesByURI(String uri) {
		Response response = client.target(uri)
				.request(IMAGE_MIME_TYPES)
				.accept(IMAGE_MIME_TYPES)
				.get(Response.class);
		
		if (response.getStatus() == 200 || response.getStatus() == 304) {
			if (!response.getMediaType().toString().matches("(?i)^image/.*"))
				return null;
			
			InputStream in = response.readEntity(InputStream.class);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int b;
			try {
				while((b=in.read()) != -1)
					buffer.write(b);
				buffer.flush();
				return buffer.toByteArray();
			} catch (IOException e) {
			} finally {
				try {
					buffer.close();
					in.close();
				} catch (IOException e) {}
			}
			
		}
		
		return null;
	}
	
	public BufferedImage getImageByURI(String uri) {
		BufferedImage image = null;
		
		Response response = client.target(uri)
				.request(IMAGE_MIME_TYPES)
				.accept(IMAGE_MIME_TYPES)
				.get(Response.class);
		
		if (response.getStatus() == 200 || response.getStatus() == 304) {
			if (!response.getMediaType().toString().matches("(?i)^image/.*"))
				return null;
			
			InputStream is = response.readEntity(InputStream.class);
			try {
				image = ImageIO.read(is);
			} catch (IOException e) {} finally {
				try {
					is.close();
				} catch (IOException e) {}
			}
		}
		
		return image;
	}
	
	public <T> T getObjectByURI(String uri, Class<T> c) {
		T t = null;
		
		Response response = client.target(uri)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
	
		
		if (response.getStatus() == 200) {
			if (!response.getMediaType().isCompatible(MediaType.APPLICATION_JSON_TYPE))
				return null;
			
			t = response.readEntity(c);
		}
		
		return t;
	}
	
	public Person getPerson(int personid) {
		return getPerson(personid, new String[]{});
	}
	
	public Person getPerson(int personid, String... embed) {
		Person person = null;
		
		WebTarget target = this.target
				.path("people")
				.path(Integer.toString(personid));
		
		for(String s : embed) {
			if (!Embeddable.isEmbeddable(Person.class, s))
				throw new NotSupportedException("\"" + s + "\" is not embeddable");
			target = target.queryParam("embed[]", s);
		}
			
		Response response = target	
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			person = response.readEntity(Person.class);
		}
		
		return person;
	}
	
	public Person getPerson(Person person) {
		if (person == null)
			throw new NullPointerException("Person cannot be null");
		
		return getPerson(person.getId(), new String[]{});
	}
	
	public Person getPerson(Person person, String... embed) {
		if (person == null)
			throw new NullPointerException("Person cannot be null");
		
		return getPerson(person.getId(), embed);
	}
	
	public List<CastCredit> getPersonCastCredits(int personid) {
		return getPersonCastCredits(personid, new String[]{});
	}
	
	public List<CastCredit> getPersonCastCredits(int personid, String... embed) {
		List<CastCredit> castCredits = null;
		
		WebTarget target = this.target
				.path("people")
				.path(Integer.toString(personid))
				.path("castcredits");
		
		for(String s : embed) {
			if (!Embeddable.isEmbeddable(CastCredit.class, s))
				throw new NotSupportedException("\"" + s + "\" is not embeddable");
				target = target.queryParam("embed[]", s);
		}
		
		Response response = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			castCredits = response.readEntity(new GenericType<List<CastCredit>>(){});
		}
		
		return castCredits;
	}
	
	public List<CastCredit> getPersonCastCredits(Person person) {
		return getPersonCastCredits(person, new String[]{});
	}
	
	public List<CastCredit> getPersonCastCredits(Person person, String... embed) {
		if (person == null)
			throw new NullPointerException("Person cannot be null");
		
		return getPersonCastCredits(person.getId(), embed);
	}
	
	public List<CrewCredit> getPersonCrewCredits(int personid) {
		return getPersonCrewCredits(personid, new String[]{});
	}
	
	private List<CrewCredit> getPersonCrewCredits(int personid, String... embed) {
		List<CrewCredit> crewCredits = null;
		
		WebTarget target = this.target
				.path("people")
				.path(Integer.toString(personid))
				.path("crewcredits");
		
		for(String s : embed) {
			if (!Embeddable.isEmbeddable(CastCredit.class, s))
				throw new NotSupportedException("\"" + s + "\" is not embeddable");
			target = target.queryParam("embed[]", s);
		}
		
		Response response = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			crewCredits = response.readEntity(new GenericType<List<CrewCredit>>(){});
		}
		
		return crewCredits;
	}
	
	public List<CrewCredit> getPersonCrewCredits(Person person) {
		return getPersonCrewCredits(person, new String[]{});
	}
	
	public List<CrewCredit> getPersonCrewCredits(Person person, String... embed) {
		if (person == null)
			throw new NullPointerException("Person cannot be null");
		
		return getPersonCrewCredits(person.getId(), embed);
	}
	
	public Map<Episode,Show> getSchedule() {
		return getSchedule(null, null);
	}
	
	public Map<Episode,Show> getSchedule(LocalDate date) {
		return getSchedule(null, date);
	}
	
	public Map<Episode,Show> getSchedule(String countryCode) {
		return getSchedule(countryCode, null);
	}
	
	public Map<Episode,Show> getSchedule(String countryCode, LocalDate date) {
		LinkedHashMap<Episode,Show> schedule = new LinkedHashMap<Episode,Show>();
		
		WebTarget target = this.target.path("schedule");
		
		if (countryCode != null && !countryCode.isEmpty())
			target = target.queryParam("country", countryCode);
		
		if (date != null)
			target = target.queryParam("date", date.toString());
		
		Response response = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			List<ScheduledEpisode> episodes = response.readEntity(new GenericType<List<ScheduledEpisode>>(){});
			for(ScheduledEpisode e : episodes) {
				e.setShowId(e.getShow().getId());
				schedule.put(e, e.getShow());
			}
		}
		
		return schedule;
	}
	
	public Map<Episode,Show> getScheduleFull() {
		LinkedHashMap<Episode,Show> schedule = new LinkedHashMap<Episode,Show>();
		
		Response response = target
				.path("schedule")
				.path("full")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200 || response.getStatus() == 304) {
			List<ScheduledEpisode> episodes = response.readEntity(new GenericType<List<ScheduledEpisode>>(){});
			for(ScheduledEpisode e : episodes) {
				e.setShowId(e.getShow().getId());
				schedule.put(e, e.getShow());
			}
		}
		
		return schedule;
	}
	
	public Show getShow(int showId) {
		return getShow(showId, new String[]{});
	}
	
	public Show getShow(int showId, String... embed) {
		Show show = null;
		
		WebTarget target = this.target
				.path("shows")
				.path(Integer.toString(showId));
		
		for(String s : embed) {
			if (!Embeddable.isEmbeddable(Show.class, s))
				throw new NotSupportedException("\"" + s + "\" is not embeddable");
			target = target.queryParam("embed[]", s);
		}
		
		Response response = target
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			show = response.readEntity(Show.class);
			setEpisodeShowId(show);
		}
		
		return show;
	}
	
	public Show getShow(Show show) {
		return getShow(show, new String[]{});
	}
	
	public Show getShow(Show show, String... embed) {
		return getShow(show.getId());
	}
	
	public List<AKA> getShowAKAs(Show show) {
		return getShowAKAs(show.getId());
	}
	
	public List<AKA> getShowAKAs(int showid) {
		List<AKA> akas = null;
		
		Response response = target
				.path("shows")
				.path(Integer.toString(showid))
				.path("akas")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			akas = response.readEntity(new GenericType<List<AKA>>(){});
			if (akas != null)
				for(AKA a : akas)
					a.setShowId(showid);
		}
		
		return akas;
	}

	private Show getShowByOtherId(String source, int id) {
		Show show = null;
		
		Response response = target
				.path("lookup")
				.path("shows")
				.queryParam(source, id)
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			show = response.readEntity(Show.class);
		}
		
		return show;
	}
	
	public Show getShowByTheTVDB(int tvdbid) {
		return getShowByOtherId("thetvdb", tvdbid);
	}
	
	public Show getShowByTVRage(int tvrageid) {
		return getShowByOtherId("tvrage", tvrageid);
	}
	
	public List<Show> getShowIndex(int page) {
		List<Show> shows = null;
		
		Response response = target
				.path("shows")
				.queryParam("page", page)
        		.request(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON)
        		.get(Response.class);
		
		if (response.getStatus() == 200) {
			shows = response.readEntity(new GenericType<List<Show>>(){});
		}
		
		return shows;
	}
	
	public Map<Integer,Long> getShowUpdates() {
		Map<Integer,Long> updates = null;
		
		Response response = target
				.path("updates")
				.path("shows")
				.request(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.get(Response.class);
		
		if (response.getStatus() == 200) {
			String s = response.readEntity(String.class);
			
			// Jackson ObjectMapper used here as MOXy was erroneously throwing an error when parsing returned JSON
			ObjectMapper mapper = new ObjectMapper();
			try {
				updates = mapper.readValue(s, new TypeReference<Map<Integer,Long>>(){});
			} catch (JsonParseException e) {
			} catch (JsonMappingException e) {
			} catch (IOException e) {
			}
		}
		
		return updates;
	}
	
	public List<Result<Person>> searchPeople(String searchString) {
		List<Result<Person>> people = null;
		Response response = target
				.path("search")
				.path("people")
				.queryParam("q", searchString)
        		.request(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON)
        		.get(Response.class);
		
		if (response.getStatus() == 200) {
			people = response.readEntity(new GenericType<List<Result<Person>>>(){});
		}
		
		return people;
	}
	
	public List<Result<Show>> searchShows(String searchString) {
		List<Result<Show>> results = null;
		
		Response response = target
        		.path("search")
        		.path("shows")
        		.queryParam("q", searchString)
        		.request(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON)
        		.get(Response.class);
		
		if (response.getStatus() == 200) {
			results = response.readEntity(new GenericType<List<Result<Show>>>(){});
		}
		
        return results;
	}
	
	public Show searchSingleShow(String searchString) {
		return searchSingleShow(searchString, new String[]{});
	}
	
	public Show searchSingleShow(String searchString, String... embed) {
		Show show = null;
		
		WebTarget target = this.target
				.path("singlesearch")
        		.path("shows")
        		.queryParam("q", searchString);
		
		for(String s : embed) {
			if (!Embeddable.isEmbeddable(Show.class, s))
				throw new NotSupportedException("\"" + s + "\" is not embeddable");
			target = target.queryParam("embed[]", s);
		}
		
        Response response = target		
        		.request(MediaType.APPLICATION_JSON)
        		.accept(MediaType.APPLICATION_JSON)
        		.get(Response.class);
        
        if (response.getStatus() == 200) {
        	show = response.readEntity(Show.class);
        	setEpisodeShowId(show);
        }
        
        return show;
	}
	
	private void setEpisodeShowId(Show show) {
		if (show == null)
			return;
		
		if (show.getEpisodes() != null)
			for(Episode e : show.getEpisodes())
				e.setShowId(show.getId());
		
		if (show.getPreviousepisode() != null)
			show.getPreviousepisode().setShowId(show.getId());
		
		if (show.getNextepisode() != null)
			show.getNextepisode().setShowId(show.getId());
	}
}
