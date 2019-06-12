package ua.training.finalproject.foodtrackingsystem.model.entity;

public class UserBuilder {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Role role;

    public UserBuilder buildId(Long id){
        this.id = id;
        return this;
    }

    public UserBuilder buildUsername(String userName){
        this.userName = userName;
        return this;
    }

    public UserBuilder buildPassword(String password){
        this.password = password;
        return this;
    }

    public UserBuilder buildEmail(String email){
        this.email = email;
        return this;
    }

    public UserBuilder buildRole(Role role){
        this.role = role;
        return this;
    }

    public User build(){
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }
}
