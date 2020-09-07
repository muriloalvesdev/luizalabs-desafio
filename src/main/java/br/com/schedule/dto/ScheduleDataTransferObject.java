package br.com.schedule.dto;


import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder(builderMethodName = "newBuilder")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class ScheduleDataTransferObject {

  @NonNull
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonProperty("send_date")
  private LocalDateTime sendDate;

  @NonNull
  private RecipientDataTransferObject recipient;

  @NonNull
  private String message;

  @NonNull
  @Length(max = 8, min = 3)
  private String type;

  @JsonIgnore
  private String status;
}
