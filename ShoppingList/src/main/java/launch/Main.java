package launch;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

	public static void main(String[] args) throws Exception {

		// Web-sovelluksen julkisten tiedostojen sijainti:
		String webappDirPath = new File("src/main/webapp/").getAbsolutePath();

		// Tomcat-palvelin, joka huolehtii HTTP-liikenteestä:
		Tomcat tomcat = new Tomcat();

		// Asetetaan Tomcatin HTTP-portti.
		String httpPort = System.getenv().getOrDefault("PORT", "8080");
		tomcat.setPort(Integer.valueOf(httpPort));

		// Luodaan Connector-olio, joka kuuntelee asettamaamme porttia:
		tomcat.getConnector();

		// Lisätään oma sovelluksemme Tomcatiin palvelimen juureen:
		Context webApp = tomcat.addWebapp("/", webappDirPath);

		// Palvelin käynnistää itsensä uudelleen muutettuasi
		// tiedostoja
		webApp.setReloadable(true);

		// Määritellään sovelluksemme resurssien sijainnit:
		WebResourceRoot resources = new StandardRoot(webApp);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
		webApp.setResources(resources);

		// Asetetaan UTF-8 -merkistö HTTP-pyyntöihin ja -vastauksiin:
		webApp.setRequestCharacterEncoding("utf-8");
		webApp.setResponseCharacterEncoding("utf-8");

		// Käynnistetään palvelin ja odotetaan yhteyksiä:
		tomcat.start();
		tomcat.getServer().await();
	}
}