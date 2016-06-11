package tvmaze.xmladapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public String marshal(LocalDate arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return arg0.toString();
	}

	@Override
	public LocalDate unmarshal(String arg0) throws Exception {
		if (null == arg0)
			return null;
		
		return new LocalDate(arg0);
	}
}
