import org.scalatest._
import org.scalatest.selenium._
import org.scalatest.time.Span
import org.scalatest.time.Seconds

class WebDriverTestSpec extends FlatSpec with Matchers with Chrome {

  val host = "http://localhost:3000/"

  "button" should "create result element" in {
    implicitlyWait(Span(5, Seconds))

    go to (host + "index.html")
    pageTitle should be ("test_title")

    click on cssSelector("button")
    find(cssSelector(".result")).get.text should be ("result_text")
  }
}