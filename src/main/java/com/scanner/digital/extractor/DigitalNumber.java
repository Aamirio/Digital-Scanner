package com.scanner.digital.extractor;

public enum DigitalNumber {

    ZERO ("0",
            " _ \n" +
                  "| |\n" +
                  "|_|"),
    ONE  ("1",
            "   \n" +
                  "  |\n" +
                  "  |"),
    TWO  ("2",
            " _ \n" +
                  " _|\n" +
                  "|_ "),
    THREE("3",
            " _ \n" +
                  " _|\n" +
                  " _|"),
    FOUR ("4",
            "   \n" +
                  "|_|\n" +
                  "  |"),
    FIVE ("5",
            " _ \n" +
                  "|_ \n" +
                  " _|"),
    SIX  ("6",
            " _ \n" +
                  "|_ \n" +
                  "|_|"),
    SEVEN("7",
            " _ \n" +
                  "  |\n" +
                  "  |"),
    EIGHT("8",
            " _ \n" +
                  "|_|\n" +
                  "|_|"),
    NINE ("9",
            " _ \n" +
                  "|_|\n" +
                  " _|");

    public final String name;
    public final String value;

    DigitalNumber(String name, String value) {
        this.name = name;
        this.value = value;
    }
}