package main.Beans;
import main.Model.Dao.ProtocolDao;
import main.Model.Protocol;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Comparator;
import java.util.List;

@ManagedBean(name="protocolsbean")
@SessionScoped
public class ProtocolsBean {
    @EJB
    private ProtocolDao protocolDao;
    private Protocol selectedProtocol;

    public ProtocolsBean() { this.protocolDao = new ProtocolDao(); }

    public Protocol getSelectedProtocol() {
        return selectedProtocol;
    }

    public void setSelectedProtocol(Protocol selectedProtocol) {
        this.selectedProtocol = selectedProtocol;
    }

    public List<Protocol> getProtocols() {
        List<Protocol> protocols = protocolDao.getAll();
        return protocols;
    }

    public String protocolDetail(Protocol protocol) {
        selectedProtocol = protocol;
        return "details";
    }

    public String saveSelectedProtocol() {
        if(selectedProtocol.getId() == null) {
            protocolDao.save(selectedProtocol);
        } else {
            protocolDao.update(selectedProtocol);
        }
        return "panel";
    }
    public String addProtocol() {
        selectedProtocol = new Protocol();
        return "details";
    }
    public String removeProtocol() {
        protocolDao.delete(selectedProtocol);
        return "panel";
    }

}
