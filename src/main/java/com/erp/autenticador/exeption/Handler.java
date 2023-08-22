package com.erp.autenticador.exeption;

import com.erp.autenticador.model.exception.*;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ClassUtils.getDescriptiveType;

@ControllerAdvice
@RestController
public class Handler {

    private final String tituloFalhaDeConexao = "Falha de conexão";
    private final String tituloNaoEncontradoComEsseParametro = "Não foi encontrado resultado com esses parâmetros";
    private final String tituloErroDePreenchimento = "Erro de preenchimento";
    private final String mensagemTenteNovamenteMaisTarde = "Tente novamente mais tarde";
    private final String OperacaoInvalida = "Operação invalida";

    private Erro novoErro(String titulo, Integer status, String exessao, String msgUser, String msgDev) {
        return new Erro(titulo, status, msgUser);
    }

    private Erro novoErroComLista(String titulo, Integer status, String msgUser, List<CampoErro> erros) {
        return new Erro(titulo, status, msgUser, erros);
    }

    public String getDevMessageFromStackTrace(Exception ex) {

        return ex.getStackTrace().toString();
    }



    //REQUISIÇÃO INVALIDA
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Erro> handlerNumberFormatException(NumberFormatException ex) {
        String titulo, msgUser, msgDev;

        titulo = "Parametro invalido";
        msgUser = "Esperava um numero, mas a entrada foi uma String";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Erro> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Erro de desserialização";
        msgUser = "Não foi possivel converter o Json em um Object";
//        msgDev = getDevMessageFromStackTrace(ex);
        msgDev = ex.getMessage();

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Endpoint inválido
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Erro> handleNoHandlerFoundException(NoHandlerFoundException ex) {

        String titulo, msgUser, msgDev, endpoint = ex.getRequestURL();

        titulo = "Endpoint não encontrado";
        msgUser = "O seguinte endpoint é inválido: " + endpoint;
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 404, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Campos vazios ou nulos (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Erro> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<CampoErro> erros = new ArrayList<>();
        String titulo, msgUser;

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.add(new CampoErro(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        titulo = "Erro de preenchimento";
        msgUser = "Os seguintes campos são invalidos: ";

        ex.printStackTrace();

        Erro erro = novoErroComLista(titulo, 409, msgUser, erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Parâmetro ausente (@RequestParam)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Erro> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        String titulo, msgUser, msgDev, parametrosAusentes = ex.getParameterName();

        titulo = "Erro de preenchimento";
        msgUser = "Os seguintes parâmetros estão ausentes: " + parametrosAusentes;
        msgDev = getDevMessageFromStackTrace(ex);
        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Tipo de parâmetro inválido (@PathVariable)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Erro> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                          WebRequest request) {

        String titulo, msgUser, msgDev, parametro = ex.getName(),
                tipoEsperado = ex.getRequiredType().getSimpleName(),
                tipoRecebido = ClassUtils.getShortName(getDescriptiveType(ex.getValue()));

        titulo = "Parâmetro inválido";
        msgUser = "Parâmetro '" + parametro + "' inválido. O tipo recebido foi " + tipoRecebido +
                ", mas era esperado receber o tipo " + tipoEsperado;
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Método HTTP não suportado
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Erro> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {

        String titulo, msgUser, msgDev, metodo = ex.getMethod();

        titulo = "Método não suportado";
        msgUser = "O método " + metodo + " não é suportado neste endpoint";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 405, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Erro> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex) {

        String titulo, msgUser, msgDev;
        titulo = "Falha de conexão";
        msgUser = "Tente novamente mais tarde";
        msgDev = getDevMessageFromStackTrace(ex);
        ex.printStackTrace();

        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }


    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<Erro> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Falha de conexão";
        msgUser = "Tente novamente mais tarde";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Erro> handleMissingPathVariableException(MissingPathVariableException ex) {

        String titulo, msgUser, msgDev;
        int status;

        titulo = "Parâmetro ausente";
        msgUser = "É necessario informar " + ex.getParameter();
        msgDev = getDevMessageFromStackTrace(ex);
        status = 500;

        ex.printStackTrace();

        Erro erro = novoErro(titulo, status, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Erro> handleConversionFailedException(ConversionFailedException ex) {

        String titulo, msgUser, msgDev;
        int status;

        titulo = "Falha de conexão";
        msgUser = "Tente novamente mais tarde";
        msgDev = getDevMessageFromStackTrace(ex);
        status = 500;

        ex.printStackTrace();

        Erro erro = novoErro(titulo, status, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<Erro> handleUnexpectedRollbackException(UnexpectedRollbackException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Falha de conexão";
        msgUser = "Tente novamente mais tarde";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    // Exceptions de recursos não encontrados -----------------------------------------------------

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Erro> handleNotFoundException(NotFound ex) {

        String titulo, msgUser, msgDev;

        titulo = "Não foram encontrados resultados para sua busca";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 404, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    //Bad Request
    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Erro> handleNotFoundException(BadRequest ex) {

        String titulo, msgUser, msgDev;

        titulo = OperacaoInvalida;
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<Erro> handleConflitoException(ConflitoException ex) {
        ex.getErros();
        String titulo, msgUser, msgDev;

        titulo = "Dado já cadastrado";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);
        ex.printStackTrace();

        Erro erro = novoErroComLista(titulo, 409, msgUser, ex.getErros());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<Erro> handleTokenInvalidoException(TokenInvalidoException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Faça login novamente";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(UserNameInvalidoExcetion.class)
    public ResponseEntity<Erro> handleUserNameInvalidoExcetion(UserNameInvalidoExcetion ex) {

        String titulo, msgUser, msgDev;

        titulo = "Nome de usuario invalido";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Erro> handleBadCredentialsException(BadCredentialsException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Erro de login: ";
        msgUser = "Usuário invalido ou senha incorreta";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 403, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<Erro> handleBadCredentialsException(InternalAuthenticationServiceException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Erro de login: ";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 403, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Erro> handleIllegalArgumentException(IllegalArgumentException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Parâmetro invalido:";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(UsuarioDesativadoException.class)
    public ResponseEntity<Erro> handleUsuarioDesativadoException(UsuarioDesativadoException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Usuário desativado";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 403, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);
    }


}
