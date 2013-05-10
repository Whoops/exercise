package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

import global._

case class Exercise( name: String, category: String )

object Exercise {

  // Just load the data from disk
  val exercises = Global.exercises

  def all(): List[Exercise] = exercises

  implicit val exerciseReads: Reads[Exercise] = (
    (__ \ "name").read[String] and
    (__ \ "category").read[String]
  )(Exercise.apply _)

  implicit val exerciseWrites = Json.writes[Exercise]

  def searchByCategory(category: String): List[Exercise] = {
    val all = exercises
    all.filter { case Exercise(_, c) => c == category }
  }
}
