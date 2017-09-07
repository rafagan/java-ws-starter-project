package vetorlog.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import vetorlog.model.prototype.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HelloWorld")
@Data
@EqualsAndHashCode(callSuper = true)
public class HelloWorld extends Model {
    @Column(name = "value_string")
    private String valueString;

    @Column(name = "value_double")
    private double valueDouble;
}
