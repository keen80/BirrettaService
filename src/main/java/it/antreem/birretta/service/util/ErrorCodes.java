package it.antreem.birretta.service.util;

/**
 *
 * @author alessio
 */
public enum ErrorCodes 
{   
    LOGIN_FAILED(200, "Credenziali di login ricevute a null"),
    LOOUT_FAILED(201, "Parametri di logout ricevuti a null"),
    REG_FAILED(202, "Registration failed"),
    REG_U01(203, "Lo username deve essere lungo almeno 5 caratteri e contenere solamente caratteri alfanumerici minuscoli e underscore"),
    REG_P01(204, "La password deve essere lunga almeno 5 caratteri e contenere solamente caratteri alfanumerici e underscore"),
    REG_USER_DUP(205, "Lo username scelto e' gia' presente. Occorre fare una scelta alternativa"),
    REG_INVALID_EMAIL(206, "Email non valida"),
    REG_INVALID_SEX(207, "Deve essere M o F"),
    REG_INVALID_FIRST(208, "First name non valido"),
    REG_INVALID_LAST(209, "Last name non valido"),
    REG_INVALID_AGE(210, "Eta non valida"),
    REQ_DELEGATION_BLOCKED(211, "L'utente ha effettuato richieste per conto di un altro utente. Non possibile."),
    UPPOS_MISSED_PARAM(212, "Specificare la tripletta (username, lat, lon) interamente."),
    INSLOC_WRONG_PARAM(213, "Specificare i parametri necessari nel modo corretto."),
    INSLOC_WRONG_NAME_PARAM(214, "Specificare il nome in maniera corretta."),
    INSLOC_WRONG_POS_PARAM(215, "Specificare la posizione nel modo corretto."),
    INSLOC_WRONG_NULL_TIPOLOC_PARAM(16, "Tipo località non fornita"),
    INSLOC_WRONG_TIPOLOC_PARAM(217, "Tipo località non esistente"),
    INSLOC_LOC_DUP(218, "Una location con lo stesso nome e' gia' presente"),
    INSBEER_BEER_DUP(219, "Una birra con lo stesso nome e' gia' presente"),
    INSBEER_WRONG_PARAM(220, "Specificare i parametri necessari nel modo corretto."),
    CHECKIN_WRONG_PARAM(221, "Specificare i parametri di check-in in modo corretto"),
    CHECKIN_TOO_MANY_DRINKS(222, "Aspetta un po' brutto alcolizzato!!!"),
    FINDLOCNEAR_WRONG_PARAM(223, "Specificare entrambe longitudine e latitudine"),
    USER_NOT_FOUND(224, "Amici immaginari non ammessi"),
    FRND_MISSED_PARAM(225, "Parametri mancanti"),
    FRND_REFUSE_ERROR(226, "Impossibile Rifiutare"),
    UPDATE_NOTIFICANION_ERROR_00(227, "Notifica non presente"),
    NULL_USER(228, "Utente non fornito"),
    NULL_BEER(229, "IdBeer non valorizzato"),
    MISSING_BEER(230, "Birra non esistente"),
    NULL_PLACE(231, "idPlace non valorizzato"),
    MISSING_PLACE(232, "Locale non esistente"),
    NULL_ACTIVITY(233,"idActivity non fornita"),
    SAVE_FEEDBACK_ERROR(234,"Impossibile salvare il Feedback"),
    PROSSIMO_ERRORE(235,""),
    WARN_FRNDREQ_01(297, "Richiesta eseguita gia' eseguita e pendente"),
    WARN_FRNDREQ_02(298, "Amicizia gia' confermata"),
    WARN_FRNDCONFIRM_00(299,"Richiesta di amicizia non presente"),
    SAVE_ERROR(300,"errore nel salvataggio"),
    PUPPA_CODE(9999, "PuppaMessage");
    
    private int code;
    private String message;
    
    ErrorCodes(int code, String message)
    {
        this.code = code;
        this.message = message;
    }
    
    public String getTitle() {
        return name();//.replace("_", ".");
    }
    
    public int getCode(){
        return code;
    }
    
    public String getMessage(){
        return message;
    }
}
