package xml

import java.lang.reflect.Field

internal class ChildFieldWrapper(field: Field) : AbstractChildFieldWrapper(field) {
    override fun iter(o: Any): Iterator<Any> {
        val co = this.get(o) ?: return iterator {}
        return iterator { yield(co) }
    }
}