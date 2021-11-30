package vn.techmaster.blogjpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String review;

    // mỗi post có thể có nhiều cmt
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    // User có thể viết nhiều Comment
    @ManyToOne
    private User user;

}
