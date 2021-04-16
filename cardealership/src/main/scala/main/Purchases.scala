package main
import scala.io.StdIn


object Purchases{

  def newPurchase(): Unit = {
    println("Cars Available: ")

    //Make/Model Selection
    var i = 1
    Main.carOptions.foreach((car:Car) => {
      println(i + " " + car.make + " " + car.model)
      i += 1
    })

    var modelChoiceIndex = StdIn.readInt() -1
    val makeChoice = Main.carOptions(modelChoiceIndex).make
    val modelChoice = Main.carOptions(modelChoiceIndex).model

    println(s"Car Selected: $makeChoice $modelChoice")
    

    //Trim Selection
    println("\nTrims:")

    i = 1; 
    Main.carOptions(modelChoiceIndex).trim.foreach((trim:String) => {
      println(i + " " + trim)
      i += 1
    })

    var trimChoice = Main.carOptions(modelChoiceIndex).trim(StdIn.readInt -1)
    println(s"Trim Selected: $trimChoice")


    //Color Selection
    println("\nColors:")

    i = 1; 
    Main.carOptions(modelChoiceIndex).color.foreach((color:String) => {
      println(i + " " + color)
      i += 1
    })

    var colorChoice = Main.carOptions(modelChoiceIndex).color(StdIn.readInt -1)
    println(s"Color Selected: $colorChoice")


    //Engine Selection
    println("\nEngines:")

    i = 1; 
    Main.carOptions(modelChoiceIndex).engine.foreach((engine:String) => {
      println(i + " " + engine)
      i += 1
    })

    var engineChoice = Main.carOptions(modelChoiceIndex).engine(StdIn.readInt -1)
    println(s"Engine Selected: $engineChoice")

    println(s"\nCar Selected: $makeChoice $modelChoice $trimChoice $engineChoice $colorChoice")
    
    println("\nPrice:")
    val carPrice = StdIn.readLine()
    
    println("\nPurchaser's Name:")
    val customerName = StdIn.readFloat()

    println("\nAddress:")
    val customerAddress = StdIn.readLine()

    println("\nPhone Number:")
    val customerPhone = StdIn.readLine()

    


  }

  def viewPurchase(): Unit = {



  }

  def editPurchase(): Unit = {



  }



}