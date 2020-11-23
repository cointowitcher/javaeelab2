package main.Beans;
import main.Model.Car;
import main.Model.CarClass;
import main.Model.Dao.CarDao;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="carsbean")
@SessionScoped
public class CarsBean {
    @EJB
    private CarDao carsDao;
    private Car selectedCar;

    public CarsBean() { carsDao = new CarDao(); }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public List<Car> getCars() {
        List<Car> cars = carsDao.getAll();
        return cars;
    }

    public String carDetail(Car car) {
        selectedCar = car;
        return "details";
    }

    public String saveSelectedCar() {
        if(selectedCar.getId() == null) {
            carsDao.save(selectedCar);
        } else {
            carsDao.update(selectedCar);
        }
        return "panel";
    }
    public String addCar() {
        selectedCar = new Car();
        return "details";
    }
    public String removeCar() {
        carsDao.delete(selectedCar);
        return "panel";
    }
}
