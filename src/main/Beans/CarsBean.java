package main.Beans;
import main.Model.Car;
import main.Model.Dao.CarDao;
import main.Model.Dao.DaoManager;
import main.Model.Dao.Table;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="carsbean")
@SessionScoped
public class CarsBean {
    private CarDao carsDao;
    private Car selectedCar;

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public CarsBean() {
        try {
            carsDao = (CarDao) DaoManager.shared().getDAO(Table.Car);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Car> getCars() {
        List<Car> cars = carsDao.getAll();
        cars.sort(Comparator.comparing(Car::getId));
        return cars;
    }

    public String carDetail(Car car) {
        selectedCar = car;
        return "details";
    }

    public String saveSelectedCar() {
        if(selectedCar.getId() == -1) {
            carsDao.save(selectedCar);
        } else {
            carsDao.update(selectedCar);
        }
        return "panel";
    }
    public String addCar() {
        selectedCar = new Car(-1, "", 0);
        return "details";
    }
    public String removeCar() {
        carsDao.delete(selectedCar);
        return "panel";
    }

}
