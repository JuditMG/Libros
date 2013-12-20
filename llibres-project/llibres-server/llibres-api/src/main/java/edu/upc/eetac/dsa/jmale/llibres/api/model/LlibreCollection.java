package edu.upc.eetac.dsa.jmale.llibres.api.model;

import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.jmale.llibres.api.links.Link;


public class LlibreCollection {
	 private List<Llibre> libros = new ArrayList<Llibre>();
     private List<Link> links = new ArrayList<Link>();
     
     
     
	public boolean add(Llibre llibre) {
		return libros.add(llibre);
	}
	public boolean add(Link link) {
		return links.add(link);
	}
	
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public List<Llibre> getLibros() {
		return libros;
	}
	public void setLibros(List<Llibre> libros) {
		this.libros = libros;
	}
	public List<Link> getLinks() {
		return links;
	}


}
