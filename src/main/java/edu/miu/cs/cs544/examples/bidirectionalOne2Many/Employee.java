package edu.miu.cs.cs544.examples.bidirectionalOne2Many;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

	@Id
	@GeneratedValue
	private int employeeNumber;
	private String name;
	@ManyToOne
	@JoinColumn
	private Department department;

	public Employee(String name, Department department) {
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", name=" + name + ", department=" + department + "]";
	}

}
