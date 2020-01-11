package com.demo.krypto.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.krypto.constants.KryptoUriConstants;
import com.demo.krypto.entity.RolesEntity;
import com.demo.krypto.entity.UsersEntity;
import com.demo.krypto.exception.CustomException;
import com.demo.krypto.model.ApiResponseModel;
import com.demo.krypto.model.IdentityAvailability;
import com.demo.krypto.model.RoleName;
import com.demo.krypto.model.StatusModel;
import com.demo.krypto.model.UserSummary;
import com.demo.krypto.model.Users;
import com.demo.krypto.model.UsersCheckRequest;
import com.demo.krypto.model.UsersUpdateModel;
import com.demo.krypto.repository.RolesRepository;
import com.demo.krypto.repository.UsersRepository;
import com.demo.krypto.securityconfig.CurrentUser;
import com.demo.krypto.securityconfig.UserPrincipal;
import com.demo.krypto.service.UserServiceInf;

@RestController
public class UserController {

	@Autowired
	private UserServiceInf userServiceInf;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RolesRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping(KryptoUriConstants.USER_ME_URI)
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) throws CustomException {

		if (ObjectUtils.isEmpty(currentUser)) {

			throw new CustomException("No record found in session", false);
		}
		return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
	}

	@PostMapping(KryptoUriConstants.PHONENUMBER_EXIST_URI)
	public ResponseEntity<IdentityAvailability> checkUsernameAvailability(@RequestBody UsersCheckRequest user) {
		Boolean isAvailable = userServiceInf.existsByPhoneNumber(user.getPhoneNumber());
		if (isAvailable) {
			return new ResponseEntity<>(new IdentityAvailability(isAvailable, "Phone Number not exist..!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new IdentityAvailability(isAvailable, "Phone Number already exist..!!"),
					HttpStatus.OK);
		}
	}

	@PostMapping(KryptoUriConstants.EMAIL_EXIST_URI)
	public ResponseEntity<IdentityAvailability> checkEmailAvailability(@RequestBody UsersCheckRequest user) {
		Boolean isAvailable = userServiceInf.existsByEmailId(user.getEmail());
		if (isAvailable) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new IdentityAvailability(isAvailable, "Email not exist..!!"));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new IdentityAvailability(isAvailable, "Email already exist..!!"));
		}
	}

	@PostMapping(KryptoUriConstants.USERS_DETAILS_BY_ID_STATUS)
	public ResponseEntity<Users> getUserDetailsByIdStatus(@Valid @CurrentUser UserPrincipal currentUser,
			@RequestBody StatusModel status) throws CustomException {
		if (ObjectUtils.isEmpty(currentUser)) {

			throw new CustomException("No record found in session", false);
		}

		Users user = userServiceInf.getUserDetailsByIdStatus(currentUser.getId(), status.getStatus());
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(KryptoUriConstants.REGISTER_USER_URI)
	public ResponseEntity<ApiResponseModel> registerUser(@Valid @RequestBody Users user) {
		try {
			UsersEntity usersEntity = new UsersEntity();
			BeanUtils.copyProperties(user, usersEntity);
			usersEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			usersEntity.setStatus(true);
			if (user.getRole().equalsIgnoreCase(RoleName.ROLE_ADMIN + "")) {
				RolesEntity userRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new CustomException("User Role not set.", false));
				usersEntity.setRoles(Collections.singleton(userRole));
			} else {
				RolesEntity userRole = roleRepository.findByRoleName(RoleName.ROLE_USER)
						.orElseThrow(() -> new CustomException("User Role not set.", false));
				usersEntity.setRoles(Collections.singleton(userRole));
			}
			UsersEntity result = userRepository.save(usersEntity);
			Users usersNew = new Users();
			BeanUtils.copyProperties(result, usersNew);

			return ResponseEntity.status(HttpStatus.OK)
					.body(new ApiResponseModel("Registration Successfull..!!", true));

		} catch (CustomException e) {

			return new ResponseEntity(new ApiResponseModel("Error Occur while Registratio..!!", false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(KryptoUriConstants.UPDATE_USER_URI)
	public ResponseEntity<UsersUpdateModel> updateUser(@RequestBody UsersUpdateModel users) {
		try {
			UsersUpdateModel user = userServiceInf.updateUserCurrentContext(users);
			return ResponseEntity.status(HttpStatus.OK).body(user);
		} catch (Exception e) {
			return new ResponseEntity(new ApiResponseModel("Error Occur while User Updation !", false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
