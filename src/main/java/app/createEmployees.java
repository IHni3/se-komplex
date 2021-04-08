package app;

import employee.*;
import mainConfiguration.Configuration;

public class createEmployees {

    public static void main(String... args) throws Exception {
        Administrator admin = new Administrator(
                0,
                "Armin",
                Roles.ADMINISTRATOR,
                "0000",
                Configuration.instance.superPin
        );
        DataAnalyst dataAnalyst = new DataAnalyst(
                1,
                "Detlef",
                Roles.DATA_ANALYST,
                "1111",
                Configuration.instance.superPin
        );

        Operator operator = new Operator(
                2,
                "Olaf",
                Roles.OPERATOR,
                "2222",
                Configuration.instance.superPin
        );
        Supervisor supervisor = new Supervisor(
                3,
                "Steffan",
                Roles.SUPERVISOR,
                "3333",
                Configuration.instance.superPin
        );


    }
}
