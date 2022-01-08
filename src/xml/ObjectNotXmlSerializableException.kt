package xml

class ObjectNotXmlSerializableException :
    XmlSerializationException("Serializable class must be marked by annotation ${XmlObject::class.java.name}")