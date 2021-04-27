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
    
    println("\nAddress:")
    val customerAddress = StdIn.readLine()

    val customer = Customer(customerFirstName, customerLastName, customerPhone, customerAddress)
    customer
  }

  //*********Get Customer name, number for queries************
  def searchCustomer(): Customer ={
    println("\nPurchaser's First Name:")
    val customerFirstName = StdIn.readLine()

    println("\nPurchaser's Last Name:")
    val customerLastName = StdIn.readLine()
    
    println("\nPhone Number:")
    val customerPhone = InputValidation.testPhone()

    val customerAddress = ""
    val customer = Customer(customerFirstName, customerLastName, customerPhone, customerAddress)
    customer
  }


  //CREATE
  //get car and customer info and call DAO insert 
  def newPurchase(): Unit ={
    val newCarConfig = getCarChoice()
    val purchaser = getCustomerInfo()
    DAO.insertPuchase(purchaser, newCarConfig)
  }

  //idk what this was supposed to do, too generic name
  def viewCustomers(): Unit ={


  }

  //View Purchase Based on Customer name & phone
  def viewPurchases(): Unit = {
    val customer = searchCustomer()
    //val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      println("Make/Model" + " " * 11 + "Trim" + " " * 17 + "Color" + " " * 11 + "Engine" + " " * 10 + "Price" + "    " + "Order Date")
      println("-" * 109)
      while(res.next()){
        println(f"${res.getString(2) + " " + res.getString(3)}%-20s ${res.getString(4)}%-20s ${res.getString(5)}%-15s ${res.getString(6)}%-15s ${res.getString(7)}%-8s ${res.getString(8)}")
      }
    }
    else{
      println("\nCustomer Not Found")
    }
  }

  //view list of customers and IDs
  //view makeOrders sum of orders by brand and each models total
  //view ModelOrders sum of orders of model
  //view TotalOrders total number of orders, maybe of each model
  //view TotalSales sum of sales


  //UPDATE

  //Update A Customers Purchase Information
  def updatePurchase(): Unit = {
    val customer = searchCustomer()
    //val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      println("ID" + " " * 4 + "Make/Model" + " " * 11 + "Trim" + " " * 17 + "Color" + " " * 11 + "Engine" + " " * 10 + "Price" + "    " + "Order Date")
      println("-" * 115)
      while(res.next()){
        println(f"${res.getString(1)}%-5s ${res.getString(2) + " " + res.getString(3)}%-20s ${res.getString(4)}%-20s ${res.getString(5)}%-15s ${res.getString(6)}%-15s ${res.getString(7)}%-8s ${res.getString(8)}")
      }
      //val orderID = StdIn.readInt()
      val orderID = InputValidation.testID(DAO.getPurchaseIDs())
      val car = getCarChoice()
      DAO.writeUpdatePurchase(car, orderID)
    }
    else{
      println("\nCustomer Not Found")
    }

    //print thier orders,
    //get thier order ID
    //ask for order ID
    //get car config
    //update on DAO method
  }

  //Update A Customers Information in DB
  def updateCustomer(): Unit ={
    //Print List of customers and IDs (at least).... READ Function
    val customer = searchCustomer()
    //val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      println("\nEnter Updated Info:")
      val updatedCustomer = getCustomerInfo()
      DAO.writeUpdateCustomer(updatedCustomer, customerID)
    }
    else{
      println("\nCustomer Not Found")
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

  //Delete Purchase from DB
  def deletePurchase(): Unit = {
    val customer = searchCustomer()
    //val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      val res = DAO.getCustomerPurchases(customerID)
      println("ID" + " " * 4 + "Make/Model" + " " * 11 + "Trim" + " " * 17 + "Color" + " " * 11 + "Engine" + " " * 10 + "Price" + "    " + "Order Date")
      println("-" * 115)
      while(res.next()){
        println(f"${res.getString(1)}%-5s ${res.getString(2) + " " + res.getString(3)}%-20s ${res.getString(4)}%-20s ${res.getString(5)}%-15s ${res.getString(6)}%-15s ${res.getString(7)}%-8s ${res.getString(8)}")
      }
      val orderID = InputValidation.testID(DAO.getPurchaseIDs()) 
      DAO.deletePurchaseEntry(orderID)
    }
    else{
      println("\nCustomer Not Found")
    }
  }

  //Delete Customer and thier Purchases from DB
  def deleteCustomer(): Unit = {
    val customer = searchCustomer()
    //val customer = getCustomerInfo()
    val customerID = DAO.testCustomerExists(customer)
    if(customerID != 0){
      DAO.deleteCustomerEntry(customerID)
    }
    else{
      println("\nCustomer Not Found")
    }
  }
}