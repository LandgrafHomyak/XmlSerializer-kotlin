package xml

import java.lang.reflect.Field

/*
*
*
*
*/
internal open class CollectionData private constructor(objectClass: Class<*>, val iterable: Boolean) {
    val children: Array<AbstractChildFieldWrapper>

    fun children(o: Any): Iterator<Any> = iterator {
        for (child in this@CollectionData.children) {
            yieldAll(child.iter(o))
        }
    }

    init {
        val children = ArrayList<AbstractChildFieldWrapper>()
        for (field in objectClass.declaredFields) {
            val parameter: XmlParameter? = field.getAnnotation(XmlParameter::class.java)
            val child: XmlChild? = field.getAnnotation(XmlChild::class.java)
            val childrenCollection: XmlChildrenCollection? = field.getAnnotation(XmlChildrenCollection::class.java)

            if (
                (if (parameter == null) 0 else 1) +
                (if (child == null) 0 else 1) +
                (if (childrenCollection == null) 0 else 1) > 1
            ) {
                throw XmlAnnotationConflictException()
            } else if (child != null) {
                children.add(ChildFieldWrapper(field))
            } else if (childrenCollection != null) {
                children.add(ChildrenCollectionFieldWrapper(field))
            }
        }
        this.children = children.toTypedArray()
    }

    constructor(objectClass: Class<*>) : this(
        objectClass,
        objectClass.getAnnotation(XmlObject::class.java)?.iterable ?: objectClass.getAnnotation(XmlChildrenCollection::class.java)?.iterable ?: throw ObjectNotXmlSerializableException()
    ) {
        if ((if (objectClass.getAnnotation(XmlObject::class.java) == null) 0 else 1) +
            (if (objectClass.getAnnotation(XmlChildrenCollection::class.java) == null) 0 else 1) > 1
        ) {
            throw XmlAnnotationConflictException()
        }
    }

    constructor(field: Field) : this(
        field.type,
        field.getAnnotation(XmlChildrenCollection::class.java)?.iterable ?: throw ObjectNotXmlSerializableException()
    ) {
        if (
            (field.getAnnotation(XmlParameter::class.java) != null) ||
            (field.getAnnotation(XmlChild::class.java) != null)
        ) {
            throw XmlAnnotationConflictException()
        }
    }
}