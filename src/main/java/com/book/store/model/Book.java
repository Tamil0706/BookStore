package com.book.store.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbn;

    @Column(name = "bookName")
    private String bookName;

    @Column(name = "price")
    private int price;

    @Column(name = "category")
    private String categories;

    @Column(name = "review")
    private String review;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false, referencedColumnName = "aurthorid", foreignKey = @ForeignKey(name = "book_author_id_fk"))
    private Author author;
}