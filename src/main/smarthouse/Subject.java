package smarthouse;

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyMovement(boolean value);

    void notifyTemperature(int value);

}
