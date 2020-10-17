package com.uptc;

import com.uptc.view.JFrameView;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        JFrameView jFrameView = new JFrameView();
        jFrameView.setVisible(true);
    }
}
