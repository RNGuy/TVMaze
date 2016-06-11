@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalTime.class, value=LocalTimeAdapter.class)
})

package tvmaze.model.show;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;

import tvmaze.xmladapters.LocalTimeAdapter;

import org.joda.time.LocalTime;
