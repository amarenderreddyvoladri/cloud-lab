package com.example.employeeservice.controller;

import java.time.Instant;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.ApiResponse;
import com.example.employeeservice.dto.EmployeeRequestDTO;
import com.example.employeeservice.dto.EmployeeResponseDTO;
import com.example.employeeservice.service.EmployeeServiceI;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Employee APIs", description = "CRUD operations for Employee service")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final EmployeeServiceI service;

	public EmployeeController(EmployeeServiceI service) {
		this.service = service;
	}

	@PostMapping
	public ApiResponse<EmployeeResponseDTO> create(@Valid @RequestBody EmployeeRequestDTO dto) {

		return ApiResponse.<EmployeeResponseDTO>builder().success(true).message("Employee created successfully")
				.data(service.create(dto)).timestamp(Instant.now().toEpochMilli()).build();
	}

	@GetMapping
	public ApiResponse<List<EmployeeResponseDTO>> getAll() {

		return ApiResponse.<List<EmployeeResponseDTO>>builder().success(true).message("Employees fetched successfully")
				.data(service.getAll()).timestamp(Instant.now().toEpochMilli()).build();
	}

	@GetMapping("/{id}")
	public ApiResponse<EmployeeResponseDTO> getById(@PathVariable Long id) {

		return ApiResponse.<EmployeeResponseDTO>builder().success(true).message("Employee fetched successfully")
				.data(service.getById(id)).timestamp(Instant.now().toEpochMilli()).build();
	}

	@PutMapping("/{id}")
	public ApiResponse<EmployeeResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO dto) {

		return ApiResponse.<EmployeeResponseDTO>builder().success(true).message("Employee updated successfully")
				.data(service.update(id, dto)).timestamp(Instant.now().toEpochMilli()).build();
	}

	@DeleteMapping("/{id}")
	public ApiResponse<String> delete(@PathVariable Long id) {

		service.delete(id);

		return ApiResponse.<String>builder().success(true).message("Employee deleted successfully").data("ID: " + id)
				.timestamp(Instant.now().toEpochMilli()).build();
	}

	@GetMapping("/email/{email}")
	public ApiResponse<EmployeeResponseDTO> getByEmail(@PathVariable String email) {

		return ApiResponse.<EmployeeResponseDTO>builder().success(true)
				.message("Employee fetched successfully by email").data(service.getByEmail(email))
				.timestamp(Instant.now().toEpochMilli()).build();
	}

	@GetMapping("/department/{dept}")
	public ApiResponse<List<EmployeeResponseDTO>> getByDepartment(@PathVariable String dept) {

		return ApiResponse.<List<EmployeeResponseDTO>>builder().success(true)
				.message("Employees fetched successfully by department").data(service.getByDepartment(dept))
				.timestamp(Instant.now().toEpochMilli()).build();
	}
}