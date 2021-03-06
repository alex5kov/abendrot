package net.aequologica.cloud.jaxrs;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import net.aequologica.cloud.jaxrs.model.Input;
import net.aequologica.cloud.jaxrs.model.Output;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ning.http.client.BodyGenerator;
import com.ning.http.client.Response;
import com.ning.http.client.SimpleAsyncHttpClient;
import com.ning.http.client.generators.ByteArrayBodyGenerator;

public class ResourceInTomcatTestComplex extends ResourceInTomcatTestAbstract {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	{
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
	
	@Test
	public void test() throws IOException, InterruptedException, ExecutionException {
		// cf. https://github.com/AsyncHttpClient/async-http-client
		SimpleAsyncHttpClient asyncHttpClient = new SimpleAsyncHttpClient.Builder()
				.setUrl("http://localhost:" + mTomcat.getConnector().getPort() + "/"+name+"/resources/complex/")
				.addHeader("Accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.build();
		
		Input input = new Input();
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, input);
		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %");
		System.out.println(sw.toString());
		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %");

		BodyGenerator bd = new ByteArrayBodyGenerator(sw.toString().getBytes("UTF8")); 		
		Future<Response> f = asyncHttpClient.post(bd);
		Response r = f.get();

		assertEquals( 200, r.getStatusCode());
		Output output = mapper.readValue(r.getResponseBody(), Output.class); 
		System.out.println(output);
		assertEquals(new Output(), output);
	}

	public static void main(String[] args) throws Exception {
		// cf. https://github.com/AsyncHttpClient/async-http-client
		SimpleAsyncHttpClient asyncHttpClient = new SimpleAsyncHttpClient.Builder()
				.setUrl("http://localhost:9876/abendrot-web/resources/complex")
				.addHeader("Accept", "application/json")
				.addHeader("Content-Type", "application/json")
				.build();
		
		Input input = new Input();
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, input);
		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %");
		System.out.println(sw.toString());
		System.out.println("% % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % % %");

		BodyGenerator bd = new ByteArrayBodyGenerator(sw.toString().getBytes("UTF8")); 		
		Future<Response> f = asyncHttpClient.post(bd);
		Response r = f.get();

		System.out.println(r.getStatusCode());
		if (r.getStatusCode() == 200) {
			Output output = mapper.readValue(r.getResponseBody(), Output.class); 
			System.out.println(output);
		}
	}
	
}
