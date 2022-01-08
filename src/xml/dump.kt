package xml

import java.io.OutputStream
import java.lang.reflect.Field

const val defaultStartIndent: UInt = 0u
const val defaultIndentDelta: UInt = 1u

fun dump(o: Any, indent: UInt = defaultStartIndent, indentDelta: UInt = defaultIndentDelta) {
    val metadata = ObjectData(o::class.java)
    println(metadata.children(o).asSequence().toList().toTypedArray())
}
//
//fun dumps(o: Any, indent: UInt = defaultStartIndent, indentDelta: UInt = defaultIndentDelta): String {
//
//}

