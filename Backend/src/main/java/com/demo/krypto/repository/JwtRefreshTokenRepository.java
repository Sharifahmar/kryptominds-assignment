package com.demo.krypto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.krypto.entity.JwtRefreshTokenEntity;

@Repository
public interface JwtRefreshTokenRepository extends CrudRepository<JwtRefreshTokenEntity, String> {

}
