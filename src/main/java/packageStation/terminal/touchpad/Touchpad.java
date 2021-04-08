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
    public void clickInit() {
        if (proxy.hasInitRights()) {
            controlUnit.setCommand(new Init(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }
    }

    @Override
    public void clickNext() {
        if (proxy.hasNextRights()) {
            controlUnit.setCommand(new Next(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }

    }

    @Override
    public void clickShutdown() {
        if (proxy.hasShutdownRights()) {
            controlUnit.setCommand(new Shutdown(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }

    }

    @Override
    public void clickLock() {
        if (proxy.hasLockRights()) {
            controlUnit.setCommand(new Lock(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }

    }

    @Override
    public void clickUnlock() {
        if (proxy.hasUnlockRights()) {
            controlUnit.setCommand(new Unlock(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }

    }

    @Override
    public void clickShowStatistics() {
        if (proxy.hasShowStatisticsRights()) {
            controlUnit.setCommand(new ShowStatistics(controlUnit.getPackageSortingStation()));
            controlUnit.touchPadBtnPressed();
        }

    }

    @Override
    public void clickChangeSearchAlgorithm() {
        if (proxy.hasChangeAlgorithmRights()) {
            if (controlUnit.getSearchAlgorithm() == SearchAlgorithm.BM) {
                controlUnit.setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.RK, controlUnit.getPackageSortingStation()));
                controlUnit.touchPadBtnPressed();
            }
            if (controlUnit.getSearchAlgorithm() == SearchAlgorithm.RK) {
                controlUnit.setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.BM, controlUnit.getPackageSortingStation()));
                controlUnit.touchPadBtnPressed();
            }

        }

    }

    @Override
    public void clickChangeSearchAlgorithmToBM() {
        controlUnit.setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.BM, controlUnit.getPackageSortingStation()));
        controlUnit.touchPadBtnPressed();
    }

    @Override
    public void clickChangeSearchAlgorithmToRK() {
        controlUnit.setCommand(new ChangeSortingAlgorithm(SearchAlgorithm.RK, controlUnit.getPackageSortingStation()));
        controlUnit.touchPadBtnPressed();
    }
}
