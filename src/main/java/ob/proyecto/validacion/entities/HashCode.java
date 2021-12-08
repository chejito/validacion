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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hashcode_id", referencedColumnName = "id")
    private User user;
}
