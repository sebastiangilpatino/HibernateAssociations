package edu.miu.cs.cs544.examples.bidirectionalOne2Many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Department {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
	private List<Employee> employeeList = new ArrayList();

	public void addEmployee(Employee employee) {
		employeeList.add(employee);
	}

	public boolean deleteEmployee(int employeeNumber) {
		Employee emp = employeeList.stream().filter(x -> x.getEmployeeNumber() == employeeNumber).findFirst()
				.orElse(null);

		if (emp != null) {
			return employeeList.remove(emp);
		}

		return false;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}
