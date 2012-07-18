package it.antreem.birretta.service;

import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dto.*;
import it.antreem.birretta.service.model.Session;
import it.antreem.birretta.service.model.User;
import it.antreem.birretta.service.util.ErrorCodes;
import it.antreem.birretta.service.util.Utils;
import java.util.Date;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * BirrettaService
 */
@Path("/bserv")
public class BirrettaService
{
    private static final Log log = LogFactory.getLog(BirrettaService.class);
    
    /**
     * Operazione di login dell'utente.<br/>
     * Nel caso l'utente faccia login correttamente, viene creata una sessione
     * attiva su DB oppure si continua ad utilizzare quella corrente.<br/>
     * In caso di login fallito si ritorna esito negativo (possibile estensione
     * futura con blocco utente, etc.).
     * 
     * @param c Credenziali dell'utente, ovvero username + password.
     * @return Esito della login
     */
    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(CredentialsDTO c) 
    {
        // Pre-conditions
        if (c == null)
        {
            log.debug("Credenziali di login passate a null. Errore.");
            return createJsonErrorResponse(ErrorCodes.LOGIN_FAILED);
        }
        
        LoginResponseDTO response = new LoginResponseDTO();
        
        String username = c.getUsername() != null ? c.getUsername() : "";
        String password = c.getPassword() != null ? c.getPassword() : "";
        String hash = Utils.SHAsum(Utils.SALT.concat(username).concat(password).getBytes());
        
        if (log.isDebugEnabled()){
            log.debug("Tentativo di login di username: " + username + " con hash pwd: " + hash); 
        }
        
        /*
         * Login failed
         *  - return an error
         */
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (u == null || !u.getPwdHash().equals(hash)){
            response.setSuccess(false);
            response.setMessage("Login fallito. Credenziali utente errate.");
            response.setSessionId(null);

            return createJsonOkResponse(response);
        }
        
        /*
         * Login successful
         *  - is there an active session? use that one
         *  - else create a new session
         */
        Session s = DaoFactory.getInstance().getSessionDao().findSessionByUsername(username);
        if (s == null) {
            s = new Session();
            s.setUsername(username);
            s.setSid(UUID.randomUUID().toString());
            s.setTimestamp(new Date());
            DaoFactory.getInstance().getSessionDao().saveSession(s);
        }
        
        response.setSuccess(true);
        response.setMessage("Login eseguito correttamente.");
        response.setSessionId(s.getSid());
        
        return createJsonOkResponse(response);
    }
    
    
    /**
     * Operazione di registrazione di un nuovo utente.<br/>
     * @param c Credenziali dell'utente, ovvero username + password.
     * @return Esito della login
     */
    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(RegistrationRequestDTO r) 
    {
        // Pre-conditions
        if (r == null)
        {
            log.debug("Dati di registrazione passate a null. Errore.");
            return createJsonErrorResponse(ErrorCodes.LOGIN_FAILED);
        }
        
        RegistrationResponseDTO response = new RegistrationResponseDTO();
        
        String username = r.getUsername() != null ? r.getUsername() : "";
        String password = r.getPassword() != null ? r.getPassword() : "";
        String hash = Utils.SHAsum(Utils.SALT.concat(username).concat(password).getBytes());
        
        if (log.isDebugEnabled()){
            log.debug("Tentativo di registrazione di username: " + username + " con hash pwd: " + hash); 
        }
        
        //-----------------------
        // Controlli di validita
        //-----------------------
        if (username.length() < 5 || !username.matches("^[a-z0-9_]*$")){
            return createJsonErrorResponse(ErrorCodes.REG_U01);
        }
        if (password.length() < 5 || !password.matches("^[a-zA-Z0-9_]*$")){
            return createJsonErrorResponse(ErrorCodes.REG_P01);
        }
        // ^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$
        if (!r.getEmail().matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")){
            return createJsonErrorResponse(ErrorCodes.REG_INVALID_EMAIL);
        }
        if (r.getSex() == null || (!r.getSex().toUpperCase().equals("M") && !r.getSex().toUpperCase().equals("F"))){
            return createJsonErrorResponse(ErrorCodes.REG_INVALID_SEX);
        }
        if (r.getAge() != null && (r.getAge() < 5 || r.getAge() > 110)){
            return createJsonErrorResponse(ErrorCodes.REG_INVALID_AGE);
        }
        if (r.getFirstName() == null || r.getFirstName().length() < 2){
            return createJsonErrorResponse(ErrorCodes.REG_INVALID_FIRST);
        }
        if (r.getLastName() == null || r.getLastName().length() < 2){
            return createJsonErrorResponse(ErrorCodes.REG_INVALID_LAST);
        }
        //-----------------------------
        // Fine controlli di validita
        //-----------------------------
        
        // Se username gia' presente, errore...
        User _u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        if (_u != null && _u.getUsername().equals(username)){
            return createJsonErrorResponse(ErrorCodes.REG_USER_DUP);
        }
        
        // ...altrimenti registrazione con successo.
        User newuser = new User();
        newuser.setAge(r.getAge());
        newuser.setEmail(r.getEmail());
        newuser.setFirstName(r.getFirstName());
        newuser.setLastName(r.getLastName());
        newuser.setSex(r.getSex().toUpperCase());
        newuser.setUsername(r.getUsername());
        newuser.setPwdHash(hash);
        
        DaoFactory.getInstance().getUserDao().saveUser(newuser);
        
        response.setSuccess(true);
        response.setMessage("Registrazione eseguita correttamente.");
        
        return createJsonOkResponse(response);
    }
    
    
    protected static Response createJsonOkResponse(Object o) {
        Response.ResponseBuilder builder = Response.ok(o, MediaType.APPLICATION_JSON);
        return builder.build();
    }
    
    protected static Response createJsonErrorResponse(ErrorCodes e, Object... actionType) {
        ErrorDTO err = Utils.createError(e, actionType);
        Response.ResponseBuilder builder = Response.ok(err, MediaType.APPLICATION_JSON);
        return builder.build();
    }
}
