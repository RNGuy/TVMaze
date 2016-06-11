package tvmaze.model;

import java.io.Serializable;
import java.util.Map;

import org.joda.time.DateTime;

public class Episode implements Comparable<Episode>, Serializable {
	private static final long serialVersionUID = -7040982349995818492L;
	private Map<String, String> _links;

	private DateTime airstamp;
	private int id;
	private Map<String, String> image;
	private String name;
	private Integer number;
	private Integer runtime;
	private Integer season;
	private int showId;
	private String url;

	public int compareTo(Episode other) {
		if (showId == other.showId) {
			if (season != null && number != null && other.season != null && other.number != null) {
				if (season == other.season)
					return number.compareTo(other.number);
				else
					return season.compareTo(other.season);
			}
		}
		if (airstamp != null && other.airstamp != null)
			return airstamp.compareTo(other.airstamp);

		return Integer.compare(id, other.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Episode other = (Episode) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Map<String, String> get_links() {
		return _links;
	}

	public DateTime getAirstamp() {
		return airstamp;
	}
	
	public int getId() {
		return id;
	}

	public Map<String, String> getImage() {
		return image;
	}

	public String getName() {
		return name;
	}

	public Integer getNumber() {
		return number;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public Integer getSeason() {
		return season;
	}
	
	public int getShowId() {
		return showId;
	}

	public String getUrl() {
		return url;
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

	public void setAirstamp(DateTime airstamp) {
		this.airstamp = airstamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(Map<String, String> image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
