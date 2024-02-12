public interface Transaction {
    int getId();

    TransactionStatus getStatus();

    void setStatus(TransactionStatus status);

    String getSender();

    String getReceiver();

    double getAmount();
}
