import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class Converter {
    ArrayList<String> xmlList = new ArrayList<>();

    public void reflection(ArrayList instances) throws IllegalAccessException {
        for(Object instance: instances) {
            xmlList.add("<ticket>");
            for (Field field : instance.getClass().getDeclaredFields()) {

                FieldAnnotation annotation = field.getAnnotation(FieldAnnotation.class);
                if (annotation != null) {
                    field.setAccessible(true);
                    xmlList.add("    <" + annotation.value() + ">" + field.get(instance) + "</" + annotation.value() + ">");
                }
            }
            xmlList.add("</ticket>");
        }
        if (instances.size() > 1){

            for(String str : xmlList){
                xmlList.set(xmlList.indexOf(str), "    "+str);
            }
            xmlList.add(0,"<catalog>");
            xmlList.add("</catalog>");
        }
    }

    public void writeXML(Path file) throws IOException {
        Files.write(file, this.xmlList, StandardCharsets.UTF_8);
    }
}
