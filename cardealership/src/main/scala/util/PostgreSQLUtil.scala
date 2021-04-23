package util

import java.sql.DriverManager
import java.sql.Connection

object PostgreSQLUtil {
      
  //JDBC driver manager to get a connection
  def getConnection() : Connection = {
    classOf[org.postgresql.Driver].newInstance()
    val conn = DriverManager.getConnection(
      "jdbc:postgresql://127.0.0.1:5432/project0", 
      "HA", 
      "GOTTEM")
    conn
  }
}