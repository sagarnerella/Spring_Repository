package com.osi.bean;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class Person {
	public Person(){
		System.out.println("person class ");
	}
private String personeName;
private String personAge;
public String getPersoneName() {
	return personeName;
}
public void setPersoneName(String personeName) {
	this.personeName = personeName;
}
public String getPersonAge() {
	return personAge;
}
public void setPersonAge(String personAge) {
	this.personAge = personAge;
}
}
