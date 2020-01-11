/**
 * 
 */
package com.demo.krypto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.krypto.entity.RolesEntity;
import com.demo.krypto.model.RoleName;

/**
 * @author Ahmar
 *
 */
@RepositoryRestResource(collectionResourceRel = "roles", path = "roles")
public interface RolesRepository extends CrudRepository<RolesEntity, Integer> {
	Optional<RolesEntity> findByRoleName(RoleName roleUser);
}
