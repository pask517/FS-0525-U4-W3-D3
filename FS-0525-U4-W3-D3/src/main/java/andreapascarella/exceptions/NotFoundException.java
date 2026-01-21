package andreapascarella.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super("L'utente con id:" + id + " non é stato trovato");
    }
}
