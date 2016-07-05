
package ar.com.rizoma.tiposcompservice.mb;

import ar.com.rizoma.tiposcompservice.entities.Persona;
import ar.com.rizoma.tiposcompservice.facades.PersonaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Administrador
 */
public class MbPersona implements Serializable{

    private Persona persona;
    private List<Persona> listPersona;
    
    @EJB
    private PersonaFacade perFacade;
    
    public MbPersona() {
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListPersona() {
        listPersona = perFacade.findAll();
        return listPersona;
    }

    public void setListPersona(List<Persona> listPersona) {
        this.listPersona = listPersona;
    }
    
    
    @PostConstruct
    public void init(){
        persona = new Persona();
        listPersona = new ArrayList<>();
    }
    
    public void createPersona(){
        try{
            perFacade.create(persona);
            persona = new Persona();
        }catch(Exception ex){
            System.out.println("Hubo un error insertando la persona" + ex.getMessage());
        }
    }
    
    
    public Persona getPersona(java.lang.Long id) {
        return perFacade.find(id);
    } 
    
    @FacesConverter(forClass = Persona.class)
    public static class PersonaControllerConverter implements Converter { 

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MbPersona controller = (MbPersona) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mbCentroPoblado");
            return controller.getPersona(getKey(value));
        }
        
        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
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
            if (object instanceof Persona) {
                Persona o = (Persona) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Persona.class.getName());
            }
        }
    }   
}
