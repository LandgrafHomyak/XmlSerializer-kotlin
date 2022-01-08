package xml

class XmlAnnotationConflictException :
    XmlSerializationException("Field can have not greater than one of this annotations: '${XmlChild::class.java.name}', '${XmlChildrenCollection::class.java.name}' or '${XmlParameter::class.java.name}'")
