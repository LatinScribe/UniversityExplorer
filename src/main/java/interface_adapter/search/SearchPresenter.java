// Author: André

package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import use_case.search.SearchOutputBoundary;

//public class SearchPresenter implements SearchOutputBoundary {
//
//    private final SearchViewModel searchViewModel;
//    private ViewManagerModel viewManagerModel;
//
//    public SignupPresenter(ViewManagerModel viewManagerModel,
//                           SearchViewModel searchViewModel) {
//        this.viewManagerModel = viewManagerModel;
//        this.searchViewModel = searchViewModel;
//    }
//
//    @Override
//    // Presenter
//    public void prepareSuccessView(SearchOutputData response) {
//        // On success, switch to the login view.
//
//        LoginState loginState = loginViewModel.getState();
//        loginState.setUsername(response.getUsername());
//        this.loginViewModel.setState(loginState);
//        loginViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView(loginViewModel.getViewName());
//        viewManagerModel.firePropertyChanged();
//    }
//
//    @Override
//    public void prepareFailView(String error) {
//        signupState = signupViewModel.getState();
//        signupState.setUsernameError(error);
//        signupViewModel.firePropertyChanged();
//    }