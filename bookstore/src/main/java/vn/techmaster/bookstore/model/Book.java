package vn.techmaster.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
  
  int id;  
  String title;
  String description;

  @Override
  public String toString() {
    return id + " - " + title + " - " + description;
  }

}
