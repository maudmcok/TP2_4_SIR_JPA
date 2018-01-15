package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    long id ;
    String name ;
    List<ElectronicDevice> electronicDevices  = new ArrayList<ElectronicDevice>();
    List<Home> homes  = new ArrayList<Home>();
    String firstname ;
    String age ;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @ManyToMany
    public List<Person> getFreinds() {
        return freinds;
    }

    public void setFreinds(List<Person> freinds) {
        this.freinds = freinds;
    }

    List<Person> freinds = new ArrayList<Person>();


    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }



    public void setId(long id) {

        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity =ElectronicDevice.class )
    @JoinColumn(name="id_person",referencedColumnName = "id")
    public List<ElectronicDevice> getElectronicDevices() {
        return electronicDevices;
    }


    public void setElectronicDevices(List<ElectronicDevice> electronicDevices) {
        this.electronicDevices = electronicDevices;
    }

    @OneToMany(targetEntity =Home.class )
    @JoinColumn(name="id_person",referencedColumnName = "id")
    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }

    public void addHome(Home home){
        homes.add(home) ;
    }

    public void addElectro(ElectronicDevice electronicDevice){
        electronicDevices.add(electronicDevice);
    }

}
