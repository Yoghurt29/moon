package com.yo.moon;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInstall {
  @Autowired
  JdbcTemplate jdbcTemplate;
  private static String SCHEMA_SQL = "/META-INF/rdb-setup/script/table/schema.sql";

  //@PostConstruct
  public void initDatabase() throws IOException, InterruptedException {
    // TODO 使用ant 或者mybatis脚本初始化
    /*System.out.println("init database...");
    InputStream script = getClass().getResourceAsStream(SCHEMA_SQL);
    String sql = null;
    try {
      sql = IOUtils.toString(script, "utf-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(sql);
    jdbcTemplate.batchUpdate(sql);
    System.out.println("init database done");*/
  }
}
