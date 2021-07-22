package edu.miu.cs.cs544.examples.OptionalUnidirectionalMany2One;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Book {

	@Id
	private int isbn;
	private String title;
	private String author;
	@ManyToOne
	@JoinTable(name = "book_publisher")
	private Publisher publisher;

	public Book(int isbn, String title, String author, Publisher publisher) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

}
