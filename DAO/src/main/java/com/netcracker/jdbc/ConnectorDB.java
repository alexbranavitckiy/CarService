package com.netcracker.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@Slf4j
public class ConnectorDB {

 private static final HikariConfig config = new HikariConfig();
 private static HikariDataSource ds;
 private static final ResourceBundle resource = ResourceBundle.getBundle("database");

 private ConnectorDB() {
 }

 static {
  initDb();
  config.setJdbcUrl(resource.getString("db.url.service") + resource.getString("db.url.database"));
  config.setUsername(resource.getString("db.user"));
  config.setPassword(resource.getString("db.password"));
  config.setMaximumPoolSize(Integer.parseInt(resource.getString("db.poolSize")));
  config.addDataSourceProperty("cachePrepStmts", resource.getString("db.cachePrepStmts"));
  config.addDataSourceProperty("prepStmtCacheSize", resource.getString("db.prepStmtCacheSize"));
  config.addDataSourceProperty("prepStmtCacheSqlLimit", resource.getString("db.prepStmtCacheSqlLimit"));
  ds = new HikariDataSource(config);
 }

 public static Connection getConnection() throws SQLException {
  return ds.getConnection();
 }

 public static void initDb() {
  try (Connection conn = DriverManager.getConnection(
   resource.getString("db.url.service") + resource.getString("db.url.database"),
   resource.getString("db.user"), resource.getString("db.password"));
       PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOW ()")
  ) {
   preparedStatement.executeQuery();
  } catch (ExceptionInInitializerError | Exception e) {
   try (Connection conn = DriverManager.getConnection(resource.getString("db.url.service"),
    resource.getString("db.user"), resource.getString("db.password"));
        PreparedStatement preparedStatementCreateDb = conn.prepareStatement("CREATE DATABASE " + resource.getString("db.url.database") + ";");
   ) {
    preparedStatementCreateDb.execute();
    log.debug("Database created successfully");
   } catch (Exception ee) {
    log.info("Database created error:{}", ee.getMessage());
   }
   try (Connection conn = DriverManager.getConnection(resource.getString("db.url.service") + resource.getString("db.url.database"),
    resource.getString("db.user"), resource.getString("db.password"));
        PreparedStatement preparedStatementInitSqlFile = conn.prepareStatement(readFileInitSQL())
   ) {
    preparedStatementInitSqlFile.execute();
    log.debug("Initialization data upload successfully");
   } catch (Exception ee) {
    log.info("Database initialization error:{}", ee.getMessage());
   }
  }
 }


 private static String readFileInitSQL() throws IOException {
  BufferedReader reader = new BufferedReader(new FileReader("data.sql"));
  String line;
  StringBuilder stringBuilder = new StringBuilder();
  String ls = System.getProperty("line.separator");
  while ((line = reader.readLine()) != null) {
   stringBuilder.append(line);
   stringBuilder.append(ls);
  }
  stringBuilder.deleteCharAt(stringBuilder.length() - 1);
  return stringBuilder.toString();
 }
}

