package factory_utils;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;


public class FactoryUtils {
    public static Object build(String archivePath, String className) {
        Object port = null;

        try {
            URL[] urls = {new File(archivePath).toURI().toURL()};
            var urlClassLoader = new URLClassLoader(urls, FactoryUtils.class.getClassLoader());
            var archiveClass = Class.forName(className, true, urlClassLoader);

            Object archiveInstance = archiveClass.getMethod("getInstance").invoke(null);

            port = archiveClass.getDeclaredField("port").get(archiveInstance);
        } catch (Exception e) {
            System.out.println("archivePath '" + archivePath + "' not found!");
            e.printStackTrace();
        }

        return port;
    }
}

