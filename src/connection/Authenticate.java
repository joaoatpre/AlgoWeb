package connection;

import utils.Config;

public class Authenticate {
    public static boolean login(String username, String password) {
        try {
            String payload = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);
            String response = Connection.post(Config.APP_BASEAPI + "/login", payload);
            return response != null && response.equalsIgnoreCase("success");
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean register(String username, String password) {
        try {
            String payload = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);
            String response = Connection.post(Config.APP_BASEAPI + "/register", payload);
            return response != null && response.equalsIgnoreCase("success");
        } catch (Exception e) {
            return false;
        }
    }
}