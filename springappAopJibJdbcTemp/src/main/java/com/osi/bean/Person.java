package com.osi.bean;

import org.springframework.stereotype.Component;

@Component
public class Person {
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
