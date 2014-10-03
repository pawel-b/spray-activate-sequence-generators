package pl.countries.model

import pl.countries.persistence.CountriesContext._
import net.fwbrasil.activate.entity.Entity
import net.fwbrasil.activate.entity.EntityWithGeneratedID
import net.fwbrasil.activate.entity.id.SegmentedIdGenerator
import net.fwbrasil.activate.sequence.IntSequenceEntity

@Alias("COUNTRY")
case class Country(code: String, name: String, currencySymbol: String) extends EntityWithGeneratedID[Int]
class CountryIdGenerator extends SegmentedIdGenerator[Country](IntSequenceEntity(sequenceName = "countrySequence", step = 10))

case class City(id: Int, code: String, name: String, latitude: Double, longitude: Double, country: Country) extends EntityWithCustomID[Int]
