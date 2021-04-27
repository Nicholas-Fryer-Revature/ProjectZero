package main

import util.PostgreSQLUtil
import scala.collection.mutable.ArrayBuffer
import java.sql.ResultSet

object DAO{
  
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


  //CREATE

  //CREATE new purchase and/or customer
  def insertPuchase(customer: Customer, car: CarConfig): Unit ={
    
    val conn = PostgreSQLUtil.getConnection()
    var customerID: Int = 0

    //Check if customer exists
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


  //READ

  //**********Change to return result set for use in purchases?***************
  //Print Entire customers table
  def printCustomers(): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("SELECT * FROM customers ORDER BY first_name")
    stmt.execute()

    val rs = stmt.getResultSet()
    while(rs.next) {
      println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + "\t" + rs.getString(4)+  "\t" + rs.getString(5))
    }
    conn.close()
  }

  //Return ArrayBuffer of Purchase IDs as ints
  def getPurchaseIDs(): ArrayBuffer[Int] ={
    val conn = PostgreSQLUtil.getConnection()
    var IDs = new ArrayBuffer[Int]

    val stmt = conn.prepareStatement("Select purchase_id FROM purchases;")
    stmt.execute()

    val rs = stmt.getResultSet()
    while(rs.next){
      IDs.addOne(rs.getInt(1))
    }
    conn.close()
    IDs
  }

  //Get Purchases by customer_ID
  def getCustomerPurchases(customer_id: Int): ResultSet ={
    val conn = PostgreSQLUtil.getConnection()
    
    val stmt = conn.prepareStatement("SELECT * FROM purchases WHERE customer_fk = ? ORDER BY date DESC;")
    stmt.setInt(1, customer_id)
    stmt.execute()

    val res = stmt.getResultSet()
    conn.close()
    res
  }


  //UPDATE

  //UPDATE purchase information
  def writeUpdatePurchase(car: CarConfig, purchaseID: Int): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("UPDATE purchases SET make = ?, model = ?, trim = ?, color = ?, engine = ?, price = ? WHERE purchase_id = ?")
    stmt.setString(1, car.make)
    stmt.setString(2, car.model)
    stmt.setString(3, car.trim)
    stmt.setString(4, car.color)
    stmt.setString(5, car.engine)
    stmt.setFloat(6, car.price)
    stmt.setInt(7, purchaseID)
    stmt.execute()
    
    println("\nSuccessfully Updated Purchase Entry!")
    conn.close()
  }

  //UPDATE customer information, NEED TEST
  def writeUpdateCustomer(customer: Customer, customerID: Int): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("UPDATE customers SET first_name = ?, last_name = ?, phone = ?, address = ? WHERE customer_id = ?;")
    stmt.setString(1, customer.firstName)
    stmt.setString(2, customer.lastName)
    stmt.setString(3, customer.phone)
    stmt.setString(4, customer.address)
    stmt.setInt(5, customerID)
    stmt.execute()

    println("\nSuccessfully Updated Customer Entry!")
    conn.close()
  }

  //DELETE

  //DELETE Customer & Cars purchased from customer
  def deleteCustomerEntry(customerID: Int): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    //Delete purchases from DB with cusomer ID
    var stmt = conn.prepareStatement("DELETE FROM purchases WHERE customer_fk = ?;")
    stmt.setInt(1, customerID)
    stmt.execute()

    //Delete customer from DB
    stmt = conn.prepareStatement("DELETE FROM customers WHERE customer_id = ?;")
    stmt.setInt(1, customerID)
    stmt.execute()

    println("\nSuccessfully Deleted Customer Entry!")
    conn.close()
  }

  //DELETE Car Purchase
  def deletePurchaseEntry(purchaseID: Int): Unit ={
    val conn = PostgreSQLUtil.getConnection()

    val stmt = conn.prepareStatement("DELETE FROM purchases WHERE purchase_id = ?;")
    stmt.setInt(1, purchaseID)
    stmt.execute()

    println("\nSucessfully Deleted Purchase Entry!")
    conn.close()
  }
}