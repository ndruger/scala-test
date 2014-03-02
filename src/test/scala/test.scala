import org.scalatest._
import org.scalatest.selenium.WebBrowser

class BlogSpec extends FlatSpec with ShouldMatchers with WebBrowser {

  implicit val webDriver: WebDriver = new HtmlUnitDriver

  val host = "http://localhost/"

  "The blog app home page" should "have the correct title" in {
    go to (host + "index.html")
    println pageTitle
    pageTitle should be ("test")
  }
}