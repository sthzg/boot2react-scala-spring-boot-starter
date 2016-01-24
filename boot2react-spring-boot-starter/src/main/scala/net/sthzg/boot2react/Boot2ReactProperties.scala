package net.sthzg.boot2react

import org.springframework.boot.context.properties.ConfigurationProperties

import scala.beans.BeanProperty

@ConfigurationProperties("boot2react")
class Boot2ReactProperties {

  /** Protocol for the node render server (defaults to http) */
  @BeanProperty var nodeRenderServerProtocol: String = "http"

  /** Host for the node render server (defaults to 127.0.0.1) */
  @BeanProperty var getNodeRenderServerHost: String = "127.0.0.1"

  /** Port for the node render server. */
  @BeanProperty var nodeRenderServerPort: Short = 9999

}
