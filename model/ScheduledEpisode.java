package tvmaze.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

@SuppressWarnings("serial")
public class ScheduledEpisode extends Episode {
	@XmlElements({
		@XmlElement(name="show"),
		@XmlElement(name="_embedded/show")
	})
	private Show show;

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}
}