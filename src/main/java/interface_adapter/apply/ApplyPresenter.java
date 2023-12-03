package interface_adapter.apply;

import interface_adapter.ViewManagerModel;
import interface_adapter.main_menu.MainMenuState;
import interface_adapter.main_menu.MainMenuViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.apply.ApplyOutputBoundary;
import use_case.apply.ApplyOutputData;

public class ApplyPresenter implements ApplyOutputBoundary {
    private ApplyViewModel applyViewModel;
    private ViewManagerModel viewManagerModel;
    private SubViewModel subViewModel;

    public ApplyPresenter(ApplyViewModel applyViewModel, ViewManagerModel viewManagerModel,SubViewModel mainMenuViewModel){
        this.applyViewModel = applyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.subViewModel = mainMenuViewModel;
    }


    @Override
    public void prepareSuccessView(ApplyOutputData applyOutputData) {
        ApplyState applyState = applyViewModel.getState();
        applyState.setUni(applyOutputData.getUniversity());
        this.applyViewModel.setState(applyState);
        //this.applyViewModel.firePropertyChanged();
        this.applyViewModel.exchangechange();

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
        SubViewState mainMenuState = subViewModel.getState();
        this.subViewModel.setState(mainMenuState);
        this.subViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(subViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
