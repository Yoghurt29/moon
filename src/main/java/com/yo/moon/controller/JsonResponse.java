package com.yo.moon.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonResponse<T> {
  public static final Boolean SUCCESS_TRUE = true;
  public static final Boolean SUCCESS_FALSE = false;
  private Boolean success = SUCCESS_TRUE;
  private T data;
  private List<String> message = new ArrayList();

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public List<String> getMessage() {
    return message;
  }

  public void setMessage(List<String> message) {
    this.message = message;
  }

  public JsonResponse() {
    super();
  }

  public JsonResponse(Boolean success, T data, List<String> message) {
    super();
    this.success = success;
    this.data = data;
    this.message = message;
  }

  public JsonResponse(Boolean success, T data, String... message) {
    super();
    this.success = success;
    this.data = data;
    this.message = Arrays.asList(message);
  }

  public void addMessage(String string) {
    if (null != string)
      this.message.add(string);
  }

  public JsonResponse(Boolean success) {
    super();
    this.success = success;
  }

}
