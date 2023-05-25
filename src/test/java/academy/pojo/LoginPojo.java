package academy.pojo;

public class LoginPojo {
    private String email;
    private String password;

    public LoginPojo (){

    }

    public LoginPojo (String email , String password){
        setEmail(email);
        setPassword(password);
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
