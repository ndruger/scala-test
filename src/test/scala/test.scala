import org.scalatest.selenium.{Chrome, Firefox}
import org.scalatest.time.{Span, Seconds, Millis}
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.FlatSpec

import org.scalatest.concurrent.Eventually

abstract class WebDriverTestSpec extends FlatSpec with Matchers with Chrome with BeforeAndAfterAll with Eventually {

  val host = "http://localhost:3000/"

  implicit override val patienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))    

  override def beforeAll() {
    implicitlyWait(Span(5, Seconds))
  }
  
  implicit class TestElement(self: Element) {
    def click(): Unit = clickOn(self)
  }
  
  def cssFind(css:String): Element = {
    find(cssSelector(css)).get
  }
  
}

class AsyncSampleSpec extends WebDriverTestSpec {
  "button" should "create result element" in {

    go to (s"${host}index.html")
    pageTitle should be ("test_title")

    cssFind("button").click()
    eventually { cssFind(".result").text should be ("result_text") }
  }
}
