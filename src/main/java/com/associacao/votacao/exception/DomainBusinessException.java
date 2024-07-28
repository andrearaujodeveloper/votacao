package com.associacao.votacao.exception;

public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String mensagem){
        super(mensagem);
    }

}
