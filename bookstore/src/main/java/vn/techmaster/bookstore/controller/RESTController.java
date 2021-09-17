package vn.techmaster.bookstore.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import vn.techmaster.bookstore.model.Book;
import vn.techmaster.bookstore.repository.BookDao;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class RESTController {

    BookDao bookDao = new BookDao();

    @GetMapping("/books")
    public ResponseEntity<List<Book>> listBooks() {
        return ResponseEntity.ok().body(bookDao.getAll());
    }

    // Xem chi tiết một đầu sách theo id
    @GetMapping("/id/2")
    public ResponseEntity<Optional<Book>> getBookById() {
        BookDao bookDao = new BookDao();
        return ResponseEntity.ok().body(bookDao.get(2));
    }

    // Thêm mới một đầu sách
    @PostMapping("/booksAfterAdd")
    public ResponseEntity<List<Book>> addBooks() {
        bookDao.add(new Book(4, "Túp lều của bác Tôm", "Tiểu thuyết chống chế độ nô lệ"));
        return ResponseEntity.ok().body(bookDao.getAll());
    }

    // Cập nhật một đầu sách theo id
    @PutMapping("/booksAfterUpdate")
    public ResponseEntity<List<Book>> updateBook() {
        bookDao.update(1, new Book(1, "Không gia đình-update", "Tiểu thuyết nổi tiếng thế giới"));
        return ResponseEntity.ok().body(bookDao.getAll());
    }

    // Xóa một đầu sách theo id
    @DeleteMapping("/booksAfterDelete")
    public ResponseEntity<List<Book>> deleteBook() {
        bookDao.deleteByID(2);
        return ResponseEntity.ok().body(bookDao.getAll());
    }

}
