package main.Beans;
import main.Model.Violation;
import main.Model.Dao.ViolationDao;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="violationsbean")
@SessionScoped
public class ViolationsBean {
    private ViolationDao violationDao;
    private Violation selectedViolation;
    public ViolationsBean() {
        violationDao = new ViolationDao();
    }
    public Violation getSelectedViolation() {
        return selectedViolation;
    }

    public void setSelectedCar(Violation selectedViolation) {
        this.selectedViolation = selectedViolation;
    }

    public List<Violation> getViolations() {
        List<Violation> violations = violationDao.getAll();
        return violations;
    }

    public String violationDetail(Violation violation) {
        selectedViolation = violation;
        return "details";
    }

    public String saveSelectedViolation() {
        if(selectedViolation.getId() == null) {
            violationDao.save(selectedViolation);
        } else {
            violationDao.update(selectedViolation);
        }
        return "panel";
    }
    public String addViolation() {
        selectedViolation = new Violation();
        return "details";
    }
    public String removeViolation() {
        violationDao.delete(selectedViolation);
        return "panel";
    }

}
