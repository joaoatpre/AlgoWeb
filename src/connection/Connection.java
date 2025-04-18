package connection;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.*;

public class Connection {
	public static String post(String url, String jsonBody) throws IOException {
		URI uri = URI.create(url);
		HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);

		try (OutputStream os = conn.getOutputStream()) {
			os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
		}

		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				response.append(line);
			return response.toString();
		}
	}

	public static String get(String url) throws IOException {
		URI uri = URI.create(url);
		HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
		conn.setRequestMethod("GET");

		try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null)
				response.append(line);
			return response.toString();
		}
	}
}
