package main.swamy.webesrvices.jaxrs.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	
	//this method called when xml and html not requested
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHelloPlain(){
		return "Plain hello";
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayHellowXml(){
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";  
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHtml(){
		  return "<html> " + "<title>" + "Hello Jersey" + "</title>"  
			        + "<body><h1>" + "Hello Jersey HTML done" + "</h1></body>" + "</html> ";  
	}
}
