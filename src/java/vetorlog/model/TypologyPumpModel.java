package vetorlog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import vetorlog.model.prototype.ModelLong;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tipologia_bombas")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"groupSites", "expectedPerformances"})
@ToString(exclude = {"groupSites", "expectedPerformances"})
public class TypologyPumpModel extends ModelLong {
    @Column(name = "descricao")
    private String description;

    @Column(name = "observacoes")
    private String observations;

    @ManyToMany(mappedBy = "typologyPumps")
    private Set<GroupSitesModel> groupSites;

    @OneToMany(mappedBy = "typologyPump")
    private Set<ExpectedPerformanceModel> expectedPerformances = new HashSet<>();
}
