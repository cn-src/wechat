package cn.javaer.wechat.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhangpeng
 */
public class WeChatTestUtils {

    @SuppressWarnings("unchecked")
    public static <T> T jaxbUnmarshal(final String xmlStr, final Class<T> clazz) {
        try {
            final JAXBContext context = JAXBContext.newInstance(clazz);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final StringReader reader = new StringReader(xmlStr);
            return (T) unmarshaller.unmarshal(reader);
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static String jaxbMarshal(final Object obj) {
        try {
            final JAXBContext context = JAXBContext.newInstance(obj.getClass());
            final Marshaller marshaller = context.createMarshaller();
            final StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (final JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readClassPathFileToUTFString(final String fileClassPath, final Class clazzForClassPath) {
        try {
            final byte[] bytes = Files.readAllBytes(Paths.get(clazzForClassPath.getResource(fileClassPath).getFile()));
            return new String(bytes, "UTF-8");
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
