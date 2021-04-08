package employee;

public class Supervisor extends Employee {
    private boolean isSenior;

    public Supervisor(int id, String name, Roles role, String pin, String superPin) throws Exception {
        super(id, name, role, pin, superPin);
    }

    public boolean isSenior() {
        return isSenior;
    }

    public void setSenior(boolean senior) {
        isSenior = senior;
    }
}
