package src.use_case.select_mode;

import src.data_access.InMemorySelectModeAccessObject;
import src.use_case.select_mode.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SelectModeInteractorTest {

    @org.junit.Test
    public void successTest(){
        SelectModeInputData inputData = new SelectModeInputData("Animals", "easy", 2);
        SelectModeDataObjectInterface selectModeDatabase = new InMemorySelectModeAccessObject();

        SelectModeOutputBoundary successPresenter = new SelectModeOutputBoundary() {
            @Override
            public void prepareSelectModeSuccessView(SelectModeOutputData selectModeOutputData) {
                ArrayList<String> actualQuestions = selectModeOutputData.getAllQuestions();
                ArrayList<String> expectedQuestions = new ArrayList<>(List.of(new String[]{"Dog or Cat?", "Dog or Cat?"}));

                assertEquals(expectedQuestions, actualQuestions);
                assertEquals(2, actualQuestions.size());
            }

            @Override
            public void prepareSelectModeFailView(String error) {
               fail("Select mode use case failure is unexpected");
                }
            };
        SelectModeInputBoundary selectModeInputInteractor = new SelectModeInteractor(selectModeDatabase, successPresenter);
        selectModeInputInteractor.execute(inputData);
        }
    }