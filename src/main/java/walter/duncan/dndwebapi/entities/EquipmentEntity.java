package walter.duncan.dndwebapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "equipment")
public final class EquipmentEntity extends GameInformationEntity { }