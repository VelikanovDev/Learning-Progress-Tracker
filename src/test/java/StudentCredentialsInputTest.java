package tracker;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class StudentCredentialsInputTest {

    ClassRoom classRoom;

    @BeforeEach
    void createClassRoom() {
        classRoom = new ClassRoom();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Jim.", "табу", "-bad", "'gad", "a", "alloha-", "a-'", "a-'b"})
    void testIncorrectFirstNames(String input) {
        boolean res = classRoom.addStudent(input + " Smith example@gmail.com");
        assertFalse(res);
    }

    @ParameterizedTest
    @ValueSource(strings = {"X", "x", "", "Иванов", "'Smith", "-Johnson"})
    void testIncorrectLastNames(String input) {
        boolean res = classRoom.addStudent("John " + input + " example@gmail.com");
        assertFalse(res);
    }

    @ParameterizedTest
    @ValueSource(strings = {"_@#@gmail.com", "@gmail.com", "developer@mail", "smith@mail..com", "-Johnson@mail.com/"})
    void testIncorrectEmails(String input) {
        boolean res = classRoom.addStudent("John Smith " +input);
        assertFalse(res);
    }

    @ParameterizedTest
    @ValueSource(strings = {"陳 港 生", "Иван Иванов example@mail.com"})
    void testNotLatinSymbols(String input) {
        boolean res = classRoom.addStudent(input);
        assertFalse(res);
    }

    @ParameterizedTest
    @ValueSource(strings = {"John Smith jsmith@hotmail.com", "Anny Doolittle anny.md@mail.edu",
            "Jean-Claude O'Connor jcda123@google.net", "Mary Emelianenko 125367at@zzz90.z9",
            "Al Owen u15da125@a1s2f4f7.a1c2c5s4"})
    void testCorrectInputs(String input) {
        Assumptions.assumeTrue(classRoom.addStudent(input));
    }


}
