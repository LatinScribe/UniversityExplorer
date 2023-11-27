package interface_adapter.apply;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import use_case.apply.ApplyOutputBoundary;
import use_case.apply.ApplyOutputData;

public class ApplyPresenter implements ApplyOutputBoundary {
    private ApplyViewModel applyViewModel;
    private ViewManagerModel viewManagerModel;
    private MainMenuViewModel mainMenuViewModel;

    public ApplyPresenter(ApplyViewModel applyViewModel, ViewManagerModel viewManagerModel,MainMenuViewModel mainMenuViewModel){
        this.applyViewModel = applyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }


    @Override
    public void prepareSuccessView(ApplyOutputData applyOutputData) {
        ApplyState applyState = applyViewModel.getState();
        applyState.setUni(applyOutputData.getUniversity());
        this.applyViewModel.setState(applyState);
        this.applyViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(applyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        ApplyState applyState = applyViewModel.getState();
        applyState.setUniversityError(error);
        applyViewModel.fireFailChange();


    }
    @Override
    public void prepareBackView() {
        MainMenuState mainMenuState = mainMenuViewModel.getState();
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
