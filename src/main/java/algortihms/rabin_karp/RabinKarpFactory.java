package algortihms.rabin_karp;


import factory_utils.FactoryUtils;
import main_configuration.Configuration;

public class RabinKarpFactory {

    public static Object build() {
        return FactoryUtils.build(Configuration.instance.pathToRabinKarpJavaArchive, "RabinKarp");
    }
}
