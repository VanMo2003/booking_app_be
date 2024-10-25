package com.example.booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booking_app.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
