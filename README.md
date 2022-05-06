# Analise du projet Carferry

[//]: # (NOMS)
[//]: # (Rapport final en francais)
[//]: # (Rapport en francais)

## Diagram

```{.mermaid width=1600}
classDiagram

Boat "1" -- "1" Wedge
Boat "1" -- "*" Ticket
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
    -Row row[]
    

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
We use a queue of vehicle because CAR and TRUCK extends VEHICLE and vehicle are put in a queue.

## Accounting

The purpose of this class is to simplify the creation of the ticket price by returning to the main class (boat) the value of the trip for the type of vehicle requested. The prices are stored in the class as a variable.

## Ticket

This class allows to retrieve and store all the information related to the ticket.
We decided to put the price directly in the ticket if we change price we knows the old price of car.
We decided to not include driver firstname, name etc. too avoid data duplication so we directly use the car class with all informations. It's easier toretrieve informations.
This class implements comparable to work with the set in BOAT bacause all tickets are unique and need to be sorted.

## Position

This class stores the position of the car linked to a ticket. The column is stored in the form of INT to allow the application to be adapted if a cargo has more than two parking lanes. The human interface then can show 'G' or 'D'  for this ferry or more if we want implement in another boat.

## Vehicle

The Vehicle class is inherited by two classes: Car and Truck. The class stores the information of the vehicle: its driver, size and weight that is assigned to it. Its daughter classes allow to store additionally information for a car its number of passengers and for a truck its weight.
Its an abstract class because there is no real vehicle. Only exist CAR and TRUCK.
