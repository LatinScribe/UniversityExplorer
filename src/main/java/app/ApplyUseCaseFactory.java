package app;

import entity.UniversityFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyController;
import interface_adapter.apply.ApplyPresenter;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.sub_menu.SubViewModel;
import use_case.apply.ApplyDataAccessInterface;
import use_case.apply.ApplyInputBoundary;
import use_case.apply.ApplyInteractor;
import use_case.apply.ApplyOutputBoundary;
import view.Applyview;

import javax.swing.*;
import java.io.IOException;

public class ApplyUseCaseFactory {
    private ApplyUseCaseFactory() {
    }

    public static Applyview create(
            ViewManagerModel viewManagerModel, ApplyViewModel applyViewModel, ApplyDataAccessInterface applyUserDataAccessObject, UniversityFactory shortUniversityFactory, SubViewModel mainMenuViewModel) {

        try {
            ApplyController applyController = createApplyUseCase(viewManagerModel, applyViewModel, applyUserDataAccessObject, shortUniversityFactory, mainMenuViewModel);
            return new Applyview(applyController, applyViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "API unable to be accessed.");
        }

        return null;
    }

    private static ApplyController createApplyUseCase(ViewManagerModel viewManagerModel, ApplyViewModel applyViewModel, ApplyDataAccessInterface applyDataAccessObject, UniversityFactory shortUniversityFactory, SubViewModel mainMenuViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ApplyOutputBoundary applyOutputBoundary = new ApplyPresenter(applyViewModel, viewManagerModel, mainMenuViewModel);

        ApplyInputBoundary applyInteractor = new ApplyInteractor(
                applyDataAccessObject, applyOutputBoundary, shortUniversityFactory);

        return new ApplyController(applyInteractor);
    }

}
