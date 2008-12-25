package org.mikem.tumblr.api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.dom4j.Node;
import org.mikem.tumblr.api.util.XmlUtil;

public class PhotoPost extends TumblePost {
	private String caption;
	private Map<Integer, Photo> photos = new HashMap<Integer, Photo>();
	
	@SuppressWarnings("unchecked")
	public PhotoPost(Element node) throws Exception {
		super(node);
		this.caption = XmlUtil.getXPathValue(node, "photo-caption");
		
		List<Node> photoUrlNodes = (List<Node>) node.selectNodes("photo-url");
		for (Node photoNode : photoUrlNodes) {
			String url = photoNode.getText();
			Integer maxWidth = XmlUtil.getXPathValueAsInteger((Element) photoNode, "@max-width");
			photos.put(maxWidth, new Photo(url));
		}
	}
	
	public PhotoPost(String id) {
		super(id);
	}
	
	public String getCaption() {
		return caption;
	}

	public Map<Integer, Photo> getPhotos() {
		return photos;
	}
	
}
