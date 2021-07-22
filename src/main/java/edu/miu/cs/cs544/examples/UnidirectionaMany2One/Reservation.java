package edu.miu.cs.cs544.examples.UnidirectionaMany2One;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

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
public class Reservation {

	@Id
	@GeneratedValue
	private int id;
	private LocalDate date;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "reservation_book")
	private Book book;
}
