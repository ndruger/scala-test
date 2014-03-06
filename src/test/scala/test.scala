import org.scalatest.selenium.Chrome
import org.scalatest.time.Span
import org.scalatest.time.Seconds
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.FlatSpec

abstract class WebDriverTestSpec extends FlatSpec with Matchers with Chrome with BeforeAndAfterAll {

  val host = "http://localhost:3000/"

  override def beforeAll() {
    implicitlyWait(Span(5, Seconds))
  }
  
  implicit class MyElement(self: Element) {
    def click(): Unit = clickOn(self)
  }
  
  def cssFind(css:String): Element = {
    find(cssSelector(css)).get
  }
  
}

class AsyncSampleSpec extends WebDriverTestSpec {

  "button" should "create result element" in {

    go to (host + "index.html")
    pageTitle should be ("test_title")

    cssFind("button").click()
    cssFind(".result").text should be ("result_text")
  }
}
