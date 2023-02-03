package personal.model;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserMapper {
    public String map(User user) {
        return String.format("%s;%s;%s;%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone() + System.lineSeparator());
    }

    public User map(String line) {
        String[] lines = line.split(";");
        if (lines.length < 4) {
            return null;
        }
        return new User(lines[0], lines[1], lines[2], lines[3]);
    }
}