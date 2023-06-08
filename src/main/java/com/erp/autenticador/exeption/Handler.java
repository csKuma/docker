package com.erp.autenticador.exeption;

import com.erp.autenticador.model.exception.BadRequest;
import com.erp.autenticador.model.exception.ConflitoException;
import com.erp.autenticador.model.exception.Erro;
import com.erp.autenticador.model.exception.NotFound;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

import static org.springframework.util.ClassUtils.getDescriptiveType;

@ControllerAdvice
@RestController
public class Handler {

    private final String tituloFalhaDeConexao = "Falha de conexão";
    private final String tituloNaoEncontradoComEsseParametro = "Não foi encontrado resultado com esses parâmetros";
    private final String tituloErroDePreenchimento = "Erro de preenchimento";
    private final String mensagemTenteNovamenteMaisTarde = "Tente novamente mais tarde";

    private Erro novoErro(String titulo, Integer status, String excecao, String msgUser, String msgDev) {
        return new Erro(titulo, status, excecao, msgUser, msgDev);
    }

    public String getDevMessageFromStackTrace(Exception ex) {

        String msgDev = ex.toString();

        for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
            if (stackTraceElement.getClassName().startsWith("com.tout.venda")) {

                String classe, metodo;
                int linha;

                classe = stackTraceElement.getClassName();
                metodo = stackTraceElement.getMethodName();
                linha = stackTraceElement.getLineNumber();

                msgDev += ": " + classe + "(método " + metodo + ": linha " + linha + ")";

                break;
            }
        }

        return msgDev;
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Erro> handleNotFoundException(NotFound ex) {

        String titulo, msgUser, msgDev;

        titulo = tituloNaoEncontradoComEsseParametro;
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 404, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<Erro> handleNotFoundException(BadRequest ex) {

        String titulo, msgUser, msgDev;

        titulo = tituloNaoEncontradoComEsseParametro;
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<Erro> handleConflitoException(ConflitoException ex) {

        String titulo, msgUser, msgDev;

        titulo = "Dado já cadastrado";
        msgUser = ex.getMessage();
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 409, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

//    @ExceptionHandler(FalhaComunicacaoApiPagamento.class)
//    public ResponseEntity<Erro> handleFalhaComunicacaoApiPagamento(FalhaComunicacaoApiPagamento ex) {
//        ex.printStackTrace();
//
//        Erro erro = novoErro(
//                "Erro na comunicação com a API de pagamento",
//                502,
//                ex.getClass().getSimpleName(),
//                ex.getMessage(),
//                getDevMessageFromStackTrace(ex));
//
//        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(erro);
//    }


    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Erro> handlerNumberFormatException(NumberFormatException ex){
        ex.printStackTrace();

        Erro erro = novoErro(
                "",
                400,
                ex.getClass().getSimpleName(),
                "Esperava um numero, mas a entrada foi uma String",
                getDevMessageFromStackTrace(ex));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // JSON inválido
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Erro> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){

        String titulo, msgUser, msgDev;

        titulo = "Erro de desserialização";
        msgUser = "Não foi possivel converter o Json em um Object";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    // Endpoint inválido
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Erro> handleNoHandlerFoundException(NoHandlerFoundException ex){

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
    public ResponseEntity<Erro> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        String titulo;
        String msgUser;
        String msgDev;
        StringBuilder camposComErros = new StringBuilder();

        for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            camposComErros.append(fieldError.getField()).append(", ");
        }

        camposComErros = new StringBuilder(camposComErros.substring(0, camposComErros.length() - 2));

        titulo = tituloErroDePreenchimento;
        msgUser = "Os seguintes campos estão vazios ou nulos: " + camposComErros;
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


    // Parâmetro ausente (@RequestParam)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Erro> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex){

        String titulo, msgUser, msgDev, parametrosAusentes = ex.getParameterName();

        titulo = tituloErroDePreenchimento;
        msgUser = "Os seguintes parâmetros estão ausentes: " + parametrosAusentes;
        msgDev = getDevMessageFromStackTrace(ex);
        ex.printStackTrace();

        Erro erro = novoErro(titulo, 400, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


    // Tipo de parâmetro inválido (@PathVariable)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Erro> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                                                                          WebRequest request){

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
    public ResponseEntity<Erro> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){

        String titulo, msgUser, msgDev, metodo = ex.getMethod();

        titulo = "Método não suportado";
        msgUser = "O método " + metodo + " não é suportado neste endpoint";
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 405, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
    }


    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public ResponseEntity<Erro> handleIncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException ex){

        String titulo, msgUser, msgDev;
        titulo = tituloFalhaDeConexao;
        msgUser = mensagemTenteNovamenteMaisTarde;
        msgDev = getDevMessageFromStackTrace(ex);
        ex.printStackTrace();

        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }


    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public ResponseEntity<Erro> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex){

        String titulo, msgUser, msgDev;

        titulo = tituloFalhaDeConexao;
        msgUser = mensagemTenteNovamenteMaisTarde;
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();

        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }


    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<Erro> handleMissingPathVariableException(MissingPathVariableException ex){

        String titulo, msgUser, msgDev;
        int status;

        titulo = "Parâmetro ausente";
        msgUser = "É necessario informar "+ex.getParameter();
        msgDev = getDevMessageFromStackTrace(ex);
        status = 500;

        ex.printStackTrace();

        Erro erro = novoErro(titulo, status, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }



    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Erro> handleConversionFailedException(ConversionFailedException ex){

        String titulo, msgUser, msgDev;
        int status;

        titulo = tituloFalhaDeConexao;
        msgUser = mensagemTenteNovamenteMaisTarde;
        msgDev = getDevMessageFromStackTrace(ex);
        status = 500;

        ex.printStackTrace();

        Erro erro = novoErro(titulo, status, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }


    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<Erro> handleUnexpectedRollbackException(UnexpectedRollbackException ex){

        String titulo, msgUser, msgDev;

        titulo = tituloFalhaDeConexao;
        msgUser = mensagemTenteNovamenteMaisTarde;
        msgDev = getDevMessageFromStackTrace(ex);

        ex.printStackTrace();


        Erro erro = novoErro(titulo, 500, ex.getClass().getSimpleName(), msgUser, msgDev);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
//    @ExceptionHandler(FeignException.class)
//    public ResponseEntity<Erro> handleFeignException(FeignException ex) throws Exception{
//
//        String titulo, msgUser, msgDev;
//
//        titulo = tituloFalhaDeConexao;
//        msgUser = mensagemTenteNovamenteMaisTarde;
//        msgDev = getDevMessageFromStackTrace(ex);
//
//        ex.printStackTrace();
//        String json = ex.getMessage().substring(ex.getMessage().indexOf("[{\"errors\""));
//        System.out.println(json);
//        ObjectMapper objectMapper= new ObjectMapper();
//        Erro[] result = objectMapper.readValue(json, Erro[].class);
//
//        Erro erro = novoErro(result[0].getTitulo(), ex.status(), ex.getClass().getSimpleName(), result[0].getMensagemUsuario(), msgDev);
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
//    }

}
