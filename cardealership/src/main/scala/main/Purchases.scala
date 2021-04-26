package main
import scala.io.StdIn
import java.sql.ResultSet
import util.InputValidation

object Purchases{

  def getCarChoice(): CarConfig ={
     println("Cars Available: ")
  
  //Make & Model Selection
    var i = 1
    JSONUtil.carList.foreach((car:Car) => {
      println(i + " " + car.make + " " + car.model)
      i += 1
    })
    
    var modelChoiceIndex = InputValidation.testChoice(JSONUtil.carList.length)
    //var modelChoiceIndex = StdIn.readInt() -1
    val makeChoice = JSONUtil.carList(modelChoiceIndex).make
    val modelChoice = JSONUtil.carList(modelChoiceIndex).model

    println(s"Car Selected: $makeChoice $modelChoice")
    
    
  //Trim Selection
    println("\nTrims:")

    i = 1; 
    JSONUtil.carList(modelChoiceIndex).trim.foreach((trim:String) => {
      println(i + " " + trim)
      i += 1
    })

    val trimChoiceIndex = InputValidation.testChoice(JSONUtil.carList(modelChoiceIndex).trim.length)
    val trimChoice = JSONUtil.carList(modelChoiceIndex).trim(trimChoiceIndex)
    println(s"Trim Selected: $trimChoice")


  //Color Selection
    println("\nColors:")

    i = 1; 
    JSONUtil.carList(modelChoiceIndex).color.foreach((color:String) => {
      println(i + " " + color)
      i += 1
    })

    val colorChoiceIndex = InputValidation.testChoice(JSONUtil.carList(modelChoiceIndex).color.length)
    val colorChoice = JSONUtil.carList(modelChoiceIndex).color(colorChoiceIndex)
    println(s"Color Selected: $colorChoice")


  //Engine Selection
    println("\nEngines:")

    i = 1; 
    JSONUtil.carList(modelChoiceIndex).engine.foreach((engine:String) => {
      println(i + " " + engine)
      i += 1
    })

    val engineChoiceIndex = InputValidation.testChoice(JSONUtil.carList(modelChoiceIndex).engine.length)
    val engineChoice = JSONUtil.carList(modelChoiceIndex).engine(engineChoiceIndex)
    println(s"Engine Selected: $engineChoice")

    println(s"\nCar Selected: $makeChoice $modelChoice $trimChoice $engineChoice $colorChoice")
    
  //Get Price  
    println("\nPrice:")
    val carPrice = InputValidation.testPrice()
    //val carPrice = StdIn.readFloat()

  //Return case class car
    val car = CarConfig(makeChoice, modelChoice, trimChoice, colorChoice, engineChoice, price = carPrice) 
    car
  }

  //Return Customer Information based on User Input
  def getCustomerInfo(): Customer ={
    println("\nPurchaser's First Name:")
    val customerFirstName = StdIn.readLine()

    println("\nPurchaser's Last Name:")
    val customerLastName = StdIn.readLine()
    
    println("\nPhone Number:")
    val customerPhone = InputValidation.testPhone()
    //val customerPhone = StdIn.readLine()
    
    println("\nAddress:")
    val customerAddress = StdIn.readLine()

    val customer = Customer(customerFirstName, customerLastName, customerPhone, customerAddress)
    customer
  }

  def newPurchase(): Unit ={
    val newCarConfig = getCarChoice()
    val purchaser = getCustomerInfo()
    DAO.insertPuchase(purchaser, newCarConfig)
  }

  def viewCustomers(): Unit ={


  }

  //View Purchase Based on Customer name & phone?
  def viewPurchases(): Unit = {
    val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      while(res.next()){
        println(res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8))
      }
    }
    else{
      println("Customer Not Found")
    }
  }

  //view list of customers and IDs
  //view makeOrders sum of orders by brand and each models total
  //view ModelOrders sum of orders of model
  //view TotalOrders total number of orders, maybe of each model
  //view TotalSales sum of sales


  //UPDATE

  def updatePurchase(): Unit = {
    val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      while(res.next()){
        println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8))
      }
      val orderID = StdIn.readInt()
      val car = getCarChoice()
      DAO.writeUpdatePurchase(car, orderID)
    }
    else{
      println("Customer Not Found")
    }

    //print thier orders,
    //get thier order ID
    //ask for order ID
    //get car config
    //update on DAO method
  }

  def updateCustomer(): Unit ={
    //Print List of customers and IDs (at least).... READ Function
    val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      println("\nEnter Updated Info:")
      val updatedCustomer = getCustomerInfo()
      DAO.writeUpdateCustomer(updatedCustomer, customerID)
    }
    else{
      println("Customer Not Found")
    }

    //Ask for input of ID
    //Check if ID input is valid
    //getCustomerInfo()
    //Write to DB where ID = input

    /**
      * Need to check the DB if exists, if you donk up inserting with a typo itll write a new 
      * customer. Thus, when you update, after you make the change you need to check if it exists, 
      * if it does, delete the donked up one. You would then have to change reference of the FK 
      * in the purchases of the donked up customer insert to the correct one, so in delete function 
      * need to have the customer and purchase deletes separate
      */

    //DAO.printCustomers()
    //var customerID = StdIn.readInt()

    
  }

  //DELETE
  def deletePurchase(): Unit = {
    val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      while(res.next()){
        println(res.getInt(1) + " " + res.getString(2) + " " + res.getString(3) + " " + res.getString(4) + " " + res.getString(5) + " " + res.getString(6) + " " + res.getString(7) + " " + res.getString(8))
      }
      val orderID = StdIn.readInt()
      DAO.deletePurchaseEntry(orderID)
    }
    else{
      println("Customer Not Found")
    }
  }

  def deleteCustomer(): Unit = {
    val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      DAO.deleteCustomerEntry(customerID)
    }
    else{
      println("Customer Not Found")
    }
  }

}