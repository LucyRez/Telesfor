package cs.hse.telesfor.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PhoneNumberValidator implements Predicate<String> {

    /**
     * Метод валидации телефонного номера
     * @param s Телефонный номер в виде строки
     * @return Ложь – телефонный номер задан некорректно, истина – всё в порядке
     */
    @Override
    public boolean test(String s) {
        //TODO: add validation
        return true;
    }
}
