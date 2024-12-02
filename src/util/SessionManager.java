package util;

public class SessionManager {
    private static SessionManager instance;

   
    private String user_id;

    
    private SessionManager() {}

  
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

   
    public String getUserId() {
        return user_id;
    }

   
    public void setUserId(String userId) {
        this.user_id = userId;
    }

   
    public void clearSession() {
        this.user_id = null;
    }
}