package com.bookstore.database.seeders;

public class DatabaseSeeder {
  public static void main(String[] args) {
    ISeeder[] seeders = new ISeeder[] {
        new PromotionSeeder(),
        // new ImportsSeeder(),
        // new UserSeeder(),
        // new BookSeeder(),
        // new AddressSeeder(),
        // new AuthorSeeder(),
        // new CartSeeder(),
        // new CategorySeeder(),
        // new PaymentSeeder(),
        // new PublisherSeeder(),
        // new ProvidersSeeder(),
        // new ShippingSeeder(),
        new OrderSeeder(),
        // new EmployeeSeeder(),

    };
    for (ISeeder seeder : seeders) {
      new Thread(() -> {
        seeder.run();
      }).start();
    }
  }
}
