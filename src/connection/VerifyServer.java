package connection;

import utils.Config;

public class VerifyServer {
	public static boolean isOnline() {
		try {
			String response = Connection.get(Config.APP_BASEAPI + "/health");
			return response != null && !response.isEmpty();
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String getVersion() {
		try {
			String response = Connection.get(Config.APP_BASEAPI + "/version");
			
			if (response == null || response.isEmpty()) {
				return "Invalid Response";
			}
			
			return response;
		} catch (Exception e) {
			return "Exception: " + e.toString();
		}
	}
}
