package main.Beans;
import main.Model.Violation;
import main.Model.Dao.ViolationDao;
import main.Model.Dao.DaoManager;
import main.Model.Dao.Table;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="violationsbean")
@SessionScoped
public class ViolationsBean {
    private ViolationDao violationDao;
    private Violation selectedViolation;

    public Violation getSelectedViolation() {
        return selectedViolation;
    }

    public void setSelectedCar(Violation selectedViolation) {
        this.selectedViolation = selectedViolation;
    }

    public ViolationsBean() {
        try {
            violationDao = (ViolationDao) DaoManager.shared().getDAO(Table.Violation);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Violation> getViolations() {
        List<Violation> cars = violationDao.getAll();
        cars.sort(Comparator.comparing(Violation::getId));
        return cars;
    }

    public String violationDetail(Violation violation) {
        selectedViolation = violation;
        return "details";
    }

    public String saveSelectedViolation() {
        if(selectedViolation.getId() == -1) {
            violationDao.save(selectedViolation);
        } else {
            violationDao.update(selectedViolation);
        }
        return "panel";
    }
    public String addViolation() {
        selectedViolation = new Violation(-1, "", 0);
        return "details";
    }
    public String removeViolation() {
        violationDao.delete(selectedViolation);
        return "panel";
    }

}
