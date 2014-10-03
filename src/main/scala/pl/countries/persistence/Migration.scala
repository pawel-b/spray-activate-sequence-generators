package pl.countries.persistence

import net.fwbrasil.activate.migration.Migration
import pl.countries.persistence.CountriesContext._
import pl.countries.model.Country
import scala.util.Random
import pl.countries.model.City

class CountriesMigration extends Migration {

  def timestamp = System.currentTimeMillis

  def randomString(len: Int): String = {
    val rand = new scala.util.Random(System.nanoTime)
    val sb = new StringBuilder(len)
    val ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    for (i <- 0 until len) {
      sb.append(ab(rand.nextInt(ab.length)))
    }
    sb.toString
  }

  def up = {
    removeAllEntitiesTables.ifExists
    createTableForAllEntities.ifNotExists

    customScript {
      for (i <- 1 to 100) new Country(randomString(2).toLowerCase, randomString(10), randomString(3).toUpperCase)
      for (i <- 1 to 100) new City(i, randomString(2).toLowerCase, randomString(10), Random.nextDouble, Random.nextDouble, CountryDao.getAllCountries.toList(Random.nextInt(100)))
    }
  }

}