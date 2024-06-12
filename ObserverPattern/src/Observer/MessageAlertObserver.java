package Observer;

import Observable.StocksObservable;

public class MessageAlertObserver implements NotificationAlertObserver {
    String phoneNo;
    StocksObservable observable;

    public MessageAlertObserver(String phoneNo, StocksObservable observable) {
        this.phoneNo = phoneNo;
        this.observable = observable;
    }

    private void sendMessage() {
        System.out.println("message sent to " + this.phoneNo);
    }

    @Override
    public void update() {
        this.sendMessage();
    }
}
