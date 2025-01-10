package mastermind.core;

import java.util.*;
import mastermind.Mastermind;

public class Code {
    public enum Color {
        GREEN, RED, BLUE, YELLOW, ORANGE, PURPLE;

        public static Color fromIndex(final int index) {
            return Color.values()[index];
        }

        public static String toString(final int index) {
            return fromIndex(index).name();
        }

        public String toSaveString() {
            return name();
        }

    }

    private final ArrayList<Color> code;

    public Code(final List<Integer> code) {
        ArrayList<Color> codeBuilder = new ArrayList<>(code.size());
        for (final int color : code) {
            codeBuilder.add(Color.fromIndex(color));
        }
        this.code = codeBuilder;
    }


    public Color getColor(final int index) {
        return code.get(index);
    }

    public static List<Color> getColorFromCode(List<Integer> codeList) {
        List<Color> colorList = new ArrayList<>(codeList.size());
        for (int color : codeList) {
            colorList.add(Color.fromIndex(color));
        }
        return colorList;
    }

    public ArrayList<Color> getColors() {
        return new ArrayList<>(code);
    }

   
    public HashMap<Color, Integer> getOccurrences() {
        HashMap<Color, Integer> occurrences = new HashMap<>();
        for (Color color : Color.values()) {
            occurrences.put(color, 0);
        }
        for (Color color : code) {
            occurrences.put(color, occurrences.get(color) + 1);
        }
        return occurrences;
    }

   
    public static boolean isValidColorIndex(int index) {
        return index >= 0 && index < Color.values().length;
    }

  
    public boolean equals(Code other) {
        return this.code.equals(other.getColors());
    }

    public static Code generateRandomCode() {
        List<Integer> codeList = new ArrayList<>();
        List<Integer> uniqueColors = new ArrayList<>();

        Random random = new Random();
        String level = State.getInstance().getGuessDifficultyLevel();
        int numColors;
    
        switch (level) {
            case "easy": numColors = random.nextInt(2) + 2; break;
            case "medium": numColors = random.nextInt(2) + 3; break;
            case "hard": numColors = random.nextInt(2) + 4; break;
            default: numColors = 4; 
        }
    
        while (uniqueColors.size() < numColors) {
            int color = random.nextInt(Color.values().length);
            if (!uniqueColors.contains(color)) {
                uniqueColors.add(color);
            }
        }
    
        for (int i = 0; i < Mastermind.CODE_LENGTH; i++) {
            codeList.add(uniqueColors.get(random.nextInt(numColors)));
        }
    
        return new Code(codeList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.size(); i++) {
            sb.append(code.get(i).toSaveString()); 
            if (i < code.size() - 1) { 
                sb.append(" "); 
            }
        }
        return sb.toString();
    }

}
