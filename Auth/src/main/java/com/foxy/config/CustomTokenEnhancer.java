package com.foxy.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.foxy.domain.sql.Role;
import com.foxy.domain.sql.User;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = new User();
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		List<Role> roles = new ArrayList<>();
		for(GrantedAuthority role: userDetails.getAuthorities())
			roles.add(new Role(role.getAuthority()));
		user.setRoles(roles);
		
		Map<String, Object> additional = new HashMap<>();
		additional.put("user", user);
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additional);
		return accessToken;
	}

}
