package main

import util.PostgreSQLUtil

object DAO{
  
//VIEW test of DB  
  def viewAll(): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("SELECT * FROM purchases;")
    stmt.execute()

    val rs = stmt.getResultSet()
    while(rs.next) {
      println(rs.getString("make"))
    }
    conn.close()
  }

//Test if row exists
  def testExists(firstName : String): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    var stmt = conn.prepareStatement("select exists(select 1 from customers where first_name = ?)")
    stmt.setString(1, firstName)
    stmt.execute()

    var res = stmt.getResultSet()
    res.next()
    val exists = res.getBoolean(1)
    println(exists)
    conn.close()
  }


//CREATE new purchase and/or customer
  def insertPuchase(firstName: String, lastName: String, phone: String, address: String, 
    make: String, model: String, trim: String, color: String, engine: String, price: Float): Unit ={
    
      val conn = PostgreSQLUtil.getConnection()
    var customerID: Int = 0

    //Check if customer exists
    var stmt = conn.prepareStatement("select exists(select 1 from customers where first_name = ? and last_name = ? and phone = ?);")
    stmt.setString(1, firstName)
    stmt.setString(2, lastName)
    stmt.setString(3, phone)
    stmt.execute()

    var res = stmt.getResultSet()
    res.next()
    val customerExists = res.getBoolean(1)

    //insert into customers if customer does not already exist
    if(customerExists == false){
      stmt = conn.prepareStatement("INSERT INTO customers VALUES (DEFAULT, ?, ?, ?, ?) RETURNING customer_id;")
      stmt.setString(1, firstName)
      stmt.setString(2, lastName)
      stmt.setString(3, phone)
      stmt.setString(4, address)
      stmt.execute()

      res = stmt.getResultSet()
      res.next()
      customerID = res.getInt(1)
      println(s"Inserted New Customer $firstName $lastName Into Database!")
      //println(customerID)
    }

    //grab existing customer_id
    else{
      stmt = conn.prepareStatement("SELECT customer_id FROM customers WHERE first_name = ? and last_name = ? and phone = ?;")
      stmt.setString(1, firstName)
      stmt.setString(2, lastName)
      stmt.setString(3, phone)
      stmt.execute()
      
      res = stmt.getResultSet()
      res.next()
      customerID = res.getInt(1)
      println("Existing Customer Found!")
      //println(customerID)
    }

    println(s"Customer ID: $customerID")

    //Inserting into Purchases table
    stmt = conn.prepareStatement("INSERT INTO purchases VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, ?);")
    stmt.setString(1, make)
    stmt.setString(2, model)
    stmt.setString(3, trim)
    stmt.setString(4, color)
    stmt.setString(5, engine)
    stmt.setFloat(6, price)
    stmt.setInt(7, customerID)
    stmt.execute()
    
    conn.close()
  }
}