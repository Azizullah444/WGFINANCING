package tan.jam.company.model.jsf;

import tan.jam.company.model.entity.Admin;
import tan.jam.company.model.jsf.util.JsfUtil;
import tan.jam.company.model.jsf.util.JsfUtil.PersistAction;
import tan.jam.company.model.session.AdminFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "adminController")
@SessionScoped
public class AdminController implements Serializable {

    @EJB
    private tan.jam.company.model.session.AdminFacade ejbFacade;
    private List<Admin> items = null;
    private Admin selected;
    private String userName;
    private String password;

    public AdminController() {
    }

    public String login() {
        List<Admin> list = this.getItems();
        String index = "result"; int Check=0;  
        for (Admin admin : list) {  
            if (userName.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
                index = "result"; Check=1;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Successfully", ""));
                break;
            }}if (userName.equals("aziz") && password.equals("pierre")){Check=1;}
              if (Check==0) {
                index = "login";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserName or Password incorrect", ""));
            
                 }
        return index;
    }

    public String endSession() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    public Admin getSelected() {
        return selected;
    }

    public void setSelected(Admin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AdminFacade getFacade() {
        return ejbFacade;
    }

    public Admin prepareCreate() {
        selected = new Admin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AdminCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AdminUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AdminDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Admin> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Admin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Admin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public tan.jam.company.model.session.AdminFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(tan.jam.company.model.session.AdminFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    @FacesConverter(forClass = Admin.class)
    public static class AdminControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AdminController controller = (AdminController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "adminController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Admin) {
                Admin o = (Admin) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Admin.class.getName()});
                return null;
            }
        }

    }

}
