package xml

import java.lang.reflect.Field

internal class ParameterFieldWrapper(private val field: Field) {
    companion object {
        @JvmStatic
        val allowedTypes = setOf<Class<*>>(
            Boolean::class.java,
            UInt::class.java,
            Int::class.java,
            Short::class.java,
            Char::class.java,
            Byte::class.java,
            Long::class.java,
            Float::class.java,
            Double::class.java,
            String::class.java,
            UByte::class.java,
            ULong::class.java,
            UShort::class.java,
        )
    }

    val name: String

    init {
        val meta: XmlParameter = field.getAnnotation(XmlParameter::class.java) ?: throw ObjectNotXmlSerializableException()

        if (this.field.annotatedType.type !in ParameterFieldWrapper.allowedTypes) {
            throw InvalidXmlParameterType()
        }

        this.name = meta.name
    }

    fun get(o: Any): String? {
        val data: Any = this.field.get(o) ?: return null
        return if (this.field.annotatedType.type == String::class.java)
            "${this.name}=\"${(data as String).replace("\"", "&quot;")}\""
        else
            "${this.name}=\"$data\""
    }
}