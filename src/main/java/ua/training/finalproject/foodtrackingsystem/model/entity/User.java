package ua.training.finalproject.foodtrackingsystem.model.entity;

/**
 * @author Andrii Kolomiiets
 * @version 1.0 19.06.2019
 */
public class User implements Entity {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Role role;

    private Client client;

    public User() {
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(Long id, String username, String password, String password2, String email, Client client, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.client = client;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

/*    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

/*    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }*/

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString(){
        return "User ID: "+ getId()+
                "\nLogin: " + getUsername() +
                "\nPassword: " + getPassword() +
                "\nEmail: " + getEmail() +
                "\nClient: " + getClient() +
                "\nRole: " + getRole();
    }
}
