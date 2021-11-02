package vn.techmaster.employeemanagementdb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import vn.techmaster.employeemanagementdb.model.CreateEmployeeReq;
import vn.techmaster.employeemanagementdb.model.EmployeeEntity;
import vn.techmaster.employeemanagementdb.model.UpdateEmployeeReq;

public interface EmployeeService {

    List<EmployeeEntity> getAll();

    Optional<EmployeeEntity> get(long id);

    EmployeeEntity createEmployee(CreateEmployeeReq employee) throws IOException;

    EmployeeEntity update(UpdateEmployeeReq employee, long id) throws IOException;

    void deleteById(long id);

    List<EmployeeEntity> searchByKeyWord(String keyword);

}
