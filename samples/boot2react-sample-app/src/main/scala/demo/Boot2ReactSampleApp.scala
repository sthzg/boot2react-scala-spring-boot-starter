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
                                  val react: NodeJsRenderService) {

  @RequestMapping(Array("/"))
  def welcome(req: HttpServletRequest , locale: Locale) = {
    initialData.data.put("locale", locale.getLanguage)
    react.createResponseEntity(react.resolveRequestThroughNodeJS(req.getRequestURI, initialData))
  }

}
