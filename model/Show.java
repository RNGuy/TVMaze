package tvmaze.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;

import tvmaze.model.show.Network;
import tvmaze.model.show.Schedule;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Show implements Comparable<Show>, Serializable {
	private static final long serialVersionUID = 6857464574895812956L;
	protected Map<String, String> _links;
	@XmlElement(name = "_embedded/cast")
	protected List<CastMember> cast;
	@XmlElement(name = "_embedded/episodes")
	protected List<Episode> episodes;
	protected Map<String, String> externals;
	protected List<String> genres;
	protected int id;
	protected Map<String, String> image;
	protected String language;
	protected String name;
	protected Network network;
	@XmlElement(name = "_embedded/nextepisode")
	protected Episode nextepisode;
	protected LocalDate premiered;
	@XmlElement(name = "_embedded/previousepisode")
	protected Episode previousepisode;
	@XmlElement(name = "rating/average")
	protected Double rating;
	protected Integer runtime;
	protected Schedule schedule;
	protected String status;
	protected String summary;
	protected String type;
	protected Integer updated;
	protected String url;
	protected String webChannel;

	public int compareTo(Show other) {
		return name.replaceAll("(?i)^the\\s", "").compareTo(
				other.name.replaceAll("(?i)^the\\s", ""));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Show other = (Show) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Map<String, String> get_links() {
		return _links;
	}

	public List<CastMember> getCast() {
		return cast;
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public Map<String, String> getExternals() {
		return externals;
	}

	public List<String> getGenres() {
		return genres;
	}

	public int getId() {
		return id;
	}

	public Map<String, String> getImage() {
		return image;
	}

	public String getLanguage() {
		return language;
	}

	public String getName() {
		return name;
	}

	public Network getNetwork() {
		return network;
	}

	public Episode getNextepisode() {
		return nextepisode;
	}

	public LocalDate getPremiered() {
		return premiered;
	}

	public Episode getPreviousepisode() {
		return previousepisode;
	}

	public Double getRating() {
		return rating;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public String getStatus() {
		return status;
	}

	public String getSummary() {
		return summary;
	}

	public String getSummaryText() {
		return summary == null ? null : summary.replaceAll("<.*?>", "");
	}

	public String getType() {
		return type;
	}

	public Integer getUpdated() {
		return updated;
	}

	public String getUrl() {
		return url;
	}

	public String getWebChannel() {
		return webChannel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void set_links(Map<String, String> _links) {
		this._links = _links;
	}

	public void setCast(List<CastMember> cast) {
		this.cast = cast;
	}

	public void setEpisodes(List<Episode> episodes) {
		this.episodes = episodes;
	}

	public void setExternals(Map<String, String> externals) {
		this.externals = externals;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(Map<String, String> image) {
		this.image = image;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public void setNextepisode(Episode nextepisode) {
		this.nextepisode = nextepisode;
	}

	public void setPremiered(LocalDate premiered) {
		this.premiered = premiered;
	}

	public void setPreviousepisode(Episode previousepisode) {
		if (previousepisode != null)
			previousepisode.setShowId(id);
		this.previousepisode = previousepisode;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdated(Integer updated) {
		this.updated = updated;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setWebChannel(String webChannel) {
		this.webChannel = webChannel;
	}
}
