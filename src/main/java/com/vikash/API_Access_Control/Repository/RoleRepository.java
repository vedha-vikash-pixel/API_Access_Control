package com.vikash.API_Access_Control.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikash.API_Access_Control.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
