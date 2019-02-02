package br.com.amigopet.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//http://localhost:8080/AmigoPet/rest/[amigopet]
@Path("amigopet")
public class AmigopetService {
	
	@GET
	public String exibir(){
		
		
		return "Teste Web Service";
	}
}
