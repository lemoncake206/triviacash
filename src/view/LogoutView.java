package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adaptors.logout.LogoutController;
import interface_adaptors.logout.LogoutViewModel;
import interface_adaptors.logout.LogoutState;
import interface_adaptors.ViewManagerModel;

public class LogoutView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logout";

    private final LogoutViewModel logoutViewModel;

    private final LogoutController logoutController;

    public final JButton logout;

    public final JButton back;

    public final ViewManagerModel viewManagerModel;

    public LogoutView(LogoutController logoutController, LogoutViewModel logoutViewModel, ViewManagerModel viewManagerModel) {
        this.logoutController = logoutController;
        this.logoutViewModel = logoutViewModel;
        logoutViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel(LogoutViewModel.LOGOUT_BUTTON_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        logout = new JButton(LogoutViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logout);
        back = new JButton("Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(back);

        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(logout)) {
                    int confirmDialogResult = JOptionPane.showConfirmDialog(
                            LogoutView.this,
                            "Are you sure you want to log out?",
                            "Logout",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmDialogResult == JOptionPane.YES_OPTION) {
                        // User pressed "Yes" for logout
                        logoutController.execute();
                    }
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(back)) {
                    viewManagerModel.setActiveView("account");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);

        this.add(buttons);
    }

    public void changeColour(Color colour) {
        this.setBackground(colour);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object obj = evt.getNewValue();
        if (obj instanceof LogoutState) {
            LogoutState state = (LogoutState) obj;
            if (state.isSuccessfulLogout()) {
                JOptionPane.showMessageDialog(this, "Logout successful");
            } else {
                JOptionPane.showMessageDialog(this, "Logout failed");
            }
        }
        // Handle other state types if needed
    }
}
