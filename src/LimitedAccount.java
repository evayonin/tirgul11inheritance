// חשבון בנק מוגבל
// ניתן להפקיד ללא הגבלה
// לא ניתן למשוך בפעולה אחת יותר מהלימיט שהוגדר לחשבון (לימיט שונה בכל חשבון)
// אסור להגיע למאזן שלילי
public class LimitedAccount extends BankAccount {
  // יורשת את השדות והמתודות של BankAccount
  private int limit;

  public LimitedAccount(String ownerFirstName, String ownerLastName, int limit) { // בנאי
    super(ownerFirstName, ownerLastName);
    this.limit = limit;
  }

  public boolean withdraw(int sum) { // צריך לשנות רק את המתודה הזאת
    if (sum <= limit) {
      return super.withdraw(sum); // שליחה למתודה בתוך BankAccount
      // כי אז זה חישוב רגיל
    } else {
      return false;
    }
  }

  public int getLimit() {
    return this.limit;
  }
} // class
