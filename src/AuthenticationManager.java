import java.util.HashMap;
import java.util.Map;

public class AuthenticationManager {

    private static AuthenticationManager instance;

    private Map<String, String> userDatabase;

    private AuthenticationManager() {

           userDatabase = new HashMap<>();
    }

    public static AuthenticationManager getInstance() {

            if (instance == null) {


            instance = new AuthenticationManager();
        }
          return instance;
    }

    public void registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {

            throw new IllegalArgumentException("Username already exists.");
        }
        userDatabase.put(username, password);
    }

    public boolean authenticateUser(String username, String password) {

        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }
}
