package packageStation.proxy;

public interface IEmployeeProxy {

    boolean checkInitRights();

    boolean checkNextRights();

    boolean checkLockRights();

    boolean checkUnlockRights();

    boolean checkChangeAlgorithmRights();

    boolean checkShowStatisticsRights();

    boolean checkShutdownRights();
}
