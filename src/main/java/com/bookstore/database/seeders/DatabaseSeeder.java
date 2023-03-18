package com.bookstore.database.seeders;

public class DatabaseSeeder {
  public static void main(String[] args) {
    ISeeder[] seeders = new ISeeder[] {
        new UserSeeder(),
        new BookSeeder(),
        new AddressSeeder(),
        new AuthorSeeder(),
        new CartSeeder(),
        new CategorySeeder(),
        new ImportsSeeder(),
        new PaymentSeeder(),
        new PromotionSeeder(),
        new PublisherSeeder(),
        new ProvidersSeeder(),
        new ShippingSeeder(),
        new OrderSeeder(),
        new EmployeeSeeder(),

    };
    for (ISeeder seeder : seeders) {
      new Thread(() -> {
        seeder.run();
      }).start();
    }
  }
}
