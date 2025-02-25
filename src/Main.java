public class Main {
  public static void main(String[] args) {
    // הערה: אני עושה הדפסה נוספת לפני ואחרי המשיכות למקרה שהמשיכה לא עבדה

    Transaction transaction = new Transaction(1000, 2000, 2); // הפקדה
    System.out.println(transaction);

    BankAccount account1 = new BankAccount("Eva", "Yonin"); // חשבון רגיל
    System.out.println(account1);

    account1.deposit(1400);
    account1.deposit(2000);
    account1.deposit(2500);
    System.out.println(account1);

    if (account1.withdraw(3000)) { // if true
      System.out.println(account1);
    } else {
      System.out.println("Withdrawal Failed.");
    }

    LimitedAccount account2 = new LimitedAccount("Dani", "Don", 1000);
    account2.deposit(500);
    account2.deposit(500);
    account2.deposit(500);
    System.out.println(account2);

    if (account2.withdraw(800)) { // if true
      System.out.println(account2);
    } else {
      System.out.println("Withdrawal Failed.");
    }

    OverdraftAccount account3 = new OverdraftAccount("Nisim", "Shimoni", 500); // מקסימום חריגה שלילית 500
    account3.deposit(500);
    account3.deposit(500);
    account3.deposit(500);
    System.out.println(account3);

    if (account3.withdraw(1999)) { // if true
      System.out.println(account3);
    } else {
      System.out.println("Withdrawal Failed.");
    }

    System.out.println("BANK SIMULATION: \n");

    BankSimulation bank = new BankSimulation(3); // שלושה חשבונות מכל סוג
    System.out.println("BEFORE TRANSACTIONS: \n");
    System.out.println(bank);
    System.out.println();

    System.out.println("AFTER TRANSACTIONS: \n");
    bank.simulate();
    System.out.println(bank);
  }
} // class