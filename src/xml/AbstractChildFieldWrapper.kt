package xml

import java.lang.reflect.Field

internal abstract class AbstractChildFieldWrapper(protected val field: Field) {
    abstract fun iter(o: Any): Iterator<Any>

    protected fun get(o: Any): Any? {
        val baseAccess = this.field.isAccessible
        this.field.isAccessible = true
        val co = this.field.get(o)
        this.field.isAccessible = false
        return co
    }
}