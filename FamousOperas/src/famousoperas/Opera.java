/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package famousoperas;

/**
 * File         Class.java
 * Description  A Opera class use in famous Operas application with fields
 *      1. Name
 * `    2. Composer
 *  `   3. Year fist performed
 *      4. City where first performed
 *      5. Sybposis
 *      6. Link to Youtube
 * Platform     Windows 10, Netbean 8.2, jdk 1.8.0 101
 * Date         5/8/2017
 * Hours        1 hour
 * Class     CS143, SCC
 * @author      yu-chi.chen
 */
public class Opera {
    //instances variables
    private String name;
    private String composer;
    private int year;
    private String city;
    private String synopsis;
    private String link;
    
    //default contructor
    public Opera() {
        name = "";
        composer = "";
        year = 0;
        city = "";
        synopsis = "";
        link = "";
    }
    
    //copy constructor
    public Opera(Opera another)
    {
        this.name = another.name;
        this.composer = another.composer;
        this.year = another.year;
        this.city = another.city;
        this.synopsis = another.synopsis;
        this.link = another.link;
    }
    
    public Opera(String name, String composer, int year, String city, String synopsis, String link) {
        this.name = name;
        this.composer = composer;
        this.year = year;
        this.city = city;
        this.synopsis = synopsis;
        this.link = link;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    
     @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opera other = (Opera) obj;
        if (!this.name.equalsIgnoreCase(other.name)) 
        {
            return false;
        }
        if (!this.composer.equalsIgnoreCase(other.composer)) 
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Opera{" + "name=" + name + ", composer=" + composer + ", year=" + year + ", city=" + city + ", synopsis=" + synopsis + ", link=" + link + '}';
    }
    
}
