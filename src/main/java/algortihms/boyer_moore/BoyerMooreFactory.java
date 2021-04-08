package algortihms.boyer_moore;


import factory_utils.FactoryUtils;
import mainConfiguration.Configuration;

public class BoyerMooreFactory {

    public static Object build() {
        return FactoryUtils.build(Configuration.instance.pathToBoyerMooreJavaArchive, "BoyerMoore");
    }
}
