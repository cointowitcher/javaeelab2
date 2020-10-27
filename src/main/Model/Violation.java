package main.Model;

public class Violation {
    Integer id;
    String name;
    Integer fineSum;

    public void setName(String name) {
        this.name = name;
    }

    public void setFineSum(Integer fineSum) {
        this.fineSum = fineSum;
    }

    public Violation(Integer id, String name, Integer fineSum) {
        this.id = id;
        this.name = name;
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
