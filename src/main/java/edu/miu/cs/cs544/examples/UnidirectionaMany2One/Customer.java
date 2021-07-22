package edu.miu.cs.cs544.examples.UnidirectionaMany2One;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

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
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn
	private List<Reservation> reservations = new ArrayList();

	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
}
