package pizza.domain;

import java.util.List;

public record Order(String customerName, List<OrderItem> orderItems) {}
