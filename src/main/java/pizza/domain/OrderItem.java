package pizza.domain;

import java.util.List;

public record OrderItem(int size, CrustType crust, String style, List<String> toppings) {}
