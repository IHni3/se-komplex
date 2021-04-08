package algortihms.boyer_moore;


import factory_utils.FactoryUtils;
import main_configuration.Configuration;

public class BoyerMooreFactory {

    public static Object build() {
        return FactoryUtils.build(Configuration.instance.pathToBoyerMooreJavaArchive, "BoyerMoore");
    }
}
