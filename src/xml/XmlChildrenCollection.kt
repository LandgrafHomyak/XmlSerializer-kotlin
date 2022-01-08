package xml


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
annotation class XmlChildrenCollection(
    val iterable: Boolean = true
)
