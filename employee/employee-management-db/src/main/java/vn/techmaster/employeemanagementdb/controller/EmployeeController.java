package vn.techmaster.employeemanagementdb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import vn.techmaster.employeemanagementdb.model.CreateEmployeeReq;
import vn.techmaster.employeemanagementdb.model.EmployeeEntity;
import vn.techmaster.employeemanagementdb.model.SearchRequest;
import vn.techmaster.employeemanagementdb.model.UpdateEmployeeReq;
import vn.techmaster.employeemanagementdb.service.EmployeeService;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public String getAll(Model model) {
        List<EmployeeEntity> listEmployees = employeeService.getAll();
        listEmployees.stream().filter(employee -> employee.getAvatar() != null).forEach(employee -> {
            try {
                employee.convertByteArrToBase64();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        model.addAttribute("employees", listEmployees);
        model.addAttribute("request", new SearchRequest());
        return "home";
    }

    // tìm theo id
    @GetMapping("/employee/{id}")
    public String getById(Model model, @PathVariable int id) {
        if (employeeService.get(id).isPresent()) {
            EmployeeEntity employee = employeeService.get(id).get();
            model.addAttribute("employee", employee);
            if (employee.getAvatar() != null) {
                try {
                    employee.convertByteArrToBase64();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return "employee";
    }

    // xóa theo id
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id, Model model) {
        if (employeeService.get(id).isPresent()) {
            employeeService.deleteById(id);
            model.addAttribute("employees", employeeService.getAll());
        }
        return "redirect:/";
    }

    // search keyword
    @PostMapping("/search")
    public String searchByKeyWord(@Valid @ModelAttribute("request") SearchRequest request, BindingResult result,
            Model model) {

        if (!result.hasFieldErrors()) {
            List<EmployeeEntity> listEmployees = employeeService.searchByKeyWord(request.getKeyword());
            listEmployees.stream().filter(employee -> employee.getAvatar() != null).forEach(employee -> {
                try {
                    employee.convertByteArrToBase64();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            });
            model.addAttribute("employees", listEmployees);
        }

        return "home";
    }

    // thêm mới employee
    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new CreateEmployeeReq());
        return "create-form";
    }

    @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    public String saveFormSubmit(@Valid @ModelAttribute("employee") CreateEmployeeReq employee, BindingResult result,
            Model model) throws IOException {
        if (result.hasErrors()) {
            return "create-form";
        }

        EmployeeEntity newEmployee = employeeService.createEmployee(employee);
        model.addAttribute("employee", newEmployee);

        return "redirect:/";
    }

    // update employee theo id
    @GetMapping("/update/{id}")
    public String getFormUpdateById(Model model, @PathVariable int id) throws UnsupportedEncodingException {
        EmployeeEntity employeeEntity = employeeService.get(id).get();
        UpdateEmployeeReq employeeUpdate = UpdateEmployeeReq.builder().withEmail(employeeEntity.getEmail())
                .withFirstName(employeeEntity.getFirstName()).withLastName(employeeEntity.getLastName())
                .withPassportNumber(employeeEntity.getPassportNumber()).withAvatar(employeeEntity.getAvatar()).build();
        if (employeeUpdate.getAvatar() != null) {
            employeeUpdate.convertByteArrToBase64();
        }

        model.addAttribute("id", id);
        model.addAttribute("employee", employeeUpdate);
        return "update-form";
    }

    @PostMapping(value = "/update/{id}", consumes = { "multipart/form-data" })
    public String updateById(@PathVariable long id, @Valid @ModelAttribute("employee") UpdateEmployeeReq employee,
            BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            return "update-form";
        }

        EmployeeEntity updatedEmployee = employeeService.update(employee, id);
        model.addAttribute("employee", updatedEmployee);

        return "redirect:/";
    }

}