@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDate.class, value=LocalDateAdapter.class),
    @XmlJavaTypeAdapter(type=Map.class, value=MapAdapter.class),
    @XmlJavaTypeAdapter(type=DateTime.class, value=DateTimeAdapter.class)
})

package tvmaze.model;

import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import tvmaze.xmladapters.DateTimeAdapter;
import tvmaze.xmladapters.LocalDateAdapter;
import tvmaze.xmladapters.MapAdapter;

