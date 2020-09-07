package br.com.schedule.domain.model.entity;


import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@Entity
@Table(name = "schedule", uniqueConstraints = {@UniqueConstraint(columnNames = {"recipient_uuid"})})
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseEntity {

  private static final long serialVersionUID = -8364326632135381856L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;

  @Column(name = "send_date", nullable = false)
  private LocalDateTime sendDate;

  @Column(nullable = false)
  private String message;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Status status;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Type type;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "recipient_uuid", referencedColumnName = "uuid",
      foreignKey = @ForeignKey(name = "uuid"), nullable = false)
  private Recipient recipient;

}
