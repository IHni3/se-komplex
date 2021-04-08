package packageStation.proxy;

import employee.Employee;
import employee.Roles;

public class MyProxy implements IMyProxy {

    Employee employee;

    public MyProxy(Employee employee) {
        this.employee = employee;

    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean hasInitRights() {
        if (employee.getRole() == Roles.SUPERVISOR) {
            return true;
        } else {
            System.out.println("unauthorized for init");
            return false;
        }
    }

    @Override
    public boolean hasNextRights() {
        if (employee.getRole() == Roles.SUPERVISOR || employee.getRole() == Roles.OPERATOR) {
            return true;
        } else {
            System.out.println("unauthorized for next");
            return false;
        }

    }

    @Override
    public boolean hasLockRights() {
        if (employee.getRole() == Roles.SUPERVISOR) {
            return true;
        } else {
            System.out.println("unauthorized for lock");
            return false;
        }
    }

    @Override
    public boolean hasUnlockRights() {
        if (employee.getRole() == Roles.SUPERVISOR) {
            return true;
        } else {
            System.out.println("unauthorized for unlock");
            return false;
        }
    }

    @Override
    public boolean hasChangeAlgorithmRights() {
        if (employee.getRole() == Roles.SUPERVISOR) {
            return true;
        } else {
            System.out.println("unauthorized for unlock");
            return false;
        }
    }

    @Override
    public boolean hasShowStatisticsRights() {
        if (employee.getRole() == Roles.SUPERVISOR ||
                employee.getRole() == Roles.DATA_ANALYST ||
                employee.getRole() == Roles.ADMINISTRATOR ||
                employee.getRole() == Roles.OPERATOR
        ) {
            return true;
        } else {
            System.out.println("unauthorized for unlock");
            return false;
        }
    }

    @Override
    public boolean hasShutdownRights() {
        if (employee.getRole() == Roles.SUPERVISOR || employee.getRole() == Roles.ADMINISTRATOR) {
            return true;
        } else {
            System.out.println("unauthorized for shutdown");
            return false;
        }
    }


}
