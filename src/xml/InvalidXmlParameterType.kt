package xml

class InvalidXmlParameterType :
    XmlSerializationException(
        "For XML object parameter allowed only this types: ${
            ParameterFieldWrapper.allowedTypes.joinToString { k -> k.name }
        }"
    )
