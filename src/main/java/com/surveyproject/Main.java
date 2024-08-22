package com.surveyproject;

import javax.swing.SwingUtilities;

import com.surveyproject.mainUi.login.LoginUI;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new LoginUI());
    }


}