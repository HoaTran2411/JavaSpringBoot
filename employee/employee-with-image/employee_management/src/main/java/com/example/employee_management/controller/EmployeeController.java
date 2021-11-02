package com.example.employee_management.controller;

import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeDao;
import com.example.employee_management.request.SearchRequest;
import com.example.employee_management.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao repo;
    @Autowired
    StorageService storageService;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("employees", repo.getAll());
        model.addAttribute("request", new SearchRequest());
        return "home";
    }

    // tìm theo id
    @GetMapping("/employee/{id}")
    public String getById(Model model, @PathVariable int id) {
        if (repo.get(id).isPresent()) {
            model.addAttribute("employee", repo.get(id).get());
            return "employee";
        } else {
            return "notExist";
        }
    }

    // thêm mới employee
    @GetMapping("/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", "Add new Employee");
        return "form";
    }

    // lưu ý khai báo theo đúng thứ tự: BindingResult phải khai báo sau employee
    // @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    // public String saveFormSubmit(@ModelAttribute Employee employee, BindingResult result) {
    //     if (result.hasErrors()) {
    //         return "form";
    //     }

    //     if (employee.getId() > 0) {
    //         repo.update(employee);
    //     } else {
    //         storageService.uploadFile(employee.getPhoto());
    //         String fileName = employee.getPhoto().getOriginalFilename();
    //         String avatar = "/photos/" + fileName;
    //         employee.setAvatar(avatar);
    //         repo.add(employee);
    //     }

    //     return "redirect:/";
    // }

    @PostMapping(value = "/save", consumes = { "multipart/form-data" })
    public String saveFormSubmit(@ModelAttribute Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "form";
        }

        if (employee.getId() > 0) {
            repo.update(employee);
        } else {
            storageService.uploadFile(employee.getPhoto());
            String fileName = employee.getPhoto().getOriginalFilename();
            String avatar = "/photos/" + fileName;
            employee.setAvatar(avatar);
            repo.add(employee);
        }

        return "result";
    }

    // update employee theo id
    @GetMapping("/update/{id}")
    public String updateById(Model model, @PathVariable int id) {
        if (repo.get(id).isPresent()) {
            model.addAttribute("employee", repo.get(id).get());
            model.addAttribute("title", "Update Employee");
        }
        return "form";
    }

    // xóa theo id
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable int id, Model model) {
        if (repo.get(id).isPresent()) {
            repo.deleteById(id);
            model.addAttribute("employees", repo.getAll());
        }
        return "redirect:/";
    }

    // tìm theo keyword
    // By default the model attribute name is taken as Bean class's name with first
    // lowercase letter, phải thêm @ModelAttribute("request")
    // để nó hiểu request là 1 attribute, còn k thì cần đặt tên giống tên class:
    // searchRequest (chữ đầu k viết hoa)
    @PostMapping("/search")
    public String searchByKeyWord(@ModelAttribute("request") SearchRequest request, BindingResult result, Model model) {
        if (!result.hasFieldErrors()) {
            model.addAttribute("employees", repo.searchByKeyWord(request.getKeyword()));
        }
        return "home";
    }
}
