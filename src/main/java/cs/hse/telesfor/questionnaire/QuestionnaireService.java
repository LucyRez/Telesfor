package cs.hse.telesfor.questionnaire;

import cs.hse.telesfor.medicaments.PatientMedicamentResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionnaireService {

    private final AnswerRepository answerRepository;

    private int countPoints(List<Integer> answers){

        int sumPoints = 0;

        for (Integer answer : answers) {
            switch (answer) {
                case -3 -> sumPoints += 1;
                case -2 -> sumPoints += 2;
                case -1 -> sumPoints += 3;
                case 0 -> sumPoints += 4;
                case 1 -> sumPoints += 5;
                case 2 -> sumPoints += 6;
                case 3 -> sumPoints += 7;
            }
        }
        return sumPoints;
    }

    // TODO: Check list
    public String saveAnswers(AnswerRequest request){
        List<Integer> answers = request.getAnswers()
                .stream()
                .map(answer -> Integer.valueOf(answer.getValue())).collect(Collectors.toList());

        List<String> wellBeingAnswers = List.of( "1", "2", "7", "8", "13", "14", "19", "20", "25", "26");
        List<Integer> answersWellBeing = request.getAnswers()
                .stream()
                .filter(answer -> wellBeingAnswers.contains(answer.getQuestionNr()))
                .map(answer -> Integer.valueOf(answer.getValue())).collect(Collectors.toList());

        List<String> moodAnswers = List.of( "5", "6", "11", "12", "17", "18", "23", "24", "29", "30");
        List<Integer> answersMood = request.getAnswers()
                .stream()
                .filter(answer -> moodAnswers.contains(answer.getQuestionNr()))
                .map(answer -> Integer.valueOf(answer.getValue())).collect(Collectors.toList());

        List<String> activityAnswers = List.of("3", "4", "9", "10", "15", "16", "21", "22", "27", "28");
        List<Integer> answersActivity = request.getAnswers()
                .stream()
                .filter(answer -> activityAnswers.contains(answer.getQuestionNr()))
                .map(answer -> Integer.valueOf(answer.getValue())).collect(Collectors.toList());


        PatientAnswer patientAnswer = new PatientAnswer(request.getPatientId(), answers,
                countPoints(answersWellBeing), countPoints(answersMood), countPoints(answersActivity), LocalDate.now());

        answerRepository.save(patientAnswer);
        return String.format("Answers for patient %s have been saved", request.getPatientId());
    }

    public List<PatientAnswerResponse> getPatientAnswers(String patientId){
        List<PatientAnswer> patientAnswers = answerRepository.getPatientAnswersByPatientId(patientId);

        List<PatientAnswerResponse> response = new ArrayList<>();
        for (PatientAnswer patientAnswer:patientAnswers) {
           List<Integer> answerValues = patientAnswer.getAnswerList();
           List<Answer> answers = new ArrayList<>();
            for (int i = 0; i < answerValues.size(); i++) {
                answers.add(new Answer(String.valueOf(i+1), String.valueOf(answerValues.get(i))));
            }
            response.add(new PatientAnswerResponse(patientAnswer.getPatientId(), answers,
                    patientAnswer.getWellBeing().toString(), patientAnswer.getMood().toString(),
                    patientAnswer.getActivity().toString(), patientAnswer.getDate().toString()));
        }

        return response;
    }


}
