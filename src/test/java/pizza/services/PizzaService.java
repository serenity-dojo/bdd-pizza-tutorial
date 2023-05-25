package pizza.services;

import pizza.domain.Order;
import pizza.domain.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class PizzaService {
    public void placeOrder(List<OrderItem> pizzaOrder) {
        // TODO: Implement this method
        System.out.println("Pizza order: " + pizzaOrder.toString());
    }

    public void completePaymentFor(List<OrderItem> pizzaOrder) {
        System.out.println("Pizza order payment completed: " + pizzaOrder.toString());
    }

    public List<Order> getPendingOrders() {
        return new ArrayList();
    }
}
