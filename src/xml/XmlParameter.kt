package xml


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class XmlParameter(
    val name: String
)
