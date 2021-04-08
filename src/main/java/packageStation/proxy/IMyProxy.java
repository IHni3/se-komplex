package packageStation.proxy;

public interface IMyProxy {

    boolean hasInitRights();

    boolean hasNextRights();

    boolean hasLockRights();

    boolean hasUnlockRights();

    boolean hasChangeAlgorithmRights();

    boolean hasShowStatisticsRights();

    boolean hasShutdownRights();
}
