//package com.example.userservice.keycloack;
//
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Service;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//
//@Service
//public class KeyCloakService {
//
//		public void addUser(UserDTO userDTO){
//				CredentialRepresentation credential = Credentials
//								.createPasswordCredentials(userDTO.getPassword());
//				UserRepresentation user = new UserRepresentation();
//				user.setUsername(userDTO.getUserName());
//				user.setFirstName(userDTO.getFirstname());
//				user.setLastName(userDTO.getLastName());
//				user.setEmail(userDTO.getEmailId());
//				user.setCredentials(Collections.singletonList(credential));
//				user.setEnabled(true);
//
//				UsersResource instance = KeycloakConfig.getUsersResource();
//				instance.create(user);
//		}
//
//		public List<UserRepresentation> getUser(String userName){
//				UsersResource usersResource = KeycloakConfig.getUsersResource();
//				List<UserRepresentation> user = usersResource.search(userName, true);
//				return user;
//
//		}
//
//		public void updateUser(String userId, UserDTO userDTO){
//				CredentialRepresentation credential = Credentials
//								.createPasswordCredentials(userDTO.getPassword());
//				UserRepresentation user = new UserRepresentation();
//				user.setUsername(userDTO.getUserName());
//				user.setFirstName(userDTO.getFirstname());
//				user.setLastName(userDTO.getLastName());
//				user.setEmail(userDTO.getEmailId());
//				user.setCredentials(Collections.singletonList(credential));
//
//				UsersResource usersResource = KeycloakConfig.getUsersResource();
//				usersResource.get(userId).update(user);
//		}
//
//		public void deleteUser(String userId){
//				UsersResource usersResource = KeycloakConfig.getUsersResource();
//				usersResource.get(userId)
//								.remove();
//		}
//
//		public void sendVerificationLink(String userId){
//				UsersResource usersResource = KeycloakConfig.getUsersResource();
//				usersResource.get(userId)
//								.sendVerifyEmail();
//		}
//
//		public void sendResetPassword(String userId){
//				UsersResource usersResource = KeycloakConfig.getUsersResource();
//
//				usersResource.get(userId)
//								.executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
//		}
//}
