import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;

public class SimpleTest {

    @Test
    public void test() throws UnsupportedEncodingException {
//        String name = "吴晓东a";
        String name = "TutorialsPoint?java西夏好和你好欧文TutorialsPoint?java西夏好和你好欧文TutorialsPoint?java西夏好和你好欧文Tutorial";
        byte[] nameBytes = name.getBytes("UTF-8");
        String defaultHexString = Hex.encodeHexString(nameBytes, false);
        System.out.println("defaultHexString is :" + defaultHexString);

        String baseEncodedName = Base64.getEncoder().encodeToString(nameBytes);
        System.out.println("baseEncodedName:"+baseEncodedName);
        String urlEncodedName = Base64.getUrlEncoder().encodeToString(nameBytes);
        System.out.println("urlEncodedName:"+urlEncodedName);
        String mimeEncodedName = Base64.getMimeEncoder().encodeToString(nameBytes);
        System.out.println("mimeEncodedName:"+mimeEncodedName);
    }

    @Test
    public void testURLEncode() throws UnsupportedEncodingException {
        String encodedStr = URLEncoder.encode("吴晓东", "utf-8");
        System.out.println(encodedStr);


    }
}
