package edu.upc.eetac.dsa.jmale.llibres.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.upc.eetac.dsa.jmale.llibres.api.links.Link;


public class Resena {
	private String Resenaid;
	private String username;
	private int llibreid;
	private String realname;
	private String content;
	private Date lastModified;
	private List<Link> links = new ArrayList<Link>();

	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getResenaid() {
		return Resenaid;
	}

	public void setResenaid(String id) {
		this.Resenaid = id;
	}

	public int getLlibreid() {
		return llibreid;
	}

	public void setLlibreid(int llibreid) {
		this.llibreid = llibreid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date creationTimestamp) {
		this.lastModified = creationTimestamp;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLink(Link link) {
		links.add(link);
	}

}
