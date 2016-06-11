package tvmaze.model;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person implements Comparable<Person>, Serializable {
	private static final long serialVersionUID = -4824976881756247662L;
	private Map<String, String> _links;
	private int id;
	private Map<String, String> image;
	private String name;
	private String url;

	public int compareTo(Person other) {
		return this.name.compareTo(other.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
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
