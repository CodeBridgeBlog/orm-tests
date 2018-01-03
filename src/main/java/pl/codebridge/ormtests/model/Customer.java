package pl.codebridge.ormtests.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static pl.codebridge.ormtests.model.TableNames.CUSTOMER;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = CUSTOMER)
public class Customer {

    @Id
    @GeneratedValue
    private UUID id;

    private String email;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "details_id")
    private CustomerDetails details;

}
