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
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Course {
	@Id
	@GeneratedValue
	private int courseNumber;
	private String name;
	@ManyToMany
	private List<Student> students = new ArrayList();
}
