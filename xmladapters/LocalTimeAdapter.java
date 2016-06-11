package tvmaze.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalTime;

public class LocalTimeAdapter extends XmlAdapter<String, LocalTime> {

	@Override
	public String marshal(LocalTime arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return arg0.toString();
	}

	@Override
	public LocalTime unmarshal(String arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return new LocalTime(arg0);
	}
}
