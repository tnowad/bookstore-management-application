package com.bookstore.database.seeders;

public class DatabaseSeeder {

  private DatabaseSeeder() {
    throw new IllegalStateException("Utility class");
  }

  public static void seeder() {
    ISeeder[] seeders = new ISeeder[] {
        new PromotionSeeder(),
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
        new OrderSeeder(),
        new EmployeeSeeder(),
        new ImportDetailSeeder(),
        new CartItemSeeder(),
        new CategoriesSeeder(),
        new ImportDetailSeeder()
    };

    for (ISeeder seeder : seeders) {
      new Thread(seeder::run).start();
    }
  }

  public static void main(String[] args) {
    DatabaseSeeder.seeder();
  }
}
