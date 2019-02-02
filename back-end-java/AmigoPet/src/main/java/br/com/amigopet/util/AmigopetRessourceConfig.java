package br.com.amigopet.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class AmigopetRessourceConfig extends ResourceConfig{
	public AmigopetRessourceConfig(){
		packages("br.com.amigopet.service");
	}
}
