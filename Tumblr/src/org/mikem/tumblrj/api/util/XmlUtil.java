package org.mikem.tumblrj.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Element;
import org.dom4j.Node;

/**
 * XML utility class.
 * 
 * @author Mike
 *
 */
public class XmlUtil {
	// 2008-12-21 00:07:32 GMT
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss zzz");

	
	public static Date getXPathValueAsDate(Element element, String xpath) throws ParseException {
		String value = XmlUtil.getXPathValue(element, xpath);
		if (value == null) {
			return null;
		}
		
		return DATE_FORMAT.parse(value);
	}
	
	public static String getXPathValue(Element document, String xpath) {
		Node node = document.selectSingleNode(xpath);
		if (node != null) {
			return node.getText();
		} 
		return null;
	}
	
	public static Long getXPathValueAsLong(Element document, String xpath, long defaultValue) {
		String value = XmlUtil.getXPathValue(document, xpath);
		if (value != null) {
			return Long.valueOf(value);
		}
		return Long.valueOf(defaultValue);
	}
	
	public static Integer getXPathValueAsInteger(Element document, String xpath) {
		String value = XmlUtil.getXPathValue(document, xpath);
		if (value != null) {
			return Integer.valueOf(value).intValue();
		}
		return null;
	}
	
	public static Boolean getXPathValueAsBoolean(Element document, String xpath) {
		String value = XmlUtil.getXPathValue(document, xpath);
		if (value != null) {
			if (value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("yes")) {
				return Boolean.TRUE;	
			}
		}
		
		return Boolean.FALSE;
	}

}
