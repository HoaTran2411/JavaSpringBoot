package vn.techmaster.democar.repository;

import java.util.List;
import java.util.Optional;

public abstract class Dao<T> {
    //hiển thị tất cả dữ liệu
    public abstract List<T> getAll();
    
    //hiển thị theo id
    public abstract Optional<T> get (int id);
    
    //cập nhật car
    public abstract void update(T t);

    //thêm car
    public abstract void add(T t);

    //xóa car
    public abstract void delete(int id);

    //Tìm kiếm car theo model hoặc manufacturer
    public abstract List<T> searchByKeyWord(String keyword);

    //Sắp xếp car theo price
    public abstract void sortByPrice();
}
