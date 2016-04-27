package by.vonotirah.linkscutter.webapp.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LinksPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		boolean hasPermission = false;

		if (targetDomainObject.equals("Link")) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UserDetails) {
				UserDetails userDetails = (UserDetails) principal;
				String principalRole = getRole(userDetails.getAuthorities());
				if (principalRole.equals("ROLE_USER")) {
					hasPermission = true;
				}
			}
		}

		return hasPermission;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

	private String getRole(Collection<? extends GrantedAuthority> authorities) {
		return authorities.iterator().next().getAuthority();
	}
}