package br.com.uniliva.ucontas.handlers;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer code;
    private String message;

}