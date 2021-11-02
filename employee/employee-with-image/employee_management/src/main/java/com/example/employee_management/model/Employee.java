package com.example.employee_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @JsonIgnore
    private int id;
    private String avatar;
    private String firstName;
    private String lastName;
    private String emailId;
    private String passportNumber;
    @JsonIgnore
    private MultipartFile photo;


    public String getFullName() {
        return lastName + " " + firstName;
    }

    public boolean matchWithKeyWord(String keyword) {
        String lowerName = getFullName().toLowerCase();
        String lowerEmail = emailId.toLowerCase();
        String lowerKeyWord = keyword.toLowerCase();
        return lowerName.contains(lowerKeyWord) || lowerEmail.contains(lowerKeyWord);
    }

}