package xml

internal class ObjectData(objectClass: Class<*>) : CollectionData(objectClass) {
    val name: String
    val parameters: Array<ParameterFieldWrapper>

    fun parameters(o: Any): Sequence<Any> = sequence {
        for (parameter in this@ObjectData.parameters) {
            yield(parameter.get(o) ?: continue)
        }
    }

    init {
        val meta: XmlObject = objectClass.getAnnotation(XmlObject::class.java) ?: throw ObjectNotXmlSerializableException()

        val parameters = ArrayList<ParameterFieldWrapper>()
        for (field in objectClass.declaredFields) {
            if (field.getAnnotation(XmlParameter::class.java) == null) {
                continue
            }
            parameters.add(ParameterFieldWrapper(field))
        }

        this.name = meta.name
        this.parameters = parameters.toTypedArray()
    }
}