import ideaeclipse.CustomProperties.Properties;

import java.io.IOException;

public class Test {
    private Test() throws IOException {
        Properties properties = new Properties(new String[]{"key1", "key2"});
        properties.start();
        System.out.println(properties.getProperty("key1"));
        System.out.println(properties.getProperty("key2"));
    }

    public static void main(String[] args) throws IOException {
        new Test();
    }
}
