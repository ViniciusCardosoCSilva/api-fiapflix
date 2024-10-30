package br.com.fiap.api_fiapflix.service.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
