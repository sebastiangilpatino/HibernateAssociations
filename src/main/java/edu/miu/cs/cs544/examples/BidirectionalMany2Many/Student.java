package edu.miu.cs.cs544.examples.BidirectionalMany2Many;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Student {

	@Id
	@GeneratedValue
	private int studentId;
	private String firstName;
	private String lastName;
	@ManyToMany(mappedBy = "students")
	private List<Course> courses = new ArrayList();

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
