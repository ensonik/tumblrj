package org.mikem.tumblr.api.model;

import java.util.List;

import org.dom4j.Element;
import org.dom4j.Node;
import org.mikem.tumblr.api.util.XmlUtil;

public class ConversationPost extends TumblePost {
	private String title;
	private String text;
	private String[] lines;
	
	@SuppressWarnings("unchecked")
	public ConversationPost(Element node) throws Exception {
		super(node);
		this.title = XmlUtil.getXPathValue(node, "conversation-title");
		this.text = XmlUtil.getXPathValue(node, "conversation-text");
		
		List<Node> lineNodes = (List<Node>) node.selectNodes("conversation/line");
		lines = new String[lineNodes.size()];
		int counter = 0;
		for (Node lineNode : lineNodes) {
			lines[counter++] = lineNode.getText();
		}
	}
	
	public ConversationPost(String id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public String[] getLines() {
		return lines;
	}
	
	
}
