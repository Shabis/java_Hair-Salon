import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.sql2o.*;


public class Stylist {
  private int id;
  private String name;
  private String schedule;

  public Stylist(String name, String schedule) {
    this.name = name;
    this.schedule = schedule;
    }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSchedule() {
    return schedule;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name, schedule FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) && this.getId() == newStylist.getId();
    }
  }

  public void save() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO stylists (name, schedule) VALUES (:name, :schedule)";
    this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("schedule", this.schedule)
      .executeUpdate()
      .getKey();
  }
}

  public static Stylist find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM stylists where id=:id";
        Stylist stylist = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Stylist.class);
        return stylist;
      }
    }

  public List<Client> getClients() {
  try(Connection con = DB.sql2o.open()) {
    String sql = "SELECT * FROM clients where stylist=:id";
    return con.createQuery(sql)
      .addParameter("id", this.id)
      .executeAndFetch(Client.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM stylists WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }


}
