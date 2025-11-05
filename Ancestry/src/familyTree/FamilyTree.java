/*
 * @author Logan_Hayes
 * 
 * Tree data structure for storing family tree data. each person
 * serves as a node. 
 * 
 */

package familyTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import person.Person;
import user.User;
import java.util.UUID;

public class FamilyTree {
	
	private final String treeID;
	private String treeName;
	private final User owner;
	private Person root;
	private final Map<String, Person> familyMembers;
	
	//constructor
	
	public FamilyTree(String treeName, Person root) {
		this.treeID = UUID.randomUUID().toString();
		this.treeName = treeName;
		this.owner = owner;
		this.root = root;
		this.familyMembers = new HashMap<>();
		addPerson(root);
	
	
	}
	
	///////////////Search by/////////////
	
	public Person searchByID(String personID) {
		return familyMembers.get(personID);
	}
	
	public Person searchByFirstName(String firstName) {
		return familyMembers.get(firstName);
	}
	
	public Person searchLastName(String LastName) {
		return familyMembers.get(LastName);
	}
	
	public List<Person> searchByMedicalCondition(String medicalCondition) {
		List<Person> result = new ArrayList<>();
		for(Person person : familyMembers.values()) {
			if (person.getDiagnosis() != null && person.getDiagnosis().contains(medicalCondition)){
				result.add(person);
				
			}
		}
		return result;
	}
	///////////////getters///////////////
	
	public String getTreeID() {
		return treeID;
	}
	
	public String getTreeName() {
		return treeName;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public Person getroot() {
		return root;
	}
	
	public Map<String, Person> getfamilyMembers(){
		return Collections.unmodifiableMap(familyMembers);
	}
	
	//////////////Setters/////////////////
	
	public void setTreeName() {
		this.treeName = treeName;
	}
	
	public void setRoot() {
		this.root = root;
	}
	
	
	//Method for adding a person (Node) to the family tree
	public void addPerson(Person person) {
		if (person == null) {
			System.out.print("This person is null");
			return;
			}
		if (familyMembers.containsKey(person.getPersonID())) {
			System.out.print("This person already exists in the tree");
			return;
			}
		familyMembers.put(person.getPersonID(), person);
		System.out.print(person.getFirstName() + " " + person.getLastName() + "was added to the family tree.");

			}
	
	
	//Method for removing a person (node) from the family tree
	public void removePerson(Person person) {
		if (person == null) {
			System.out.print("This person node is null");
			}
		
		if (!familyMembers.containsKey(person.getPersonID())) {
			System.out.print("This person is not found in the tree");
			return;
			}
		familyMembers.remove(person.getPersonID());
		System.out.print(person.getFirstName() + " " + person.getLastName() + "was removed from the family tree.");
			}
	}
