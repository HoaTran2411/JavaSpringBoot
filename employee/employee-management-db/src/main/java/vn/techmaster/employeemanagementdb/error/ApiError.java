package vn.techmaster.employeemanagementdb.error;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private Date timestamp;
    private String message;
    private String details;
}
