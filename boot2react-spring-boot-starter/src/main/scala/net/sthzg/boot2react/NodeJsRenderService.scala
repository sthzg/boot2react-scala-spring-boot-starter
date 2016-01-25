package net.sthzg.boot2react

import java.net.URI

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import net.sthzg.boot2react.configprops.Boot2ReactProperties
import org.apache.http.client.fluent.{Request, Content}
import org.apache.http.entity.ContentType
import org.slf4j.{LoggerFactory, Logger}
import org.springframework.http.{MediaType, HttpHeaders, HttpStatus, ResponseEntity}


class NodeJsRenderService(val properties: Boot2ReactProperties) {
  private final val logger: Logger = LoggerFactory.getLogger(getClass)
  private final val mapper: ObjectMapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  /**
    * Gets the results for a server-side render pass utilizing the node render instance.
    *
    * @param reqURI requested uri
    * @return RouterResults populated results object for the current request
    */
  def resolveRequestThroughNodeJS(reqURI: String, initialData: String): RouterResults = {
    logger.info(s"Request rendering $reqURI through NodeJS render engine")

    var content: Content = null
    var results: RouterResults = new RouterResults
    try {
      content = Request
        .Post("http://127.0.0.1:" + properties.getNodeRenderServerPort + "/render")
        .addHeader("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
        .bodyString("{\"reqURI\": \"" + reqURI + "\", \"initialData\": " + initialData + "}", ContentType.APPLICATION_JSON)
        .execute
        .returnContent
    }
    catch {
      case ex: Throwable => throw ex
    }

    try {
      assert(content != null)
      results = mapper.readValue(content.asString, classOf[RouterResults])
    }
    catch {
      case ex: Throwable => throw ex
    }

    results
  }

  /**
    * Generates the ResponseEntity that will be returned to the client.
    *
    * @param results result instance that will be populated in the render engine.
    * @return HTTP response to send to the client
    */
  def createResponseEntity(results: RouterResults): ResponseEntity[String] = {

    results.redirect match {
      case true =>
        val headers: HttpHeaders = new HttpHeaders()
        headers.setLocation(new URI(results.redirectTo))
        new ResponseEntity[String](null, headers, HttpStatus.FOUND)

      case false =>
        val body: String = String.format("<!DOCTYPE html>%s", results.body)
        new ResponseEntity[String](body, HttpStatus.valueOf(results.httpStatus))
    }
  }
}
