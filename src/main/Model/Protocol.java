package main.Model;

import javax.persistence.*;

@Table
@Entity
@NamedQueries({
        @NamedQuery(name = "Protocol.findAll", query = "select p from Protocol p")
})
public class Protocol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Basic
    @Column(name = "car_id")
    int carId;
    @Basic
    @Column(name = "violation_id")
    int violationId;

    public Protocol() { }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setViolationId(int violationId) {
        this.violationId = violationId;
    }

    public Integer getId() {
        return id;
    }

    public int getViolationId() {
        return violationId;
    }

    public int getCarId() {
        return carId;
    }
}
