package calculator;

public class ExtendedInputInterpreter extends InputInterpreter {
    private Memory memory;
    public ExtendedInputInterpreter(CalculationEngine engine) {
        super(engine);
        this.memory = new Memory();
    }

    @Override
    public Operation getOperation(String operationName) {
        Operation operation = super.getOperation(operationName);

        if (operation != null) {
            return operation;
        }

        if (operationName.equals("/")) {
            return new DivisionOperation();
        }

        if (operationName.equals("ms")) {
            return new MemorySaveOperation(this.memory);
        }

        if (operationName.equals("mr")) {
            return new MemoryRecallOperation(this.memory);
        }

        return null;
    }
}
