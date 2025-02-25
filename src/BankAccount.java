// חשבון בנק רגיל
// הפקדה ומשיכה ללא הגבלה
// אסור להגיע למאזן שלילי
public class BankAccount {

  private String ownerFirstName;
  private String ownerLastName;
  private int balance;
  private Transaction[] history; // מערך הטרנזקציות

  public BankAccount(String ownerFirstName, String ownerLastName) {
    this.ownerFirstName = ownerFirstName;
    this.ownerLastName = ownerLastName;
    this.balance = 0; // מאתחלים את המאזן ל-0
    this.history = new Transaction[0]; // אתחול מערך טרנזקציות מגודל 0
  }

  public String getOwnerFullName() {
    String fullName = this.ownerFirstName + " " + this.ownerLastName;
    return fullName;
  }

  public String toString() {
    String history = "";
    for (int i = 0; i < this.history.length; i++) {
      history += (i + 1) + ". " + this.history[i];
    }
    return "Full Name: " + getOwnerFullName() + "\nCurrent Balance: " + this.balance + "\nTransaction History: \n"
        + history;
  }

  public void addToHistory(int before, int after, int type) { // setter
    Transaction[] newArray = new Transaction[this.history.length + 1]; // מערך חדש של היסטוריית טרנזקציות עם מקום נוסף
    for (int i = 0; i < this.history.length; i++) { // העתקה מהמערך הישן
      newArray[i] = this.history[i];
    }
    newArray[newArray.length - 1] = new Transaction(before, after, type);
    // עדכון האיבר האחרון במערך ההיסטורייה החדש כאובייקט טרנזקציה חדש שמקבל את
    // הפרמטרים
    this.history = newArray;
  }

  // סעיף ט
  public void deposit(int sum) { // הפקדות
    Transaction transaction = new Transaction(this.balance, this.balance + sum, 2); // יצירת אובייט חדש טרנזקציה הפקדה
    Transaction[] newArray = new Transaction[this.history.length + 1]; // מערך חדש של היסטוריית טרנזקציות עם מקום נוסף
    for (int i = 0; i < this.history.length; i++) { // העתקה מהמערך הישן
      newArray[i] = this.history[i];
    }
    newArray[newArray.length - 1] = transaction; // עדכון האיבר האחרון במערך ההיסטורייה החדש- ההפקדה האחרונה
    this.history = newArray; // עדכון המערך המקורי

    // or we could just use the addToHistory method:
    // addToHistory(this.balance, this.balance + sum, 2);
    this.balance += sum; // עדכון המאזן בחשבון
  }

  // משיכות - סעיף י״א
  public boolean withdraw(int sum) { // aproved- true, not aproved- false
    if ((this.balance - sum) <= 0) {
      return false; // לא ניתן למשוך
    } else {
      Transaction transaction = new Transaction(this.balance, this.balance - sum, 1);
      Transaction[] newArray = new Transaction[this.history.length + 1];
      for (int i = 0; i < this.history.length; i++) { // העתקה מהמערך הישן
        newArray[i] = this.history[i];
      }
      newArray[newArray.length - 1] = transaction;
      this.history = newArray;
      // or we could just use the addToHistory method:
      // addToHistory(this.balance, this.balance - sum, 1);
      this.balance -= sum;
      return true; // המשיכה בוצעה
    }
  }

  public void printTransaction() { // מדפיסים את ההיסטוריה
    for (int i = 0; i < this.history.length; i++) {
      System.out.println(this.history[i]);
    }
  }

  public int getBalance() { // getter for the BankSimulation class
    return this.balance;
  }

  public void setBalance(int balance) { // setter for the BankSimulation class
    this.balance = balance;
  }

  public Transaction[] getHistory() { // getter for the BankSimulation class
    return this.history;
  }

  public void setHistory(Transaction[] history) { // setter for the BankSimulation class
    this.history = history;
  }
} // class
