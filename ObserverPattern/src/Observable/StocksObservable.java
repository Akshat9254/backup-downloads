package Observable;

import Observer.NotificationAlertObserver;

public interface StocksObservable {
    public void add(NotificationAlertObserver observer);
    public void remove(NotificationAlertObserver observer);
    public void notifySubscribers();
    public void setStocks(int newStocks);
    public int getStocks();
}
