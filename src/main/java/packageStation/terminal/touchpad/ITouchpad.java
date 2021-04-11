package packageStation.terminal.touchpad;

public interface ITouchpad {

    void init();

    void next();

    void shutDown();

    void lock();

    void unlock();

    void showStatistics();

    void changeSearchAlgorithmToBM();

    void changeSearchAlgorithmToRK();
}
