// חשבון אוברדראפט
// הפקדה ללא הגבלה
// משיכה ללא הגבלה וניתן להגיע למאזן שלילי
// מוגדרת מסגרת שאסור לחרוג ממנה- מספר מסוים מקסימלי למאזן השלילי שאליו ניתן להגיע
public class OverdraftAccount extends BankAccount {

  private int maxOverdraft; // מסגרת - חריגה מקסימלית של מאזן שלילי

  public OverdraftAccount(String ownerFirstName, String ownerLastName, int maxOverdraft) {
    super(ownerFirstName, ownerLastName);
    if (maxOverdraft <= 0) { // אם הוכנס ערך שלילי נשאיר
      this.maxOverdraft = maxOverdraft;
    } else { // אם הוכנס ערך חיובי אז נהפוך אותו לשלילי
      this.maxOverdraft = (-1) * maxOverdraft;
    }
  }

  public boolean withdraw(int sum) { // צריך לשנות רק את המתודה הזאת
    if (getBalance() - sum <= maxOverdraft) {
      return false; // לא ניתן למשוך
    } else {
      int before = getBalance();
      this.setBalance(before - sum);
      int after = getBalance();
      //
      Transaction transaction = new Transaction(before, after, 1);
      Transaction[] newArray = new Transaction[getHistory().length + 1];
      for (int i = 0; i < getHistory().length; i++) {
        newArray[i] = getHistory()[i];
      }
      newArray[newArray.length - 1] = transaction;
      setHistory(newArray);
      // or we could just use the addToHistory method: (w/o getters & setters)
      // addToHistory(this.balance, this.balance - sum, 1);
      return true; // המשיכה בוצעה
    }
  }

  public int getMaxOverdraft() {
    return this.maxOverdraft;
  }
} // class
