package interface_adaptors.logout;


import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;


public class LogoutPresenter implements LogoutOutputBoundary {
    private final LogoutViewModel logoutViewModel;

    public LogoutPresenter(LogoutViewModel logoutViewModel) {
        this.logoutViewModel = logoutViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        LogoutState logoutState = logoutViewModel.getState();


        logoutState.setUsername(response.getUsername());
        this.logoutViewModel.setState(logoutState);
        logoutViewModel.firePropertyChanged();
    }


}
