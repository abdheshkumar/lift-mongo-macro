package org.eiennohito

import reflect.macros.Context
import com.foursquare.field.Field
import com.foursquare.rogue.{DummyField, QueryField}

/**
 * @author eiennohito
 * @since 17.01.13 
 */

object TestMacro {
  type CT[T] = Context {
    type PrefixType = T
  }
  def makeGreat[T: c.WeakTypeTag](c: Context)(got: c.Expr[T]): c.Expr[QueryField[T, AnyRef]] = {
    import c.universe._

    val name = got match {
      case Expr(Select(_, x)) => x.toTermName.decoded
      case _ => c.error(c.enclosingPosition, showRaw(got)); ""
    }

    c.universe.reify(new QueryField[T, AnyRef](new DummyField[T, AnyRef](c.literal(name).splice, null)))
  }
}
