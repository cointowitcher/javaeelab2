package main.Beans;
import main.Model.Car;
import main.Model.Dao.ProtocolDao;
import main.Model.Dao.DaoManager;
import main.Model.Dao.Table;
import main.Model.Protocol;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="protocolsbean")
@SessionScoped
public class ProtocolsBean {
    private ProtocolDao protocolDao;
    private Protocol selectedProtocol;

    public Protocol getSelectedProtocol() {
        return selectedProtocol;
    }

    public void setSelectedProtocol(Protocol selectedProtocol) {
        this.selectedProtocol = selectedProtocol;
    }

    public ProtocolsBean() {
        try {
            protocolDao = (ProtocolDao) DaoManager.shared().getDAO(Table.Protocol);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Protocol> getProtocols() {
        List<Protocol> protocols = protocolDao.getAll();
        protocols.sort(Comparator.comparing(Protocol::getId));
        return protocols;
    }

    public String protocolDetail(Protocol protocol) {
        selectedProtocol = protocol;
        return "details";
    }

    public String saveSelectedProtocol() {
        if(selectedProtocol.getId() == -1) {
            protocolDao.save(selectedProtocol);
        } else {
            protocolDao.update(selectedProtocol);
        }
        return "panel";
    }
    public String addProtocol() {
        selectedProtocol = new Protocol(-1, 0, 0);
        return "details";
    }
    public String removeProtocol() {
        protocolDao.delete(selectedProtocol);
        return "panel";
    }

}
