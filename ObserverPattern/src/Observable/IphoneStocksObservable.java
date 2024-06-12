package Observable;

import Observer.NotificationAlertObserver;

import java.util.ArrayList;
import java.util.List;

public class IphoneStocksObservable implements StocksObservable {
    List<NotificationAlertObserver> observerList;
    int stocks;

    public IphoneStocksObservable() {
        this.observerList = new ArrayList<>();
        this.stocks = 0;
    }

    @Override
    public void add(NotificationAlertObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(NotificationAlertObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(NotificationAlertObserver observer: observerList) {
            observer.update();
        }
    }

    @Override
    public void setStocks(int newStocks) {
        if(stocks == 0) {
            notifySubscribers();
        }
        stocks = newStocks;
    }

    @Override
    public int getStocks() {
        return stocks;
    }
}
