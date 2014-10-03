package pl.countries.persistence
import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.json.spray.SprayJsonContext
import net.fwbrasil.activate.storage.StorageFactory
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import net.fwbrasil.activate.migration.Migration
import pl.countries.model._
import net.fwbrasil.activate.storage.relational.PooledJdbcRelationalStorage
import net.fwbrasil.activate.storage.relational.idiom.h2Dialect

object CountriesContext extends ActivateContext {
  val storage = new PooledJdbcRelationalStorage {
    val jdbcDriver = "org.h2.Driver"
    val user = Some("sa")
    val password = Some("")
    val url = "jdbc:h2:target/countries;AUTO_SERVER=TRUE"
    val dialect = h2Dialect
  }
  override protected def entitiesPackages = List("pl.countries.model")
}

//json formats for spray
object ModelJsonProtocol extends DefaultJsonProtocol with SprayJsonContext with SprayJsonSupport {
  val context = CountriesContext

  implicit val countryFormat = jsonFormat3(Country)
  implicit val airportFormat = jsonFormat6(City)
}
