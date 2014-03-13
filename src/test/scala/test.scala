import org.scalatest.selenium.{Chrome, Firefox}
import org.scalatest.time.{Span, Seconds, Millis}
import java.util.concurrent.TimeUnit

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.FlatSpec
import org.openqa.selenium.chrome._
import org.openqa.selenium._
import org.openqa.selenium.WebElement
import scala.util.Random

import org.scalatest.concurrent.Eventually

abstract class WebDriverTestSpec extends FlatSpec with Matchers with Chrome with BeforeAndAfterAll with Eventually {

  val host = "http://localhost:3001/architecture-examples/backbone/"

  implicit override val patienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))    

  override def beforeAll() {
    implicitlyWait(Span(5, Seconds))
  }
  
  implicit class TestElement(self: Element) {
    def click(): Unit = clickOn(self)
    def sendKeys(s: String): Unit = {
      self.click()
      pressKeys(s)
    }
  }
  
  def cssFind(css:String): Element = {
    find(cssSelector(css)).get
  }
  
  def cssCountAll(css:String): Int = {
    val eles: Iterator[Element] = findAll(cssSelector(css))
    eles.length
  }
  
  def randomText: String = {
    Random.alphanumeric.take(10).mkString
  }

  implicit class ParsableString(self: String) {
  }
  
  implicit class ForceParsableString(self: String) {
    def toIntOption( s:String ):Option[Int] = {
      try{
        Some(Integer.parseInt(s, 10))
      } catch {
        case _ => None
       }
    }
    def forceParseInt(): Int = {
      toIntOption(self).getOrElse(-1)
    }
  }
}

abstract class WebDriverTestSpec2 extends FlatSpec with Matchers with BeforeAndAfterAll with Eventually {

  val host = "http://localhost:3001/architecture-examples/backbone/"

  val driver = new ChromeDriver
  
  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS)

  implicit override val patienceConfig =
    PatienceConfig(timeout = Span(2, Seconds), interval = Span(5, Millis))    

  def cssFind(css:String): WebElement = {
    driver.findElement(By.id(css))
  }
}
/*
class AsyncSampleSpec extends WebDriverTestSpec {
  "button" should "create result element" in {

    go to (s"${host}index.html")
    pageTitle should be ("test_title")

    cssFind("button").click()
    eventually { cssFind(".result").text should be ("result_text") }
  }
}
*/

class MVCSpec extends WebDriverTestSpec {

  object Todo {
    def addItem: Unit = {
      val t = randomText
      
      val count =  
      cssFind("#new-todo").click()
      cssFind("#new-todo").sendKeys(s"${t}\n")
      
      cssFind("#todo-count").text.forceParseInt should be (1)
      eventually { cssFind("#todo-list label").text should be (t) }
      
      cssFind("#todo-list label").text.should.be(t)
    }
  }
  
  "button" should "create result element" in {

    go to (s"${host}index.html")
    Todo.addItem
//    val neko = new Todo()
//    neko.addItem
  }
}

/*

class MVCSpec extends WebDriverTestSpec2 {

  "button" should "create result element" in {
    driver.get(s"${host}index.html")

    val inputElement = driver.findElement(By.id("new-todo"))

    inputElement.sendKeys("neko")
    eventually { cssFind(".result").text should be ("result_text") }
  }
}
*/
