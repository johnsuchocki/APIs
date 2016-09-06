package localhost.googlemaps.api;

import java.io.*;
import java.net.*;

/**
 * 
 * Class that requests Google Map data via API
 * 
 * @author John Suchocki
 * @date 9/6/16
 *
 */

public class RestRequest {

	/**
	 * API URL, char set, and API key below
	 */
	protected static String endpoint = "https://maps.googleapis.com/maps/api/directions/";
	protected static String charset = "UTF-8";
	protected static String key = "AIzaSyAI-b0OwKFzq2tHeLht0JiYzgN2kF6k_l8";

	public static void main(String[] args) {

		try {

			/**
			 * Portions of the API request, including start/end point and return
			 * type of response, to be fed into query string
			 */
			String origin = "Ellicott City MD";
			String destination = "Woodlawn MD";
			String returnType = "json";
			String mode = "bicycling";
			String avoid = "ferries | indoor";

			/**
			 * Creates the URL parameters as a string
			 */
			String queryString = String.format("origin=%s&destination=%s&mode=%s&avoid=%s&key=%s",
					URLEncoder.encode(origin, charset), URLEncoder.encode(destination, charset),
					URLEncoder.encode(mode, charset), URLEncoder.encode(avoid, charset),
					URLEncoder.encode(key, charset));

			/**
			 * Creates a new URL out of endpoint, returnType, and queryString
			 */
			URL googleDirections = new URL(endpoint + returnType + "?" + queryString);
			HttpURLConnection connection = (HttpURLConnection) googleDirections.openConnection();
			connection.setRequestMethod("GET");

			/**
			 * Throw an error if we did not successfully connect
			 */
			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
			}

			/**
			 * Read responses into buffer then loop buffer line by line and
			 * print to the console
			 */
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while (br.readLine() != null) {
				System.out.println(br.readLine());
			}

			connection.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
}
