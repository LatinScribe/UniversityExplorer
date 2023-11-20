package interface_adapter.apply;

import interface_adapter.ViewManagerModel;
import use_case.apply.ApplyOutputBoundary;
import use_case.apply.ApplyOutputData;

public class ApplyPresenter implements ApplyOutputBoundary {
    private ApplyViewModel applyViewModel;
    private ViewManagerModel viewManagerModel;

    public ApplyPresenter(ApplyViewModel applyViewModel, ViewManagerModel viewManagerModel){
        this.applyViewModel = applyViewModel;
        this.viewManagerModel = viewManagerModel;
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
}
