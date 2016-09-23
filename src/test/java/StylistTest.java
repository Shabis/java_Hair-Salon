import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class StylistTest {

  @Before
  public void setUp() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteClientsQuery = "DELETE FROM clients *;";
      String deleteStylistsQuery = "DELETE FROM stylists *;";
      con.createQuery(deleteClientsQuery).executeUpdate();
      con.createQuery(deleteStylistsQuery).executeUpdate();
    }
  }

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Stylist Charlotte", "M-F");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void Stylist_instantiatesWithName_String() {
    Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
    assertEquals("Stylist Charlotte", myStylist.getName());
  }

  @Test
  public void Stylist_instantiatesWithSchedule_String() {
    Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
    assertEquals("M-F", myStylist.getSchedule());
  }

  @Test
   public void equals_returnsTrueIfNamesAretheSame() {
     Stylist firstStylist = new Stylist("Stylist Charlotte", "M-F");
     Stylist secondStylist = new Stylist("Stylist Charlotte", "M-F");
     assertTrue(firstStylist.equals(secondStylist));
   }

   @Test
    public void save_savesIntoDatabase_true() {
      Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
    }

    @Test
    public void all_returnsAllInstancesOfStylist_true() {
      Stylist firstStylist = new Stylist("Stylist Charlotte", "M-F");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Stylist Chris", "F-Sun");
      secondStylist.save();
      assertEquals(true, Stylist.all().get(0).equals(firstStylist));
      assertEquals(true, Stylist.all().get(1).equals(secondStylist));
    }

    @Test
    public void save_assignsIdToObject() {
      Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
      myStylist.save();
      Stylist savedStylist = Stylist.all().get(0);
      assertEquals(myStylist.getId(), savedStylist.getId());
    }

    @Test
    public void getId_stylistsInstantiateWithAnId_1() {
      Stylist testStylist = new Stylist("Stylist Charlotte", "M-F");
      testStylist.save();
      assertTrue(testStylist.getId() > 0);
    }

    @Test
    public void find_returnsStylistWithSameId_secondStylist() {
      Stylist firstStylist = new Stylist("Stylist Charlotte", "M-F");
      firstStylist.save();
      Stylist secondStylist = new Stylist("Stylist Charlotte", "M-F");
      secondStylist.save();
      assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
    }

    @Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
      Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
      myStylist.save();
      Client firstClient = new Client("Jane", "Monday 8am", "cut", myStylist.getId());
      firstClient.save();
      Client secondClient = new Client("Jane", "Monday 8am", "cut", myStylist.getId());
      secondClient.save();
      Client[] clients = new Client[] { firstClient, secondClient };
      assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
    }
}
