import java.util.Random;

public class BankSimulation {
  // אותם שדות
  private BankAccount[] regularAccounts; // מערך חשבונות מסוג רגיל
  private LimitedAccount[] limitedAccounts; // מערך חשבונות מסוג מוגבל
  private OverdraftAccount[] overdraftAccounts; // מערך חשבונות מסוג אוברדראפט

  // סעיף י״ד
  public BankSimulation(int count) { // בנאי
    Random random = new Random();

    // אתחול כל מערך שיהיה מגודל קאונט (ז״א קאונט חשבונות מאותו סוג)
    // יצירת אובייקט חשבון בנק לכל אחד מאיברי המערך
    // והשמת מאזן התחלתי אקראי לכל אחד מחשבונות המערך (בין 10000 ל-50000$)

    this.regularAccounts = new BankAccount[count]; // חשבונות רגילים
    for (int i = 0; i < regularAccounts.length; i++) {
      regularAccounts[i] = new BankAccount("Regular Account User", "Number " + (i + 1)); // חשבון רגיל חדש
      int randomBalance = random.nextInt(10000, 50001); // בנפרד בכל סוג כדי שלא יהיה אותו מאזן
      this.regularAccounts[i].setBalance(randomBalance);
    }

    this.limitedAccounts = new LimitedAccount[count]; // חשבונות מוגבלים
    for (int i = 0; i < limitedAccounts.length; i++) {
      int limit = random.nextInt(100, 1001); // לימיט אקראי לחשבון מוגבל
      limitedAccounts[i] = new LimitedAccount("Limited Account User", "Number " + (i + 1), limit); // חשבון מוגבל חדש
      int randomBalance = random.nextInt(10000, 50001);
      this.limitedAccounts[i].setBalance(randomBalance);
    }

    this.overdraftAccounts = new OverdraftAccount[count]; // חשבונות אוברדראפט
    for (int i = 0; i < overdraftAccounts.length; i++) {
      // נניח שהחריגה השלילית המקסימלית היא מ500$ עד 1000$
      int maxOverdraft = random.nextInt(500, 1001);
      overdraftAccounts[i] = new OverdraftAccount("Overdraft Account User", "Number " + (i + 1), maxOverdraft);
      int randomBalance = random.nextInt(10000, 50001);
      this.overdraftAccounts[i].setBalance(randomBalance);
    }
  }

  // סעיף ט״ו
  public String toString() {
    int totalAccounts = 0;
    int totalBalance = 0;
    int maxBalance = 0; // יכול להתחיל מ0
    int minBalance = Integer.MAX_VALUE; // לא יכול להיות להתחיל מ0 כי יכולים להיות מאזנים שליליים (עדכון לא נכון)
    // MAX_VALUE for min - כדי שהערך הראשוני יהיה יותר גבוה מכל מאזן
    String maxBalanceUser = "";
    String minBalanceUser = "";

    for (int i = 0; i < regularAccounts.length; i++) {
      totalAccounts++; // 1
      totalBalance += this.regularAccounts[i].getBalance(); // 2
      if (this.regularAccounts[i].getBalance() > maxBalance) { // 3
        maxBalance = this.regularAccounts[i].getBalance();
        maxBalanceUser = this.regularAccounts[i].getOwnerFullName(); // עדכון שם המשתמש עם המאזן הגבוה ביותר
      }
      // בלי אלס כי צריך לבדוק את שני המקרים בכל החשבונות
      if (this.regularAccounts[i].getBalance() < minBalance) { // 4
        minBalance = this.regularAccounts[i].getBalance();
        minBalanceUser = this.regularAccounts[i].getOwnerFullName(); // עדכון שם המשתמש עם המאזן הנמוך ביותר
      }
    }

    for (int i = 0; i < limitedAccounts.length; i++) {
      totalAccounts++; // 1
      totalBalance += this.limitedAccounts[i].getBalance(); // 2
      if (this.limitedAccounts[i].getBalance() > maxBalance) { // 3
        maxBalance = this.limitedAccounts[i].getBalance();
        maxBalanceUser = this.limitedAccounts[i].getOwnerFullName();
      }
      if (this.limitedAccounts[i].getBalance() < minBalance) { // 4
        minBalance = this.limitedAccounts[i].getBalance();
        minBalanceUser = this.limitedAccounts[i].getOwnerFullName();
      }
    }

    for (int i = 0; i < overdraftAccounts.length; i++) {
      totalAccounts++; // 1
      totalBalance += this.overdraftAccounts[i].getBalance(); // 2
      if (this.overdraftAccounts[i].getBalance() > maxBalance) { // 3
        maxBalance = this.overdraftAccounts[i].getBalance();
        maxBalanceUser = this.overdraftAccounts[i].getOwnerFullName();
      }
      if (this.overdraftAccounts[i].getBalance() < minBalance) { // 4
        minBalance = this.overdraftAccounts[i].getBalance();
        minBalanceUser = this.overdraftAccounts[i].getOwnerFullName();
      }
    }

    return "1. Total number of accounts: " + totalAccounts + "\n2. Total balace: " + totalBalance
        + "\n3. The account with the biggest balance: " + maxBalanceUser
        + " with a balance of " + maxBalance
        + "\n4. The account with the smallest balance: " + minBalanceUser
        + " with a balance of " + minBalance;
  }

  // המשך סעיף י״ד
  public void simulate() {
    Random random = new Random();
    int transactionType = random.nextInt(1, 3); // 1-2 אין 3 כי אין שימוש בעמלה אז רק
    int randomAmount = random.nextInt(100, 1001); // סכום אקראי לפעולות 100-1000$
    if (transactionType == 1) { // משיכות
      for (int i = 0; i < regularAccounts.length; i++) { // חשבונות רגילים
        // פןלימורפיזם:
        this.regularAccounts[i].withdraw(randomAmount); // המתודה ממומשת כפי שהיא ממומשת בבנק אקאונט
      }
      for (int i = 0; i < limitedAccounts.length; i++) { // חשבונות מוגבלים
        if (randomAmount <= limitedAccounts[i].getLimit()) {
          // פןלימורפיזם:
          limitedAccounts[i].withdraw(randomAmount); // המתודה ממומשת כפי שהיא ממומשת בלימיטד אקאונט
        } else {
          break;
        }
      }
      for (int i = 0; i < overdraftAccounts.length; i++) { // חשבונות אוברדראפט
        if (overdraftAccounts[i].getBalance() - randomAmount >= overdraftAccounts[i].getMaxOverdraft()) {
          // פןלימורפיזם:
          overdraftAccounts[i].withdraw(randomAmount); // המתודה ממומשת כפי שהיא ממומשת באוברדראפט אקאונט
        } else {
          break;
        }
      }
    } // סוף המשיכות

    if (transactionType == 2) { // הפקדות
      for (int i = 0; i < regularAccounts.length; i++) { // חשבונות רגילים
        this.regularAccounts[i].deposit(randomAmount);
      }
      for (int i = 0; i < limitedAccounts.length; i++) { // חשבונות רגילים
        this.limitedAccounts[i].deposit(randomAmount);
      }
      for (int i = 0; i < overdraftAccounts.length; i++) { // חשבונות רגילים
        this.overdraftAccounts[i].deposit(randomAmount);
      }
    } // סוף ההפקדות
  }
} // class
