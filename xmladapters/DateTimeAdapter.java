package tvmaze.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;

public class DateTimeAdapter extends XmlAdapter<String, DateTime> {

	@Override
	public String marshal(DateTime arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return arg0.toString();
	}

	@Override
	public DateTime unmarshal(String arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return new DateTime(arg0);
	}
}
