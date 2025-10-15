package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseData {
  private WebElement card;
  private String title;
  private String startDate;
  private Integer price;

  public String getCourseNameAndStartDate() {
    return String.format("%s, %s", title, startDate);
  }

  public String getCourseNameAndPrice() {
    return String.format("%s, %s", title, price);
  }
}
