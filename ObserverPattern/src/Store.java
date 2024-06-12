import Observable.IphoneStocksObservable;
import Observable.StocksObservable;
import Observer.EmailAlertObserver;
import Observer.MessageAlertObserver;
import Observer.NotificationAlertObserver;

public class Store {
    public static void main(String[] args) {
        StocksObservable iphoneStocksObservable = new IphoneStocksObservable();


        NotificationAlertObserver observer1 = new EmailAlertObserver("john@test.com", iphoneStocksObservable);
        NotificationAlertObserver observer2 = new MessageAlertObserver("+919794229032", iphoneStocksObservable);
        NotificationAlertObserver observer3 = new EmailAlertObserver("jane@test.com", iphoneStocksObservable);

        iphoneStocksObservable.add(observer1);
        iphoneStocksObservable.add(observer2);
        iphoneStocksObservable.add(observer3);

        iphoneStocksObservable.setStocks(10);

        iphoneStocksObservable.remove(observer1);

        iphoneStocksObservable.setStocks(0);
        iphoneStocksObservable.setStocks(10);

    }
}
