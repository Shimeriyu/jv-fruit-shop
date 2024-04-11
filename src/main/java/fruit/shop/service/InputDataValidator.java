package fruit.shop.service;

import fruit.shop.model.Fruit;
import java.util.Arrays;
import java.util.Objects;

public class InputDataValidator {
    private static final int FRUIT_INDEX = 1;
    private static final int QUANTITY_INDEX = 2;
    private static final int EXPECTED_INPUT_COLUMNS = 3;
    private static final int MINIMUM_QUANTITY_VALUE = 0;

    public String[] test(String[] inputData) {
        if (inputData.length != EXPECTED_INPUT_COLUMNS) {
            throwException("Row contains wrong number of parameters");
        }
        if (Arrays.stream(inputData).anyMatch(Objects::isNull)) {
            throwException("Row contains null value");
        }
        try {
            Fruit.valueOf(inputData[FRUIT_INDEX].toUpperCase());
        } catch (IllegalArgumentException e) {
            throwException("Fruit: " + inputData[FRUIT_INDEX] + "is not defined in shop");
        }
        try {
            Integer.parseInt(inputData[QUANTITY_INDEX]);
        } catch (NumberFormatException e) {
            throwException("Quantity column contains non-numerical data");
        }
        if (Integer.parseInt(inputData[QUANTITY_INDEX]) < MINIMUM_QUANTITY_VALUE) {
            throwException("Quantity cannot be negative");
        }
        return inputData;
    }

    private void throwException(String message) {
        throw new TransactionInputValidationException("Input csv is invalid. " + message);
    }
}