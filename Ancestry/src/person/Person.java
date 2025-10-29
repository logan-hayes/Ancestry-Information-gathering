/*
 * @author Logan Hayes
 * 
 * Person object serving as the basis for building family trees
 */

package person;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.time.Period;

public class Person {
	
	//Basic info
	private String personID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private LocalDate dateOfDeath;
	private MaritalStatus maritalStatus;
	private String photoLink;
	private Sex sex;
	
	//Relationship info
	private Person father;
	private Person mother;
	private List<Person> siblings;
	private List<Person> children;
	private Person grandmother;
	private Person grandfather;
	private Person spouse;
	
	//Medical info
	
	////////////////////////////////////////////////////////
	// may change these to enum or seperate classes for analytics and data clarity (consult class)
	private List<String> diagnosis; 
	private Map<String, String> systemByDiagnosis;
	private String causeOfDeath;
	private List<String> hereditaryDisease;
	private List<String> medications;
	///////////////////////////////////////////////////////
	
	//Additional info
	private String occupation;
	private String additionalNotes;
	
	
	public enum MaritalStatus{MARRIED, SINGLE, DIVORCED,
		WIDOWED, SEPERATED}
	
	public enum Sex{MALE, FEMALE, UNKNOWN}
	
	

	//constructor default
	public Person() {
	
	}
	
	//Constructor with fields
	public Person(String personID, String firstName, String lastName,
			LocalDate dateOfBirth, LocalDate dateOfDeath,
			MaritalStatus maritalStatus, String photoLink, Sex sex,
			Person father, Person mother, List<Person> siblings, List<Person> children,
			Person grandmother, Person grandfather, Person spouse, List<String> diagnosis,
			Map<String, String> systemByDiagnosis, String causeOfDeath, List<String> hereditaryDisease,
			List<String> medications, String occupation, String additionalNotes) {
		
		this.personID = personID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.maritalStatus = maritalStatus;
		this.photoLink = photoLink;
		this.sex = sex;
		this.father = father;
		this.mother = mother;
		this.siblings = siblings;
		this.children = children;
		this.grandmother = grandmother;
		this.grandfather = grandfather;
		this.spouse = spouse;
		this.diagnosis = diagnosis;
		this.systemByDiagnosis = systemByDiagnosis;
		this.causeOfDeath = causeOfDeath;
		this.hereditaryDisease = hereditaryDisease;
		this.medications = medications;
		this.occupation = occupation;
		this.additionalNotes = additionalNotes;
	}
	
	///////////////////////////Getter methods///////////////////////////////
	
	//get personID
	public String getPersonID() {
		return personID;
	}
	
	//get first name
	public String getFirstName() {
		return firstName;
	}
		
	//get last name
	public String getLastName() {
		return lastName;
	}
	
	//get date of birth
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	//get death date
	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}
	
	//get marital status
	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	
	//get photo link
		public String getPhotoLink() {
			return photoLink;
	}
		
	//get sex of person
	public Sex getSex() {
		return sex;
	}
	
	//get father
	public Person getFather() {
		return father;
	}
	
	//get mother
	public Person getMother() {
		return mother;
	}
	
	//get siblings
	public List<Person> getSiblings() {
		return siblings;
	}
		
	//get children
	public List<Person> getChildren() {
		return children;
	}
		
	//get grandmother
	public Person getGrandmother() {
		return grandmother;
	}
		
	//get grandfather
	public Person getGrandfather() {
		return grandfather;
	}
	
	//get spouse
	public Person getspouse() {
		return spouse;
	}
	
	//get diagnosis
	public List<String> getDiagnosis() {
		return diagnosis;
	}
	
	//get diagnosis
	public String getCauseOfDeath() {
		return causeOfDeath;
	}
	
	//get bodily system by diagnosis
	public Map<String, String> getSystemByDiagnosis() {
		return systemByDiagnosis;
	}
		
	//get hereditary diseases
	public List<String> getHereditaryDisease() {
		return hereditaryDisease;
	}
	
	//get cause of death
	public List<String> getMedications() {
		return medications;
	}
	
	//get person's occupation
	public String getOccupation() {
		return occupation;
	}
	
	//get additional notes
		public String getAdditionalNotes() {
			return additionalNotes;
		}
		
	///////////////////////////Setter methods///////////////////////////////
		
	//set personID
	public void setPersonID(String personID) {
		this.personID = personID;
	}
	
	//set first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
		
	//set last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//set date of birth
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	//set death date
	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	
	//set marital status
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	//set photo link
		public void SetPhotoLink(String photoLink) {
			this.photoLink = photoLink;
	}
		
	//set sex of person
	public void setSex(Sex sex) {
		this.sex =  sex;
	}
	
	//set father
	public void setFather(Person father) {
		this.father = father;
	}
	
	//set mother
	public void setMother(Person mother) {
		this.mother = mother;
	}
	
	//set siblings
	public void setSiblings(List<Person> siblings) {
		this.siblings = siblings;
	}
		
	//set children
	public void setChildren(List<Person> children) {
		this.children = children;
	}
		
	//set grandmother
	public void setGrandmother(Person grandmother) {
		this.grandmother = grandmother;
	}
		
	//set grandfather
	public void setGrandfather(Person grandfather) {
		this.grandfather = grandfather;
	}
	
	//set spouse
	public void setspouse(Person spouse) {
		this.spouse = spouse;
	}
	
	//set diagnosis
	public void setDiagnosis(List<String> diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	//set diagnosis
	public void setCauseOfDeath(String causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}
	
	//set bodily system by diagnosis
	public void setSystemByDiagnosis(Map<String, String> systemByDiagnosis) {
		this.systemByDiagnosis = systemByDiagnosis;
	}
		
	//set hereditary diseases
	public void setHereditaryDisease(List<String> hereditaryDisease) {
		this.hereditaryDisease = hereditaryDisease;
	}
	
	//set cause of death
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	
	//set person's occupation
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	//set additional notes
		public void setAdditionalNotes(String additionalNotes) {
			this.additionalNotes = additionalNotes;
		}
	/////////////////////////////////////////////////////////////////////
	
	//find if person is deceased
	public boolean isDeceased() {
		return dateOfDeath != null;
	}
	
	//find the age at death 
	public int getAgeAtDeath() {
		if (isDeceased() && dateOfBirth != null) {
			return Period.between(dateOfBirth, dateOfDeath).getYears();
		} else {
			return -1;
		}
	}
		
	//Find current age of a person
	public int getCurrentAge() {
		if (!isDeceased() && dateOfBirth != null) {
			return Period.between(dateOfBirth, LocalDate.now()).getYears();
		} else {
				return -1;
			}
		
		
	}
}

