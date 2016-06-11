package tvmaze.model;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

public class CastCredit implements Serializable {
	private static final long serialVersionUID = -5027308112140220991L;
	private Map<String, String> _links;
	@XmlElement(name = "_embedded/character")
	private Character character;
	@XmlElement(name = "_embedded/show")
	private Show show;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CastCredit other = (CastCredit) obj;
		if (_links == null) {
			if (other._links != null)
				return false;
		} else if (!_links.equals(other._links))
			return false;
		return true;
	}

	public Map<String, String> get_links() {
		return _links;
	}

	public Character getCharacter() {
		return character;
	}

	public Show getShow() {
		return show;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_links == null) ? 0 : _links.hashCode());
		return result;
	}

	public void set_links(Map<String, String> _links) {
		this._links = _links;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public void setShow(Show show) {
		this.show = show;
	}
}
