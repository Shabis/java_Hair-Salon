import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;

  public class Client {
    private String name;
    private String appointment;
    private String service;
    private int stylist;
    private int id;

  public Client(String name, String appointment, String service, int stylist) {
    this.name = name;
    this.appointment = appointment;
    this.service = service;
    this.stylist = stylist;
  }

  public String getName() {
    return name;
  }

  public String getAppointment() {
    return appointment;
  }

  public String getService() {
    return service;
  }

  public int getStylist() {
    return stylist;
  }

  public int getId() {
    return id;
  }

  public static List<Client> all() {
    String sql = "SELECT id, name, appointment, service, stylist FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName())
      && this.getId() == newClient.getId()
      && this.getAppointment().equals(newClient.getAppointment())
      && this.getService().equals(newClient.getService())
      && this.getStylist() == newClient.getStylist();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Clients(name, appointment, service, stylist) VALUES(:name, :appointment, :service, :stylist)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("appointment", this.appointment)
        .addParameter("service", this.service)
        .addParameter("stylist", this.stylist)
        .executeUpdate()
        .getKey();
      }
    }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
        return client;
      }
    }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void update(String name, String appointment, String service) {
    try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name, appointment = :appointment, service = :service WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("appointment", appointment)
      .addParameter("service", service)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
