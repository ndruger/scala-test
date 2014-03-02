import org.scalatest._
import org.scalatest.selenium.WebBrowser
import org.scalatest.selenium._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import concurrent.Eventually._

class BlogSpec extends FlatSpec with ShouldMatchers with WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  val host = "http://localhost/"

  "The blog app home page" should "have the correct title" in {
    go to (host + "index.html")
    eventually { pageTitle should be ("") }
  }
}