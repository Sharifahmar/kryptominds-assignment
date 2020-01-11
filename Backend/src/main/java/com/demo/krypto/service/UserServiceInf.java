/**
 *
 */
package com.demo.krypto.service;

import com.demo.krypto.exception.CustomException;
import com.demo.krypto.model.Users;
import com.demo.krypto.model.UsersUpdateModel;

/**
 * @author Ahmar
 *
 */

public interface UserServiceInf {

	Boolean existsByPhoneNumber(String userName);

	Boolean existsByEmailId(String emailId);

	Users getUserDetailsByIdStatus(Long id ,Boolean status) throws CustomException;

	UsersUpdateModel updateUserCurrentContext(UsersUpdateModel users) throws Exception;



}
