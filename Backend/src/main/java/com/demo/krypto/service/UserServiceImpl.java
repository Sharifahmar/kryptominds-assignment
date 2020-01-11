/**
 *
 */
package com.demo.krypto.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.krypto.entity.UsersEntity;
import com.demo.krypto.exception.CustomException;
import com.demo.krypto.model.Users;
import com.demo.krypto.model.UsersUpdateModel;
import com.demo.krypto.repository.UsersRepository;

/**
 * @author Ahmar
 *
 */
@Component
public class UserServiceImpl implements UserServiceInf {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public Boolean existsByPhoneNumber(String phoneNumber) {
		return !usersRepository.existsByPhoneNumber(phoneNumber);

	}

	@Override
	public Boolean existsByEmailId(String emailId) {
		return !usersRepository.existsByEmail(emailId);

	}

	@Override
	public Users getUserDetailsByIdStatus(Long id, Boolean status) throws CustomException {
		UsersEntity userDetails = usersRepository.findByIdAndStatus(id, status)
				.orElseThrow(() -> new CustomException("Data with Userid and Status not found ", false));
		Users users = new Users();
		BeanUtils.copyProperties(userDetails, users);
		return users;
	}

	@Override
	public UsersUpdateModel updateUserCurrentContext(UsersUpdateModel users) throws Exception {
		UsersEntity usersEntity = usersRepository.findByPhoneNumberAndStatus(users.getPhoneNumber(), true)
				.orElseThrow(() -> new Exception("Record not found with phoneNumber and status..!!"));
		BeanUtils.copyProperties(users, usersEntity);
		UsersUpdateModel users2 = new UsersUpdateModel();
		UsersEntity usersEntityNew = usersRepository.save(usersEntity);
		BeanUtils.copyProperties(usersEntityNew, users2);
		return users2;
	}

}
