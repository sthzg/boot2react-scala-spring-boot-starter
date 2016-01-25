package net.sthzg.boot2react

import net.sthzg.boot2react.configprops.Boot2ReactProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.{ScopedProxyMode, Scope, Bean, Configuration}
import org.springframework.web.context.WebApplicationContext

@Configuration
@EnableConfigurationProperties(Array(classOf[Boot2ReactProperties]))
class Boot2ReactAutoConfiguration {

  @Autowired
  private val properties: Boot2ReactProperties = null

  /** A request-scoped data store that will be passed to the Node render instance. */
  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  def initialData(): InitialData = new InitialData()

  /** Service class that provides methods to render a request through a NodeJS application.  */
  @Bean
  def nodeJsRenderService(): NodeJsRenderService = new NodeJsRenderService(properties)
}
