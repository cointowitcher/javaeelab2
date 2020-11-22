package main.Model;

import main.Services.validation.Price;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Table
@Entity
@NamedQueries({
        @NamedQuery(name = "Violation.findAll", query = "select v from Violation v")
})
public class Violation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Size(max = 100, message = "The name of the violation should be less than 100 characters")
    String name;
    @Price
    Integer fineSum;

    public Violation() { }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) {
        this.name = name;
    }
    public void setFineSum(Integer fineSum) {
        this.fineSum = fineSum;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFineSum() {
        return fineSum;
    }
}
