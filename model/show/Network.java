package tvmaze.model.show;

import java.io.Serializable;

public class Network implements Comparable<Network>, Serializable {
	private static final long serialVersionUID = 2676561089026924428L;
	private Country country;
	private int id;
	private String name;

	public int compareTo(Network o) {
		return name.replaceAll("(?i)^the\\s.*", "").compareTo(
				o.name.replaceAll("(?i)^the\\s.*", ""));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Network other = (Network) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Country getCountry() {
		return country;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
}
