package edu.miu.cs.cs544.examples.BidirectionaMany2One;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Office {

	@Id
	private int roomNumber;
	private int building;
	@OneToMany(mappedBy = "office", cascade = CascadeType.PERSIST)
	private List<Employee> employees = new ArrayList();

	public Office(int roomNumber, int building) {
		this.roomNumber = roomNumber;
		this.building = building;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	@Override
	public String toString() {
		return "Office [roomNumber=" + roomNumber + ", building=" + building + "]";
	}

}
