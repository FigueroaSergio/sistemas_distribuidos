package datamodel;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="persona")
@XmlAccessorType (XmlAccessType.FIELD)
public class Person {
	
	@XmlAttribute
	private String name;
	private String lastName;
	private Date birthday;
	
	 public Person( String name, String lastName, Date birthday) {
		this.name=name;
		this.lastName=lastName;
		this.birthday= birthday;
	}
	 public String  toString() {
		 String pattern = "MM-dd-yyyy";
		 SimpleDateFormat format=new SimpleDateFormat(pattern);
		 
		 return "Nombre: "+this.name+" last name: "+this.lastName;
	 }
	 public String getName() {
		 return name;
	 }
	 public String getLastName() {
		 return lastName;
	 }
	 public Date getBirthday() {
		 return birthday;
	 }
}
