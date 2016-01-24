package net.sthzg.boot2react

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.slf4j.{LoggerFactory, Logger}
import scala.collection.mutable.Map


class InitialData() {
  private final val logger: Logger = LoggerFactory.getLogger(getClass)

  private final val mapper: ObjectMapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  var data: Map[String, Any] = Map()


  /** Returns current data as serialized Json. */
  def toJson: String = generateJson(data)

  /**
    * Returns current data as serialized Json prefixed with with a string.
    *
    * @param prefix prefix used to wrap current data in the serialized Json
    */
  def toJson(prefix: String): String =
    generateJson(wrap(prefix))

  private def wrap(prefix: String): Map[String, Any] =
    Map(prefix -> data)

  private def generateJson(result: Map[String, Any]): String = {
    try {
      mapper.writeValueAsString(result)
    }
    catch {
      case e: JsonProcessingException =>
        logger.error("Cannot process data as Json.", data)
        null
    }
  }
}
