public class Main {

    public static void main(String[] args) {

        PaymentProcessor payPal = new PayPalAdapter(new PayPalGateway());
        payPal.processPayment(1500);

        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(2500);

    }

}