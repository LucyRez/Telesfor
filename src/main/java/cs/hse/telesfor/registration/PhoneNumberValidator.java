package cs.hse.telesfor.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PhoneNumberValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return true;
    }
}
