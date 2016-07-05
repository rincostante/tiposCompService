
package ar.com.rizoma.tiposcompservice.ws;

import ar.com.rizoma.tiposcompservice.entities.Persona;
import ar.com.rizoma.tiposcompservice.facades.PersonaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author Administrador
 */
@WebService(serviceName = "TiposComplejosWs")
@Stateless()
public class TiposComplejosWs {
    
    @EJB
    private PersonaFacade personaFacade;

    @WebMethod(operationName = "insertarPersona")
    public void createTipo(@WebParam(name = "persona") Persona persona) {
        System.out.println("Iniciando método del servicio llamado desde el cliente");
        Persona per = new Persona();
        per.setDni(Long.valueOf("36789457"));
        per.setEdad(56);
        per.setNombre("Enrique Cadícamo");
        //personaFacade.create(per);
        System.out.println("Devolviendo resultados");
    }
    
    @WebMethod(operationName = "listarPersonas")
    public List<Persona> getPersonas(){
        return personaFacade.findAll();
    }
}
