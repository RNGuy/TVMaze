package tvmaze.model;

import java.io.Serializable;

import tvmaze.model.show.Country;

public class AKA implements Serializable {
	private static final long serialVersionUID = 7641994317136220622L;
	private Country country;
	private String name;
	private int showId;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AKA other = (AKA) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (showId != other.showId)
			return false;
		return true;
	}

	public Country getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	public int getShowId() {
		return showId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + showId;
		return result;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}
}
