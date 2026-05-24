package com.example.employeeservice.dto;

import com.example.employeeservice.entity.Employee;

public class EmployeeMapper {

	// DTO → Entity
	public static Employee toEntity(EmployeeRequestDTO dto) {
		Employee emp = new Employee();
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		return emp;
	}

	// Entity → DTO
	public static EmployeeResponseDTO toDTO(Employee employee) {
		return EmployeeResponseDTO.builder().id(employee.getId()).name(employee.getName()).email(employee.getEmail())
				.department(employee.getDepartment()).salary(employee.getSalary()).build();
	}
}