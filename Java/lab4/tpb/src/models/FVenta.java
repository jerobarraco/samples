package models;

import javax.persistence.*;
import org.hibernate.annotations.ForceDiscriminator;

@Entity
@DiscriminatorValue(value="VENTA")
public class FVenta extends DocumentoComercial{
}
