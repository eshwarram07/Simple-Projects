

import java.util.*;

// Transaction Class
class Transaction {
    private static int counter = 1;
    private int transactionId;
    private String type;
    private double amount;
    private double balanceAfter;
    private Date date;

    public Transaction(String type, double amount, double balanceAfter) {
        this.transactionId = counter++;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
               " | Type: " + type +
               " | Amount: " + amount +
               " | Balance: " + balanceAfter +
               " | Date: " + date;
    }
}

// Account Class
class Account {
    private static int accCounter = 1000;
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountHolderName, double initialBalance) {
        this.accountNumber = accCounter++;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction("Account Created", initialBalance, balance));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount, balance));
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdraw", amount, balance));
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void printTransactionHistory() {
        System.out.println("\nTransaction History for " + accountHolderName + " (Acc No: " + accountNumber + ")");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

// Bank Class (manages multiple accounts)
class Bank {
    private List<Account> accounts = new ArrayList<>();

    public Account createAccount(String name, double initialBalance) {
        Account acc = new Account(name, initialBalance);
        accounts.add(acc);
        System.out.println("Account Created! Account Number: " + acc.getAccountNumber());
        return acc;
    }

    public Account getAccount(int accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNo) return acc;
        }
        return null;
    }

    public void transfer(int fromAccNo, int toAccNo, double amount) {
        Account fromAcc = getAccount(fromAccNo);
        Account toAcc = getAccount(toAccNo);

        if (fromAcc != null && toAcc != null) {
            if (fromAcc.getBalance() >= amount) {
                fromAcc.withdraw(amount);
                toAcc.deposit(amount);
                System.out.println("Transfer Successful from " + fromAccNo + " to " + toAccNo);
            } else {
                System.out.println("Transfer failed! Insufficient funds.");
            }
        } else {
            System.out.println("Invalid account number(s).");
        }
    }
}

// Main Class
public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        Account currentAcc = null;

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Select Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Transaction History");
            System.out.println("7. Transfer");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    sc.nextLine(); // consume newline
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();
                    currentAcc = bank.createAccount(name, bal);
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    int accNo = sc.nextInt();
                    currentAcc = bank.getAccount(accNo);
                    if (currentAcc == null) {
                        System.out.println("Account not found!");
                    } else {
                        System.out.println("Account selected: " + currentAcc.getHolderName());
                    }
                    break;

                case 3:
                    if (currentAcc != null) {
                        System.out.print("Enter deposit amount: ");
                        double dep = sc.nextDouble();
                        currentAcc.deposit(dep);
                    } else {
                        System.out.println("Please select an account first!");
                    }
                    break;

                case 4:
                    if (currentAcc != null) {
                        System.out.print("Enter withdraw amount: ");
                        double wd = sc.nextDouble();
                        currentAcc.withdraw(wd);
                    } else {
                        System.out.println("Please select an account first!");
                    }
                    break;

                case 5:
                    if (currentAcc != null) {
                        System.out.println("Balance: " + currentAcc.getBalance());
                    } else {
                        System.out.println("Please select an account first!");
                    }
                    break;

                case 6:
                    if (currentAcc != null) {
                        currentAcc.printTransactionHistory();
                    } else {
                        System.out.println("Please select an account first!");
                    }
                    break;

                case 7:
                    System.out.print("Enter sender account number: ");
                    int from = sc.nextInt();
                    System.out.print("Enter receiver account number: ");
                    int to = sc.nextInt();
                    System.out.print("Enter transfer amount: ");
                    double amt = sc.nextDouble();
                    bank.transfer(from, to, amt);
                    break;

                case 8:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
