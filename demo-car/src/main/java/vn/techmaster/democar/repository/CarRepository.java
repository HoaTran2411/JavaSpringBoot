package vn.techmaster.democar.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import vn.techmaster.democar.model.Car;

@Repository
public class CarRepository extends Dao<Car> {

    private List<Car> carList = new ArrayList<>();

    // lấy dữ liệu từ file json
    public CarRepository() {
        try {
            File file = ResourceUtils.getFile("classpath:static/data/MOCK_DATA.json");
            ObjectMapper mapper = new ObjectMapper();
            carList.addAll(mapper.readValue(file, new TypeReference<ArrayList<Car>>() {
            }));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // hiển thị tất cả car
    @Override
    public List<Car> getAll() {
        return carList;
    }

    // hiển thị theo id
    @Override
    public Optional<Car> get(int id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

     // thêm car
     @Override
     public void add(Car car) {
         //tự tạo id cho newCar
         int id = 1;
         if (!carList.isEmpty()) {
             // tìm ra id lớn nhất trong list
             int max = carList.get(0).getId();
             for (Car carEle : carList) {
                 if (max < carEle.getId()) {
                     max = carEle.getId();
                 }
             }
 
             id = max + 1;
         }
         car.setId(id);
         car.setImage("../images/toyota.png");
         carList.add(car);
     }

    // cập nhật car
    @Override
    public void update(Car car) {
        get(car.getId()).ifPresent(existCar -> {
            existCar.setManufacturer(car.getManufacturer());
            existCar.setModel(car.getModel());
            existCar.setPrice(car.getPrice());
            existCar.setSales(car.getSales());
        });
    }

    // xóa car theo id
    @Override
    public void delete(int id) {
        get(id).ifPresent(existCar -> carList.remove(existCar));
    }

    // Tìm kiếm car theo model hoặc manufacturer
    @Override
    public List<Car> searchByKeyWord(String keyword) {
        // trả về list các car có chứa keyword ở model hoặc manufacturer
        return carList.stream().filter(car -> (car.getManufacturer().toLowerCase().contains(keyword.toLowerCase())
                || car.getModel().toLowerCase().contains(keyword.toLowerCase()))).collect(Collectors.toList());
    }

    // Sắp xếp car theo price
    @Override
    public void sortByPrice() {
        carList.sort((Car a, Car b) -> (a.getPrice() - b.getPrice()));
    }

}
