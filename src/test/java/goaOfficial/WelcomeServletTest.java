package goaOfficial;

import static org.junit.Assert.*;

import org.eclipse.jetty.testing.HttpTester;
import org.eclipse.jetty.testing.ServletTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WelcomeServletTest {
	
	private static ServletTester tester; 
	
	@BeforeClass
	
	public static void init(){
		 tester =  new ServletTester();
	}
	
	@Before
	public void setUp() throws Exception{
		
		tester.addServlet(Welcome.class, "/");
		tester.start();
		
	}

	@Test
	public void testDoGet() throws Exception{
		
		HttpTester req = new HttpTester();
		req.setMethod("GET");
		req.setURI("/");
		req.setVersion("HTTP/1.0");
		
		HttpTester resp = new HttpTester();
		resp.parse(tester.getResponses(req.generate()));
		assertEquals(200, resp.getStatus());
		assertEquals("Welcome", resp.getContent());
		
		
	}
	
	@Test
	public void testDoPost() throws Exception {
		
		
		
		HttpTester req = new HttpTester();
		req.setMethod("POST");
		req.setURI("/");
		req.setVersion("HTTP/1.0");
		
		HttpTester resp = new HttpTester();
		resp.parse(tester.getResponses(req.generate()));
		assertEquals(200, resp.getStatus());
		assertEquals(null, resp.getContent());
		
		
	}
	
	}
