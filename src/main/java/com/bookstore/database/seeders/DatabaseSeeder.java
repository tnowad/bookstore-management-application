package com.bookstore.database.seeders;

public class DatabaseSeeder {
  public static void main(String[] args) {
    ISeeder[] seeders = new ISeeder[] {
        new PromotionSeeder(),
<<<<<<< HEAD
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
=======
        new ImportsSeeder(),
        new UserSeeder(),
        new BookSeeder(),
        new AddressSeeder(),
        new AuthorSeeder(),
        new CartSeeder(),
        new CategorySeeder(),
        new PaymentSeeder(),
        new PaymentMethodSeeder(),
        new PublisherSeeder(),
        new ProvidersSeeder(),
        new ShippingSeeder(),
        new OrderSeeder(),
        new EmployeeSeeder(),
        new ImportDetailSeeder(),
        new CartItemSeeder(),
        new CategoriesSeeder(),
>>>>>>> 563e04fb5c081508ef69d168f535bf84dcec4cc4

    };
    for (ISeeder seeder : seeders) {
      new Thread(() -> {
        seeder.run();
      }).start();
    }
  }
}
