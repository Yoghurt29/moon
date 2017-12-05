package com.yo.moon.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(value = "index", tags = {
    "index" })
@RestController
@RequestMapping(value = "index", produces = "application/json;charset=utf-8")
public class IndexConroller {
  @Autowired
  JdbcTemplate jdbcTemplate;
  private final Logger logger = Logger.getLogger(IndexConroller.class);

  @Value("${about}")
  String about;

  @GetMapping("about")
  public JsonResponse index() {
    JsonResponse<String> response = new JsonResponse<String>();
    System.out.println(about);
    response.setData(about);
    return response;
  }

  @GetMapping("hello")
  public String helloEurekaDemo(
      @ApiParam(name = "name", required = true) @RequestParam String name) {
    return "Hello World" + name;
  }

  @GetMapping("helloExtResponse")
  public String helloJsonResponse() {
    int a = 5 / 0;
    return "ok";
  }

  @GetMapping("helloJdbc")
  public JsonResponse<Integer> helloJdbc() {
    Integer integer = jdbcTemplate.queryForObject("select count(*) from moon.user", Integer.class);
    return new JsonResponse<Integer>(true, integer);
  }
}
