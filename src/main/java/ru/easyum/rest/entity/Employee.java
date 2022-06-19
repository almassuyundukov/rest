package ru.easyum.rest.entity;

import lombok.Data;
import ru.easyum.rest.validation.EmailCheck;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 2, max = 20, message = "Invalid size")
    @NotBlank(message = "Field not be empty")
    private String firstName;
    private String lastName;
    private Double salary;
    @NotBlank(message = "Field not be empty")
    private String department;
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            message = "Invalid phone number")
    private String number;
    @EmailCheck
    private String email;//spring.com
}
