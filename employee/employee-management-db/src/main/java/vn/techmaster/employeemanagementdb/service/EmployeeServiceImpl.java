package vn.techmaster.employeemanagementdb.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.employeemanagementdb.error.NotFoundException;
import vn.techmaster.employeemanagementdb.model.CreateEmployeeReq;
import vn.techmaster.employeemanagementdb.model.EmployeeEntity;
import vn.techmaster.employeemanagementdb.model.UpdateEmployeeReq;
import vn.techmaster.employeemanagementdb.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    @Override
    public List<EmployeeEntity> getAll() {
        return employeeRepo.findAll().stream()
                .sorted((o1, o2) -> o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeEntity> get(long id) {
        if (!employeeRepo.findById(id).isPresent()){
            throw new NotFoundException("Employee not found for this id: " + id);
        }
        return employeeRepo.findById(id);
    }

    @Override
    public EmployeeEntity createEmployee(CreateEmployeeReq employee) throws IOException {
        EmployeeEntity employeeEntity = EmployeeEntity.builder().withAvatar(employee.getAvatarImg().getBytes())
                .withEmail(employee.getEmail()).withFirstName(employee.getFirstName())
                .withLastName(employee.getLastName()).withPassportNumber(employee.getPassportNumber()).build();
        return employeeRepo.save(employeeEntity);
        // Check email exist

    }

    @Override
    public EmployeeEntity update(UpdateEmployeeReq employeeReq, long id) throws IOException {

        EmployeeEntity employeeUpdate;

        if (employeeRepo.findById(id).isEmpty()) {
            throw new NotFoundException("Employee not found for this id: " + id);
        }

        if (employeeReq.getAvatarImg().getOriginalFilename() != "") {
            employeeUpdate = EmployeeEntity.builder().withId(id).withAvatar(employeeReq.getAvatarImg().getBytes())
                    .withEmail(employeeReq.getEmail()).withFirstName(employeeReq.getFirstName())
                    .withLastName(employeeReq.getLastName()).withPassportNumber(employeeReq.getPassportNumber())
                    .build();
        } else {
            employeeUpdate = EmployeeEntity.builder().withId(id).withAvatar(employeeRepo.findById(id).get().getAvatar())
                    .withEmail(employeeReq.getEmail()).withFirstName(employeeReq.getFirstName())
                    .withLastName(employeeReq.getLastName()).withPassportNumber(employeeReq.getPassportNumber())
                    .build();
        }

        return employeeRepo.save(employeeUpdate);
    }

    @Override
    public void deleteById(long id) {
        Optional<EmployeeEntity> employee = employeeRepo.findById(id);
        if (employee.isPresent()) {
            employeeRepo.deleteById(id);
        } else {
            throw new NotFoundException("No employee found");
        }
    }

    @Override
    public List<EmployeeEntity> searchByKeyWord(String keyword) {
        return getAll().stream().filter(employee -> employee.matchWithKeyWord(keyword)).collect(Collectors.toList());
    }

}