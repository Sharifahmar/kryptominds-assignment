/**
 * 
 */
package com.demo.krypto.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.demo.krypto.entity.ProfileEntity;

/**
 * @author Ahmar
 *
 */
@Configuration
public class SpringDataRestConfiguration extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(ProfileEntity.class);
	}
}