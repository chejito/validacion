package ob.proyecto.validacion.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entidad que gestiona los HashCode en la base de datos.
 */
@Entity
@Table(name ="hashcodes")
public class HashCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer hash;

    @Column
    private Timestamp timeStamp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public HashCode() {}

    public HashCode(Integer hash, Timestamp timeStamp, User user) {
        this.hash = hash;
        this.timeStamp = timeStamp;
        this.user = user;
    }

    public HashCode(Long id, Integer hash, Timestamp timeStamp, User user) {
        this.id = id;
        this.hash = hash;
        this.timeStamp = timeStamp;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "HashCode{" +
                "id=" + id +
                ", hash=" + hash +
                ", timeStamp=" + timeStamp +
                ", user=" + user +
                '}';
    }
}
