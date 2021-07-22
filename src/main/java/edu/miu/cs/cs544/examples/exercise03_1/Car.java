package edu.miu.cs.cs544.examples.exercise03_1;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Car {

	@Id
	@GeneratedValue
	private int id;
	private short year;
	private String model;
	private String maker;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn
	private Owner owner;

}
