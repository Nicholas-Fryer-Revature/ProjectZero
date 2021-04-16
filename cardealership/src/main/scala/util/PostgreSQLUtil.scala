package util

import java.sql.DriverManager

object PostgreSQLUtil {
      classOf[org.postgresql.Driver].newInstance()

      //Use JDBC driver manager to get a connection
      //get connection takes 3 arguments, we will hardcode credentials for now
      val conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/chinook", "postgres", "3109")


      val stmt = conn.prepareStatement("SELECT * FROM track WHERE length(name) > ?;")
      stmt.setInt(1,50)
      stmt.execute()


      var rs = stmt.getResultSet()
      while(rs.next()){
        println(rs.getString("name"))
      }
  
}