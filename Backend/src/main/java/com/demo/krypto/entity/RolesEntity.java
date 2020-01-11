package com.demo.krypto.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.demo.krypto.model.RoleName;

@Entity
@Table(name = "ROLES")
public class RolesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4792550554279530954L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private int roleId;
	
	@Enumerated(EnumType.STRING)
    @NaturalId
	@Column(name = "ROLE_NAME", nullable = false)
	private RoleName roleName;

	@Column(name = "ROLE_DESC", columnDefinition = "TEXT")
	private String description;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	

	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
