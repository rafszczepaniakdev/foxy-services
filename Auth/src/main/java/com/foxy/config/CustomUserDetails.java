package com.foxy.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.foxy.domain.sql.Role;
import com.foxy.domain.sql.User;

public class CustomUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1638445051757918909L;

	private String username;
	
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(User byUsername){
		this.username = byUsername.getUsername();
		this.password = byUsername.getPassword();
		
		List<GrantedAuthority> auths = new ArrayList<>();
		for(Role role: byUsername.getRoles())
			auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));
		this.authorities = auths;
		System.out.println(this.authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
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
