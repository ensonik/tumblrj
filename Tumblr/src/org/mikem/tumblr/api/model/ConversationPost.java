package org.mikem.tumblr.api.model;

import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
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

	@Override
	protected void doSetupPostParams(PostMethod post) {
		if (this.title != null) {
			post.addParameter("title", this.getTitle());	
		}
		post.addParameter("conversation", this.getText());
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getLines() {
		return lines.clone();
	}

	public void setLines(String[] lines) {
		this.lines = lines.clone();
	}
	
}
