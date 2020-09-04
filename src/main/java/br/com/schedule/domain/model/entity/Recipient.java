package br.com.schedule.domain.model.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(builderMethodName = "newBuilder", access = AccessLevel.PUBLIC)
@Getter
@Setter
@Entity
@Table(name = "recipient", uniqueConstraints = {@UniqueConstraint(columnNames = {"recipient"})})
@AllArgsConstructor
@NoArgsConstructor
public class Recipient extends BaseEntity {

  private static final long serialVersionUID = -2674827418854848988L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(nullable = false)
  private String recipient;

  @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
  private List<Schedule> schedules;

}
