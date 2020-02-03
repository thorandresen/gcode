package app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Generator generator = new Generator();

        float startX = 10f;
        float startY = 10f;
        float length = 100f;

        /*generator.move(startX, startY);
        generator.moveAndExtrudeActuator(startX, startY, length, 4f, 0.2f);
        generator.moveAndExtrudeConstraint(60.4f, startY, 4f, length, 0.2f);*/

       
        generator.move(startX, startY);
        generator.moveAndExtrudeActuator(startX, startY, length, 1.8f, 0.2f);
        generator.move(startX, startY);
        generator.moveAndExtrudeActuator(startX, startY, length, 1.8f, 0.4f);
        generator.move(startX, startY);
        generator.moveAndExtrudeActuator(startX, startY, length, 1.8f, 0.8f);
        generator.move(startX, startY);
        generator.moveAndExtrudeActuator(startX, startY, length, 1.8f, 1.0f);
        generator.move(startX, startY);
        generator.moveAndExtrudeConstraint(startX, startY, 2f, length, 1.2f);
        generator.move(startX, startY);
        generator.moveAndExtrudeConstraint(startX, startY, 2f, length, 1.4f);
        generator.move(startX, startY);
        generator.moveAndExtrudeConstraint(startX, startY, 2f, length, 1.6f);
        generator.move(startX, startY);
        generator.moveAndExtrudeConstraint(startX, startY, 2f, length, 1.8f);
       
    }

}

class Generator {
    List<String> lines = new ArrayList<>();
    Path file = Paths.get("test.txt");

    public void moveAndExtrudeConstraint(float xStart, float yStart, float length, float width, float z)
            throws IOException {
        float currentY = yStart;
        float currentX = xStart + width;
        float i;

        for (i = 0.6f; i < length; i += 0.6f) {
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E2.5");
            currentY = yStart + i;
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.01");
            lines.add("G1" + " X" + xStart + " Y" + currentY + " Z" + z + " F600 E2.5");
            i += 0.6f;
            currentY = yStart + i;
            lines.add("G1" + " X" + xStart + " Y" + currentY + " Z" + z + " F600 E0.01");
        }
        //lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F1000 E1");
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void moveAndExtrudeActuator(float xStart, float yStart, float length, float width, float z)
            throws IOException {
        float currentY = yStart + width;
        float currentX = xStart;
        float i;

        for (i = 0.6f; i < length-0.6; i += 0.6f) {
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.03");
            currentX = xStart + i;
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.01");
            lines.add("G1" + " X" + currentX + " Y" + yStart + " Z" + z + " F600 E0.03");
            i += 0.6f;
            currentX = xStart + i;
            lines.add("G1" + " X" + currentX + " Y" + yStart + " Z" + z + " F600 E0.01");
        }

        lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.01");
        currentX = xStart + i;
        lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.03");
        lines.add("G1" + " X" + currentX + " Y" + yStart + " Z" + z + " F600 E0.01");
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void move(float x, float y) throws IOException {
        lines.add("G0" + " X" + x + " Y" + y + " F1000");

        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}