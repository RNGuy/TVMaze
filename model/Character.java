package tvmaze.model;

import java.io.Serializable;
import java.util.Map;

public class Character implements Comparable<Character>, Serializable {
	private static final long serialVersionUID = -8959789685875896323L;
	protected Map<String, String> _links;
	protected int id;
	protected Map<String, String> image;
	protected String name;
	protected String url;

	public int compareTo(Character o) {
		return name.compareTo(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Map<String, String> get_links() {
		return _links;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setImage(Map<String, String> image) {
		this.image = image;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
