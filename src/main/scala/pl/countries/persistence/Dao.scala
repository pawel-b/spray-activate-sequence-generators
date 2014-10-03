package pl.countries.persistence

import pl.countries.persistence.CountriesContext._
import pl.countries.model._

object CountryDao {
  def getAllCountries() = {
    transactional {
      query {
        (e: Country) => where() select (e) orderBy (e.name)
      }
    }
  }

  def getByCode(code: String) = {
    transactional {
      query {
        (e: Country) => where(e.code :== code) select (e) orderBy (e.name)
      }
    }
  }
}

object CityDao {
  def getAllCities() = {
    transactional {
      query {
        (e: City) => where() select (e) orderBy (e.name)
      }
    }
  }
}
