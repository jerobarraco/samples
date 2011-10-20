package models;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="COMPRA")
public class FCompra extends DocumentoComercial{

}
