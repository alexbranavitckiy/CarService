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
 private static final String URL_SERVICES = resource.getString("db.url.service");
 private static final String URL_DATABASE = resource.getString("db.url.database");
 private static final String USER = resource.getString("db.user");
 private static final String PASSWORD = resource.getString("db.password");

 public static HikariDataSource getDs() {
  return ds;
 }

 public static void setDs(HikariDataSource ds) {
  ConnectorDB.ds = ds;
 }

 private ConnectorDB() {
 }

 static {
  initDb();
  config.setJdbcUrl(URL_SERVICES + URL_DATABASE + "?stringtype=unspecified");
  config.setUsername(USER);
  config.setPassword(PASSWORD);
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
   URL_SERVICES + URL_DATABASE,
   USER, PASSWORD);
       PreparedStatement preparedStatement = conn.prepareStatement("SELECT NOW ()")
  ) {
   preparedStatement.executeQuery();
  } catch (ExceptionInInitializerError | Exception e) {
   try (Connection conn = DriverManager.getConnection(URL_SERVICES, USER, PASSWORD);
        PreparedStatement preparedStatementCreateDb = conn.prepareStatement("CREATE DATABASE " + URL_DATABASE + ";");
   ) {
    preparedStatementCreateDb.execute();
    log.debug("Database created successfully");
   } catch (Exception ee) {
    log.error("Database created error:{}", ee.getMessage());
   }
   try (Connection conn = DriverManager.getConnection(URL_SERVICES + URL_DATABASE, USER, PASSWORD);
        PreparedStatement preparedStatementInitSqlFile = conn.prepareStatement(readFileInitSQL())
   ) {
    preparedStatementInitSqlFile.execute();
    log.debug("Initialization data upload successfully");
   } catch (Exception ee) {
    log.error("Database initialization error:{}", ee.getMessage());
   }
  }
 }

 private static String readFileInitSQL() throws IOException {
  try (BufferedReader reader = new BufferedReader(new FileReader("data.sql"))) {
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
}

