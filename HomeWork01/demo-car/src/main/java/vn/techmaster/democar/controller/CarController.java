package vn.techmaster.democar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.democar.model.Car;
import vn.techmaster.democar.repository.CarRepository;
import vn.techmaster.democar.request.SearchRequest;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository repo;

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", repo.getAll());
        return "home";
    }

    // nhấn vào xem chi tiết, hiện ra car theo id;
    @GetMapping("/{id}")
    public String getCarById(@PathVariable int id, Model model) {
        if (repo.get(id).isPresent()) {
            model.addAttribute("car", repo.get(id).get());
        }
        return "car";
    }

    // thêm car (client thêm bằng submit form)
    @GetMapping("/add")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "form";
    }

    // update car (client update trong form submit)
    @GetMapping("/update/{id}")
    public String updateCar(@PathVariable int id, Model model) {
        if (repo.get(id).isPresent()) {
            model.addAttribute("car", repo.get(id).get());
        }
        return "form";
    }

    // trả về kết quả khi submit form
    @PostMapping("/save")
    public String saveForm(Car car, BindingResult binding) {
        if (binding.hasErrors()) {
            return "form";
        }
        if (car.getId() > 0) {
            repo.update(car);
        } else {
            repo.add(car);
        }
        return "redirect:/cars";
    }

    // xóa car
    @GetMapping("/remove/{id}")
    public String removeCar(@PathVariable int id, Model mode) {
        repo.delete(id);
        return "redirect:/cars";
    }

    // Tìm kiếm car theo model hoặc manufacturer
    @GetMapping("/search")
    public String searchCar(Model model) {
        model.addAttribute("searchRequest", new SearchRequest());
        return "search";
    }

    @PostMapping("/search")
    public String searchCar(@ModelAttribute SearchRequest request, BindingResult binding, Model model) {
        if (!binding.hasFieldErrors()) {
            model.addAttribute("cars", repo.searchByKeyWord(request.getKeyWord()));
        }
        return "search";
    }

    // Sắp xếp car theo price (giá từ thấp đến cao)
    @GetMapping("/sort")
    public String sortCar(Model model) {
        repo.sortByPrice();
        model.addAttribute("cars", repo.getAll());
        return "home";
    }

}
