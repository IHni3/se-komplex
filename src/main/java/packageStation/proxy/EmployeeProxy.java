package packageStation.proxy;

import employee.Employee;

public class EmployeeProxy implements IEmployeeProxy {

    private Employee employee;

    public EmployeeProxy(Employee employee) {
        this.employee = employee;

    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean checkInitRights() {
        switch (employee.getRole()) {
            case SUPERVISOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: init");
                return false;
            }
        }
    }

    @Override
    public boolean checkNextRights() {
        switch (employee.getRole()) {
            case SUPERVISOR, OPERATOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: next");
                return false;
            }
        }

    }

    @Override
    public boolean checkLockRights() {

        switch (employee.getRole()) {
            case SUPERVISOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: lock");
                return false;
            }
        }
    }

    @Override
    public boolean checkUnlockRights() {

        switch (employee.getRole()) {
            case SUPERVISOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: unlock");
                return false;
            }
        }
    }

    @Override
    public boolean checkChangeAlgorithmRights() {

        switch (employee.getRole()) {
            case SUPERVISOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: algorithm change");
                return false;
            }
        }
    }

    @Override
    public boolean checkShowStatisticsRights() {
        switch (employee.getRole()) {
            case SUPERVISOR, OPERATOR, ADMINISTRATOR, DATA_SCIENTIST -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: statistics");
                return false;
            }
        }
    }

    @Override
    public boolean checkShutdownRights() {

        switch (employee.getRole()) {
            case SUPERVISOR, ADMINISTRATOR -> {
                return true;
            }
            default -> {
                System.out.println("authorization denied: shutdown");
                return false;
            }
        }
    }


}
