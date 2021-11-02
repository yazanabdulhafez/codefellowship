package ASAC.codefellowship.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String bio;
@OneToMany(mappedBy = "appUser")
    private List<Post> userPosts;

    @ManyToMany
    @JoinTable(
            name="user_followers",
            joinColumns = { @JoinColumn(name = "primaryUser") },
            inverseJoinColumns = { @JoinColumn(name = "followedUser") }
    )
    Set<AppUser> followers;

    @ManyToMany(mappedBy = "followers")
    Set<AppUser> usersFollowedBy;

    //constructors
    public AppUser(){

    }

    public AppUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, String firstName, String lastName, Date dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }


    //getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    //for the followers part

    public void addFollower(AppUser follower) {
        followers.add(follower);
    }

    public void removeFollower(AppUser follower) {
        followers.remove(follower);
    }

    public Set<AppUser> getFollowers() {
        return followers;
    }

    //override methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
