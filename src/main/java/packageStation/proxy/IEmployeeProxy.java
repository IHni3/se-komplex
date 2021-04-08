package packageStation.proxy;

public interface IEmployeeProxy {

    boolean hasInitRights();

    boolean hasNextRights();

    boolean hasLockRights();

    boolean hasUnlockRights();

    boolean hasChangeAlgorithmRights();

    boolean hasShowStatisticsRights();

    boolean hasShutdownRights();
}
