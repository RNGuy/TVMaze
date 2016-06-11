package tvmaze.model.show;

import java.io.Serializable;
import java.util.Arrays;

import org.joda.time.LocalTime;

public class Schedule implements Serializable {
	private static final long serialVersionUID = 5725707889032098739L;
	private String[] days;
	private LocalTime time;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (!Arrays.equals(days, other.days))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		return true;
	}

	public String[] getDays() {
		return days;
	}

	public LocalTime getTime() {
		return time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(days);
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	public void setDays(String[] days) {
		this.days = days;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}
