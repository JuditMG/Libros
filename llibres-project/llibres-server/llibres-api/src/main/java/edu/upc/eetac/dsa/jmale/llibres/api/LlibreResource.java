package edu.upc.eetac.dsa.jmale.llibres.api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import edu.upc.eetac.dsa.jmale.llibres.api.links.LlibresAPILinkBuilder;
import edu.upc.eetac.dsa.jmale.llibres.api.model.Llibre;
import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibreCollection;


@Path("/llibre")
public class LlibreResource {
	
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	@Context
	private UriInfo uriInfo;

    @Context
    private SecurityContext security;
    
    
    @GET
	@Path("/{llibreid}")
	@Produces(MediaType.LLIBRES_API_LLIBRE)
	public Response getLibre(@PathParam("llibreid") String llibreid,
			@Context Request req) {
		CacheControl cc = new CacheControl();
		Llibre llibre = new Llibre();

		// TODO Retrieve sting identified by sting id from the database and
		// return it.

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServiceUnavailableException(e.getMessage());
		}
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();

			String sql = "select * from llibres where llibreid="
					+ llibreid ;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
                llibre.setLlibreid(rs.getInt("llibreid"));
                llibre.setTitulo(rs.getString("titulo"));
                llibre.setAutor(rs.getString("autor"));
                llibre.setLengua(rs.getString("lengua"));
                llibre.setEdicion(rs.getString("edicion"));
                llibre.setFecha_edicion(rs.getString("fecha_edicion"));
                llibre.setFecha_impresion(rs.getString("fecha_impresion"));
                llibre.setEditorial(rs.getString("editorial"));
                llibre.setLastModified(rs.getTimestamp("lastModified"));
			} else {
				throw new LlibreNotFoundException();
			}
			rs.close();
		} catch (SQLException e) {
			throw new InternalServerException(e.getMessage());
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				throw new InternalServerException(e.getMessage());
			}
		}
		EntityTag eTag = new EntityTag(Integer.toString(llibre.getLastModified()
				.hashCode()));
		// Verify if it matched with etag available in http request
		Response.ResponseBuilder rb = req.evaluatePreconditions(eTag);
		// If ETag matches the rb will be non-null;
		// Use the rb to return the response without any further processing
		if (rb != null) {
			return rb.cacheControl(cc).tag(eTag).build();
		}
		// If rb is null then either it is first time request; or resource is
		// modified
		// Get the updated representation and return with Etag attached to it
		rb = Response.ok(llibre).cacheControl(cc).tag(eTag);

		return rb.build();
	}
	


