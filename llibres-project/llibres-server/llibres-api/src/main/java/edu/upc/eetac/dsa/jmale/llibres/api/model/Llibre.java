package edu.upc.eetac.dsa.jmale.llibres.api.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import edu.upc.eetac.dsa.jmale.llibres.api.links.Link;

public class Llibre {

    private int llibreid;
    private String titulo;
    private String autor;
    private String lengua;
    private String edicion;
    private String fecha_edicion;
    private String fecha_impresion;
    private String editorial;
    private Timestamp lastModified;
    private List<Link> links = new ArrayList<Link>();
    
    
	public int getLlibreid() {
		return llibreid;
	}
	public void setLlibreid(int llibreid) {
		this.llibreid = llibreid;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getLengua() {
		return lengua;
	}
	public void setLengua(String lengua) {
		this.lengua = lengua;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	
	public String getFecha_edicion() {
		return fecha_edicion;
	}
	public void setFecha_edicion(String fecha_edicion) {
		this.fecha_edicion = fecha_edicion;
	}
	public String getFecha_impresion() {
		return fecha_impresion;
	}
	public void setFecha_impresion(String fecha_impresion) {
		this.fecha_impresion = fecha_impresion;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public Timestamp getLastModified() {
		return lastModified;
	}
	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public void addLink(Link buildURIStingId) {
		// TODO Auto-generated method stub
		
	}
	
    
    
    
	
	
}
