package com.paypal.bfs.test.employeeserv;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Long>{

}
