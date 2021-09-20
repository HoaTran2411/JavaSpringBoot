package vn.techmaster.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.repository.BookDao;

@Controller
public class BookController {
  @Autowired
  private BookDao bookDao;

  // Trang chủ
  @GetMapping("/")
  public String home(Model model) {
    return "home";
  }

  // get all books
  @GetMapping("/books")
  public String listAll(Model model) {
    model.addAttribute("books", bookDao.getAll());
    return "allbooks";
  }

  // Xem chi tiết một đầu sách theo id
  @GetMapping("/book/{id}")
  public String getBookByID(@PathVariable int id, Model model) {
    model.addAttribute("book", bookDao.get(id).get().toString());
    return "bookById";
  }

  // Thêm mới một đầu sách
  @GetMapping("/addBooks/{id}/{title}/{description}")
  public String addBook(@PathVariable int id, @PathVariable String title, @PathVariable String description,
      Model model) {
    bookDao.add(new Book(id, title, description));
    model.addAttribute("books", bookDao.getAll());
    return "allbooks";
  }

  // Cập nhật một đầu sách theo id
  @GetMapping("/updateBooks/{id}/{title}/{description}")
  public String updateBookById(@PathVariable int id, @PathVariable String title, @PathVariable String description,
      Model model) {
    bookDao.update(id, new Book(id, title, description));
    model.addAttribute("books", bookDao.getAll());
    return "allbooks";
  }

  // Xóa một đầu sách theo id
  @GetMapping("/deleteBooks/{id}")
  public String deleteBookById(@PathVariable int id, Model model) {
    bookDao.deleteByID(id);
    model.addAttribute("books", bookDao.getAll());
    return "allbooks";
  }

}
