package com.example.employee_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @JsonIgnore
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String passportNumber;

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