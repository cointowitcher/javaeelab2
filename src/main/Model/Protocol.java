package main.Model;

public class Protocol {
    Integer id;
    int carId;

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setViolationId(int violationId) {
        this.violationId = violationId;
    }

    int violationId;

    public Protocol(Integer id, int carId, int violationId) {
        this.id = id;
        this.carId = carId;
        this.violationId = violationId;
//        HttpServletRe
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
