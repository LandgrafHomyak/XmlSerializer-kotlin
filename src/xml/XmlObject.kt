package xml


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class XmlObject(
    val name: String,
    val iterable: Boolean = false
)
