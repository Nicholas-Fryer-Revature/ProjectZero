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

  //Test if customer exists, True: Return ID, False: Return 0
  def testCustomerExists(customer: Customer): Int ={
    val conn = PostgreSQLUtil.getConnection()
    var customerID = 0
    
    //Test if customer exists in database
    var stmt = conn.prepareStatement("select exists(select 1 from customers where first_name = ? and last_name = ? and phone = ?);")
    stmt.setString(1, customer.firstName)
    stmt.setString(2, customer.lastName)
    stmt.setString(3, customer.phone)
    stmt.execute()

    var res = stmt.getResultSet()
    res.next()
    val customerExists = res.getBoolean(1)

    //grab existing customer_id
    if (customerExists == true){
      stmt = conn.prepareStatement("SELECT customer_id FROM customers WHERE first_name = ? and last_name = ? and phone = ?;")
      stmt.setString(1, customer.firstName)
      stmt.setString(2, customer.lastName)
      stmt.setString(3, customer.phone)
      stmt.execute()
      
      res = stmt.getResultSet()
      res.next()
      customerID = res.getInt(1)
      println("\nExisting Customer Found!")
    }
    customerID
  } 


//CREATE new purchase and/or customer
  def insertPuchase(customer: Customer, car: CarConfig): Unit ={
    
    val conn = PostgreSQLUtil.getConnection()
    var customerID: Int = 0

    //Check if customer exists
    /*
    var stmt = conn.prepareStatement("select exists(select 1 from customers where first_name = ? and last_name = ? and phone = ?);")
    stmt.setString(1, customer.firstName)
    stmt.setString(2, customer.lastName)
    stmt.setString(3, customer.phone)
    stmt.execute()

    var res = stmt.getResultSet()
    res.next()
    val customerExists = res.getBoolean(1)
    */

    customerID = testCustomerExists(customer)

    //insert into customers if customer does not already exist
    if(customerID == 0){
      var stmt = conn.prepareStatement("INSERT INTO customers VALUES (DEFAULT, ?, ?, ?, ?) RETURNING customer_id;")
      stmt.setString(1, customer.firstName)
      stmt.setString(2, customer.lastName)
      stmt.setString(3, customer.phone)
      stmt.setString(4, customer.address)
      stmt.execute()

      var res = stmt.getResultSet()
      res.next()
      customerID = res.getInt(1)
      println("Inserted New Customer " + customer.firstName + " " + customer.lastName + "Into Database!")
      //println(s"\nInserted New Customer $customer.firstName $customer.lastName Into Database!")
      //println(customerID)
    }

    //grab existing customer_id
    /*
    else{
      stmt = conn.prepareStatement("SELECT customer_id FROM customers WHERE first_name = ? and last_name = ? and phone = ?;")
      stmt.setString(1, customer.firstName)
      stmt.setString(2, customer.lastName)
      stmt.setString(3, customer.phone)
      stmt.execute()
      
      res = stmt.getResultSet()
      res.next()
      customerID = res.getInt(1)
      println("\nExisting Customer Found!")
    }
    */
    println(s"Customer ID: $customerID")

    //Inserting into Purchases table
    var stmt = conn.prepareStatement("INSERT INTO purchases VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, DEFAULT, ?);")
    stmt.setString(1, car.make)
    stmt.setString(2, car.model)
    stmt.setString(3, car.trim)
    stmt.setString(4, car.color)
    stmt.setString(5, car.engine)
    stmt.setFloat(6, car.price)
    stmt.setInt(7, customerID)
    stmt.execute()
    println("Added New Purchase Into Database!\n")

    conn.close()
  }
}