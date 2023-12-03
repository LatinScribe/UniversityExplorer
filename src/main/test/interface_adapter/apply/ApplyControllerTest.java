package interface_adapter.apply;

import entity.CommonUniversityFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.apply.*;

public class ApplyControllerTest {

    private ApplyUseCaseInteractorStub applyUseCaseInteractorStub;
    private ApplyController applyController;

    @Before
    public void setUp() {
        applyUseCaseInteractorStub = new ApplyUseCaseInteractorStub();
        applyController = new ApplyController(applyUseCaseInteractorStub);
    }

    @Test
    public void whenExecuteProvidedWithData_UseCaseInteractorExecuteCalled() {
        applyController.execute("1200", "28");
        assert (applyUseCaseInteractorStub.executeQueryCalled);
    }

    @Test
    public void whenBackIsCalled_UseCaseInteractorExecuteBackCalled() {
        applyController.executeBack();
        assert (applyUseCaseInteractorStub.executeBackCalled);
    }

    private static class ApplyUseCaseInteractorStub implements ApplyInputBoundary {
        private boolean executeBackCalled = false;
        private boolean executeQueryCalled = false;

        @Override
        public void execute(ApplyInputData applyInputData) {
            executeQueryCalled = true;
        }

        @Override
        public void executeBack() {
            executeBackCalled = true;
        }
    }

}
