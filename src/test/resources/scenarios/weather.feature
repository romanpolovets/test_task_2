Feature: Проверка получения информации о погоде

  Scenario Outline: Отправляем запрос за текущей погодой (метод /current.json) по четырем городам
    Given Установили базовый URL "http://api.weatherapi.com/v1/"
    When Запросили текущую погоду для города "<city>"
    Then Проверили температуру в градусах Цельсия <float>
    Examples:
      | city   | float |
      | London | 12.0  |
      | Paris  | 2.0   |
      | Minsk  | 4.0   |
      | Madrid | 9.0   |

  Scenario Outline: Получаем варианты ошибок
    Given Установили базовый URL "http://api.weatherapi.com/v1/"
    When Сделали запрос с невалидным путем "<path>"
    Then Проверили получение ошибки <statusCode>
    Examples:
      | path   | statusCode |
      | &q=L   | 400        |
      | abc    | 403        |
      | &a     | 400        |
      | Madrid | 403        |








