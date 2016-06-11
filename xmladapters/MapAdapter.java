package tvmaze.xmladapters;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MapAdapter extends XmlAdapter<Element, Map<String, String>> {

	@Override
	public Element marshal(Map<String, String> arg0) throws Exception {
		throw new UnsupportedOperationException("Not Implemented");
	}

	@Override
	public Map<String, String> unmarshal(Element rootElement) throws Exception {
		NodeList nodeList = rootElement.getChildNodes();
        Map<String,String> map = new HashMap<String, String>(nodeList.getLength());
        for(int x=0; x<nodeList.getLength(); x++) {
            Node node = nodeList.item(x);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                map.put(node.getNodeName(), node.getTextContent());
            }
        }
        return map;
	}
}
