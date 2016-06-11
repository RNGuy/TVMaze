package tvmaze.model.show;

import java.io.Serializable;

public class Country implements Comparable<Country>, Serializable {
	private static final long serialVersionUID = 2432747970593288288L;
	private String code;
	private String name;
	private String timezone;

	public int compareTo(Country arg0) {
		return name.replaceAll("(?i)^the\\s", "").compareTo(arg0.name.replaceAll("(?i)^the\\s", ""));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getTimezone() {
		return timezone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
}
