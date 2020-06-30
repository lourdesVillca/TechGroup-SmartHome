package smarthouse;

public interface Observer {
    void update(int value);

    void update(boolean status);

    String getObjectName();
}
