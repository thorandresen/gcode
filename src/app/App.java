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

        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.2f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.4f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.6f);
        generator.move(4f, 3f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.2f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.4f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeConstraint(4f, 3f, 7f, 20f, 0.6f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 0.8f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 1.0f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 1.2f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 0.8f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 1.0f);
        generator.move(4f, 3f);
        generator.moveAndExtrudeActuator(4f, 3f, 20f, 6.8f, 1.2f);

        /*
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 0.2f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 0.2f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 0.4f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 0.4f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 0.6f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 0.6f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 0.8f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 0.8f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 1.0f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 1.0f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 1.2f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 1.2f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 1.4f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 1.4f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 1.6f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 1.6f);
         * 
         * generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 10f,
         * 1.8f); generator.moveAndExtrudeActuator(4f, 10f, 10f, 10f, 1.8f);
         * 
         * /*generator.move(4f,3f); generator.moveAndExtrudeConstraint(4f, 3f, 7f, 5f,
         * 0.6f); generator.move(4f,3f); generator.moveAndExtrudeActuator(4f, 3f, 5f,
         * 5f, 0.8f); generator.move(9f,8f); generator.moveAndExtrudeConstraint(9f, 8f,
         * 2.3f, -5f, 0.8f);
         */

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

        for (i = 0.4f; i < length; i += 0.4f) {
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E1");
            currentY = yStart + i;
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.2");
            lines.add("G1" + " X" + xStart + " Y" + currentY + " Z" + z + " F600 E1");
            i += 0.4f;
            currentY = yStart + i;
            lines.add("G1" + " X" + xStart + " Y" + currentY + " Z" + z + " F600 E0.2");
        }

        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void moveAndExtrudeActuator(float xStart, float yStart, float length, float width, float z)
            throws IOException {
        float currentY = yStart + width;
        float currentX = xStart;
        float i;

        for (i = 0.4f; i < length; i += 0.4f) {
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E0.2");
            currentX = xStart + i;
            lines.add("G1" + " X" + currentX + " Y" + currentY + " Z" + z + " F600 E1");
            lines.add("G1" + " X" + currentX + " Y" + yStart + " Z" + z + " F600 E0.2");
            i += 0.4f;
            currentX = xStart + i;
            lines.add("G1" + " X" + currentX + " Y" + yStart + " Z" + z + " F600 E1");
        }

        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    public void move(float x, float y) throws IOException {
        lines.add("G0" + " X" + x + " Y" + y + " F300");

        Files.write(file, lines, StandardCharsets.UTF_8);
    }
}