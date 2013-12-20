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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import edu.upc.eetac.dsa.jmale.llibres.api.model.Llibre;
import edu.upc.eetac.dsa.jmale.llibres.api.model.LlibreCollection;
import edu.upc.eetac.dsa.jmale.llibres.api.model.Resena;



@Path("/{llibreid}/resena")
public class ResenaResource {
	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	@Context
	private UriInfo uriInfo;

	
	@POST
	@Consumes(MediaType.LLIBRES_API_RESENA)
	@Produces(MediaType.LLIBRES_API_RESENA)
	public Resena createReseña(Resena resena) {
	
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServiceUnavailableException(e.getMessage());
		}
		try {
			Statement stmt = conn.createStatement();
			 String sql = "select * from resenas where username='"
                     + resena.getUsername() + "' and llibreid='"
                     + resena.getLlibreid() + "';";
			 ResultSet rs = stmt.executeQuery(sql);
			 
			  if (rs.next())
                  throw new ResenaNotFoundException();

          sql = "insert into resenas (llibreid, username, content) ";
          sql += "values (" + resena.getLlibreid() + ", '"
                          + resena.getUsername() + "', '" + resena.getContent()
                          + "');";

          	stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
          	rs = stmt.getGeneratedKeys();
          	
          	
			
			if (rs.next()) {
				 String id = rs.getString(1);
                 rs.close();
                 
                 sql = "select users.name, resenas.* from users,resenas where resenas.resenaid="
                                 + id + " and users.username=resenas.username";

                 rs = stmt.executeQuery(sql);
                 rs.next();
                 resena.setResenaid(id);
                 resena.setLlibreid(rs.getInt("llibreid"));
                 resena.setContent(rs.getString("content"));
                 resena.setUsername(rs.getString("username"));
                 resena.setLastModified(rs.getDate("lastModified"));

                
                
			} else {
				throw new ResenaNotFoundException();
			}
		    rs.close();
		} catch (SQLException e) {
			throw new InternalServerException(e.getMessage());
		}
		return resena;

	}



	@GET
	@Path("/{resenaid}")
	@Produces(MediaType.LLIBRES_API_RESENA)
	public Response getSting(@PathParam("resenaid") String resenaid , @PathParam("llibreid") String llibreid ,
			@Context Request req) {
		CacheControl cc = new CacheControl();
		Resena reseña = new Resena();

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

			String sql = "select * from resenas where resenaid="
					+ resenaid + " and llibreid="+llibreid+ ";";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				reseña.setResenaid(rs.getString("resenaid"));
				reseña.setLlibreid(rs.getInt("llibreid"));
				reseña.setUsername(rs.getString("username"));
				reseña.setRealname(rs.getString("realname"));
				reseña.setLastModified(rs.getTimestamp("lastModified"));
				reseña.setContent(rs.getString("content"));
			} else {
				throw new ResenaNotFoundException();
			}
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
		EntityTag eTag = new EntityTag(Integer.toString(reseña.getLastModified()
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
		rb = Response.ok(reseña).cacheControl(cc).tag(eTag);

		return rb.build();
	}

	@DELETE
	@Path("/{Resenaid}")
	public void deleteSting(@PathParam("Resenaid") String resenaid) {
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
			sql = "delete from resenas where resenaid=" + resenaid;
			// Aqui para dar el ok que se a completado no se si hacerlo con
			// resulset porque da error, pero con int parece que funciona
			// int rs2 = stmt.executeUpdate(sql);
			// rs2.close(); CAL????
			int rs2 = stmt.executeUpdate(sql);
			if (rs2 == 0)
				throw new ResenaNotFoundException();

		} catch (SQLException e) {
			throw new InternalServerException(e.getMessage());
		}
		// return?? No confirmamos que ok o que error??????
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

	@Context
	private SecurityContext security;

	@PUT
	@Path("/{resenaid}")
	@Consumes(MediaType.LLIBRES_API_RESENA)
	@Produces(MediaType.LLIBRES_API_RESENA)
	public Resena updateSting(@PathParam("resenaid") String resenaid, Resena resena) {

		
		/*if (security.isUserInRole("registered")) {
			if (!security.getUserPrincipal().getName()
					.equals(sting.getUsername()))
				;
			throw new ForbiddenException("you are not allowed...");
		} else {
			
		}*/
		
     
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServiceUnavailableException(e.getMessage());
		}

		try {
			Statement stmt = conn.createStatement();
            String sql;

			
      //      sql = "update  resenas SET resenas.content='" + resena.getContent()
        //            + "' where resenaid=" + resenaid;
        	sql = "update resenas set resenas.content='"
					+ resena.getContent() +"' where resena.resenaid='" + resenaid;
			
            sql = "select * from resenas where resenaid=" + resenaid;

			ResultSet rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				

				
				resena.setResenaid(rs.getString("resenaid"));
				resena.setLlibreid(rs.getInt("llibreid"));
				resena.setUsername(rs.getString("username"));
				resena.setRealname(rs.getString("realname"));
				resena.setLastModified(rs.getTimestamp("lastModified"));
				resena.setContent(rs.getString("content"));

			} else {
			}
			rs.close();
			stmt.close();
			conn.close();
		
		} catch (SQLException e) {
			throw new InternalServerException(e.getMessage());
		}
		
		return resena;
	}
}