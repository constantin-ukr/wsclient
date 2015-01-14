package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    val url = Seq(119, 115, 58, 47, 47, 108, 111, 99, 97, 108, 104, 111, 115, 116, 47, 97, 112, 105, 47, 118, 49, 47, 119, 115).map(_.toChar).mkString
    val message = Seq(104, 101, 108, 108, 111).map(_.toChar).mkString
    Ok(views.html.instruction(url, message))
  }

}