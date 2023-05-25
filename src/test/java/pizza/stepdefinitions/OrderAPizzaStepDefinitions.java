package pizza.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pizza.domain.CrustType;
import pizza.domain.Order;
import pizza.domain.OrderItem;
import pizza.services.MessageService;
import pizza.services.PizzaService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderAPizzaStepDefinitions {

    PizzaService pizzaService = new PizzaService();
    MessageService messageService = new MessageService();

    @DataTableType
    public OrderItem order(Map<String, String> rowValues) {
        int size = Integer.parseInt(rowValues.get("Size"));
        CrustType crust = CrustType.valueOf(rowValues.get("Crust").toUpperCase());
        String style = rowValues.get("Style");
        List<String> toppings = (rowValues.get("Toppings") == null) ? new ArrayList<>()
                : Arrays.asList(rowValues.get("Toppings").split(","));
        return new OrderItem(size, crust, style, toppings);
    }

    List<OrderItem> pizzaOrder;
    String username;

    @Given("{} wants to order the following pizza:")
    public void wantsToOrderTheFollowingPizza(String username, List<OrderItem> pizzaOrder) {
        this.username = username;
        this.pizzaOrder = pizzaOrder;
    }

    @When("he places his order")
    public void hePlacesHisOrder() {
        pizzaService.placeOrder(pizzaOrder);
    }

    @And("he successfully pays for his order")
    public void heSuccessfullyPaysForHisOrder() {
        pizzaService.completePaymentFor(pizzaOrder);
    }

    @Then("Peter should be sent a message confirming his order and estimated delivery time")
    public void peterShouldBeSentAMessageConfirmingHisOrderAndEstimatedDeliveryTime() {
        assertThat(messageService.getMessagesFor(username)).isNotEmpty();
    }

    @And("the order should be added to the order list")
    public void theOrderShouldBeAddedToTheOrderList() {
        // Check that the order was added to the order list
        List<Order> ordersForPeter = pizzaService.getPendingOrders().stream()
                .filter(order -> order.customerName().equals(username))
                .toList();
        assertThat(ordersForPeter).isNotEmpty();
    }

    @Given("the current menu prices per size are:")
    public void theCurrentMenuPricesPerSizeAre(DataTable menuPrices) {
        System.out.println("The current menu prices per size are: " + menuPrices.toString());
    }

    @And("the current topping prices are:")
    public void theCurrentToppingPricesAre(DataTable toppingPrices) {
        System.out.println("The current topping prices are: " + toppingPrices.toString());
    }

    @Given("Peter is looking at the menu")
    public void peterIsLookingAtTheMenu() {
    }

    @And("he chooses the following options:")
    public void heChoosesTheFollowingOptions(DataTable options) {
        System.out.println("He chooses the following options: " + options.toString());
    }

    @Then("he should be able to see the total price of the pizza is {double}")
    public void heShouldBeAbleToSeeTheTotalPriceOfThePizzaIsPrice(double expectedPrice) {
        // Check that the price is the expeceted price
    }

    @When("he selects a {string} pizza")
    public void heSelectsAPizza(String speciality) {

    }
}
