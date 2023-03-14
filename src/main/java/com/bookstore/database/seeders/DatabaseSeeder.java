package com.bookstore.database.seeders;

public class DatabaseSeeder {
  public static void main(String[] args) {
    ISeeder[] seeders = new ISeeder[] {
        new UserSeeder(),
        new BookSeeder(),
        new AddressSeeder(),
    };
    for (ISeeder seeder : seeders) {
      new Thread(() -> {
        seeder.run();
      }).start();
    }
  }
}
