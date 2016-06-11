package tvmaze.model;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class CrewCredit implements Serializable {
	private static final long serialVersionUID = -8281965147596684468L;
	private Map<String, String> _links;
	@XmlElement(name = "_embedded/show")
	private Show show;
	private String type;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrewCredit other = (CrewCredit) obj;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public Map<String, String> get_links() {
		return _links;
	}

	public Show getShow() {
		return show;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	public void set_links(Map<String, String> _links) {
		this._links = _links;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public void setType(String type) {
		this.type = type;
	}
}