@DELETE
@Path("/{Llibreid}")
public void deleteSting(@PathParam("Llibreid") String llibreid) {
	// TODO Delete record in database stings identified by stingid.
	Connection conn = null;
	try {
		conn = ds.getConnection();
	} catch (SQLException e) {
		throw new ServiceUnavailableException(e.getMessage());
	}
	Statement stmt = null;
	String sql;
	try {
		stmt = conn.createStatement();
		sql = "delete from llibres where llibreid=" + llibreid;

		int rs2 = stmt.executeUpdate(sql);
		if (rs2 == 0)
			throw new LlibreNotFoundException();

	} catch (SQLException e) {
		throw new InternalServerException(e.getMessage());
	}
	finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


@POST
@Consumes(MediaType.LLIBRES_API_LLIBRE)
@Produces(MediaType.LLIBRES_API_LLIBRE)
public Llibre createLlibre(Llibre llibre) {

        Statement stmt = null;

        
        /*if (!security.isUserInRole("admin")) {

                throw new ForbiddenException("You are nor allowed");

        } */

        Connection conn = null;
        try {
                conn = ds.getConnection();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new ServiceUnavailableException(e.getMessage());
        }

        try {
                stmt = conn.createStatement();
                

                String sql = "insert into llibres (titulo, autor, lengua, edicion, fecha_edicion, fecha_impresion, editorial)";
                sql += "values ('" + llibre.getTitulo() + "', '" + llibre.getAutor()
                                + "', '" + llibre.getLengua() + "','" + llibre.getEdicion()
                                + "','" + llibre.getFecha_edicion() + "','" + llibre.getFecha_impresion() + "','"
                                + llibre.getEditorial() + "');";

                stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                        int id = rs.getInt(1);
                        rs.close();
                        // TODO: Retrieve the created sting from the database to get all
                        // the remaining fields
                        sql = "select lastModified from llibres where llibreid=" + id;

                        rs = stmt.executeQuery(sql);
                        rs.next();
                        llibre.setLlibreid(id);
                        llibre.setLastModified(rs.getTimestamp("lastModified"));
                        
                } else {
                        // TODO: Throw exception, something has failed. Don't do now
                }

                rs.close();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new InternalServerException(e.getMessage());
        } finally {

                try {
                        stmt.close();
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        return llibre;
}


@PUT
@Path("/{llibreid}")
@Consumes(MediaType.LLIBRES_API_LLIBRE)
@Produces(MediaType.LLIBRES_API_LLIBRE)
public Llibre actualitzallibre(@PathParam("llibreid") String llibreid, Llibre llibre) {

        Statement stmt = null;
/*
        if (!security.isUserInRole("admin")) {

                throw new ForbiddenException("You are nor allowed");

        }
*/
        Connection conn = null;
        try {
                conn = ds.getConnection();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new ServiceUnavailableException(e.getMessage());
        }

        try {
                
                stmt = conn.createStatement();
                String sql = "update  llibres SET llibres.titulo='"
                                + llibre.getTitulo() + "',llibres.autor='" + llibre.getAutor()
                                + "', llibres.lengua='" + llibre.getLengua()
                                + "', llibres.edicion='" + llibre.getEdicion()
                                + "', llibres.fecha_edicion='" + llibre.getFecha_edicion()
                                + "', llibres.fecha_impresion='" + llibre.getFecha_impresion()
                                + "', llibres.editorial='" + llibre.getEditorial()
                                + "' where llibreid=" + llibreid;

                int rows = stmt.executeUpdate(sql);

                if (rows == 0)
                        throw new LlibreNotFoundException();

                sql = "select lastModified from llibres where llibreid=" + llibreid;

                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                        llibre.setLastModified(rs.getTimestamp("lastModified"));
                        

                } else {
                        throw new LlibreNotFoundException();
                }
                rs.close();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new InternalServerException(e.getMessage());
        } finally {

                try {
                        stmt.close();
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        return llibre;
}

@GET
@Produces(MediaType.LLIBRES_API_LIBRO_COLLECTION)

public LlibreCollection getLibros() {

        
        
        LlibreCollection llibrescol = new LlibreCollection();

        Statement stmt = null;

        Connection conn = null;
        try {
                conn = ds.getConnection();
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new ServiceUnavailableException(e.getMessage());
        }

        try {
                stmt = conn.createStatement();
                String sql = "select * from llibres ;";
                
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                        
                        Llibre llibre = new Llibre();
                        llibre.setLlibreid(rs.getInt("llibreid"));
                        llibre.setTitulo(rs.getString("titulo"));
                        llibre.setAutor(rs.getString("autor"));
                        llibre.setLengua(rs.getString("lengua"));
                        llibre.setEdicion(rs.getString("edicion"));
                        llibre.setFecha_edicion(rs.getString("fecha_edicion"));
                        llibre.setFecha_impresion(rs.getString("fecha_impresion"));
                        llibre.setEditorial(rs.getString("editorial"));
                        llibre.setLastModified(rs.getTimestamp("lastModified"));

                        llibre.addLink(LlibresAPILinkBuilder.buildURIStingId(uriInfo,
                                        llibre.getLlibreid() - 1, "prev"));
                        llibre.addLink(LlibresAPILinkBuilder.buildURIStingId(uriInfo,
                                        llibre.getLlibreid(), "self"));
                        llibre.addLink(LlibresAPILinkBuilder.buildURIStingId(uriInfo,
                                        llibre.getLlibreid() + 1, "next"));
                        llibrescol.add(llibre);
                }
                rs.close();

        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new InternalServerException(e.getMessage());
        } finally {

                try {
                        stmt.close();
                        conn.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        

        // devolvemos el sting
        return llibrescol;
}

}



