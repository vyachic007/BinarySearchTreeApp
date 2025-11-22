package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public List<Integer> readIntegersFromFile(String filename) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split("\\s+");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        try {
                            numbers.add(Integer.parseInt(token));
                        } catch (NumberFormatException e) {
                            System.err.println("Предупреждение: некорректное число '" + token + "' пропущено");
                        }
                    }
                }
            }
        }

        return numbers;
    }
}
