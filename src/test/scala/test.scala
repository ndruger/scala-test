import org.scalatest._
import org.scalatest.selenium._
import org.scalatest.time.Span
import org.scalatest.time.Seconds


class WebDriverTestSpec extends FlatSpec with Matchers with Chrome {

  val host = "http://localhost:3000/"

  implicit class MyElement(self: Element) {
    def click(): Unit = clickOn(self)
  }
  
  def cssFind(css:String): Element = {
    find(cssSelector(css)).get
  }
  
  "button" should "create result element" in {
    implicitlyWait(Span(5, Seconds))

    go to (host + "index.html")
    pageTitle should be ("test_title")

    cssFind("button").click()
    cssFind(".result").text should be ("result_text")
  }
}