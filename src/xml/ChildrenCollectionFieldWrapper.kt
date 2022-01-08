package xml

import java.lang.reflect.Field

internal class ChildrenCollectionFieldWrapper(field: Field) : AbstractChildFieldWrapper(field) {
    private val data: CollectionData = CollectionData(field)
    override fun iter(o: Any): Iterator<Any> {
        val co: Any = this.get(o) ?: return iterator {}
        return this@ChildrenCollectionFieldWrapper.data.children(co)
    }
}