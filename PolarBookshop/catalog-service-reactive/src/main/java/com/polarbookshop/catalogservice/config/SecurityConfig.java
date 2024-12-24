package com.polarbookshop.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.*;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.savedrequest.NoOpServerRequestCache;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {

		return http
				.authorizeExchange(authorize -> authorize
						.pathMatchers("/actuator/**").permitAll()
						.pathMatchers(HttpMethod.GET, "/", "/books/**").permitAll()
						.anyExchange().hasRole("employee")
				)
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
				//.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())))
				.requestCache(requestCacheSpec ->
						requestCacheSpec.requestCache(NoOpServerRequestCache.getInstance()))
				.csrf(ServerHttpSecurity.CsrfSpec::disable)
				.build();
	}

	@Bean
	public ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter() {
		var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

		ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
		reactiveJwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter));
		return reactiveJwtAuthenticationConverter;
	}

	/*Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
		JwtAuthenticationConverter jwtAuthenticationConverter =
				new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
				(new GrantedAuthoritiesExtractor());
		return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
	}

	static class GrantedAuthoritiesExtractor
			implements Converter<Jwt, Collection<GrantedAuthority>> {

		public Collection<GrantedAuthority> convert(Jwt jwt) {
			Collection<?> authorities = (Collection<?>)
					jwt.getClaims().getOrDefault("roles", Collections.emptyList());

			return authorities.stream()
					.map(Object::toString)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		}
	}*/

}
