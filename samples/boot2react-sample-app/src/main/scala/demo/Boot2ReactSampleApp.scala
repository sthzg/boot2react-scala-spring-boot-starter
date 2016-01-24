package demo

import java.util.Locale
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}

import net.sthzg.boot2react.{NodeJsRenderService, InitialData, RouterResults}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@SpringBootApplication
class Boot2ReactSampleApp

object Boot2ReactSampleApp {
  def main(args: Array[String]) : Unit = SpringApplication.run(classOf[Boot2ReactSampleApp], args :_ *)
}


@Controller
class MvcControllers @Autowired()(val initialData: InitialData,
                                  val react: NodeJsRenderService){

  @RequestMapping(Array("/"))
  def welcome(request: HttpServletRequest , response: HttpServletResponse, locale: Locale) = {
    var resp: ResponseEntity[String] = null
    var results: RouterResults = new RouterResults()

    initialData.data.put("locale", locale.getLanguage)
//    additionalData match {
//      case Some(data) => initialData.data ++=  data
//      case None => // ignored
//    }
    val initialJson: String = initialData.toJson("boot2react")
    results = react.resolveRequestThroughNodeJS(request.getRequestURI, initialJson)

    resp = react.createResponseEntity(results)
    resp
  }

}
