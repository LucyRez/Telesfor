package cs.hse.telesfor.questionnaire;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
class Question{
    private final String questionNr;
    private final String negativeAnswer;
    private final String positiveAnswer;
}

@Getter
@EqualsAndHashCode
@ToString
public class Questionnaire {
    private final List<Question> questions = List.of(
            new Question("1", "Самочувствие плохое", "Самочувствие хорошее"),
            new Question("2", "Чувствую себя слабым", "Чувствую себя сильным"),
            new Question("3", "Пассивный", "Активный"),
            new Question("4", "Малоподвижный", "Подвижный"),
            new Question("5", "Грустный", "Веселый"),
            new Question("6", "Плохое настроение", "Хорошее настроение"),
            new Question("7", "Разбитый", "Работоспособный"),
            new Question("8", "Обессиленный", "Полный сил"),
            new Question("9", "Медлительный", "Быстрый"),
            new Question("10", "Бездеятельный", "Деятельный"),
            new Question("11", "Несчастный", "Счастливый"),
            new Question("12", "Мрачный", "Жизнерадостный"),
            new Question("13", "Напряженный", "Расслабленный"),
            new Question("14", "Больной", "Здоровый"),
            new Question("15", "Безучастный", "Увлеченный"),
            new Question("16", "Равнодушный", "Взволнованный"),
            new Question("17", "Унылый", "Восторженный"),
            new Question("18", "Печальный", "Радостный"),
            new Question("19", "Усталый", "Отдохнувший"),
            new Question("20", "Изнуренный", "Свежий"),
            new Question("21", "Сонливый", "Возбужденный"),
            new Question("22", "Желание отдохнуть", "Желание работать"),
            new Question("23", "Озабоченный", "Спокойный"),
            new Question("24", "Пессимистичный", "Оптимистичный"),
            new Question("25", "Утомляемый", "Выносливый"),
            new Question("26", "Вялый", "Бодрый"),
            new Question("27", "Соображать трудно", "Соображать легко"),
            new Question("28", "Рассеянный", "Внимательный"),
            new Question("29", "Разочарованный", "Полный надежд"),
            new Question("30", "Недовольный", "Довольный"));

}
