package vn.techmaster.bookstore.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Dao<T> {

  protected List<T> collections = new ArrayList<>();

  public abstract List<T> getAll();

  // Xem chi tiết một đầu sách theo id
  public abstract Optional<T> get(int id);

  // Thêm mới một đầu sách
  public abstract void add(T t);

  // Cập nhật một đầu sách theo id
  public abstract void update(int id, T t);

  // Xóa một đầu sách theo id
  public abstract void deleteByID(int id);

}