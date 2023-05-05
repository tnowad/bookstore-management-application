package com.bookstore.util;

public class InputValidator {

  public static String validateName(String name)
    throws IllegalArgumentException {
    if (name == null || name.trim().isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }
    String regex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    if (!name.matches(regex)) {
      throw new IllegalArgumentException(
        "Name can only contain letters and the characters ',. -'."
      );
    }
    return name;
  }

  public static String validateUsername(String username)
    throws IllegalArgumentException {
    if (username == null || username.trim().isEmpty()) {
      throw new IllegalArgumentException("Username cannot be empty.");
    }
    String regex = "^[a-zA-Z0-9._-]{3,}$";
    if (!username.matches(regex)) {
      throw new IllegalArgumentException(
        "Username can only contain letters, numbers, underscores, dashes, and periods."
      );
    }
    return username;
  }

  public static String validateEmail(String email)
    throws IllegalArgumentException {
    if (email == null || email.trim().isEmpty()) {
      throw new IllegalArgumentException("Email cannot be empty.");
    }
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    if (!email.matches(regex)) {
      throw new IllegalArgumentException("Email is not valid.");
    }
    return email;
  }

  public static String validatePhone(String phone)
    throws IllegalArgumentException {
    if (phone == null || phone.trim().isEmpty()) {
      throw new IllegalArgumentException("Phone number cannot be empty.");
    }
    String regex = "(84|0[3|5|7|8|9])+([0-9]{8})";
    if (!phone.matches(regex)) {
      throw new IllegalArgumentException("Phone number is not valid.");
    }
    return phone;
  }

  public static String validateCity(String city)
    throws IllegalArgumentException {
    if (city == null || city.trim().isEmpty()) {
      throw new IllegalArgumentException("City cannot be empty.");
    }
    String regex = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
    if (!city.matches(regex)) {
      throw new IllegalArgumentException(
        "City can only contain letters and the characters ' ' and '-'."
      );
    }
    return city;
  }

  public static String validateState(String state)
    throws IllegalArgumentException {
    if (state == null || state.trim().isEmpty()) {
      throw new IllegalArgumentException("State cannot be empty.");
    }
    String regex = "^[a-zA-Z]{2}$";
    if (!state.matches(regex)) {
      throw new IllegalArgumentException(
        "State must be a valid two-letter abbreviation."
      );
    }
    return state;
  }

  public static String validateStreet(String street)
    throws IllegalArgumentException {
    if (street == null || street.trim().isEmpty()) {
      throw new IllegalArgumentException("Street address cannot be empty.");
    }
    String regex = "^[a-zA-Z0-9\\s,'-]*$";
    if (!street.matches(regex)) {
      throw new IllegalArgumentException(
        "Street address can only contain letters, numbers, spaces, commas, apostrophes, and hyphens."
      );
    }
    return street;
  }

  public static String validateZip(String zip) throws IllegalArgumentException {
    if (zip == null || zip.trim().isEmpty()) {
      throw new IllegalArgumentException("Zip code cannot be empty.");
    }
    String regex = "^\\d{5}(?:[-\\s]\\d{4})?$";
    if (!zip.matches(regex)) {
      throw new IllegalArgumentException("Zip code is not valid.");
    }
    return zip;
  }

  public static String validateISBN(String isbn) {
    if (isbn == null || isbn.trim().isEmpty()) {
      throw new IllegalArgumentException("ISBN cannot be empty.");
    }
    String regex = "^\\d{11,13}$";
    if (!isbn.matches(regex)) {
      throw new IllegalArgumentException("ISBN is not valid.");
    }
    return isbn;
  }

  public static String validatePassword(String password) {
    if (password == null || password.trim().isEmpty()) {
      throw new IllegalArgumentException("Password cannot be empty.");
    }
    String regex = "^[a-zA-Z0-9_]{8,20}$";
    if (!password.matches(regex)) {
      throw new IllegalArgumentException("Password is not valid.");
    }
    return password;
  }
}
