public class Main {

    public static void main(String[] args) {

        PaymentContext creditCard =
                new PaymentContext(new CreditCardPayment());

        creditCard.executePayment(1500);

        PaymentContext paypal =
                new PaymentContext(new PayPalPayment());

        paypal.executePayment(2500);

    }

}