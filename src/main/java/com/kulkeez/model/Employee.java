package com.kulkeez.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name="EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name = "id")
    private int id;

    @NotNull
	@Size(min = 2, message = "Name should have at least 2 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "joiningDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joiningDate;

    @Column(name = "retiringDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate retiringDate;

    @Column(name="dept")
    private String dept;

    @Past
    @Column(name = "birthDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonIgnore 	// Sensitive data, therefore, applying static filtering and do not send this field as response
	private LocalDate birthDate;
}