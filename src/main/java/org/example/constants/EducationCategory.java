package org.example.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Random;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum EducationCategory {
  PROGRAMMING("Программирование"),
  ARCHITECTURE("Архитектура"),
  DATA_SCIENCE("Data Science"),
  INFRASTRUCTURE("Инфраструктура"),
  GAMEDEV("GameDev"),
  SECURITY("Безопасность"),
  MANAGEMENT("Управление"),
  ANALYSIS("Аналитика и анализ"),
  BUSINESS("Бизнес и продукт в IT"),
  TESTING("Тестирование"),
  IT_WITHOUT_PROGRAMMING("IT без программирования"),
  IMPORT_SUBSTITUTION("Импортозамещение"),
  CORPORATE("Корпоративные курсы");

  private final String category;

  private static final Random RANDOM = new Random();

  static Stream<String> randomEducationCategoryProvider() {
    EducationCategory[] values = EducationCategory.values();
    return Stream.of(values[RANDOM.nextInt(values.length)].getCategory());
  }
}