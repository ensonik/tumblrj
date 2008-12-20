package org.mikem.tumblr.api.util;

import org.dom4j.Element;
import org.dom4j.Node;

public class XmlUtil {
	
	public static String getXPathValue(Element document, String xpath) {
		Node node = document.selectSingleNode(xpath);
		if (node != null) {
			return node.getText();
		} 
		return null;
	}
	
	public static Integer getXPathValueAsInteger(Element document, String xpath) {
		String value = XmlUtil.getXPathValue(document, xpath);
		if (value != null) {
			return Integer.valueOf(value).intValue();
		}
		return null;
	}

}
