package com.yo.moon.exception;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.PAYLOAD_TOO_LARGE;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yo.moon.controller.JsonResponse;

/**
 * 全局异常拦截
 * 
 */
@ControllerAdvice
public class RestExceptionHandler {

  private final Logger logger = getLogger(RestExceptionHandler.class);

  @Autowired
  private ObjectMapper objectMapper;

  @ResponseBody
  @ExceptionHandler(DemoException.class)
  @ResponseStatus(org.springframework.http.HttpStatus.OK)
  public JsonResponse DemoExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
    // 业务异常
    if (findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
      throw ex;
    }

    final JsonResponse response = logFailed(req, ex);
    print(response);
    return response;
  }

  @ResponseBody
  @ExceptionHandler(Throwable.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public JsonResponse restExceptionHandler(HttpServletRequest req, Exception ex) throws Exception {
    // If the exception is annotated with @ResponseStatus rethrow it and let
    // the framework handle it
    if (findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
      throw ex;
    }

    final JsonResponse response = logFailed(req, ex);
    print(response);
    return response;
  }

  @ResponseBody
  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler({
      IllegalArgumentException.class })
  public JsonResponse badRequestHandler(HttpServletRequest req, Exception ex) throws Exception {
    final JsonResponse response = logFailed(req, ex);
    print(response);
    return response;
  }

  @ResponseBody
  @ResponseStatus(value = PAYLOAD_TOO_LARGE, reason = "文件过大")
  @ExceptionHandler({
      MaxUploadSizeExceededException.class })
  public JsonResponse requestTooLongExceptionHandler(HttpServletRequest req, Exception ex)
      throws Exception {
    final JsonResponse response = logFailed(req, ex);
    print(response);
    return response;
  }

  @ResponseBody
  @ResponseStatus(FORBIDDEN)
  @ExceptionHandler(AccessDeniedException.class)
  public JsonResponse accessDeniedExceptionHandler(HttpServletRequest req, Exception ex)
      throws Exception {
    final JsonResponse response = logFailed(req, ex);
    response.addMessage("禁止访问");
    print(response);
    return response;
  }

  @ResponseBody
  @ResponseStatus(UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeException.class)
  public JsonResponse mediaTypeNotSupportedExceptionHandler(HttpServletRequest req, Exception ex)
      throws Exception {
    final JsonResponse response = logFailed(req, ex);
    response.addMessage("不支持的媒体类型");
    print(response);
    return response;
  }

  @ResponseBody
  @ResponseStatus(METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public JsonResponse methodNotSupportedExceptionHandler(HttpServletRequest req, Exception ex)
      throws Exception {
    final JsonResponse response = logFailed(req, ex);
    response.addMessage("不支持的请求方法类型");
    print(response);
    return response;
  }

  private JsonResponse logFailed(HttpServletRequest req, Throwable tb) {
    final JsonResponse response = new JsonResponse(false);
    response.addMessage(tb.getLocalizedMessage());

    if (logger.isErrorEnabled()) {
      logger.error(format("请求%s失败", req.getRequestURI()), tb);
    }

    return response;
  }

  private void print(JsonResponse response) {
    if (logger.isErrorEnabled()) {
      try {
        logger.error(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
      } catch (JsonProcessingException ignore) {
      }
    }
  }

}
