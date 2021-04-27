package util

import scala.io.StdIn
import scala.collection.mutable.ArrayBuffer

object InputValidation {
  
  //test if input is a valid phone, return number
  def testPhone(): String ={ 
    
    //10 to 13 digits
    var isValidNumber = false
    var phone: String = "0"
    while(!isValidNumber){
      var input = StdIn.readLine().trim()
      
      if(input.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}")){
        phone = input
        isValidNumber = true
      }
      else if(input.matches("[0-9]{3}[0-9]{3}[0-9]{4}")){
        phone = input.substring(0, 3) + "-" +  input.substring(3, 6) + "-" + input.substring(6)
        isValidNumber = true
      }
      else{
        println("Invalid Phone Number. Try Again")
      }
    } 
    phone
  }

  //Test if input is valid float, return float
  def testPrice(): Float ={
    var isValidPrice = false
    var price: Float = 0

    while(!isValidPrice){
      var input = StdIn.readLine().trim()

      if(input.matches("\\d+\\.?\\d*")){
        price = input.toFloat
        price = BigDecimal(price).setScale(2, BigDecimal.RoundingMode.HALF_UP).toFloat
        isValidPrice = true
      }
      else{
        println("Invalid Numer! Try Again")
      }
    }
    price
  }

  //Test if input is valid array index, return index
  def testChoice(length: Int): Int ={
    var index: Int = 0
    var isValidChoice = false

    while(!isValidChoice){
      var input = StdIn.readLine().trim()
      if(input.matches("^[1-9]\\d*") && input.toInt <= length){
        index = input.toInt - 1
        isValidChoice = true
      }
      else{
        println("Invalid Choice! Try Again")
      }
    }
    index
  }

  //Test if input is valid ID, return ID
  def testID(ids: ArrayBuffer[Int]): Int ={
    var id: Int = 0
    var isValidId = false
    while(!isValidId){
      var input = StdIn.readLine()
      if(input.matches("^[1-9]\\d*") && ids.contains(input.toInt)){
        id = input.toInt
        isValidId = true
      } 
      else{
        println("Invalid ID! Try Again")
      }       
    }
    id
  }

}
