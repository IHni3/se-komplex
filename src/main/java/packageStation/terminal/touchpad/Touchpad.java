package packageStation.terminal.touchpad;

import employee.Employee;
import packageStation.ControlUnit;
import packageStation.command.Shutdown;
import packageStation.command.*;
import packageStation.proxy.IEmployeeProxy;
import packageStation.proxy.EmployeeProxy;

public class Touchpad implements ITouchpad {
    Employee employee;
    IEmployeeProxy proxy;
    ControlUnit controlUnit;


    public Touchpad(Employee employee, ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        this.employee = employee;
        proxy = new EmployeeProxy(employee);
    }

    @Override
    public void init() {
        if (proxy.checkInitRights()) {
            controlUnit.setCommand(new Init(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }
    }

    @Override
    public void next() {
        if (proxy.checkNextRights()) {
            controlUnit.setCommand(new Next(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }

    }

    @Override
    public void shutDown() {
        if (proxy.checkShutdownRights()) {
            controlUnit.setCommand(new Shutdown(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }

    }

    @Override
    public void lock() {
        if (proxy.checkLockRights()) {
            controlUnit.setCommand(new Lock(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }

    }

    @Override
    public void unlock() {
        if (proxy.checkUnlockRights()) {
            controlUnit.setCommand(new Unlock(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }

    }

    @Override
    public void showStatistics() {
        if (proxy.checkShowStatisticsRights()) {
            controlUnit.setCommand(new ShowStatistics(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadUsed();
        }

    }

    @Override
    public void changeSearchAlgorithmToBM() {
        controlUnit.setCommand(new ChangeSearchAlgorithm(SearchAlgorithm.BM, controlUnit.getPackageSortingStation()));
        controlUnit.touchPadUsed();
    }

    @Override
    public void changeSearchAlgorithmToRK() {
        controlUnit.setCommand(new ChangeSearchAlgorithm(SearchAlgorithm.RK, controlUnit.getPackageSortingStation()));
        controlUnit.touchPadUsed();
    }
}
