package com.restapi.deboo.vo;

import com.restapi.deboo.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserVO implements UserDetails {

    private UserEntity user;

    public UserVO(UserEntity user) {
        // TODO Auto-generated constructor stub
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getAuth()));

        return authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
//        return this.user.getPassword();
        return Integer.toString(this.user.getId());
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return Integer.toString(this.user.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
