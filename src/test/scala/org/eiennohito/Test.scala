package org.eiennohito

import com.foursquare.rogue.{Query, LiftRogue}
import net.liftweb.mongodb.{MongoDocumentMeta, MongoDocument}
import com.foursquare.rogue.MongoHelpers.AndCondition
import org.bson.types.ObjectId
import org.scalatest.FreeSpec
import org.scalatest.matchers.ShouldMatchers
import com.foursquare.rogue
import com.foursquare.field.Field
import com.mongodb.BasicDBObject

/**
 * @author eiennohito
 * @since 17.01.13 
 */

trait DocumentRogue extends LiftRogue {
  import language.experimental.macros
  import com.foursquare.rogue._
  implicit def mongoDocToQBldr[R <: MongoDocument[R]](rec: MongoDocumentMeta[R]): Query[rec.type, R, InitialState] = {
    Query[rec.type, R, InitialState](
          rec.asInstanceOf[rec.type], rec.collectionName, None, None, None, None, None, AndCondition(Nil, None), None, None, None)

  }

  implicit def x2Field[T](got: T): QueryField[T, _] = macro TestMacro.makeGreat[T]
}

object DocumentRogue extends DocumentRogue

case class TestDoc(id: ObjectId, owner: String) extends MongoDocument[TestDoc] {
  def _id = id
  def meta = TestDoc
}

object TestDoc extends MongoDocumentMeta[TestDoc] {
  def owner: String = ???
}

class DocumentRogueSpec extends FreeSpec with ShouldMatchers {
  import DocumentRogue._
  "Rogue with case classes" - {
    "should generate find for owner" in {
      type T = MongoDocumentMeta[TestDoc] with TestDoc
      val q = TestDoc where (_.owner eqs "asd")
      q.toString() should equal ("""db.testdocs.find({ "owner" : "asd"})""")
    }
  }
}
