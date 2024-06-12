package Observer;

import Observable.StocksObservable;

public class EmailAlertObserver implements NotificationAlertObserver {
    String emailId;
    StocksObservable observable;

    public EmailAlertObserver(String emailId, StocksObservable observable) {
        this.emailId = emailId;
        this.observable = observable;
    }

    private void sendEmail() {
        System.out.println("email sent to " + emailId);
    }

    @Override
    public void update() {
        sendEmail();
    }
}
