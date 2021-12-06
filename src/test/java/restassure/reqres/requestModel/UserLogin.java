package restassure.reqres.requestModel;

public class UserLogin {

    private String email;
    private String password;

    public UserLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public UserLogin(String email) {
        setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
