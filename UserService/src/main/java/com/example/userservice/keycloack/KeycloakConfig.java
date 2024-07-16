//package com.example.userservice.keycloack;
//
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.admin.client.resource.UsersResource;
//
//public class KeycloakConfig {
//		static Keycloak keycloak = null;
//		final static String serverUrl = "http://localhost:9090";
//		public final static String realm = "akka_book_shop";
//		final static String clientId = "auth-service-v1";
//		final static String clientSecret = "b2neT4F7jdeToIGfSnFe7S8ObDPCx6Lm";
//		final static String userName = "admindemo";
//		final static String password = "admin";
//
//		public KeycloakConfig() {
//		}
//
//		private static Keycloak getInstance(){
//				if(keycloak == null){
//						keycloak = KeycloakBuilder.builder()
//										.serverUrl(serverUrl)
//										.realm(realm)
//										.grantType(OAuth2Constants.PASSWORD)
//										.username(userName)
//										.password(password)
//										.clientId(clientId)
//										.clientSecret(clientSecret)
//										.resteasyClient(new ResteasyClientBuilder()
//														.connectionPoolSize(10)
//														.build())
//                    .build();
//				}
//				return keycloak;
//		}
//
//		public static UsersResource getUsersResource() {
//				return getInstance().realm(realm).users();
//		}
//}
