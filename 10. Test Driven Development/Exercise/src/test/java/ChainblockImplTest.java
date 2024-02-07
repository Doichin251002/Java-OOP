import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest {
    private static final TransactionStatus MISSING_TRANSACTION_STATUS = TransactionStatus.FAILED;

    private Chainblock chainblock;
    private Transaction transaction;

    @Before
    public void setUp() {
        this.chainblock = new ChainblockImpl();

        this.transaction = new TransactionImpl(1,
                TransactionStatus.SUCCESSFUL,
                "from1",
                "to1",
                200);
    }

    @Test
    public void testAddValidTransaction() {
        assertEquals(0, this.chainblock.getCount());

        addOneTransaction();

        assertEquals(1, this.chainblock.getCount());
        assertTrue(this.chainblock.contains(this.transaction));
    }

    @Test
    public void testAddExistingTransactionShouldNotChangeChainBlockState() {
        addOneTransaction();
        assertEquals(1, this.chainblock.getCount());
        assertTrue(this.chainblock.contains(this.transaction.getId()));

        addOneTransaction();
        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void testChangeTransactionStatusByValidId() {
        addOneTransaction();
        TransactionStatus statusBeforeUpdating = TransactionStatus.SUCCESSFUL;
        assertEquals(statusBeforeUpdating, this.transaction.getStatus());

        int id = this.transaction.getId();
        TransactionStatus statusAfterUpdating = TransactionStatus.ABORTED;
        this.chainblock.changeTransactionStatus(id, statusAfterUpdating);

        assertEquals(statusAfterUpdating, this.transaction.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusThrowsAtNotExistingId() {
        addOneTransaction();

        this.chainblock.changeTransactionStatus(-1, TransactionStatus.ABORTED);
    }

    @Test
    public void testRemoveValidTransactionById() {
        addOneTransaction();

        int id = this.transaction.getId();
        this.chainblock.removeTransactionById(id);

        assertEquals(0, this.chainblock.getCount());
        assertFalse(this.chainblock.contains(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionThrowsAtNotExistingId() {
        this.chainblock.removeTransactionById(-1);
    }

    @Test
    public void testGetValidTransactionById() {
        addOneTransaction();

        int id = this.transaction.getId();
        Transaction actualTransaction = this.chainblock.getById(id);

        assertEquals(this.transaction, actualTransaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTransactionThrowsAtNotExistingId() {
        addOneTransaction();

        this.chainblock.getById(-1);
    }

    @Test
    public void testGetByValidTransactionStatusShouldReturnWithSameStatus() {
        addMultipleTransactions();
        TransactionStatus expectedStatus = TransactionStatus.ABORTED;

        Iterable<Transaction> actualTransactions = this.chainblock.getByTransactionStatus(expectedStatus);

        actualTransactions.forEach(actual -> assertEquals(expectedStatus, actual.getStatus()));
    }

    @Test
    public void testGetByValidTransactionStatusShouldReturnInValidSize() {
        TransactionStatus status = TransactionStatus.ABORTED;
        long expectedSize = chainBlockToList().stream()
                .filter(t -> t.getStatus().equals(status))
                .count();

        Iterable<Transaction> actualResult = this.chainblock.getByTransactionStatus(status);

        int actualSize = getIterableSize(actualResult);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetByValidTransactionStatusShouldReturnInDescOrderByAmount() {
        addMultipleTransactions();

        TransactionStatus status = TransactionStatus.ABORTED;
        Iterator<Transaction> expectedTransactionsIterator = chainBlockToList().stream()
                .filter(t -> t.getStatus().equals(status))
                .sorted(Utils.byAmountDesc())
                .iterator();

        Iterable<Transaction> actualIterableTransactions = this.chainblock.getByTransactionStatus(status);

        assertIterators(expectedTransactionsIterator, actualIterableTransactions.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusThrowsAtMissingStatus() {
        addMultipleTransactions();

        this.chainblock.getByTransactionStatus(MISSING_TRANSACTION_STATUS);
    }

    @Test
    public void testGetAllSendersWithValidTransactionStatusShouldReturnInValidSize() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        Iterable<Transaction> expectedIterable = this.chainblock.getByTransactionStatus(status);
        int expectedSize = getIterableSize(expectedIterable);

        Iterable<String> actualIterable = this.chainblock.getAllSendersWithTransactionStatus(status);

        int actualSize = getIterableSize(actualIterable);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetAllSendersWithValidTransactionStatusShouldReturnInDescOrderByAmount() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        List<String> expectedIterableSenders = new ArrayList<>();
        this.chainblock.getByTransactionStatus(status).forEach(t -> expectedIterableSenders.add(t.getSender()));

        Iterable<String> actualIterableSenders = this.chainblock.getAllSendersWithTransactionStatus(status);

        assertIterators(expectedIterableSenders.iterator(), actualIterableSenders.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusThrowsAtMissingStatus() {
        addMultipleTransactions();

        this.chainblock.getAllSendersWithTransactionStatus(MISSING_TRANSACTION_STATUS);
    }

    @Test
    public void testGetAllReceiversWithValidTransactionStatusShouldReturnInValidSize() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        Iterable<Transaction> expectedResult = this.chainblock.getByTransactionStatus(status);
        int expectedSize = getIterableSize(expectedResult);

        Iterable<String> actualResult = this.chainblock.getAllReceiversWithTransactionStatus(status);

        int actualSize = getIterableSize(actualResult);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetAllReceiversWithValidTransactionStatusShouldReturnInDescOrderByAmount() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        List<String> expectedIterableReceivers = new ArrayList<>();
        this.chainblock.getByTransactionStatus(status).forEach(t -> expectedIterableReceivers.add(t.getReceiver()));

        Iterable<String> actualIterableReceivers = this.chainblock.getAllReceiversWithTransactionStatus(status);

        assertIterators(expectedIterableReceivers.iterator(), actualIterableReceivers.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusThrowsAtMissingStatus() {
        addMultipleTransactions();

        this.chainblock.getAllReceiversWithTransactionStatus(MISSING_TRANSACTION_STATUS);
    }

    @Test
    public void testGetAllTransactionsOrderedByAmountDescendingThenById() {
        addMultipleTransactions();

        List<Transaction> expectedTransactions = chainBlockToList()
                .stream()
                .sorted()
                .sorted(Utils.byAmountDesc())
                .collect(Collectors.toList());

        Iterable<Transaction> actualTransactions = this.chainblock.getAllOrderedByAmountDescendingThenById();

        assertIterators(expectedTransactions.iterator(), actualTransactions.iterator());
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturnWithSameSender() {
        addMultipleTransactions();
        String expectedSender = "from2";

        Iterable<Transaction> actualIterableSenders = this.chainblock.getBySenderOrderedByAmountDescending(expectedSender);

        actualIterableSenders.forEach(actual -> assertEquals(expectedSender, actual.getSender()));
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingShouldReturnInValidSize() {
        addMultipleTransactions();
        String sender = "from2";
        long expectedSize = this.chainBlockToList().stream()
                .filter(t -> t.getSender().equals(sender))
                .count();

        Iterable<Transaction> actualTransactions = this.chainblock.getBySenderOrderedByAmountDescending(sender);

        int actualSize = getIterableSize(actualTransactions);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetBySenderOrderedByAmountDescending() {
        addMultipleTransactions();
        String sender = "from2";
        Iterator<Transaction> expectedTransactionsIterator = this.chainBlockToList().stream()
                .filter(t -> t.getSender().equals(sender))
                .sorted(Utils.byAmountDesc())
                .iterator();

        Iterable<Transaction> actualIterableTransactions = this.chainblock.getBySenderOrderedByAmountDescending(sender);

        assertIterators(expectedTransactionsIterator, actualIterableTransactions.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingThrowsAtNotExistingSender() {
        addMultipleTransactions();

        this.chainblock.getBySenderOrderedByAmountDescending("notExist");
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdShouldReturnWithSamReceiver() {
        addMultipleTransactions();
        String expectedReceiver = "to7";

        Iterable<Transaction> actualIterableSenders = this.chainblock.getByReceiverOrderedByAmountThenById(expectedReceiver);

        actualIterableSenders.forEach(actual -> assertEquals(expectedReceiver, actual.getReceiver()));
    }

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdShouldReturnInValidSize() {
        addMultipleTransactions();
        String receiver = "to7";
        long expectedSize = this.chainBlockToList().stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .count();

        Iterable<Transaction> actualTransactions = this.chainblock.getByReceiverOrderedByAmountThenById(receiver);

        int actualSize = getIterableSize(actualTransactions);
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void testGetByReceiverOrderedByDescAmountThenById() {
        addMultipleTransactions();
        String receiver = "to7";
        List<Transaction> expectedTransactions = new ArrayList<>();
        this.chainblock.getAllOrderedByAmountDescendingThenById().forEach(t -> {
            if (t.getReceiver().equals(receiver)) {
                expectedTransactions.add(t);
            }
        });

        Iterable<Transaction> actualIterableTransactions = this.chainblock.getByReceiverOrderedByAmountThenById(receiver);

        assertIterators(expectedTransactions.iterator(), actualIterableTransactions.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverOrderedByAmountThenByIdThrowsAtNotExistingReceiver() {
        addMultipleTransactions();

        this.chainblock.getByReceiverOrderedByAmountThenById("notExist");
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmount() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        double maxAmount = 600;

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getByTransactionStatusAndMaximumAmount(status, maxAmount);

        actualIterableTransactions.forEach(actual -> {
            assertEquals(status, actual.getStatus());

            assertTrue(actual.getAmount() <= maxAmount);
        });
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnInValidSizeAndOrder() {
        addMultipleTransactions();
        TransactionStatus status = TransactionStatus.SUCCESSFUL;
        double maxAmount = 600;
        List<Transaction> expectedTransactions = new ArrayList<>();
        this.chainblock.getByTransactionStatus(status).forEach(t -> {
            if (t.getAmount() <= maxAmount) {
                expectedTransactions.add(t);
            }
        });

        Iterable<Transaction> actualTransactions = this.chainblock
                .getByTransactionStatusAndMaximumAmount(status, maxAmount);

        int actualSize = getIterableSize(actualTransactions);
        assertEquals(expectedTransactions.size(), actualSize);
        assertIterators(expectedTransactions.iterator(), actualTransactions.iterator());
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnEmptyAtMissingStatus() {
        addMultipleTransactions();

        Iterable<Transaction> iterableTransactionsWithMissingStatus = this.chainblock
                .getByTransactionStatusAndMaximumAmount(MISSING_TRANSACTION_STATUS, 100);

        int actualSize = getIterableSize(iterableTransactionsWithMissingStatus);
        assertEquals(0, actualSize);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnEmptyAtNotValidAmount() {
        addMultipleTransactions();

        Iterable<Transaction> iterableTransactionsWithNotValidAmount = this.chainblock
                .getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, -1);

        int actualSize = getIterableSize(iterableTransactionsWithNotValidAmount);
        assertEquals(0, actualSize);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountShouldReturnEmptyAtMismatch() {
        addMultipleTransactions();

        Iterable<Transaction> iterableMismatchingTransactions = this.chainblock
                .getByTransactionStatusAndMaximumAmount(MISSING_TRANSACTION_STATUS, -1);

        int actualSize = getIterableSize(iterableMismatchingTransactions);
        assertEquals(0, actualSize);
    }

    @Test
    public void testGetBySenderAndMinimumAmountDescending() {
        addMultipleTransactions();
        String sender = "from2";
        double minAmount = 500;

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getBySenderAndMinimumAmountDescending(sender, minAmount);

        actualIterableTransactions.forEach(actual -> {
            assertEquals(sender, actual.getSender());

            assertTrue(actual.getAmount() > minAmount);
        });
    }

    @Test
    public void testGetBySenderAndMinimumAmountDescendingShouldReturnInValidSizeAndOrder() {
        addMultipleTransactions();
        String sender = "from2";
        double minAmount = 500;
        List<Transaction> expectedTransactions = new ArrayList<>();
        this.chainblock.getBySenderOrderedByAmountDescending(sender).forEach(t -> {
            if (t.getAmount() > minAmount) expectedTransactions.add(t);
        });

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getBySenderAndMinimumAmountDescending(sender, minAmount);

        int actualSize = getIterableSize(actualIterableTransactions);
        assertEquals(expectedTransactions.size(), actualSize);
        assertIterators(expectedTransactions.iterator(), actualIterableTransactions.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingThrowsAtMissingSender() {
        addMultipleTransactions();

        this.chainblock.getBySenderAndMinimumAmountDescending("notExisting", 500);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingThrowsAtNotValidAmount() {
        addMultipleTransactions();

        this.chainblock.getBySenderAndMinimumAmountDescending("from2", 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingThrowsAtMismatch() {
        addMultipleTransactions();

        this.chainblock.getBySenderAndMinimumAmountDescending("notExisting", 1000);
    }

    @Test
    public void testGetByReceiverAndAmountRangeLowerBoundInclusiveAndUpperBoundExclusive() {
        addMultipleTransactions();
        String receiver = "to7";
        double lowerBoundInclusiveAmount = 500;
        double upperBoundExclusiveAmount = 900;

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getByReceiverAndAmountRange(receiver, lowerBoundInclusiveAmount, upperBoundExclusiveAmount);

        actualIterableTransactions.forEach(t -> {
            assertEquals(receiver, t.getReceiver());

            assertTrue(t.getAmount() >= lowerBoundInclusiveAmount);
            assertTrue(t.getAmount() < upperBoundExclusiveAmount);
        });
    }

    @Test
    public void testGetByReceiverAndAmountRangeShouldReturnInValidSizeAndOrder() {
        addMultipleTransactions();
        String receiver = "to7";
        double lowerBoundInclusiveAmount = 500;
        double upperBoundExclusiveAmount = 900;
        List<Transaction> expectedTransactions = new ArrayList<>();
        this.chainblock.getByReceiverOrderedByAmountThenById(receiver).forEach(t -> {
            if (t.getAmount() >= lowerBoundInclusiveAmount
                    && t.getAmount() < upperBoundExclusiveAmount) {
                expectedTransactions.add(t);
            }
        });

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getByReceiverAndAmountRange(receiver, lowerBoundInclusiveAmount, upperBoundExclusiveAmount);

        int actualSize = getIterableSize(actualIterableTransactions);
        assertEquals(expectedTransactions.size(), actualSize);
        assertIterators(expectedTransactions.iterator(), actualIterableTransactions.iterator());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsAtMissingReceiver() {
        addMultipleTransactions();

        this.chainblock.getByReceiverAndAmountRange("notExisting", 500, 900);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsAtEqualsBoundsOfAmount() {
        addMultipleTransactions();

        this.chainblock.getByReceiverAndAmountRange("to7", 1000, 1000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsAtNotValidBoundsOfAmount() {
        addMultipleTransactions();

        this.chainblock.getByReceiverAndAmountRange("to7", 1000, 800);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsAtMissingAmountRange() {
        addMultipleTransactions();

        this.chainblock.getByReceiverAndAmountRange("to7", 1000, 2000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeThrowsAtMismatch() {
        addMultipleTransactions();

        this.chainblock.getByReceiverAndAmountRange("notExisting", 1000, 2000);
    }

    @Test
    public void testGetAllInAmountRangeInclusive() {
        addMultipleTransactions();
        double expectedLowerBoundInclusiveAmount = 500;
        double expectedUpperBoundInclusiveAmount = 900;

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getAllInAmountRange(expectedLowerBoundInclusiveAmount, expectedUpperBoundInclusiveAmount);

        actualIterableTransactions.forEach(t -> {
            assertTrue(t.getAmount() >= expectedLowerBoundInclusiveAmount);

            assertTrue(t.getAmount() <= expectedUpperBoundInclusiveAmount);
        });
    }

    @Test
    public void testGetAllInAmountRangeInclusiveShouldReturnInValidSizeAndInsertionOrder() {
        addMultipleTransactions();
        double lowerBoundInclusiveAmount = 500;
        double upperBoundInclusiveAmount = 900;

        List<Transaction> expectedTransactions = new ArrayList<>();
        for (Transaction t : chainBlockToList()) {
            if (t.getAmount() >= lowerBoundInclusiveAmount
                    && t.getAmount() <= upperBoundInclusiveAmount) {
                expectedTransactions.add(t);
            }
        }

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getAllInAmountRange(lowerBoundInclusiveAmount, upperBoundInclusiveAmount);

        int actualSize = getIterableSize(actualIterableTransactions);
        assertEquals(expectedTransactions.size(), actualSize);
        assertIterators(expectedTransactions.iterator(), actualIterableTransactions.iterator());
    }

    @Test
    public void testGetAllInAmountRangeInclusiveShouldReturnEmptyAtMismatch() {
        addMultipleTransactions();

        Iterable<Transaction> actualIterableTransactions = this.chainblock
                .getAllInAmountRange(1000, 2000);

        int actualSize = getIterableSize(actualIterableTransactions);
        assertEquals(0, actualSize);
    }

    @Test
    public void testIteratorOfChainBlock() {
        Iterator<Transaction> expectedIterator = chainBlockToList().iterator();

        Iterator<Transaction> actualIterator = this.chainblock.iterator();

        assertIterators(expectedIterator, actualIterator);
    }

    private <T> void assertIterators(Iterator<T> expectedIterator, Iterator<T> actualIterator) {
        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            T expected = expectedIterator.next();
            T actual = actualIterator.next();

            assertEquals(expected, actual);
        }
    }

    private static <T> int getIterableSize(Iterable<T> actualResult) {
        Iterator<T> iterator = actualResult.iterator();

        int actualSize = 0;
        while (iterator.hasNext()) {
            iterator.next();
            actualSize++;
        }

        return actualSize;
    }

    private void addOneTransaction() {
        this.chainblock.add(this.transaction);
    }

    private void addMultipleTransactions() {
        List<Transaction> transactions = List.of(
                new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "from2", "to7", 100),
                new TransactionImpl(6, TransactionStatus.ABORTED, "from3", "to7", 500),
                new TransactionImpl(10, TransactionStatus.UNAUTHORIZED, "from4", "to4", 100),
                new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "from2", "to6", 600),
                new TransactionImpl(9, TransactionStatus.SUCCESSFUL, "from7", "to7", 500),
                new TransactionImpl(7, TransactionStatus.ABORTED, "from8", "to8", 200),
                new TransactionImpl(4, TransactionStatus.SUCCESSFUL, "from8", "to7", 800),
                new TransactionImpl(13, TransactionStatus.UNAUTHORIZED, "from2", "to10", 900));

        addOneTransaction();
        transactions.forEach(this.chainblock::add);
    }

    private List<Transaction> chainBlockToList() {
        addMultipleTransactions();

        List<Transaction> transactions = new ArrayList<>();
        this.chainblock.iterator().forEachRemaining(transactions::add);

        return transactions;
    }
}