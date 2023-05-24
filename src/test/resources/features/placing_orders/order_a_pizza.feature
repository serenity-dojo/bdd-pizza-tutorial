Feature: Order a pizza

  Rule: Users should be able to order pizzas and receive appropriate confirmation
    Example: Peter orders a basic pizza
      Given Peter wants to order the following pizza:
        | size | crust | topping |
        | 12   | thin  | cheese  |
      When he places his order
      And he successfully pays for his order
      Then Peter should be sent a message confirming his order and estimated delivery time
      And the order should be added to the order list

    Example: Peter orders several different custom-made pizzas
      Given Peter wants to order the following pizza:
        | size | crust | style      | topping                   |
        | 12   | thin  | custom     | cheese, ham               |
        | 8    | thick | custom     | pepperoni, peppers, onion |
        | 10   | thin  | Margherita |                           |
      When he places his order
      And he successfully pays for his order
      Then Peter should be sent a message confirming his order and estimated delivery time
      And the order should be added to the order list

  Rule: Users can order pizzas with their own choice of toppings
    Background: Peter is looking at the menu
      Given the current menu prices per size are:
        | Speciality      | Size 8 | Size 10 | Size 12 |
        | Margherita      | 10.00  | 11.00   | 12.00   |
        | Hawaiian        | 12.00  | 13.00   | 14.00   |
        | Pepperoni Feast | 14.00  | 15.00   | 16.00   |
      And the current topping prices are:
        | cheese       | Free |
        | ham          | Free |
        | onion        | Free |
        | extra cheese | 1.00 |

    Scenario Outline: Peter orders several different custom-made pizzas
      Given Peter is looking at the menu
      When he selects a <Speciality> pizza
      And he chooses the following options:
        | Size   | Crust   | Toppings   |
        | <Size> | <Crust> | <Toppings> |
      Then he should be able to see the total price of the pizza is <Price>
      Examples:
        | Speciality      | Size | Crust | Toppings                 | Price |
        | Margherita      | 12   | thin  | cheese, ham              | 12.00 |
        | Hawaiian        | 10   | think | ham, onion               | 13.00 |
        | Pepperoni Feast | 12   | pan   | ham, onion, extra cheese | 17    |

  Rule: Users can order speciality pizzas
    Example: Peter orders several different custom-made pizzas
      Given Peter wants to order the following pizza:
        | size | crust | speciality      |
        | 12   | thin  | Margherita      |
        | 12   | thick | Hawaiian        |
        | 10   | pan   | Pepperoni Feast |
      When he places his order
      And he successfully pays for his order
      Then Peter should be sent a message confirming his order and estimated delivery time
      And the order should be added to the order list

  Rule: Users should be able to order a pizza with various sizes, crust types, toppings


