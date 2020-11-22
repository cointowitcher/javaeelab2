package main.Model;

import main.Services.validation.Price;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table
@SecondaryTable(name = "car_names")
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select c from Car c")
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", table = "car_names")
    @Pattern(regexp = "[A-Za-z]{3,20}")
    private String name = "";
    @Price
    private Integer price = 0;
    @Enumerated(EnumType.STRING)
    private CarClass carClass;

    public Car() {}

    public void setCarClass(CarClass carClass) { this.carClass = carClass; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CarClass getCarClass() { return carClass; }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
