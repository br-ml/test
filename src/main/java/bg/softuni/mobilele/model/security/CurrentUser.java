package bg.softuni.mobilele.model.security;

import bg.softuni.mobilele.model.entities.enums.UserRoleEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
@Component
@SessionScope
public class CurrentUser {

//    private Long id;
//    private String username;
//
//
//    public CurrentUser() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public boolean isAnonymous() {
//        return this.username == null;
//    }



    private static final String ANONYMOUS_NAME = "anonymous";
    private String name = ANONYMOUS_NAME;
    private boolean isAnonymous = true;
    private List<UserRoleEnum> userRoles = new ArrayList<>();

    public String getName() {
        return name;
    }

    public CurrentUser setName(String name) {
        this.name = name;
        return this;
    }

    public CurrentUser setUserRoles(List<UserRoleEnum> newUserRoles) {
        userRoles.clear();
        userRoles.addAll(newUserRoles);
        return this;
    }

    public boolean isAdmin() {
        return userRoles.contains(UserRoleEnum.ADMIN);
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public boolean isLoggedIn() { return !isAnonymous();}

    public CurrentUser setAnonymous(boolean anonymous) {
        if (anonymous) {
            this.name = ANONYMOUS_NAME;
            this.userRoles.clear();
        }
        isAnonymous = anonymous;
        return this;
    }
}