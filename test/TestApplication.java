import com.cifor.practice.database.TestConnectionAndTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cifor.practice.Application;
import java.sql.SQLException;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestApplication {


    @Autowired
    TestConnectionAndTransaction testDatabase;

    @Test
    public void testDatabase() throws SQLException, ClassNotFoundException {
        testDatabase.test();
    }

}
