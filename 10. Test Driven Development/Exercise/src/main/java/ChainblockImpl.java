import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {
    private Map<Integer, Transaction> idTransactions;

    public ChainblockImpl() {
        this.idTransactions = new LinkedHashMap<>();
    }

    public int getCount() {
        return this.idTransactions.size();
    }

    public void add(Transaction transaction) {
        if (!contains(transaction)) {
            this.idTransactions.put(transaction.getId(), transaction);
        }
    }

    public boolean contains(Transaction transaction) {
        return this.idTransactions.containsValue(transaction);
    }

    public boolean contains(int id) {
        return this.idTransactions.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if (!contains(id)) {
            throw new IllegalArgumentException(String.format("Transaction with id [%d] is not exist in chain block.", id));
        }

        Transaction transaction = this.idTransactions.get(id);
        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException(String.format("Transaction with id [%d] is not exist in chain block.", id));
        }

        this.idTransactions.remove(id);
    }

    public Transaction getById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException(String.format("Transaction with id [%d] is not exist in chain block.", id));
        }

        return this.idTransactions.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = idTransactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(String.format("Transactions with status [%s] are not exist in chain block.", status));
        }

        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<String> senders = new ArrayList<>();
        getByTransactionStatus(status).forEach(t -> senders.add(t.getSender()));

        if (senders.isEmpty()) {
            throw new IllegalArgumentException(String.format("Senders with transaction status [%s] are not exist in chain block.", status));
        }

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<String> receivers = new ArrayList<>();
        getByTransactionStatus(status).forEach(t -> receivers.add(t.getReceiver()));

        if (receivers.isEmpty()) {
            throw new IllegalArgumentException(String.format("Receivers with transaction status [%s] are not exist in chain block.", status));
        }

        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return idTransactions.values()
                .stream()
                .sorted()
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> transactions = idTransactions.values().stream()
                .filter(t -> t.getSender().equals(sender))
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(String.format("Sender with name [%s] is not exist in chain block.", sender));
        }

        return transactions;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> transactions = new ArrayList<>();

        getAllOrderedByAmountDescendingThenById().forEach(t -> {
            if (t.getReceiver().equals(receiver)) {
                transactions.add(t);
            }
        });

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(String.format("Receiver with name [%s] is not exist in chain block.", receiver));
        }

        return transactions;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return this.idTransactions.values().stream()
                .filter(t -> t.getStatus().equals(status))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> transactions = this.idTransactions.values().stream()
                .filter(t -> t.getSender().equals(sender))
                .filter(t -> t.getAmount() > amount)
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(String.format("Transactions with sender [%s] and amount bigger than [%.2f] are not exist in chain block.", sender, amount));
        }

        return transactions;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        if (lo == hi) {
            throw new IllegalArgumentException(String.format("Lower bound of amount [%.2f] cannot be equals to upper bound [%.2f].", lo, hi));
        } else if (lo > hi) {
            throw new IllegalArgumentException(String.format("Lower bound of amount [%.2f] cannot be bigger than upper bound [%.2f].", lo, hi));
        }

        List<Transaction> transactions = new ArrayList<>();
        getByReceiverOrderedByAmountThenById(receiver).forEach(t -> {
            if (t.getAmount() >= lo && t.getAmount() < hi) {
                transactions.add(t);
            }
        });

        if (transactions.isEmpty()) {
            throw new IllegalArgumentException(String.format("Transactions with receiver [%s] and amount in range [%.2f - %.2f] are not exist in chain block.", receiver, lo, hi));
        }

        return transactions;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return idTransactions.values().stream()
                .filter(t -> t.getAmount() >= lo)
                .filter(t -> t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return this.idTransactions.values().iterator();
    }
}
