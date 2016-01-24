package net.sthzg.boot2react

import scala.beans.BeanProperty

// TODO make it a case class
class RouterResults {
  @BeanProperty var httpStatus: Integer = null
  @BeanProperty var body: String = null
  @BeanProperty var error: String = null
  @BeanProperty var redirect: Boolean = false
  @BeanProperty var redirectTo: String = null
}
