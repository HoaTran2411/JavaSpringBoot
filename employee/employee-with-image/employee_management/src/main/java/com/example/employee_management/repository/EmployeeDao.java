package com.example.employee_management.repository;

import com.example.employee_management.model.Employee;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeDao extends Dao<Employee> {
    public EmployeeDao(String csvFile) {
        this.readCSV(csvFile);
    }

    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper(); // Dùng để ánh xạ cột trong CSV với từng trường trong POJO
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(',');
            ObjectReader oReader = mapper.readerFor(Employee.class).with(schema);
            Reader reader = new FileReader(file);
            MappingIterator<Employee> mi = oReader.readValues(reader); // Iterator đọc từng dòng trong file
            while (mi.hasNext()) {
                Employee employee = mi.next();
                this.add(employee);
                System.out.println(employee);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Employee> getAll() {
        return collections.stream()
                .sorted((o1, o2) -> o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> get(int id) {
        return collections.stream().filter(employee -> employee.getId() == id).findFirst();
    }

    @Override
    public void add(Employee employee) {
        // tự tạo id
        int id;
        if (collections.isEmpty()) {
            id = 1;
        } else {
            Employee lastEmployee = collections.get(collections.size() - 1);
            id = lastEmployee.getId() + 1;
        }
        employee.setId(id);
        
        collections.add(employee);
    }

    @Override
    public void update(Employee employee) {
        get(employee.getId()).ifPresent(oldEmployee -> {
            oldEmployee.setFirstName(employee.getFirstName());
            oldEmployee.setLastName(employee.getLastName());
            oldEmployee.setEmailId(employee.getEmailId());
            oldEmployee.setPassportNumber(employee.getPassportNumber());
            oldEmployee.setAvatar(employee.getAvatar());
            oldEmployee.setPhoto(employee.getPhoto());
        });
    }

    @Override
    public void delete(Employee employee) {
        get(employee.getId()).ifPresent(existEmployee -> collections.remove(existEmployee));
    }

    @Override
    public void deleteById(int id) {
        get(id).ifPresent(existEmployee -> collections.remove(existEmployee));
    }

    @Override
    public List<Employee> searchByKeyWord(String keyword) {
        return collections.stream().filter(employee -> employee.matchWithKeyWord(keyword))
                .sorted((o1, o2) -> o1.getFirstName().toLowerCase().compareTo(o2.getFirstName().toLowerCase()))
                .collect(Collectors.toList());
    }

}
