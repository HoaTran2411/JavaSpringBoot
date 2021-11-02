package vn.techmaster.employeemanagementdb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.employeemanagementdb.model.CreateEmployeeReq;
import vn.techmaster.employeemanagementdb.model.UpdateEmployeeReq;
import vn.techmaster.employeemanagementdb.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class RestEmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable long id) {
        return ResponseEntity.ok(employeeService.get(id));
    }

    // gửi lên chỉ dữ liệu dạng text thì dùng @RequestBody, có dạng file thì dùng
    // @ModelAttribute
    @PostMapping(value = "/employees", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createEmployee(@Valid @ModelAttribute CreateEmployeeReq employee) throws IOException {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @PutMapping(value = "/employees/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<?> updateEmployee(@Valid @ModelAttribute UpdateEmployeeReq employee, @PathVariable long id)
            throws IOException {
        return ResponseEntity.ok(employeeService.update(employee, id));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        employeeService.deleteById(id);
        return ResponseEntity.ok("Delete sucessfully!");
    }

    // xử lý @valid, nhưng trường hợp file không chạy
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
