package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Superhero {
  private String birthDate = "2000-11-08";
  private String city = RandomStringUtils.randomAlphanumeric(10);
  private String fullName = RandomStringUtils.randomAlphanumeric(5);
  private String gender = "F";
  private Integer id;
  private String mainSkill = RandomStringUtils.randomAlphanumeric(15);
  private String phone = RandomStringUtils.randomNumeric(10);
}
