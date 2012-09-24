package bieberfever.compositeservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author BieberFever
 */
public class RestUtils {

	/**
	 * Do a REST call to a specific URI
	 * @param requestUri The URI to connect to
	 * @param httpMethod The HTTP request type to use (POST, GET etc.)
	 * @param postdata The data to post if the request type is POST
	 * @return The response for the request
	 */
	public static String doRestCall(String requestUri, String httpMethod, String postdata) {

		String response = "";
		try {
			// connect to the web service via HTTP GET
			URL url = new URL(requestUri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			

			connection.setRequestMethod(httpMethod);

			if (httpMethod.equals("POST")) {
				//This connection is used for output
				connection.setDoOutput(true);

				//set the content type
				connection.setRequestProperty("Content-Type", "application/xml");
				
				OutputStreamWriter outputStream = new OutputStreamWriter(connection.getOutputStream());
				outputStream.write(postdata);
				outputStream.close();
			}
			
			//connect, receive response, then disconnect
			connection.connect();
			if (connection.getResponseCode() != 200) {
			    throw new IOException(connection.getResponseMessage());
			}
			response = receiveResponse(connection);
			connection.disconnect();

		} catch (IOException e) {
			System.err.println(e.toString());
		}
		return response;
	}

	/**
	 * Receives a response from a connection
	 * @param connection The connection to receive a result from
	 * @return The response
	 * @throws IOException Throws an exception with the server response message if the response cannot be read
	 */
	private static String receiveResponse(HttpURLConnection connection) throws IOException {
		String response = "";
		try {
			InputStream inputStream = connection.getInputStream();
			// read the contents
			byte[] buffer = new byte[1024];
			int length;
			//while there's more input to read (restricted to 1024 bytes)
			while ((length = inputStream.read(buffer)) > 0) {
				for (int i = 0; i < length; i++) {
					response += (char) buffer[i];
				}
			}
			inputStream.close();
		} catch (IOException e) {
			throw new IOException(connection.getResponseMessage());
		}
		return response;
	}
}