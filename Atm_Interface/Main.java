public class Main {
    public static void main(String[] args) {
            BankAccount userAccount = new BankAccount(1500);
            new AtmMachine(userAccount);
    }
}
