/**
 * 
 */
package com.demo.krypto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.krypto.entity.ProfileEntity;

/**
 * @author Ahmar
 *
 */
@CrossOrigin(origins = "*")
@RepositoryRestResource(path = "profileRepo")
public interface ProfileRepository extends CrudRepository<ProfileEntity, Long>{

	Boolean existsByPhoneNumber(String phoneNumber);

	Boolean existsByEmail(String email);

	@RestResource(path = "donarList")
	List<ProfileEntity> findByStatus(@RequestParam("value") Boolean value);

	@RestResource(exported = false)
	Iterable<ProfileEntity> findAll();

	@RestResource(path = "donarListByIdAndStatus")
	List<ProfileEntity> findByProfileIdAndStatus(@RequestParam("id") long id, @RequestParam("value") Boolean value);

	Optional<ProfileEntity> findByPhoneNumberAndStatus(String phoneNumber, boolean b);

	//@Query(name = "donarSearchCriteria")
//	List<ProfileEntity> findByDonarSearchCriteria(@Param("firstName") String firstName,
//			@Param("phoneNumber") String phoneNumber, @Param("email") String email, @Param("status") Boolean status);

}
