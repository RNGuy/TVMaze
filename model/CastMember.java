package tvmaze.model;

import java.io.Serializable;

public class CastMember implements Comparable<CastMember>, Serializable {
	private static final long serialVersionUID = -8285061907261377499L;
	protected Character character;
	protected Person person;

	public int compareTo(CastMember o) {
		if (person != null && o.person != null && person.getName() != null
				&& o.person.getName() != null)
			return person.getName().compareTo(o.person.getName());
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CastMember other = (CastMember) obj;
		if (character == null) {
			if (other.character != null)
				return false;
		} else if (!character.equals(other.character))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		return true;
	}

	public Character getCharacter() {
		return character;
	}

	public Person getPerson() {
		return person;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((character == null) ? 0 : character.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		return result;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
