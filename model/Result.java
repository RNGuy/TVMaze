package tvmaze.model;

import javax.xml.bind.annotation.XmlAnyElement;

public class Result<T> {
	private Double score;
	@XmlAnyElement(lax=true)
	private T item;

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
