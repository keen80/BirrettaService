package it.antreem.birretta.service;

import it.antreem.birretta.service.model.json.Metadata;
import it.antreem.birretta.service.model.json.Status;
import it.antreem.birretta.service.model.json.BeerSingle;
import it.antreem.birretta.service.dao.DaoException;
import it.antreem.birretta.service.dao.DaoFactory;
import it.antreem.birretta.service.dto.*;
import it.antreem.birretta.service.model.*;
import it.antreem.birretta.service.util.ErrorCodes;
import it.antreem.birretta.service.util.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.util.JSONPObject;
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
        
        log.info("Tentativo di login di username: " + username + " con hash pwd: " + hash); 
        
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
    
    @POST
    @Path("/logout")
    @Consumes("application/json")
    @Produces("application/json")
    public Response logout(LogoutRequestDTO req, @Context HttpServletRequest httpReq) 
    {
        // Pre-conditions
        if (req == null)
        {
            log.debug("Parametri di logout passati a null. Errore.");
            return createJsonErrorResponse(ErrorCodes.LOOUT_FAILED);
        }
        
        // Blocco richieste di un utente per un altro
        if (!req.getUsername().equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        // Delete any existing session
        Session s = DaoFactory.getInstance().getSessionDao().findSessionByUsername(req.getUsername());
        if (s != null){
            DaoFactory.getInstance().getSessionDao().deleteSessionBySid(s.getSid());
        }
        
        GenericResultDTO res = new GenericResultDTO();
        res.setSuccess(true);
        res.setMessage("Logout eseguito con successo");
        
        return createJsonOkResponse(res);
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
        
        GenericResultDTO response = new GenericResultDTO();
        
        String username = r.getUsername() != null ? r.getUsername() : "";
        String password = r.getPassword() != null ? r.getPassword() : "";
        String hash = Utils.SHAsum(Utils.SALT.concat(username).concat(password).getBytes());

        log.info("Tentativo di registrazione di username: " + username + " con hash pwd: " + hash); 
        
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
    
    @GET
    @Path("/updatePos")
    @Produces("application/json")
    public Response updatePos (@QueryParam("username") String username,
                               @QueryParam("lon") Double lon,
                               @QueryParam("lat") Double lat,
                               @Context HttpServletRequest httpReq) 
    {
        // Pre-conditions
        if (username == null || lat == null || lon == null){
            return createJsonErrorResponse(ErrorCodes.UPPOS_MISSED_PARAM);
        }
        
        // Blocco richieste di un utente per un altro
        if (!username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        DaoFactory.getInstance().getGeoLocDao().updateLoc(u.getId().toString(), lon, lat);
        
        return createJsonOkResponse(new GenericResultDTO(true, "Operazione eseguita correttamente"));
    }
    
    @GET
    @Path("/findLocType")
    @Produces("application/json")
    public Response findLocType (@QueryParam("cod") final String _cod)
    {
        String cod = _cod == null ? "" : _cod;
        List<LocType> list = DaoFactory.getInstance().getLocTypeDao().findLocTypesByCodLike(cod);
        return createJsonOkResponse(list);
    }
    
    @GET
    @Path("/findLocation")
    @Produces("application/json")
    public Response findLocation (@QueryParam("name") final String _name)
    {
        String name = _name == null ? "" : _name;
        List<Location> list = DaoFactory.getInstance().getLocationDao().findLocationsByNameLike(name);
        return createJsonOkResponse(list);
    }
    
    @GET
    @Path("/findLocNear")
    @Produces("application/json")
    public Response findLocNear (@QueryParam("lon") final Double lon,
                                 @QueryParam("lat") final Double lat,
                                 @DefaultValue("0.8") @QueryParam("radius") final Double radius)
    {
        if (lon == null || lat == null){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_PARAM);
        }
        
        List<Location> list = DaoFactory.getInstance().getLocationDao().findLocationNear(lat, lon, radius);
        return createJsonOkResponse(list);
    }
    
    @POST
    @Path("/insertLoc")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertLoc(Location l) 
    {
        // Pre-conditions
        if (l == null){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_PARAM);
        }
        
        // Validazione parametri di input
        // TODO: Creare messaggi di errore appositi per ogni errore
        if (l.getName() == null || l.getName().length() < 2){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_NAME_PARAM);
        }
        if (l.getPos() == null || l.getPos().size() != 2){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_POS_PARAM);
        }
        if (l.getIdLocType() == null){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_NULL_TIPOLOC_PARAM);
        }
        // Controllo LocType 
        /* gmorlini:
         * in precedenza veniva fatto findLocTypeById
         * più sensato fare findLocTypeByCod
         */
        LocType type = DaoFactory.getInstance().getLocTypeDao().findLocTypeByCod(l.getIdLocType());
        if (type == null){
            return createJsonErrorResponse(ErrorCodes.INSLOC_WRONG_TIPOLOC_PARAM);
        }
        
        // Inserimento su DB
        Location _l = DaoFactory.getInstance().getLocationDao().findLocationByName(l.getName());
        if (_l != null){
            return createJsonErrorResponse(ErrorCodes.INSLOC_LOC_DUP);
        }
        log.debug("location valida");
        DaoFactory.getInstance().getLocationDao().saveLocation(l);
        
        GenericResultDTO result = new GenericResultDTO(true, "Inserimento eseguito con successo");
        return createJsonOkResponse(result);
    }
    
    
    @GET
    @Path("/findBeerById")
    @Produces("application/json")
    public Response findBeerById (@QueryParam("id") final String _id)
    {
        String id = _id == null ? "" : _id;
        Beer b = DaoFactory.getInstance().getBeerDao().findById(id);
        return createJsonOkResponse(b);
    }
    
    @GET
    @Path("/findUserById")
    @Produces("application/json")
    public Response findUserById (@QueryParam("id") final String _id)
    {
        String id = _id == null ? "" : _id;
        User u = DaoFactory.getInstance().getUserDao().findById(id);
        return createJsonOkResponse(u);
    }
    
    @GET
    @Path("/findLocById")
    @Produces("application/json")
    public Response findLocById (@QueryParam("id") final String _id)
    {
        String id = _id == null ? "" : _id;
        Location l = DaoFactory.getInstance().getLocationDao().findById(id);
        return createJsonOkResponse(l);
    }
    
    @GET
    @Path("/findLocTypeById")
    @Produces("application/json")
    public Response findLocTypeById (@QueryParam("id") final String _id)
    {
        String id = _id == null ? "" : _id;
        LocType l = DaoFactory.getInstance().getLocTypeDao().findById(id);
        return createJsonOkResponse(l);
    }
    
    @GET
    @Path("/findBeer")
    @Produces("application/json")
    public Response findBeer (@QueryParam("name") final String _name)
    {
        String name = _name == null ? "" : _name;
        List<Beer> list = DaoFactory.getInstance().getBeerDao().findBeersByNameLike(name);
        return createJsonOkResponse(list);
    }
    /**
     * Restituisce le birre con tutti i relativi dettagli in formato JSONP.
     */
    @GET
    @Path("/listBeer_jsonp")
    @Produces("application/json")
    public JSONPObject listBeer_jsonp (
	@QueryParam("maxElement") final String _maxElemet,
	@DefaultValue("callback") @QueryParam("callback") String callbackName,
        @DefaultValue("complete") @QueryParam("details") String details)
    {
		return new JSONPObject(callbackName,listBeer(_maxElemet,details));
	}
     /**
     * Restituisce le birre con tutti i relativi dettagli in formato JSON.
     */
    @GET
    @Path("/listBeer")
    @Produces("application/json")
    public ResultDTO listBeer (@QueryParam("maxElement") final String _maxElemet,
                               @DefaultValue("complete") @QueryParam("details") String details)
    {
        log.info("reuest list of "+_maxElemet+" beer "+details);
        int maxElemet = _maxElemet == null ? -1 : new Integer(_maxElemet);
        if(details.equalsIgnoreCase("single"))
            
            //lista elementi semplificata
        {
        ArrayList<BeerSingle> list = DaoFactory.getInstance().getBeerDao().listBeerSingle(maxElemet);
        ResultDTO result = new ResultDTO();
        Status status= new Status();
        status.setCode("OK");
        status.setMsg("Status OK");
        status.setSuccess(true);
        Body body =new Body<BeerSingle>();
        body.setList(list);
        Metadata metaData = new Metadata();
        metaData.setBadge("OK", 1, "Notification OK");
        metaData.setNotification("OK", 1, "Notification OK");       
        it.antreem.birretta.service.model.json.Response response = new it.antreem.birretta.service.model.json.Response(status, body, metaData);
        result.setResponse(response);
        return result;
        }
        else
            //details=complete lista completa dettagli birra
        {
        ArrayList<Beer> list = DaoFactory.getInstance().getBeerDao().listBeer(maxElemet);
        ResultDTO result = new ResultDTO();
        Status status= new Status();
        status.setCode("OK");
        status.setMsg("Status OK");
        status.setSuccess(true);
        Body body =new Body<Beer>();
        body.setList(list);
        Metadata metaData = new Metadata();
   //     metaData.setBadge("OK", 1, "Notification OK");
    //    metaData.setNotification("OK", 1, "Notification OK");
        
            it.antreem.birretta.service.model.json.Response response = new it.antreem.birretta.service.model.json.Response(status, body, metaData);
        result.setResponse(response);
        return result;
        }
        
    }
    /**
     * Restituisce le birre con tutti i relativi dettagli in formato JSONP.
     */
    @GET
    @Path("/listDrink_jsonp")
    @Produces("application/json")
    public JSONPObject listDrink_jsonp (
	@QueryParam("maxElement") final String _maxElemet,
	@DefaultValue("callback") @QueryParam("callback") String callbackName)
    {
		return new JSONPObject(callbackName,listDrink(_maxElemet));
	}
     /**
     * Restituisce le birre con tutti i relativi dettagli in formato JSON.
     */
    @GET
    @Path("/listDrink")
    @Produces("application/json")
    public ResultDTO listDrink (@QueryParam("maxElement") final String _maxElemet)
    {
        log.info("reuest list of "+_maxElemet+" drink");
        int maxElemet = _maxElemet == null ? -1 : new Integer(_maxElemet);
        ArrayList<Drink> list = DaoFactory.getInstance().getDrinkDao().getDrinksList(maxElemet);
        ResultDTO result = new ResultDTO();
        Status status= new Status();
        status.setCode("OK");
        status.setMsg("Status OK");
        status.setSuccess(true);
        Body body =new Body<Drink>();
        body.setList(list);
        Metadata metaData = new Metadata();
        metaData.setBadge("OK", 1, "Notification OK");
        metaData.setNotification("OK", 1, "Notification OK");
     
        it.antreem.birretta.service.model.json.Response response = new it.antreem.birretta.service.model.json.Response(status, body, metaData);
        result.setResponse(response);
        return result;
        
        
    }
    @POST
    @Path("/insertBeer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertBeer(Beer b) 
    {
        // Pre-conditions
        if (b == null){
            return createJsonErrorResponse(ErrorCodes.INSBEER_WRONG_PARAM);
        }
        
        // Validazione parametri di input
        // TODO: Creare messaggi di errore appositi per ogni errore
        if (b.getName() == null || b.getName().length() < 2){
            return createJsonErrorResponse(ErrorCodes.INSBEER_WRONG_PARAM);
        }
       
        // Controllo duplicati
        Beer _b = DaoFactory.getInstance().getBeerDao().findBeerByName(b.getName());
        if (_b != null){
            return createJsonErrorResponse(ErrorCodes.INSBEER_BEER_DUP);
        }
        
        // Inserimento su DB
        DaoFactory.getInstance().getBeerDao().saveBeer(b);
        
        GenericResultDTO result = new GenericResultDTO(true, "Inserimento eseguito con successo");
        return createJsonOkResponse(result);
    }
    
    /**
     * Operazione di check-in.
     * TODO: Controllo di prossimita' location =&gt; future versioni
     * 
     * @param c Richiesta di check-in
     * @param httpReq Header HTTP per blocco richieste cross-user
     * @return Esito operazione o errore
     */
    @POST
    @Path("/checkIn")
    @Consumes("application/json")
    @Produces("application/json")
    public Response checkIn(CheckInRequestDTO c, @Context HttpServletRequest httpReq) 
    {
        // Pre-conditions + controllo validita' parametri
        if (c == null){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_WRONG_PARAM);
        }
        if (c.getUsername() == null || c.getIdBeer() == null || c.getIdLocation() == null){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_WRONG_PARAM);
        }
        /* Puo' esserci una bevuta senza voto? per ora si'...
        // TODO: Eventuale check di check-in senza voto
        if (c.getIdFeedback() != null && (c.getScore() < 0 || c.getScore() > 10)){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_WRONG_PARAM);
        }
        */
        // Blocco richieste di un utente per un altro
        if (!c.getUsername().equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        // Recupero dati necessari (utente dovrebbe essere ok perche' ha passato il controllo precedente)
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(c.getUsername());
        Beer b = DaoFactory.getInstance().getBeerDao().findById(c.getIdBeer());
        Location l = DaoFactory.getInstance().getLocationDao().findById(c.getIdLocation());
        
        // Controllo che location e birra effettivamente esistano
        if (b == null){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_WRONG_PARAM);
        }
        if (l == null){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_WRONG_PARAM);
        }
        
        // Controllo che negli ultimi 10 minuti non ci siano piu' di tre bevute
        List<Drink> lastDrinks = DaoFactory.getInstance().getDrinkDao().findRecentDrinks(u.getUsername(), 10);
        if (lastDrinks.size() >= 3){
            return createJsonErrorResponse(ErrorCodes.CHECKIN_TOO_MANY_DRINKS);
        }
        
        // Preparazione oggetto di modello
        Drink d = new Drink();
        d.setIdBeer(c.getIdBeer());
        d.setIdPlace(c.getIdLocation());
        d.setIdUser(u.getIdUser());
        d.setImage(c.getPicture());
        d.setIdFeedback(c.getIdFeedback());
        d.setTimestamp(new Date());
        
        // Scrittura su DB
        DaoFactory.getInstance().getDrinkDao().saveDrink(d);
                
        // Ricerca di nuovi badge e premi da assegnare scatenati da questo check-in
        List<Badge> newBadges = Utils.checkBadges(c.getUsername());
        if (newBadges != null && !newBadges.isEmpty()){
            DaoFactory.getInstance().getBadgeDao().saveUserBadges(c.getUsername(), newBadges);
        }
        
        // Controllo mayorships + notifiche a chi le ha perdute
        // TODO: controllo mayorships + notifiche a chi le ha perdute
        
        GenericResultDTO result = new GenericResultDTO(true, "Check-in eseguito con successo");
        return createJsonOkResponse(result);
    }
    
    
    @GET
    @Path("/findMyDrinks")
    @Produces("application/json")
    public Response findMyDrinks (@QueryParam("username") final String username, 
                                  @DefaultValue("10") @QueryParam("limit") Integer limit, 
                                  @Context HttpServletRequest httpReq)
    {
        // Blocco richieste di un utente per un altro
        if (username == null || !username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        if (limit < 0 || limit > 100) {
            limit = 10;
        }
        List<Drink> list = DaoFactory.getInstance().getDrinkDao().findDrinksByUsername(username, limit);
        return createJsonOkResponse(list);
    }
    
    @GET
    @Path("/findMyBadges")
    @Produces("application/json")
    public Response findMyBadges (@QueryParam("username") final String username, 
                                  @Context HttpServletRequest httpReq)
    {
        // Blocco richieste di un utente per un altro
        if (username == null || !username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        List<Badge> list = DaoFactory.getInstance().getBadgeDao().findUserBadges(username);
        return createJsonOkResponse(list);
    }
    
    @GET
    @Path("/findUsers")
    @Produces("application/json")
    public Response findUsers (@QueryParam("username") String username, 
                               @QueryParam("first") String first,
                               @QueryParam("last") String last, 
                               @Context HttpServletRequest httpReq)
    {
        List<User> list = DaoFactory.getInstance().getUserDao().findUsers(username, first, last);
        return createJsonOkResponse(list);
    }
    
    
    @POST
    @Path("/frndReq")
    @Consumes("application/json")
    @Produces("application/json")
    public Response frndReq(FriendReqDTO c, @Context HttpServletRequest httpReq) 
    {
        if (c == null){
            return createJsonErrorResponse(ErrorCodes.FRND_MISSED_PARAM);
        }
        
        String myid = c.getIdRequestor();
        String frndid = c.getIdRequested();
        
        User me = DaoFactory.getInstance().getUserDao().findById(myid);
        String username = me.getUsername();
        if (username == null || !username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        User frnd = DaoFactory.getInstance().getUserDao().findById(frndid);
        if (frnd == null){
            return createJsonErrorResponse(ErrorCodes.USER_NOT_FOUND);
        }
        
        if (!DaoFactory.getInstance().getFriendReqDao().existFriendReq(myid, frndid)
            && !DaoFactory.getInstance().getFriendDao().areFriends(myid, frndid)){
            DaoFactory.getInstance().getFriendReqDao().saveFriendReq(myid, frndid);
        }
        
        GenericResultDTO result = new GenericResultDTO(true, "Richiesta eseguita con successo");
        return createJsonOkResponse(result);
    }
    
    @POST
    @Path("/frndConfirm")
    @Consumes("application/json")
    @Produces("application/json")
    public Response frndConfirm(FriendReqDTO c, @Context HttpServletRequest httpReq) 
    {
        if (c == null){
            return createJsonErrorResponse(ErrorCodes.FRND_MISSED_PARAM);
        }
        
        String myid = c.getIdRequested();
        String frndid = c.getIdRequestor();
        
        User me = DaoFactory.getInstance().getUserDao().findById(myid);
        String username = me.getUsername();
        if (username == null || !username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        
        User frnd = DaoFactory.getInstance().getUserDao().findById(frndid);
        if (frnd == null){
            return createJsonErrorResponse(ErrorCodes.USER_NOT_FOUND);
        }
        
        // Se esiste la richiesta effettivamente la confermo
        if (DaoFactory.getInstance().getFriendReqDao().existFriendReq(frndid, myid)){
            DaoFactory.getInstance().getFriendDao().saveFriendship(myid, frndid);
            DaoFactory.getInstance().getFriendReqDao().deleteFriendReq(frndid, myid);
            
            GenericResultDTO result = new GenericResultDTO(true, "Amicizia accettata con successo");
            return createJsonOkResponse(result);
        }
        else {
            GenericResultDTO result = new GenericResultDTO(false, "Richiesta di amicizia non presente");
            return createJsonOkResponse(result);
        }
    }
    
    @POST
    @Path("/frndRefuse")
    @Consumes("application/json")
    @Produces("application/json")
    public Response frndRefuse(FriendReqDTO c, @Context HttpServletRequest httpReq) 
    {
        if (c == null){
            return createJsonErrorResponse(ErrorCodes.FRND_MISSED_PARAM);
        }
        
        String id1 = c.getIdRequested();
        String id2 = c.getIdRequestor();
        User me = DaoFactory.getInstance().getUserDao().findById(id1);
        String username = me.getUsername();
        if (username != null && username.equals(httpReq.getHeader("btUsername")))
        {
            DaoFactory.getInstance().getFriendReqDao().deleteFriendReq(id1, id2);
            DaoFactory.getInstance().getFriendDao().deleteFriendship(id1, id2);
            GenericResultDTO result = new GenericResultDTO(true, "Amicizia rimossa con successo");
            return createJsonOkResponse(result);
        }
        
        me = DaoFactory.getInstance().getUserDao().findById(id2);
        username = me.getUsername();
        if (username != null && username.equals(httpReq.getHeader("btUsername")))
        {
            DaoFactory.getInstance().getFriendReqDao().deleteFriendReq(id1, id2);
            DaoFactory.getInstance().getFriendDao().deleteFriendship(id1, id2);
            GenericResultDTO result = new GenericResultDTO(true, "Amicizia rimossa con successo");
            return createJsonOkResponse(result);
        }
        
        return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
    }
    
    @GET
    @Path("/findFrndReqs")
    @Consumes("application/json")
    @Produces("application/json")
    public Response findFrndReqs(@QueryParam("username") final String username, 
                                 @Context HttpServletRequest httpReq) 
    {
        // Blocco richieste di un utente per un altro
        if (username == null || !username.equals(httpReq.getHeader("btUsername"))){
            return createJsonErrorResponse(ErrorCodes.REQ_DELEGATION_BLOCKED);
        }
        User u = DaoFactory.getInstance().getUserDao().findUserByUsername(username);
        List<String> pendingReqs = DaoFactory.getInstance().getFriendReqDao().findPendingReqs(u.getIdUser());
        return createJsonOkResponse(pendingReqs);
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
    
    /**
     * Metodo di echo di prova per verifica di sessione.
     * 
     * @param value
     * @return 
     */
    @GET
    @Path("/echo")
    @Produces("text/html")
    public String echo (@DefaultValue("puppa") @QueryParam("value") String value) 
    {
        return value;
    }
}
