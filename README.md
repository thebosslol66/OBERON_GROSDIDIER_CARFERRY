# Analise du projet Carferry

```mermaid

classDiagram

Boat "1" -- "1" Wedge
Boat "1" -- "1*" Ticket
Boat "1" -- "1" Accounting

Row "1" -- "*" Vehicle
Row --|> Comparable~E~ : Implements
Wedge "1" -- "many" Row
Vehicle <|-- Car
Vehicle <|-- Truck
Vehicle "1" -- "1" Driver
Ticket "1" -- "1" Vehicle
Ticket --|> Comparable~E~ : Implements
Ticket "1" -- "1" Position

class Comparable~E~ {
    <<interface>>

    +compareTo(E e) int
}

class Position {
    -int row
    -int pos

    +getRow() int
    +getPos() int
}

class Driver {
    -String name
    -String firstName
    -int permNumber

    +equals(Object o) boolean
    +getName() String
    +getFirstName() String
    +getPermNumber() int
}

class Vehicle {
    <<abstract>>
    -String registration
    -double weight
    -double length
    -Driver driver

    +getDriver() Driver
    +getWeight() double
    +getLength() double
    +getRegistration() String
}

class Car {
    -int passengersNumber

    -getPassengerNb() int
}

class Truck {
    -double cargoWeight

    -getCargoWeight() double
}

class Row {
    -Queue~Vehicle~ row
    -int rowNumber

    +getTotalVehicleLength() double
    +getVehicleNumber() int
    +addVehicle(Vehicle v) Position
    +removeVehicle() Vehicle
    +getTotalWeight() double
}

class Wedge {
    -double length
    -double maxWeight
    -final double spaceBetweenVehicles
    

    +addVehicle(Vehicle v) Position
    +removeVehicle() Vehicle
    +getVehicleRow(int rowNumber) Queue~Vehicle~
    -getLengthLeft(int rowNumber) double
    -getTotalWeigth() double
    -isEmpty() boolean
}

class Ticket {
    -double price
    
    +getName() String
    +getFirstName() String
    +getRegistration() String

    +getPlaceInWedge() Position
    +getPrice() double
    +equals(Object o) boolean
}

class Accounting {
    -final double baseCarPrice
    -final double baseTruckPrice
    -final double pricePerPassenger
    -final double pricePerKgCargo

    +getPrice(Vehicle v) double
}

class Boat {
    -Wedge wedge
    -Accounting accounting
    -Set~Ticket~ listing
    -boolean canLoad
    -int passengers

    +addVehicle(Vehicle v) Ticket
    +removeVehicle() Vehicle
    +getVehicleRow(int rowNumber) Queue~Vehicle~
    +getListingTicket() Set~Ticket~
    +getTicketFromVehicle(Vehicle v) Ticket
}
```

[//]: # (Quentin Accounting Ticket Postion Vehicle)
[//]: # (Moi le reste)

## Boat

This is the main class of this application.
We decided to make this class to group all boat methods and to have one interface.Then we can add easly a class BAR or EXCHANGE OFFICE.
We put listing directly in this class but we can put it in wedge too.
We choose a Set for listing because we must be organise and all tickets are unique (one ticket per each different vehicle)

## Wedge

Group all methods for contain and manage vehicles. We set length, maxWeight and spaceBetweenVehicle here because it's common with all rows and all rows info are only used by this class.
getTotalWeight(), getTotalVehicleLength() and getVehicleNumber() are only used by imself but if we want more information we can have here.

## Row

Manage vehicles in unique row. We implements comparable to sort easly rows when adding and removing vehicle from Wedge.
