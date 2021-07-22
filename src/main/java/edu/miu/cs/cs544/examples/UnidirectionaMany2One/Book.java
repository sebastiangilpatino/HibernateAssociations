package edu.miu.cs.cs544.examples.UnidirectionaMany2One;

import javax.persistence.Entity;
import javax.persistence.Id;

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

	public Book(int isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

}
