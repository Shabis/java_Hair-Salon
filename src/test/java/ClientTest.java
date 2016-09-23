import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ClientTest {

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
    public void Client_instantiatesCorrectly_true() {
      Client testClient = new Client("Jane", "Monday 8am", "cut", 1);
      assertEquals(true, testClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithName_String() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      assertEquals("Jane", myClient.getName());
    }

    @Test
    public void Client_instantiatesWithAppointment_String() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      assertEquals("Monday 8am", myClient.getAppointment());
    }

    @Test
    public void Client_instantiatesWithService_String() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      assertEquals("cut", myClient.getService());
    }

    @Test
    public void Client_instantiatesWithStylist_int() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      assertEquals(1, myClient.getStylist());
    }

    @Test
    public void Client_instantiatesWithId() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      myClient.save();
      assertTrue(myClient.getId() > 0);
    }

    @Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
      Client oneClient = new Client("Jane", "Monday 8am", "cut", 1);
      oneClient.save();
      Client twoClient = new Client("Jane", "Monday 8am", "cut", 1);
      twoClient.save();
      assertEquals(true, Client.all().get(0).equals(oneClient));
      assertEquals(true, Client.all().get(1).equals(twoClient));
    }

    @Test
    public void save_returnsTrueIfDescriptionsAretheSame() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      myClient.save();
      assertTrue(Client.all().get(0).equals(myClient));
    }

    @Test
    public void save_assignsIdToObject() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      myClient.save();
      Client savedClient = Client.all().get(0);
      assertEquals(myClient.getId(), savedClient.getId());
    }

    @Test
    public void find_returnsClientWithSameId_secondClient() {
      Client firstClient = new Client("Jane", "Monday 8am", "cut", 1);
      firstClient.save();
      Client secondClient = new Client("Jane", "Monday 8am", "cut", 1);
      secondClient.save();
      assertEquals(Client.find(secondClient.getId()), secondClient);
    }

    @Test
    public void save_savesStylistIdIntoDB_true() {
      Stylist myStylist = new Stylist("Stylist Charlotte", "M-F");
      myStylist.save();
      Client myClient = new Client("Jane", "Monday 8am", "cut", myStylist.getId());
      myClient.save();
      Client savedClient = Client.find(myClient.getId());
      assertEquals(savedClient.getStylist(), myStylist.getId());
    }

    @Test
    public void update_updatesClientDescription_true() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      myClient.save();
      myClient.update("Jim", "Monday 8am", "cut");
      assertEquals("Jim", Client.find(myClient.getId()).getName());
    }

    @Test
    public void delete_deletesClient_true() {
      Client myClient = new Client("Jane", "Monday 8am", "cut", 1);
      myClient.save();
      int myClientId = myClient.getId();
      myClient.delete();
      assertEquals(null, Client.find(myClientId));
    }
}
