package interface_adapter.prefapply;

import interface_adapter.ViewManagerModel;
import interface_adapter.apply.ApplyState;
import interface_adapter.apply.ApplyViewModel;
import interface_adapter.sub_menu.SubViewModel;
import interface_adapter.sub_menu.SubViewState;
import use_case.apply.ApplyOutputData;
import use_case.prefapply.PrefApplyOutputBoundary;
import use_case.prefapply.PrefApplyOutputData;

public class PrefApplyPresenter implements PrefApplyOutputBoundary {
    private PrefApplyViewModel prefapplyViewModel;
    private ViewManagerModel viewManagerModel;
    private SubViewModel mainMenuViewModel;

    public PrefApplyPresenter(PrefApplyViewModel prefapplyViewModel, ViewManagerModel viewManagerModel,SubViewModel mainMenuViewModel){
        this.prefapplyViewModel = prefapplyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }


    @Override
    public void prepareSuccessView(PrefApplyOutputData prefapplyOutputData) {
        PrefApplyState prefapplyState = prefapplyViewModel.getState();
        prefapplyState.setUni(prefapplyOutputData.getUniversity());
        this.prefapplyViewModel.setState(prefapplyState);
        //this.applyViewModel.firePropertyChanged();
        this.prefapplyViewModel.exchangechange();

        viewManagerModel.setActiveView(prefapplyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String error) {
        PrefApplyState prefapplyState = prefapplyViewModel.getState();
        prefapplyState.setUniversityError(error);
        prefapplyViewModel.fireFailChange();


    }
    @Override
    public void prepareBackView() {
        SubViewState mainMenuState = mainMenuViewModel.getState();
        this.mainMenuViewModel.setState(mainMenuState);
        this.mainMenuViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
