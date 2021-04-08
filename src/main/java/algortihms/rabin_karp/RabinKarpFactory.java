package algortihms.rabin_karp;


import factory_utils.FactoryUtils;
import mainConfiguration.Configuration;

public class RabinKarpFactory {

    public static Object build() {
        return FactoryUtils.build(Configuration.instance.pathToRabinKarpJavaArchive, "RabinKarp");
    }
}
