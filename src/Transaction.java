// פעולה שמתבצאת בבנק (הפקדה, משיכה)
public class Transaction {

  private int before; // מאזן החשבון טרם פעולה
  private int after; // מאזן החשבון לאחר פעולה
  private int type; // 1- withdraw , 2- deposit , 3-עמלה

  public Transaction(int before, int after, int type) { // בנאי
    this.before = before;
    this.after = after;
    this.type = type;
  }

  public String toString() {
    String type = "";
    if (this.type == 1) {
      type = "withdraw";
    } else if (this.type == 2) {
      type = "deposit";
    } else { // 3
      type = "fee";
    }
    return "Transaction type: " + type + "\nBalance before the transaction: " + this.before
        + "\nBalance after the transaction: " + this.after + "\n";
  }
} // class
