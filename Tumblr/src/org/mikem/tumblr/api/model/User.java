package org.mikem.tumblr.api.model;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.mikem.tumblr.api.util.XmlUtil;

public class User {
	private boolean canUploadAudio;
	private boolean canUploadAiff;
	private boolean canUploadVideo;
	private long maxVideoBytesUploaded;
	private List<UserTumblelog> userTumblelogs = new ArrayList<UserTumblelog>();
	
	@SuppressWarnings("unchecked")
	public User(Document document) throws Exception {
		Element root = document.getRootElement();
		this.canUploadVideo = XmlUtil.getXPathValueAsBoolean(root, "@can-upload-audio");
		this.canUploadAiff = XmlUtil.getXPathValueAsBoolean(root, "@can-upload-aiff");
		this.canUploadVideo = XmlUtil.getXPathValueAsBoolean(root, "@can-upload-video");
		this.maxVideoBytesUploaded = XmlUtil.getXPathValueAsLong(root, "@max-video-bytes-uploaded");
		
		List<Node> userLogNodes = document.selectNodes("//tumblr/tumblelog");
		for (Node userLogNode : userLogNodes) {
			userTumblelogs.add(new UserTumblelog((Element) userLogNode));
		}
	}
	
	
	public List<UserTumblelog> getUserTumblelogs() {
		return userTumblelogs;
	}
	public boolean isCanUploadAudio() {
		return canUploadAudio;
	}
	public boolean isCanUploadAiff() {
		return canUploadAiff;
	}
	public boolean isCanUploadVideo() {
		return canUploadVideo;
	}
	public long getMaxVideoBytesUploaded() {
		return maxVideoBytesUploaded;
	}
}
