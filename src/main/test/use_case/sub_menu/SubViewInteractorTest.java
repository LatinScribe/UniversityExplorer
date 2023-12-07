package use_case.sub_menu;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SubViewInteractorTest {
    private SubViewOutputBoundaryStub subViewPresenterStub;
    private SubViewInteractor subViewInteractor;

    @Before
    public void setUp() {
        subViewPresenterStub = new SubViewOutputBoundaryStub();
        subViewInteractor = new SubViewInteractor(subViewPresenterStub);
    }

    @Test
    public void testExecuteSearchView() {
        // Act
        SubViewInputData subViewInputData = new SubViewInputData("search");
        subViewInteractor.execute(subViewInputData);

        // Assert
        assertTrue(subViewPresenterStub.searchViewCalled);
    }

    @Test
    public void testExecuteApplyView() {
        // Act
        SubViewInputData subViewInputData = new SubViewInputData("recommendation");
        subViewInteractor.execute(subViewInputData);

        // Assert
        assertTrue(subViewPresenterStub.applyViewCalled);
    }

    @Test
    public void testExecuteZipSearchView() {
        // Act
        SubViewInputData subViewInputData = new SubViewInputData("zip_search");
        subViewInteractor.execute(subViewInputData);

        // Assert
        assertTrue(subViewPresenterStub.zipSearchViewCalled);
    }

    @Test
    public void testExecuteMainView() {
        // Act
        SubViewInputData subViewInputData = new SubViewInputData("loggedInViewName");
        subViewInteractor.execute(subViewInputData);

        // Assert
        assertTrue(subViewPresenterStub.mainViewCalled);
    }

    private static class SubViewOutputBoundaryStub implements SubViewOutputBoundary {
        private boolean searchViewCalled;
        private boolean applyViewCalled;
        private boolean zipSearchViewCalled;
        private boolean mainViewCalled;

        @Override
        public void prepareSearchView() { searchViewCalled = true; }

        @Override
        public void prepareApplyView() {
            applyViewCalled = true;
        }

        @Override
        public void prepareZipSearchView() {
            zipSearchViewCalled = true;
        }

        @Override
        public void prepareMainMenuView() {
            mainViewCalled = true;
        }

    }
}
