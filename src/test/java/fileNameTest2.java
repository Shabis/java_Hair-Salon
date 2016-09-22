import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class ____Test {

  @Before
    public void setUp() {
      DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/______", null, null);
    }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String delete___Query = "DELETE FROM _________ *;";
      String delete___Query = "DELETE FROM _________ *;";
      con.createQuery(delete___Query).executeUpdate();
      con.createQuery(delete___Query).executeUpdate();
    }
  }
