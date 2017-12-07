package cn.javaer.wechat.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zhangpeng
 */
public class WeChatTestUtils {

    @SuppressWarnings("unchecked")
    public static <T> T jaxbUnmarshal(String xmlStr, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xmlStr);
            return (T) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readClassPathFileToUTFString(String fileClassPath, Class clazzForClassPath) {
        try {
            final byte[] bytes = Files.readAllBytes(Paths.get(clazzForClassPath.getResource(fileClassPath).getFile()));
            return new String(bytes, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
