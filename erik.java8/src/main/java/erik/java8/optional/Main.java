package erik.java8.optional;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<Car> optionalCar=Optional.empty();
        Optional<Car> car = Optional.of(new Car());

        optionalCar.map(Car::getInsurance);

        Optional<Object> optionalO = Optional.ofNullable(null);


    }

    public String getCarInsuranceName(Optional<Person> optionalPerson){

         return optionalPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");



    }

}
