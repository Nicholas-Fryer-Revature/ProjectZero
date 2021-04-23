package main
import scala.io.StdIn


object Purchases{

  def newPurchase(): Unit = {
    println("Cars Available: ")

  //Make/Model Selection
    
    /*Main.carOptions.foreach((car:Car) => {
      println(i + " " + car.make + " " + car.model)
      i += 1
    })
    */
    
    var i = 1
    JSONUtil.carList.foreach((car:Car) => {
      println(i + " " + car.make + " " + car.model)
      i += 1
    })
    

    var modelChoiceIndex = StdIn.readInt() -1
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

    var trimChoice = JSONUtil.carList(modelChoiceIndex).trim(StdIn.readInt -1)
    println(s"Trim Selected: $trimChoice")


  //Color Selection
    println("\nColors:")

    i = 1; 
    JSONUtil.carList(modelChoiceIndex).color.foreach((color:String) => {
      println(i + " " + color)
      i += 1
    })

    var colorChoice = JSONUtil.carList(modelChoiceIndex).color(StdIn.readInt -1)
    println(s"Color Selected: $colorChoice")


  //Engine Selection
    println("\nEngines:")

    i = 1; 
    JSONUtil.carList(modelChoiceIndex).engine.foreach((engine:String) => {
      println(i + " " + engine)
      i += 1
    })

    var engineChoice = JSONUtil.carList(modelChoiceIndex).engine(StdIn.readInt -1)
    println(s"Engine Selected: $engineChoice")

    println(s"\nCar Selected: $makeChoice $modelChoice $trimChoice $engineChoice $colorChoice")
    
  //Get Customer Info & Price  
    println("\nPrice:")
    val carPrice = StdIn.readFloat()
    
    println("\nPurchaser's Name:")
    val customerName = StdIn.readLine()

    println("\nAddress:")
    val customerAddress = StdIn.readLine()

    println("\nPhone Number:")
    val customerPhone = StdIn.readLine()


  //Call DAO insertPurchase here
  }

  //View Purchase Based on Customer name & Address?
  def viewPurchase(): Unit = {



  }
  //view makeOrders sum of orders by brand and each models total
  //view ModelOrders sum of orders of model
  //view TotalOrders total number of orders, maybe of each model
  //view TotalSales sum of sales

  def editPurchase(): Unit = {



  }

  def deletePurchase(): Unit = {



  }



}